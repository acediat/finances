package ru.acediat.finances.domain.repositories

import ru.acediat.finances.domain.mappers.Mapper
import ru.acediat.finances.model.db.cashaccounts.CashAccountWithOperationsTable
import ru.acediat.finances.model.db.cashaccounts.CashAccountsDao
import ru.acediat.finances.model.db.cashaccounts.CashAccountsTable
import ru.acediat.finances.ui.entities.CashAccount
import ru.acediat.finances.ui.entities.CashAccountWithOperations
import java.math.BigDecimal
import javax.inject.Inject

interface CashAccountRepository {
    suspend fun getCashAccountsWithOperations(): List<CashAccountWithOperations>

    suspend fun saveCashAccount(account: CashAccount)
}

class CashAccountsRepositoryImpl @Inject constructor(
    private val cashAccountsDao: CashAccountsDao,
    private val cashAccountWithOperationsMapper: Mapper<CashAccountWithOperationsTable, CashAccountWithOperations>,
    private val cashAccountMapper: Mapper<Pair<CashAccountsTable, BigDecimal>, CashAccount>,
) : CashAccountRepository {
    override suspend fun getCashAccountsWithOperations() = cashAccountsDao
        .getCashAccountsWithOperations()
        .map(cashAccountWithOperationsMapper::map)

    override suspend fun saveCashAccount(account: CashAccount) {
        cashAccountsDao.insertCashAccount(cashAccountMapper.reverseMap(account).first)
    }
}