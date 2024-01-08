package ru.acediat.finances.model.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "categories")
data class CategoriesTable(
    @ColumnInfo("category_id")
    @PrimaryKey(autoGenerate = true)
    val categoryId: Long = 0,
    val name: String,
    val color: Int,
    val iconFileName: String,
    @ColumnInfo("hidden_from_analytics")
    val hiddenFromAnalytics: Boolean,
)