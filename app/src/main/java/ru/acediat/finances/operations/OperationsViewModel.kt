package ru.acediat.finances.operations

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.acediat.finances.domain.usecases.OperationUseCase
import ru.acediat.finances.domain.usecases.OperationsEffect
import ru.acediat.finances.domain.usecases.OperationsState
import javax.inject.Inject

class OperationsViewModel @Inject constructor(
    private val operationsUseCase: OperationUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<OperationsState>(OperationsState.Loading)
    val state = _state.asStateFlow()

    private val _effect = MutableSharedFlow<OperationsEffect>()
    val effect = _effect.asSharedFlow()

    fun getOperations() = viewModelScope.launch(Dispatchers.IO) {
        _state.value = operationsUseCase.getOperations()
    }

}