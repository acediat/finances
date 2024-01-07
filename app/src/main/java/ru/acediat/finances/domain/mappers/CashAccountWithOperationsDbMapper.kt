package ru.acediat.finances.domain.mappers

import ru.acediat.finances.model.db.cashaccounts.CashAccountWithOperationsTable
import ru.acediat.finances.model.db.cashaccounts.CashAccountsTable
import ru.acediat.finances.model.db.operations.OperationsTable
import ru.acediat.finances.ui.entities.CashAccount
import ru.acediat.finances.ui.entities.CashAccountWithOperations
import ru.acediat.finances.ui.entities.operations.Operation
import java.math.BigDecimal
import javax.inject.Inject

class CashAccountWithOperationsDbMapper @Inject constructor(
    private val cashAccountMapper: Mapper<Pair<CashAccountsTable, BigDecimal>, CashAccount>,
    private val operationMapper: Mapper<OperationsTable, Operation>,
) : Mapper<CashAccountWithOperationsTable, CashAccountWithOperations> {

    override fun map(value: CashAccountWithOperationsTable) = CashAccountWithOperations(
        cashAccountMapper.map(
            Pair(
                value.cashAccount,
                BigDecimal(value.cashAccount.startValue) + BigDecimal.ZERO.apply {
                    value.operations.forEach {
                        plus(BigDecimal(it.value))
                    }
                }
            )
        ),
        value.operations.map(operationMapper::map),
    )

    override fun reverseMap(value: CashAccountWithOperations) = CashAccountWithOperationsTable(
        cashAccountMapper.reverseMap(value.account).first,
        value.operations.map(operationMapper::reverseMap),
    )
}