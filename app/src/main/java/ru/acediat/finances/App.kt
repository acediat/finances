package ru.acediat.finances

import android.app.Application
import ru.acediat.finances.di.AndroidModule
import ru.acediat.finances.di.AppComponent
import ru.acediat.finances.di.DaggerAppComponent
import ru.acediat.finances.model.di.DatabaseModule

class App : Application() {

    lateinit var appComponent: AppComponent
    override fun onCreate() {
        super.onCreate()
        initAppComponent()
    }

    private fun initAppComponent() {
        appComponent = DaggerAppComponent.builder()
            .androidModule(AndroidModule(this))
            .databaseModule(DatabaseModule())
            .build()
    }
}