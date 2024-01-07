package ru.acediat.finances.model.db.cashaccounts

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction

@Dao
interface CashAccountsDao {

    @Query("select * from cash_accounts")
    suspend fun getCashAccounts() : List<CashAccountsTable>

    @Transaction
    @Query("select * from cash_accounts")
    suspend fun getCashAccountsWithOperations(): List<CashAccountWithOperationsTable>

    @Insert
    suspend fun insertCashAccount(cashAccount: CashAccountsTable)
}