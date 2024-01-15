package ru.acediat.finances.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.faltenreich.skeletonlayout.Skeleton
import com.faltenreich.skeletonlayout.applySkeleton
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import kotlinx.coroutines.launch
import ru.acediat.finances.R
import ru.acediat.finances.core.StateFragment
import ru.acediat.finances.core.applicationContext
import ru.acediat.finances.databinding.FragmentMainBinding
import ru.acediat.finances.model.AssetsFolder
import ru.acediat.finances.ui.entities.operations.Operation
import javax.inject.Inject

class SummaryFragment : StateFragment<SummaryState>() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var assetsFolder: AssetsFolder

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private var operationsListSkeleton: Skeleton? = null

    private lateinit var viewModel: MainViewModel

    private val operationsAdapter = ListDelegationAdapter(
        dividerDelegateAdapter(),
        operationDelegateAdapter(),
    )

    private var accountsAdapter: CashAccountAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        applicationContext.appComponent.inject(this)
        viewModel = ViewModelProvider(requireActivity(), viewModelFactory)[MainViewModel::class.java]
        Log.d("tag", viewModel.toString())
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        accountsAdapter = CashAccountAdapter(0, assetsFolder) { _, position ->
            viewModel.selectedCashAccount = position
        }
        binding.addButton.setOnClickListener {
            AddOperationFragment().show(childFragmentManager, "addOperationFragment")
        }
        binding.accountsList.adapter = accountsAdapter
        binding.operationsList.adapter = operationsAdapter
        lifecycleScope.launch { viewModel.state.collect (::onStateChanged) }
        lifecycleScope.launch { viewModel.effect.collect(::onEffect) }
        viewModel.getOperations()
    }

    override fun onStateChanged(newState: SummaryState) = when(newState) {
        is SummaryState.Loading -> showLoadingState()
        is SummaryState.Loaded.EmptyOperations -> showEmptyState(newState)
        is SummaryState.Loaded.ShowingCashAccount -> showOperations(newState)
        else -> {}
    }

    private fun onEffect(effect: SummaryEffect) = when(effect) {
        is SummaryEffect.OperationSaved -> onOperationSaved(effect.operation)
        is SummaryEffect.SaveError -> {}
    }

    private fun showLoadingState() {
        setEmptyVisibility(false)
        operationsListSkeleton = binding.operationsList.applySkeleton(R.layout.item_operation, 10)
        operationsListSkeleton?.showSkeleton()
    }

    private fun showEmptyState(state: SummaryState.Loaded.EmptyOperations) {
        setEmptyVisibility(true)
        operationsListSkeleton?.showOriginal()
        accountsAdapter?.setItems(state.cashAccounts)
    }

    private fun showOperations(state: SummaryState.Loaded.ShowingCashAccount) {
        setEmptyVisibility(false)
        operationsListSkeleton?.showOriginal()
        operationsAdapter.apply {
            items = state.operations
            notifyDataSetChanged()
        }
        accountsAdapter?.setItems(state.cashAccounts)
    }

    private fun onOperationSaved(operation: Operation) {
        viewModel.getOperations()
    }

    private fun setEmptyVisibility(visibility: Boolean) {
        binding.emptyView.root.isVisible = visibility
        binding.operationsList.isVisible = !visibility
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}