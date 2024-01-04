package ru.acediat.finances.domain.repositories

import ru.acediat.finances.domain.mappers.Mapper
import ru.acediat.finances.entities.operations.Operation
import ru.acediat.finances.model.db.OperationsDao
import ru.acediat.finances.model.db.OperationsTable
import javax.inject.Inject

interface OperationsRepository {
    suspend fun getOperations(): List<Operation>
    suspend fun saveOperation(operation: Operation)
}

class OperationsRepositoryImpl @Inject constructor(
    private val operationsDao: OperationsDao,
    private val dbOperationMapper: Mapper<OperationsTable, Operation>,
) : OperationsRepository {
    override suspend fun getOperations() = operationsDao
        .getAllOperations()
        .map(dbOperationMapper::map)

    override suspend fun saveOperation(operation: Operation) =
        operationsDao.insertOperation(dbOperationMapper.reverseMap(operation))
}
