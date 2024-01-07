package ru.acediat.finances.model.db.cashaccounts

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cash_accounts")
data class CashAccountsTable(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    @ColumnInfo(name = "icon_file_name")
    val iconFileName: String,
    @ColumnInfo(name = "start_value")
    val startValue: String,
)