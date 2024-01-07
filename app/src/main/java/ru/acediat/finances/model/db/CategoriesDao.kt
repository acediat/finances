package ru.acediat.finances.model.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CategoriesDao {

    @Query("select * from categories")
    suspend fun getAllCategories(): List<CategoriesTable>

    @Insert
    suspend fun insertCategory(categoriesTable: CategoriesTable)
}