package com.example.githubapp.data.repositori

import com.example.githubapp.data.GithubUser
import com.example.githubapp.data.UsersRepository
import com.example.githubapp.data.domain.INetworkStatus
import com.example.githubapp.ui.other.SchedulerProvider
import io.reactivex.rxjava3.core.Single


class GithubUserRepoCombinedImpl(
    private val localRepo: GithubUsersRepo,
    private val webRepo: GithubUsersRepo,
    private val networkStatus: INetworkStatus,
    private val schedulerProvider: SchedulerProvider
) : GithubUsersRepo {

    override fun githubUsers(): Single<List<GithubUser>> =
        networkStatus.isOnlineSingle().flatMap { isOnline ->
            if (isOnline) {
                webRepo.githubUsers()
                // преобразовать в тип entity и записать в базу insert
            } else {
                localRepo.githubUsers()
            }.subscribeOn(schedulerProvider.io())
        }

    override fun userRepos(repoUrl: String): Single<List<UsersRepository>> =
        networkStatus.isOnlineSingle().flatMap { isOnline ->
            if (isOnline) {
                webRepo.userRepos(repoUrl)
                // преобразовать в тип entity и записать в базу insert
            } else {
                localRepo.userRepos(repoUrl)
            }.subscribeOn(schedulerProvider.io())
        }

    override fun githubUser(login: String): Single<GithubUser> =
        networkStatus.isOnlineSingle().flatMap { isOnline ->
            if (isOnline) {
                webRepo.githubUser(login)
                // преобразовать в тип entity и записать в базу insert
            } else {
                localRepo.githubUser(login)
            }.subscribeOn(schedulerProvider.io())
        }
}