package ru.acediat.finances.di

import android.content.Context
import dagger.Module
import dagger.Provides
import ru.acediat.finances.model.AssetsFolder

@Module
class AndroidModule(private val appContext: Context) {

    @Provides
    fun provideApplicationContext(): Context = appContext

    @Provides
    fun provideAssetsFolder(context: Context): AssetsFolder = AssetsFolder(context)
}