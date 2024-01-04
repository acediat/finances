package ru.acediat.finances.di

import dagger.Component
import ru.acediat.finances.model.di.DatabaseModule
import ru.acediat.finances.operations.AddOperationFragment
import ru.acediat.finances.operations.OperationsFragment
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidModule::class,
    DatabaseModule::class,
    OperationsModule::class,
    ViewModelFactoryModule::class,
])
interface AppComponent {

    fun inject(fragment: OperationsFragment)
    fun inject(fragment: AddOperationFragment)
}