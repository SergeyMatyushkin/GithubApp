package com.example.githubapp.data.repositori

import com.example.githubapp.data.GithubUser
import com.example.githubapp.data.UsersRepository
import com.example.githubapp.data.room.GithubDatabase
import com.example.githubapp.data.room.GithubRepositoryEntity
import com.example.githubapp.data.room.GithubUserEntity
import com.example.githubapp.ui.utils.githubUserListMap
import com.example.githubapp.ui.utils.githubUserMap
import com.example.githubapp.ui.utils.usersReposMap
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

class GithubUsersLocalRepoImpl(db: GithubDatabase) : GithubLocalRepo {

    private val userDao = db.userDao
    private val repositoryDao = db.repositoryDao

    override fun githubUsers(): Single<List<GithubUser>> {
        return userDao.getAll().map {
            githubUserListMap(it)
        }
    }

    override fun userRepos(repoUrl: String): Single<List<UsersRepository>> {
        return repositoryDao.getAll().map {
            usersReposMap(it)
        }
    }


    override fun githubUser(login: String): Single<GithubUser> {
        return userDao.findByLogin(login).map{
            githubUserMap(it)
        }
    }

    override fun putGithubUser(users: List<GithubUserEntity>): Completable {
        return userDao.insert(users)
    }

    override fun putGithubUser(user: GithubUserEntity): Completable {
        return userDao.insert(user)
    }

    override fun putGithubRepos(repos: List<GithubRepositoryEntity>): Completable {
        return repositoryDao.insert(repos)
    }

}