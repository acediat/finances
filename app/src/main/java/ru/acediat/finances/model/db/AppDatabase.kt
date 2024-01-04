package ru.acediat.finances.model.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [OperationsTable::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun operationsDao(): OperationsDao
}