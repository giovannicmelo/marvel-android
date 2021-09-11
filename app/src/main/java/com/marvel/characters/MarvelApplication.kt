package com.marvel.characters

import android.app.Application
import com.marvel.characters.di.getAppModules
import com.marvel.core.getCoreModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.core.module.Module

class MarvelApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            printLogger(Level.ERROR)
            androidContext(this@MarvelApplication)
            modules(getModules())
        }
    }

    private fun getModules(): MutableList<Module> {
        val modules: MutableList<Module> = arrayListOf()
        modules.addAll(getAppModules())
        modules.addAll(getCoreModules())
        return modules
    }
}