package com.example.githubapp.data.repositori

import com.example.githubapp.data.GithubUser
import com.example.githubapp.data.UsersRepository
import io.reactivex.rxjava3.core.Single

interface GithubUsersRepo {

    fun githubUsers(): Single<List<GithubUser>>
    fun userRepos(repoUrl:String): Single<List<UsersRepository>>
    fun githubUser(login: String) : Single<GithubUser>

}