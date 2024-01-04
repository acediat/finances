package ru.acediat.finances.domain.usecases

import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import ru.acediat.finances.core.ViewEffect
import ru.acediat.finances.core.ViewState
import ru.acediat.finances.domain.repositories.OperationsRepository
import ru.acediat.finances.entities.operations.Operation
import ru.acediat.finances.entities.operations.OperationsDivider
import ru.acediat.finances.entities.operations.OperationsListEntity
import java.lang.Exception
import java.math.BigDecimal
import javax.inject.Inject

sealed class OperationsState: ViewState {
    data object Loading: OperationsState()
    data object EmptyOperations: OperationsState()
    data class SortedOperationsReceived(
        val operations: List<OperationsListEntity>,
        val summary: BigDecimal
    ): OperationsState()
}

sealed class OperationsEffect: ViewEffect {
    data object SaveError: OperationsEffect()
    data class OperationSaved(
        val operation: Operation
    ): OperationsEffect()
}

interface OperationUseCase {
    suspend fun getOperations(): OperationsState
    suspend fun saveOperation(operation: Operation): OperationsEffect
}

class OperationUseCaseImpl @Inject constructor(
    private val operationsRepository: OperationsRepository
): OperationUseCase {

    override suspend fun getOperations(): OperationsState {
        val operations = operationsRepository.getOperations()
        if (operations.isEmpty()) {
            return OperationsState.EmptyOperations
        }
        val summary = BigDecimal.ZERO
        operations.forEach { summary.plus(it.value) }
        return OperationsState.SortedOperationsReceived(sortOperations(operations), summary)
    }

    override suspend fun saveOperation(operation: Operation): OperationsEffect = coroutineScope {
        try {
            async { operationsRepository.saveOperation(operation) }.await()
        } catch (e: Exception) {
            return@coroutineScope OperationsEffect.SaveError
        }
        return@coroutineScope OperationsEffect.OperationSaved(operation)
    }

    private fun sortOperations(operations: List<Operation>): List<OperationsListEntity> {
        val groupedByDate = operations.groupBy { it.date }
        val result = mutableListOf<OperationsListEntity>()
        groupedByDate.entries.forEach {
            it.value.forEach(result::add)
            result.add(OperationsDivider(it.key))
        }
        return result
    }
}