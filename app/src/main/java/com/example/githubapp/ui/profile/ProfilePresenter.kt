package com.example.githubapp.ui.profile

import com.example.githubapp.domain.GithubUsersRepo
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter

class ProfilePresenter(
    private val login: String?,
    private val usersRepo: GithubUsersRepo,
    private val router: Router
) : MvpPresenter<ProfileView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        setUser()
    }

    private fun setUser() {
        login?.let {
            viewState.setUser(usersRepo.getUser(login))

        }
    }

    fun backPressed(): Boolean {
        router.exit()
        return true

    }
}