package com.example.githubapp.ui.profile

import com.example.githubapp.domain.GithubUsersRepo
import com.example.githubapp.ui.other.SchedulerProvider
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.disposables.Disposable
import moxy.MvpPresenter

class ProfilePresenter(
    private val login: String?,
    private val usersRepoImpl: GithubUsersRepo,
    private val router: Router
) : MvpPresenter<ProfileView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        setUser()
    }

    private var currentDisposable: Disposable? = null
        set(value) {
            field?.takeIf { !it.isDisposed }?.dispose()
            field = value
        }

    private val schedulerProvider: SchedulerProvider = SchedulerProvider()



    private fun setUser() {
        login?.let {
            currentDisposable = usersRepoImpl.githubUser(login)
                .observeOn(schedulerProvider.ui())
                .subscribe {
                   viewState.setUser(it)
                }

        }
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

    override fun onDestroy() {
        currentDisposable = null
        super.onDestroy()
    }


}