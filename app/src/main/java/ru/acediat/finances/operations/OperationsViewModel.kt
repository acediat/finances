package ru.acediat.finances.operations

import kotlinx.coroutines.flow.MutableStateFlow
import ru.acediat.finances.core.StateViewModel
import ru.acediat.finances.core.ViewState
import ru.acediat.finances.model.Operation

sealed class OperationsState: ViewState {
    object Loading: OperationsState()
    data class OperationsReceived(val operations: List<Operation>): OperationsState()
}

class OperationsViewModel: StateViewModel<OperationsState>() {

    override val _state: MutableStateFlow<OperationsState> = MutableStateFlow(OperationsState.Loading)


}