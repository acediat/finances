package ru.acediat.finances.model.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface OperationsDao {

    @Query("select * from operations")
    suspend fun getAllOperations(): List<OperationsTable>

    @Insert
    suspend fun insertOperation(operation: OperationsTable)
}