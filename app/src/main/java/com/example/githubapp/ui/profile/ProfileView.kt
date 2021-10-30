package com.example.githubapp.ui.profile

import com.example.githubapp.data.GithubUser

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface ProfileView: MvpView {
    fun setUser(user: GithubUser)
    fun updateList()
    fun setCountLike()
}