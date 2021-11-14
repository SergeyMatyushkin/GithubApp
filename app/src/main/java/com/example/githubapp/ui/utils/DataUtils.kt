package com.example.githubapp.ui.utils

import android.content.Context
import com.example.githubapp.App
import com.example.githubapp.data.GithubUser
import com.example.githubapp.data.UsersRepository
import com.example.githubapp.data.room.GithubRepositoryEntity
import com.example.githubapp.data.room.GithubUserEntity


val Context.app: App
    get() {
        return applicationContext as App
    }

fun githubUserListMap(users: List<GithubUserEntity>) =
    users.map {
        githubUserMap(it)
    }

fun githubUserMap(githubUser: GithubUserEntity) = GithubUser(
    githubUser.avatarUrl,
    githubUser.id,
    githubUser.login,
    githubUser.organizationsUrl,
    githubUser.reposUrl,
    githubUser.like
)

fun usersReposMap(repos: List<GithubRepositoryEntity>) =
    repos.map {
        UsersRepository(
            it.htmlUrl,
            it.id,
            it.userId,
            it.name,
            it.description,
            it.likeCounter,
        )
    }

fun githubUserEntityMap(githubUser: GithubUser) = GithubUserEntity(
    githubUser.avatarUrl,
    githubUser.id,
    githubUser.login,
    githubUser.organizationsUrl,
    githubUser.reposUrl,
    githubUser.like
)

fun githubUserEntityListMap(users: List<GithubUser>) =
    users.map {
        githubUserEntityMap(it)
    }

fun usersReposEntityMap(repos: List<UsersRepository>, userId: Int?) =
    repos.map {
        GithubRepositoryEntity(
            it.htmlUrl,
            it.id,
            userId,
            it.name,
            it.description,
            it.likeCounter,
        )
    }