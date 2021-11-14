package com.example.githubapp

import android.app.Application
import com.example.githubapp.data.Dependency.AppComponent
import com.example.githubapp.data.Dependency.AppModule
import com.example.githubapp.data.Dependency.DaggerAppComponent

import com.facebook.stetho.Stetho




class App : Application() {

    val appComponent: AppComponent by lazy{
        DaggerAppComponent
            .builder()
            .appModule(AppModule(this))
            .build()
    }



    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this)
    }
}