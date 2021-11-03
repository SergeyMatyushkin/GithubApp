package com.example.githubapp.data.repositori

import com.example.githubapp.data.GithubUser
import com.example.githubapp.data.UsersRepository
import com.example.githubapp.data.room.GithubDatabase
import com.example.githubapp.ui.utils.githubUserListMap
import com.example.githubapp.ui.utils.githubUserMap
import com.example.githubapp.ui.utils.usersReposMap
import io.reactivex.rxjava3.core.Single

class GithubUsersLocalRepoImpl(private val db: GithubDatabase) : GithubUsersRepo {

    override fun githubUsers(): Single<List<GithubUser>> {
        return db.userDao.getAll().map {
            githubUserListMap(it)
        }
    }

    override fun userRepos(repoUrl: String): Single<List<UsersRepository>> {
        return db.repositoryDao.getAll().map {
            usersReposMap(it)
        }
    }


    override fun githubUser(login: String): Single<GithubUser> {
        return db.userDao.findByLogin(login).map{
            githubUserMap(it)
        }
    }
}