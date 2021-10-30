package com.example.githubapp

import android.app.Application
import com.example.githubapp.data.datasource.GitHubApi
import com.example.githubapp.data.domain.EventBus
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

const val BASE_URL = "https://api.github.com"

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
    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    val api: GitHubApi by lazy {

        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(GitHubApi::class.java)
    }

}