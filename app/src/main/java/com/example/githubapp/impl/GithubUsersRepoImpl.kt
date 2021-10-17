package com.example.githubapp.impl

import com.example.githubapp.domain.AppState
import com.example.githubapp.domain.GithubUser
import com.example.githubapp.domain.GithubUsersRepo
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.BehaviorSubject

class GithubUsersRepoImpl: GithubUsersRepo {
    private val repositories = listOf(
        GithubUser("login1"),
        GithubUser("login2"),
        GithubUser("login3"),
        GithubUser("login4"),
        GithubUser("login5")
    )
    private val behaviorSubject = BehaviorSubject.createDefault<AppState>(AppState.Success(repositories))

    override val githubUsers: Observable<AppState> = behaviorSubject

    override fun githubUser(login: String) : Observable<GithubUser> {
        return Observable.just(GithubUser(login))
    }
}