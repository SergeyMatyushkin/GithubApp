package com.example.githubapp

import android.app.Application
import com.example.githubapp.domain.EventBus
import com.example.githubapp.impl.GithubUsersRepoImpl
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router

class App:Application() {

    //Временно до даггера положим это тут
    // навигация
    private val cicerone: Cicerone<Router> by lazy {
        Cicerone.create()
    }
    val navigatorHolder get() = cicerone.getNavigatorHolder()
    val router get() = cicerone.router

    val eventBus = EventBus
    // репозиторий
    val usersRepo = GithubUsersRepoImpl()

}