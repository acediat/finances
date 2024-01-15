package ru.acediat.finances.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.launch
import ru.acediat.finances.core.applicationContext
import ru.acediat.finances.databinding.FragmentAddOperationBinding
import ru.acediat.finances.model.AssetsFolder
import javax.inject.Inject

class AddOperationFragment : BottomSheetDialogFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var assetsFolder: AssetsFolder

    private var _binding: FragmentAddOperationBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: MainViewModel
    private lateinit var accountsAdapter: CashAccountAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddOperationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        applicationContext.appComponent.inject(this@AddOperationFragment)
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity(), viewModelFactory)[MainViewModel::class.java]
        accountsAdapter = CashAccountAdapter(assetsFolder = assetsFolder)
        accountsChooseList.adapter = accountsAdapter
        saveButton.setOnClickListener {
            lifecycleScope.launch {
                viewModel.addNewOperation(
                    valueInput.text.toString().toDouble(),
                    false,
                    accountsAdapter.getSelectedAccount().id
                )
            }
        }
    }
}