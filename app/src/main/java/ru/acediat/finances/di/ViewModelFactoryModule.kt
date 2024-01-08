package ru.acediat.finances.di

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import ru.acediat.finances.core.ViewModelFactory

@Module
interface ViewModelFactoryModule {

    @Binds
    fun bindViewModelFactory(impl: ViewModelFactory): ViewModelProvider.Factory
}