package ru.acediat.finances.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ru.acediat.finances.domain.mappers.CashAccountDbMapper
import ru.acediat.finances.domain.mappers.CashAccountWithOperationsDbMapper
import ru.acediat.finances.domain.mappers.Mapper
import ru.acediat.finances.domain.mappers.OperationDbMapper
import ru.acediat.finances.domain.repositories.CashAccountRepository
import ru.acediat.finances.domain.repositories.CashAccountsRepositoryImpl
import ru.acediat.finances.domain.repositories.OperationsRepository
import ru.acediat.finances.domain.repositories.OperationsRepositoryImpl
import ru.acediat.finances.domain.usecases.SummaryUseCase
import ru.acediat.finances.domain.usecases.SummaryUseCaseImpl
import ru.acediat.finances.domain.usecases.SaveOperationUseCase
import ru.acediat.finances.domain.usecases.SaveOperationUseCaseImpl
import ru.acediat.finances.model.db.cashaccounts.CashAccountWithOperationsTable
import ru.acediat.finances.model.db.cashaccounts.CashAccountsTable
import ru.acediat.finances.ui.entities.operations.Operation
import ru.acediat.finances.model.db.operations.OperationsTable
import ru.acediat.finances.ui.MainViewModel
import ru.acediat.finances.ui.entities.CashAccount
import ru.acediat.finances.ui.entities.CashAccountWithOperations
import java.math.BigDecimal

@Module
interface MainModule {
    @Binds
    fun provideOperationRepository(impl: OperationsRepositoryImpl): OperationsRepository

    @Binds
    fun provideCashAccountRepository(impl: CashAccountsRepositoryImpl): CashAccountRepository

    @Binds
    fun provideOperationMapper(impl: OperationDbMapper): Mapper<OperationsTable, Operation>

    @Binds
    fun provideOperationsUseCase(impl: SummaryUseCaseImpl): SummaryUseCase

    @Binds
    fun provideSaveOperationUseCase(impl: SaveOperationUseCaseImpl): SaveOperationUseCase

    @Binds
    fun provideCashAccountMapper(impl: CashAccountDbMapper): Mapper<Pair<CashAccountsTable, BigDecimal>, CashAccount>

    @Binds
    fun provideCashAccountWithOperations(
        impl: CashAccountWithOperationsDbMapper
    ): Mapper<CashAccountWithOperationsTable, CashAccountWithOperations>

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    fun bindOperationsViewModel(viewModel: MainViewModel): ViewModel
}