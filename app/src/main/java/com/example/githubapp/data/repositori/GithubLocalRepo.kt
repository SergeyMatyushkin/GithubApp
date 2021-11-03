package com.example.githubapp.data.repositori

import com.example.githubapp.data.GithubUser
import com.example.githubapp.data.UsersRepository
import com.example.githubapp.data.room.GithubRepositoryEntity
import com.example.githubapp.data.room.GithubUserEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface GithubLocalRepo {

    fun githubUsers(): Single<List<GithubUser>>
    fun userRepos(repoUrl:String): Single<List<UsersRepository>>
    fun githubUser(login: String) : Single<GithubUser>
    fun putGithubUser(users: List<GithubUserEntity>): Completable
    fun putGithubUser(user: GithubUserEntity): Completable
    fun putGithubRepos (repos:List<GithubRepositoryEntity>): Completable

}