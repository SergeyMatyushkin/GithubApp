package com.example.githubapp.data.domain

import com.example.githubapp.data.GithubUser

interface IItemView {
    var pos: Int
}
interface UserItemView: IItemView {
    fun setGitUser(gitHunUser: GithubUser)
}