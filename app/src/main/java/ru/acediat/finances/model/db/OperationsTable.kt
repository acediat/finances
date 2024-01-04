package ru.acediat.finances.model.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "operations")
data class OperationsTable(
    @ColumnInfo
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    @ColumnInfo
    val value: String = "",
    @ColumnInfo
    val date: String = "",
    @ColumnInfo("hidden_from_analytics")
    val hiddenFromAnalytics: Boolean = false
)