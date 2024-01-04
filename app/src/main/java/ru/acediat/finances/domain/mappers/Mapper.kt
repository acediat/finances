package ru.acediat.finances.domain.mappers

interface Mapper <I, O> {
    fun map(value: I): O
    fun reverseMap(value: O): I
}
