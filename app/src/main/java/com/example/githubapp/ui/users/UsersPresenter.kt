package com.example.githubapp.ui.users

import com.example.githubapp.AndroidScreens
import com.example.githubapp.domain.GithubUser
import com.example.githubapp.domain.GithubUsersRepo
import com.example.githubapp.domain.IUserListPresenter
import com.example.githubapp.domain.UserItemView
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter

class UsersPresenter(private val usersRepo: GithubUsersRepo, private val router: Router) :
    MvpPresenter<UsersView>() {
    class UsersListPresenter : IUserListPresenter {
        val users = mutableListOf<GithubUser>()
        override var itemClickListener: ((UserItemView) -> Unit)? = null

        override fun getCount() = users.size

        override fun bindView(view: UserItemView) {
            val user = users[view.pos]
            view.setLogin(user.login)
        }
    }

    val usersListPresenter = UsersListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()


    }

    fun loadData() {
        val users = usersRepo.getUsers()
        usersListPresenter.users.addAll(users)
        usersListPresenter.itemClickListener = { itemView ->
            router.navigateTo(AndroidScreens().profile(users[itemView.pos].login))
        }
        viewState.updateList()
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

}