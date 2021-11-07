package com.example.githubapp.ui.users


import com.example.githubapp.AndroidScreens
import com.example.githubapp.data.GithubUser
import com.example.githubapp.data.domain.AppState
import com.example.githubapp.data.domain.UserItemView
import com.example.githubapp.data.domain.UserListPresenter
import com.example.githubapp.data.repositori.GithubUsersRepo
import com.example.githubapp.ui.other.SchedulerProvider
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.disposables.CompositeDisposable
import moxy.MvpPresenter
import org.koin.java.KoinJavaComponent.inject

class UsersPresenter() :
    MvpPresenter<UsersView>() {

    class UsersListPresenter : UserListPresenter {
        val users = mutableListOf<GithubUser>()
        override var itemClickListener: ((UserItemView) -> Unit)? = null

        override fun getCount() = users.size

        override fun bindView(view: UserItemView) {
            val user = users[view.pos]
            user.let {
                view.setGitUser(it)
                view.imageLoad(user.avatarUrl)
            }
        }
    }

    private val schedulerProvider: SchedulerProvider = SchedulerProvider()

    private val usersRepo : GithubUsersRepo by inject(GithubUsersRepo::class.java)
    private val router: Router by inject(Router::class.java)

    val usersListPresenter = UsersListPresenter()
    private var currentDisposable = CompositeDisposable()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()

        loadData()
    }

    private fun loadData() {
        currentDisposable.add(usersRepo.githubUsers()
            .observeOn(schedulerProvider.ui())
            .subscribe { userList -> renderData(AppState.Success(userList)) })
    }

    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Success -> {
                appState.data.let { dataUsers ->
                    if (dataUsers != null) {
                        usersListPresenter.users.addAll(dataUsers)
                    }

                    usersListPresenter.itemClickListener = { itemView ->
                        dataUsers?.get(itemView.pos)?.let {
                            router.navigateTo(AndroidScreens().profile(it))
                        }
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