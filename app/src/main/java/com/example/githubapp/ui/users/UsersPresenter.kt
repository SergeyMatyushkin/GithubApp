package com.example.githubapp.ui.users

import com.example.githubapp.AndroidScreens
import com.example.githubapp.domain.*
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
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
    private var currentDisposable = CompositeDisposable()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()


    }

    private fun loadData() {
        currentDisposable.add(usersRepo.githubUsers
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { appState -> renderData(appState) })
    }

    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Success -> {
                appState.data?.let { dataUsers ->
                    usersListPresenter.users.addAll(dataUsers)

                    usersListPresenter.itemClickListener = { itemView ->
                        router.navigateTo(AndroidScreens().profile(dataUsers[itemView.pos].login))
                    }
                    viewState.updateList()
                }
            }
        }

    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

    override fun onDestroy() {
        currentDisposable.dispose()
        super.onDestroy()
    }

}