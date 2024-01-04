package ru.acediat.finances.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ru.acediat.finances.domain.mappers.Mapper
import ru.acediat.finances.domain.mappers.OperationDbMapper
import ru.acediat.finances.domain.repositories.OperationsRepository
import ru.acediat.finances.domain.repositories.OperationsRepositoryImpl
import ru.acediat.finances.domain.usecases.OperationUseCase
import ru.acediat.finances.domain.usecases.OperationUseCaseImpl
import ru.acediat.finances.entities.operations.Operation
import ru.acediat.finances.model.db.OperationsTable
import ru.acediat.finances.operations.OperationsViewModel

@Module
interface OperationsModule {
    @Binds
    fun provideOperationRepository(impl: OperationsRepositoryImpl): OperationsRepository

    @Binds
    fun provideOperationMapper(impl: OperationDbMapper): Mapper<OperationsTable, Operation>

    @Binds
    fun provideOperationsUseCase(impl: OperationUseCaseImpl): OperationUseCase

    @Binds
    @IntoMap
    @ViewModelKey(OperationsViewModel::class)
    fun bindOperationsViewModel(viewModel: OperationsViewModel): ViewModel
}