package com.example.githubapp

import com.example.githubapp.ui.profile.ProfileFragment
import com.example.githubapp.ui.users.UsersFragment
import com.github.terrakok.cicerone.Screen
import com.github.terrakok.cicerone.androidx.FragmentScreen

class AndroidScreens : IScreens {
    override fun users() = FragmentScreen { UsersFragment.newInstance() }
    override fun profile(login: String) = FragmentScreen { ProfileFragment.newInstance(login) }
}

interface IScreens {
    fun users(): Screen
    fun profile(login: String): Screen
}
