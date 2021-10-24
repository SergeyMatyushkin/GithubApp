package com.example.githubapp.domain

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

interface GithubUsersRepo {

    val githubUsers: Observable<AppState>
    val userRepos: Single<List<UsersRepository>>
    fun githubUser(login: String) : Single<GithubUser>

}