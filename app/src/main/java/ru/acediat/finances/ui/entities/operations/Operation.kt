package ru.acediat.finances.ui.entities.operations

import org.threeten.bp.LocalDateTime
import java.math.BigDecimal

data class Operation(
    val id: Long = 0,
    val value: BigDecimal,
    //val category: Category,
    val date: LocalDateTime,
    val hiddenFromAnalytics: Boolean,
    val cashAccountId: Long,
): OperationsListEntity()