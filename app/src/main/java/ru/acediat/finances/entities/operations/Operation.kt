package ru.acediat.finances.entities.operations

import org.threeten.bp.LocalDate
import java.math.BigDecimal

data class Operation(
    val id: Long,
    val value: BigDecimal,
    //val category: Category,
    val date: LocalDate,
    val hiddenFromAnalytics: Boolean,
): OperationsListEntity()