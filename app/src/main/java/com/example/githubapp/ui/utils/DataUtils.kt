package com.example.githubapp.ui.utils

import android.content.Context
import android.widget.ImageView
import com.example.githubapp.App
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.githubapp.data.GithubUser
import com.example.githubapp.data.UsersRepository
import com.example.githubapp.data.room.GithubRepositoryEntity
import com.example.githubapp.data.room.GithubUserEntity

val Context.app: App
    get() {
        return  applicationContext as App
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
            it.name,
            it.description,
            it.likeCounter,
        )
    }