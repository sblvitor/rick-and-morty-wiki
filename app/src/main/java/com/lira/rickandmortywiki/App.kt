package com.lira.rickandmortywiki

import android.app.Application
import com.lira.rickandmortywiki.data.di.DataModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
        }

        DataModule.load()
    }

}