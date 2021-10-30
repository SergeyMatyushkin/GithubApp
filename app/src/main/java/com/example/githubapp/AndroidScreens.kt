package com.example.githubapp

import com.example.githubapp.data.GithubUser

import com.example.githubapp.ui.profile.ProfileFragment
import com.example.githubapp.ui.users.UsersFragment
import com.github.terrakok.cicerone.Screen
import com.github.terrakok.cicerone.androidx.FragmentScreen

class AndroidScreens : IScreens {
    override fun users() = FragmentScreen { UsersFragment.newInstance() }

    override fun profile(githubUser: GithubUser) = FragmentScreen { ProfileFragment.newInstance(githubUser) }
}

interface IScreens {
    fun users(): Screen
    fun profile(githubUser: GithubUser): Screen
}