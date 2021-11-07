package com.example.githubapp.data.datasource


import com.example.githubapp.data.GithubUser
import com.example.githubapp.data.UsersRepository
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url

interface GitHubApi {

    @GET("/users")
    fun getGitHubUsers(): Single<List<GithubUser>>

    @GET("/users/{username}")
    fun getUserByLogin(
        @Path("username") login: String
    ): Single<GithubUser>

    @GET
    fun getUserRepos(@Url urlRepository: String): Single<List<UsersRepository>>
}