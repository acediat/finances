package ru.acediat.finances.model.db

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.acediat.finances.model.db.cashaccounts.CashAccountsDao
import ru.acediat.finances.model.db.cashaccounts.CashAccountsTable
import ru.acediat.finances.model.db.operations.OperationsDao
import ru.acediat.finances.model.db.operations.OperationsTable

@Database(
    entities = [
        OperationsTable::class,
        CashAccountsTable::class,
        CategoriesTable::class,
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun operationsDao(): OperationsDao
    abstract fun cashAccountDao(): CashAccountsDao
}