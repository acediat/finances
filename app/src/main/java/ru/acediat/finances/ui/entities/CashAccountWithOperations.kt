package ru.acediat.finances.ui.entities

import ru.acediat.finances.ui.entities.operations.Operation

data class CashAccountWithOperations(
    val account: CashAccount,
    val operations: List<Operation>
)