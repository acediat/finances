package ru.acediat.finances.domain.mappers

import org.threeten.bp.LocalDateTime
import ru.acediat.finances.ui.entities.operations.Operation
import ru.acediat.finances.model.db.operations.OperationsTable
import java.math.BigDecimal
import javax.inject.Inject

class OperationDbMapper @Inject constructor(): Mapper<OperationsTable, Operation> {
    override fun map(value: OperationsTable) = Operation(
        id = value.categoryId,
        value = BigDecimal(value.value),
        date = LocalDateTime.parse(value.date),
        hiddenFromAnalytics = value.hiddenFromAnalytics,
        cashAccountId = value.cashAccountId,
    )

    override fun reverseMap(value: Operation) = OperationsTable(
        value = value.value.toPlainString(),
        date = value.date.toString(),
        hiddenFromAnalytics = value.hiddenFromAnalytics,
        categoryId = 0,
        cashAccountId = value.cashAccountId,
    )
}