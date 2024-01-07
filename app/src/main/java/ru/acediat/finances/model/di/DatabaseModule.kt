package ru.acediat.finances.model.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import dagger.Module
import dagger.Provides
import ru.acediat.finances.model.db.AppDatabase
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideAppDatabase(appContext: Context): AppDatabase = Room.databaseBuilder(
        appContext,
        AppDatabase::class.java,
        "operations_db",
    ).addCallback(object : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
        }
    }).build()

    @Provides
    fun provideOperationsDao(database: AppDatabase) = database.operationsDao()

    @Provides
    fun provideCashAccountDao(database: AppDatabase) = database.cashAccountDao()
}