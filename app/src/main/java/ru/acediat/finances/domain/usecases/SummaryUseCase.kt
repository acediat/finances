package ru.acediat.finances.domain.usecases

import android.util.Log
import ru.acediat.finances.domain.repositories.CashAccountRepository
import ru.acediat.finances.domain.repositories.OperationsRepository
import ru.acediat.finances.ui.entities.CashAccount
import ru.acediat.finances.ui.entities.CashAccountWithOperations
import ru.acediat.finances.ui.entities.operations.Operation
import ru.acediat.finances.ui.entities.operations.OperationsDivider
import ru.acediat.finances.ui.entities.operations.OperationsListEntity
import java.math.BigDecimal
import javax.inject.Inject

interface SummaryUseCase {

    suspend fun getCashAccountsWithOperations(): List<CashAccountWithOperations>
    suspend fun getOperationsWithDates(): List<OperationsListEntity>
    fun groupOperationsByDate(operations: List<Operation>): List<OperationsListEntity>
}

class SummaryUseCaseImpl @Inject constructor(
    private val operationsRepository: OperationsRepository,
    private val cashAccountRepository: CashAccountRepository,
): SummaryUseCase {

    override suspend fun getCashAccountsWithOperations(): List<CashAccountWithOperations> {
        val userAccounts = cashAccountRepository.getCashAccountsWithOperations().toMutableList()
        val allAccount = CashAccount(
            summary = BigDecimal.ZERO.apply {
                userAccounts.forEach { plus(it.account.summary) }
            },
            name = "All",
            startValue = BigDecimal.ZERO,
            iconFileName = "billing_card.svg",
        )
        val allOperations = mutableListOf<Operation>().apply {
            userAccounts.forEach { addAll(it.operations) }
        }
        userAccounts.add(CashAccountWithOperations(allAccount, allOperations))
        return userAccounts
    }

    override suspend fun getOperationsWithDates(): List<OperationsListEntity>{
        val operations = operationsRepository.getOperations()
        return groupOperationsByDate(operations)
    }

    override fun groupOperationsByDate(operations: List<Operation>): List<OperationsListEntity> {
        val groupedByDate = operations.groupBy { it.date.toLocalDate() }
        val result = mutableListOf<OperationsListEntity>()
        groupedByDate.entries.forEach {
            it.value.forEach(result::add)
            result.add(OperationsDivider(it.key))
        }
        return result.reversed()
    }
}