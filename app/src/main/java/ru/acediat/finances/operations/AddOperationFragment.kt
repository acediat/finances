package ru.acediat.finances.operations

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import ru.acediat.finances.core.applicationContext
import ru.acediat.finances.databinding.FragmentAddOperationBinding
import javax.inject.Inject

class AddOperationFragment : BottomSheetDialogFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private var _binding: FragmentAddOperationBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: OperationsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddOperationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        applicationContext.appComponent.inject(this)
        viewModel = ViewModelProvider(requireActivity(), viewModelFactory)[OperationsViewModel::class.java]
    }
}