package ru.acediat.finances.di

import android.content.Context
import dagger.Module
import dagger.Provides

@Module
class AndroidModule(private val appContext: Context) {

    @Provides
    fun provideApplicationContext(): Context = appContext
}