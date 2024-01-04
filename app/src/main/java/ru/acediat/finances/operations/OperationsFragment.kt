package ru.acediat.finances.operations

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
import ru.acediat.finances.databinding.FragmentOperationsBinding
import ru.acediat.finances.domain.usecases.OperationsState
import ru.acediat.finances.entities.operations.OperationsListEntity
import javax.inject.Inject

class OperationsFragment : StateFragment<OperationsState>() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private var _binding: FragmentOperationsBinding? = null
    private val binding get() = _binding!!
    private var operationsListSkeleton: Skeleton? = null

    private lateinit var viewModel: OperationsViewModel

    private val operationsAdapter = ListDelegationAdapter(
        dividerDelegateAdapter(),
        operationDelegateAdapter(),
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        applicationContext.appComponent.inject(this)
        viewModel = ViewModelProvider(requireActivity(), viewModelFactory)[OperationsViewModel::class.java]
        Log.d("tag", viewModel.toString())
        _binding = FragmentOperationsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.addButton.setOnClickListener {
            AddOperationFragment().show(childFragmentManager, "addOperationFragment")
        }
        lifecycleScope.launch { viewModel.state.collect (::onStateChanged) }
        viewModel.getOperations()
    }

    override fun onStateChanged(newState: OperationsState) = when(newState) {
        is OperationsState.Loading -> showLoadingState()
        is OperationsState.EmptyOperations -> showEmptyState()
        is OperationsState.SortedOperationsReceived -> showOperations(newState.operations)
    }

    private fun showLoadingState() {
        setEmptyVisibility(false)
        operationsListSkeleton = binding.operationsList.applySkeleton(R.layout.item_operation, 10)
        operationsListSkeleton?.showSkeleton()
    }

    private fun showEmptyState() {
        setEmptyVisibility(true)
        operationsListSkeleton?.showOriginal()
    }

    private fun showOperations(operations: List<OperationsListEntity>) {
        setEmptyVisibility(false)
        binding.operationsList.adapter = operationsAdapter.apply { items = operations }
        operationsListSkeleton?.showOriginal()
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