package ru.acediat.finances.model.db.cashaccounts

import androidx.room.Embedded
import androidx.room.Relation
import ru.acediat.finances.model.db.operations.OperationsTable

data class CashAccountWithOperationsTable(
    @Embedded
    val cashAccount: CashAccountsTable,
    @Relation(
        parentColumn = "id",
        entityColumn = "parent_cash_account_id",
    )
    val operations: List<OperationsTable>,
)