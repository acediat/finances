package ru.acediat.finances.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.threeten.bp.LocalDateTime
import ru.acediat.finances.core.ViewEffect
import ru.acediat.finances.core.ViewState
import ru.acediat.finances.domain.usecases.SummaryUseCase
import ru.acediat.finances.domain.usecases.SaveOperationUseCase
import ru.acediat.finances.ui.entities.CashAccount
import ru.acediat.finances.ui.entities.CashAccountWithOperations
import ru.acediat.finances.ui.entities.operations.Operation
import ru.acediat.finances.ui.entities.operations.OperationsListEntity
import javax.inject.Inject

sealed class SummaryState : ViewState {
    data object Loading : SummaryState()

    data object Error : SummaryState()

    sealed class Loaded(
        val cashAccounts: List<CashAccount>,
        val selectedCashAccount: Int,
    ) : SummaryState() {
        class EmptyOperations(
            cashAccounts: List<CashAccount>,
            selectedCashAccount: Int,
        ) : Loaded(cashAccounts, selectedCashAccount)

        class ShowingCashAccount(
            cashAccounts: List<CashAccount>,
            selectedCashAccount: Int,
            val operations: List<OperationsListEntity>,
        ) : Loaded(cashAccounts, selectedCashAccount)
    }
}

sealed class SummaryEffect : ViewEffect {
    data object SaveError : SummaryEffect()
    data class OperationSaved(
        val operation: Operation
    ) : SummaryEffect()
}

class MainViewModel @Inject constructor(
    private val operationsUseCase: SummaryUseCase,
    private val saveOperationUseCase: SaveOperationUseCase,
) : ViewModel() {

    var selectedCashAccount: Int = 0
        set(value) {
            field = value
            val newState = if (cashAccountWithOperations.isEmpty()) {
                SummaryState.Error
            } else if (cashAccountWithOperations[value].operations.isEmpty()) {
                SummaryState.Loaded.EmptyOperations(cashAccountWithOperations.map { it.account }, value)
            } else {
                createShowingCashAccountState()
            }
            Log.d("tag", "lol_lol")
            cashAccountWithOperations.forEach {
                Log.d("tag", it.account.toString())
            }
            _state.value = newState
        }

    private val _state = MutableStateFlow<SummaryState>(SummaryState.Loading)
    val state = _state.asStateFlow()

    private val _effect = MutableSharedFlow<SummaryEffect>()
    val effect = _effect.asSharedFlow()

    private var cashAccountWithOperations = mutableListOf<CashAccountWithOperations>()

    fun getOperations() {
        _state.value = SummaryState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            val newCashAccountsWithOperations = operationsUseCase.getCashAccountsWithOperations()
            cashAccountWithOperations.clear()
            cashAccountWithOperations.addAll(newCashAccountsWithOperations)
            selectedCashAccount = 0
        }
    }

    fun addNewOperation(
        value: Double,
        hiddenFromAnalytics: Boolean,
        cashAccountId: Long,
        date: LocalDateTime = LocalDateTime.now(),
    ) = viewModelScope.launch(Dispatchers.IO) {
        val result = saveOperationUseCase.addOperation(value, date, hiddenFromAnalytics, cashAccountId)
        result?.let {
            _effect.emit(SummaryEffect.OperationSaved(it))
        } ?: run {
            _effect.emit(SummaryEffect.SaveError)
        }
    }

    private fun createShowingCashAccountState() = SummaryState.Loaded.ShowingCashAccount(
        cashAccountWithOperations.map { it.account },
        selectedCashAccount,
        operationsUseCase.groupOperationsByDate(
            cashAccountWithOperations[selectedCashAccount].operations
        )
    )

}