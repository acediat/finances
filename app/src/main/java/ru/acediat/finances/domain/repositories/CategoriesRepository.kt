package ru.acediat.finances.domain.repositories

import ru.acediat.finances.model.db.CategoriesDao
import ru.acediat.finances.ui.entities.Category
import javax.inject.Inject


interface CategoriesRepository {
    suspend fun getCategories(): List<Category>
}
class CategoriesRepositoryImpl @Inject constructor(
    private val categoryDao: CategoriesDao,
) : CategoriesRepository {

    override suspend fun getCategories(): List<Category> {
        return emptyList()
    }
}