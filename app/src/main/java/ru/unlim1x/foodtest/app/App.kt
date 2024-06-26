package ru.unlim1x.foodtest.app

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import ru.unlim1x.foodtest.di.appModule
import ru.unlim1x.foodtest.di.dataModule
import ru.unlim1x.foodtest.di.domainModule

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@App)
            modules(listOf(appModule, domainModule, dataModule))
        }

    }
}