package ru.acediat.finances.domain.usecases

import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import org.threeten.bp.LocalDateTime
import ru.acediat.finances.domain.repositories.OperationsRepository
import ru.acediat.finances.ui.entities.operations.Operation
import java.lang.Exception
import java.math.BigDecimal
import javax.inject.Inject

interface SaveOperationUseCase {

    suspend fun addOperation(
        value: Double,
        date: LocalDateTime,
        hiddenFromAnalytics: Boolean,
        cashAccountId: Long,
    ): Operation?
}

class SaveOperationUseCaseImpl @Inject constructor(
    private val operationsRepository: OperationsRepository,
) : SaveOperationUseCase {

    override suspend fun addOperation(
        value: Double,
        date: LocalDateTime,
        hiddenFromAnalytics: Boolean,
        cashAccountId: Long,
    ): Operation? = coroutineScope {
        val operation = Operation(
            value = BigDecimal.valueOf(value),
            date = date,
            hiddenFromAnalytics = false,
            cashAccountId = cashAccountId,
        )
        try {
            async { operationsRepository.saveOperation(operation) }.await()
        } catch (e: Exception) {
            return@coroutineScope null
        }
        return@coroutineScope operation
    }

}