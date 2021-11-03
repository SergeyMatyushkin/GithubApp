package com.example.githubapp.data.repositori

import com.example.githubapp.data.GithubUser
import com.example.githubapp.data.UsersRepository
import com.example.githubapp.data.domain.INetworkStatus
import com.example.githubapp.ui.other.SchedulerProvider
import com.example.githubapp.ui.utils.githubUserEntityListMap
import com.example.githubapp.ui.utils.githubUserEntityMap
import com.example.githubapp.ui.utils.usersReposEntityMap
import io.reactivex.rxjava3.core.Single


class GithubUserRepoCombinedImpl(
    private val localRepo: GithubLocalRepo,
    private val webRepo: GithubUsersRepo,
    private val networkStatus: INetworkStatus,
    private val schedulerProvider: SchedulerProvider
) : GithubUsersRepo {

    override fun githubUsers(): Single<List<GithubUser>> =
        networkStatus.isOnlineSingle().flatMap { isOnline ->
            if (isOnline) {
                webRepo.githubUsers().flatMap { users ->
                    Single.fromCallable {
                        val roomUsers = githubUserEntityListMap(users)
                        localRepo.putGithubUser(roomUsers)
                            .subscribe()
                        users
                    }
                }
            } else {
                localRepo.githubUsers()
            }.subscribeOn(schedulerProvider.io())
        }

    override fun userRepos(repoUrl: String, userId:Int?): Single<List<UsersRepository>> =
        networkStatus.isOnlineSingle().flatMap { isOnline ->
            if (isOnline) {
                webRepo.userRepos(repoUrl,userId).flatMap { repos ->
                    Single.fromCallable {
                        val roomRepos = usersReposEntityMap(repos,userId)
                        localRepo.putGithubRepos(roomRepos)
                            .subscribe()
                        repos
                    }
                }
            }
            else {
                localRepo.userRepos(repoUrl)
            }.subscribeOn(schedulerProvider.io())
        }

    override fun githubUser(login: String): Single<GithubUser> =
        networkStatus.isOnlineSingle().flatMap { isOnline ->
            if (isOnline) {
                webRepo.githubUser(login).flatMap { user ->
                    Single.fromCallable {
                        val roomUser = githubUserEntityMap(user)
                        localRepo.putGithubUser(roomUser)
                            .subscribe()
                        user
                    }
                }
            } else {
                localRepo.githubUser(login)
            }.subscribeOn(schedulerProvider.io())
        }
}