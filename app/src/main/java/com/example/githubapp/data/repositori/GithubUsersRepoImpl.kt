package com.example.githubapp.data.repositori

import com.example.githubapp.data.GithubUser
import com.example.githubapp.data.UsersRepository
import com.example.githubapp.data.datasource.GitHubApi
import com.example.githubapp.ui.other.SchedulerProvider
import io.reactivex.rxjava3.core.Single

class GithubUsersRepoImpl(
    private val githubApi: GitHubApi,
    private val schedulerProvider: SchedulerProvider
) : GithubUsersRepo {

    override fun githubUsers(): Single<List<GithubUser>> =
        githubApi.getGitHubUsers().subscribeOn(schedulerProvider.io())

    override fun userRepos(repoUrl: String): Single<List<UsersRepository>> =
        githubApi.getUserRepos(repoUrl).subscribeOn(schedulerProvider.io())

    override fun githubUser(login: String): Single<GithubUser> =
        githubApi.getUserByLogin(login).subscribeOn(schedulerProvider.io())

}