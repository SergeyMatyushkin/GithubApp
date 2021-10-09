package com.example.githubapp

import android.app.Application
import com.example.githubapp.domain.GithubUsersRepo
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router

class App : Application() {
    companion object {
        lateinit var instance: App
    }

    //Временно до даггера положим это тут
    private val cicerone: Cicerone<Router> by lazy {
        Cicerone.create()
    }
    val navigatorHolder get() = cicerone.getNavigatorHolder()
    val router get() = cicerone.router

    val usersRepo = GithubUsersRepo()

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}