package ru.acediat.finances.domain.mappers

import org.threeten.bp.LocalDate
import ru.acediat.finances.entities.operations.Operation
import ru.acediat.finances.model.db.OperationsTable
import java.math.BigDecimal
import javax.inject.Inject

class OperationDbMapper @Inject constructor(): Mapper<OperationsTable, Operation> {
    override fun map(value: OperationsTable) = Operation(
        id = value.id,
        value = BigDecimal(value.value),
        date = LocalDate.parse(value.date),
        hiddenFromAnalytics = value.hiddenFromAnalytics,
    )

    override fun reverseMap(value: Operation) = OperationsTable(
        id = value.id,
        value = value.value.toPlainString(),
        date = value.date.toString(),
        hiddenFromAnalytics = value.hiddenFromAnalytics,
    )
}