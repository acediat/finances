package ru.acediat.finances.model

import org.threeten.bp.LocalDate
import java.math.BigDecimal

data class Operation(
    val value: BigDecimal,
    val category: Category,
    val date: LocalDate,
    val hiddenFromAnalytics: Boolean,
)