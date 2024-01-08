package ru.acediat.finances.domain.mappers

import ru.acediat.finances.model.db.cashaccounts.CashAccountsTable
import ru.acediat.finances.ui.entities.CashAccount
import java.math.BigDecimal
import javax.inject.Inject

class CashAccountDbMapper @Inject constructor() : Mapper<Pair<CashAccountsTable, BigDecimal>, CashAccount> {

    override fun map(value: Pair<CashAccountsTable, BigDecimal>) = CashAccount(
        id = value.first.id,
        name = value.first.name,
        iconFileName = value.first.iconFileName,
        summary = value.second,
        startValue = BigDecimal(value.first.startValue),
    )

    override fun reverseMap(value: CashAccount) = Pair(
        CashAccountsTable(
            id = value.id,
            name = value.name,
            iconFileName = value.iconFileName,
            startValue = value.startValue.toPlainString(),
        ),
        value.summary,
    )
}