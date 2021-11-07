package com.example.githubapp

import android.app.Application
import com.example.githubapp.data.Dependency.apiModule
import com.example.githubapp.data.Dependency.application
import com.example.githubapp.data.Dependency.ciceroneModule
import com.example.githubapp.data.Dependency.repoModule
import com.facebook.stetho.Stetho
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin


class App : Application() {



    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this)
        // Koin

        startKoin {
            // declare used Android context
            androidContext(this@App)
            // declare modules
            modules(application, ciceroneModule, repoModule, apiModule)
        }

    }



}