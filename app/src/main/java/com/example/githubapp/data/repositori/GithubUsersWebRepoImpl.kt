package com.example.githubapp.data.repositori

import com.example.githubapp.data.GithubUser
import com.example.githubapp.data.UsersRepository
import com.example.githubapp.data.datasource.GitHubApi
import com.example.githubapp.ui.other.SchedulerProvider
import io.reactivex.rxjava3.core.Single

class GithubUsersWebRepoImpl(
    private val githubApi: GitHubApi

) : GithubUsersRepo {

    override fun githubUsers(): Single<List<GithubUser>> =
        githubApi.getGitHubUsers()

    override fun userRepos(repoUrl: String, userId:Int?): Single<List<UsersRepository>> =
        githubApi.getUserRepos(repoUrl)

    override fun githubUser(login: String): Single<GithubUser> =
        githubApi.getUserByLogin(login)

}