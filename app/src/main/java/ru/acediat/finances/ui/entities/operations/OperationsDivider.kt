package ru.acediat.finances.ui.entities.operations

import org.threeten.bp.LocalDate

data class OperationsDivider(
    val date: LocalDate
): OperationsListEntity()