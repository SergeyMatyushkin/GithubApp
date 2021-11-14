package com.example.githubapp.ui.profile

import com.example.githubapp.data.GithubUser
import com.example.githubapp.data.UsersRepository
import com.example.githubapp.data.domain.EventBus
import com.example.githubapp.data.domain.MinusLikeEvent
import com.example.githubapp.data.domain.PlusLikeEvent
import com.example.githubapp.data.repositori.GithubUsersRepo
import com.example.githubapp.ui.other.SchedulerProvider
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.disposables.CompositeDisposable
import moxy.MvpPresenter
import javax.inject.Inject


class ProfilePresenter(
    private val githubUser: GithubUser?
) : MvpPresenter<ProfileView>() {


    @Inject
    lateinit var router:Router
    @Inject
    lateinit var eventBus :EventBus

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        setUser()
        setRepoList()
    }

    private var currentDisposable = CompositeDisposable()
    private val schedulerProvider: SchedulerProvider = SchedulerProvider()
    @Inject
    lateinit var usersRepoImpl : GithubUsersRepo
    val userRepoList = mutableListOf<UsersRepository>()


    private fun setUser() {
        githubUser?.login?.let {
            currentDisposable.add(
                usersRepoImpl.githubUser(it)
                    .observeOn(schedulerProvider.ui())
                    .subscribe { gitUser -> viewState.setUser(gitUser) })
        }

    }

    private fun setRepoList() {
        githubUser?.reposUrl?.let {
            currentDisposable.add(usersRepoImpl.userRepos(it, githubUser.id)
                .observeOn(schedulerProvider.ui())
                .subscribe { userRepoListIn ->

                    userRepoList.addAll(userRepoListIn)
                    viewState.updateList()
                })
        }
    }

    fun onLikeClick(likeCounter: Int) {
        if (likeCounter == 1) {
            eventBus.post(PlusLikeEvent())
        } else {
            eventBus.post(MinusLikeEvent())
        }
        viewState.setCountLike()
    }

    fun setLikeCount(count: Int): Int {
        var total = 0
        currentDisposable.add(eventBus.get()
            .subscribe {
                if (it is PlusLikeEvent) {
                    total = count + 1
                } else if (it is MinusLikeEvent) {
                    if (count > 0) {
                        total = count - 1
                    }
                }
            })

        return total
    }

    fun openUserRepo(repoUrl:String?){
        viewState.openUserRepo(repoUrl)
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

    override fun onDestroy() {
        currentDisposable.clear()
        super.onDestroy()
    }
}