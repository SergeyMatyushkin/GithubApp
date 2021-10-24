package com.example.githubapp.impl
import com.example.githubapp.domain.AppState
import com.example.githubapp.domain.GithubUser
import com.example.githubapp.domain.GithubUsersRepo
import com.example.githubapp.domain.UsersRepository
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.subjects.BehaviorSubject
import java.util.concurrent.TimeUnit

const val LOADING_DELAY = 1000L

class GithubUsersRepoImpl: GithubUsersRepo {

    private val usersList = listOf(
        GithubUser("login1"),
        GithubUser("login2"),
        GithubUser("login3"),
        GithubUser("login4"),
        GithubUser("login5")
    )

    private val userRepoList = listOf(
        UsersRepository("nasa", "nasa api application",1),
        UsersRepository("notes", "user notes application, help to organization your day"),
        UsersRepository("github client", "github client , github api application. " +
                "Users hub information, repositories and ratings")
    )
    private val behaviorSubject = BehaviorSubject.createDefault<AppState>(AppState.Success(usersList))

    override val githubUsers: Observable<AppState> = behaviorSubject

        //get() = field.delay(LOADING_DELAY, TimeUnit.MILLISECONDS)

    override val userRepos: Single<List<UsersRepository>> = Single.just(userRepoList)

    override fun githubUser(login: String) : Single<GithubUser>{
        return Single.just(GithubUser(login))
    }



}