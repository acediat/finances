package ru.acediat.finances.model.db.operations

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import ru.acediat.finances.model.db.cashaccounts.CashAccountsTable

@Entity(
    tableName = "operations",
    foreignKeys = [
        ForeignKey(
            entity = CashAccountsTable::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("parent_cash_account_id"),
            onUpdate = ForeignKey.CASCADE,
            onDelete = ForeignKey.CASCADE,
        )
    ])
data class OperationsTable(
    @ColumnInfo("operation_id")
    @PrimaryKey(autoGenerate = true)
    val operationId: Long = 0,
    val value: String,
    val date: String,
    @ColumnInfo("hidden_from_analytics")
    val hiddenFromAnalytics: Boolean,
    @ColumnInfo("category_id")
    val categoryId: Long,
    @ColumnInfo("parent_cash_account_id")
    val cashAccountId: Long,
)