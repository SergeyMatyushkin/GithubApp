package com.example.githubapp.domain

import io.reactivex.rxjava3.core.Observable

interface GithubUsersRepo{

    val githubUsers: Observable<AppState>
    fun githubUser(login: String) : Observable<GithubUser>

}