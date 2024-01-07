package ru.acediat.finances.ui.entities

data class Category(
    val id: Long = 0,
    val name: String,
    val color: Int,
    val iconFileName: String,
    val hiddenFromAnalytics: Boolean,
)