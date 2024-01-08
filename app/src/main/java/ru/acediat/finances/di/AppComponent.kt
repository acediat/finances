package ru.acediat.finances.di

import dagger.Component
import ru.acediat.finances.model.di.DatabaseModule
import ru.acediat.finances.ui.AddOperationFragment
import ru.acediat.finances.ui.SummaryFragment
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidModule::class,
    DatabaseModule::class,
    MainModule::class,
    ViewModelFactoryModule::class,
])
interface AppComponent {

    fun inject(fragment: SummaryFragment)
    fun inject(fragment: AddOperationFragment)
}