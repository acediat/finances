package ru.acediat.finances.ui.entities

import java.math.BigDecimal

data class CashAccount(
    val id: Long = 0,
    val summary: BigDecimal,
    val startValue: BigDecimal,
    val name: String,
    val iconFileName: String,
)