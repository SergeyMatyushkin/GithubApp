package com.example.githubapp.data.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [GithubUserEntity::class, GithubRepositoryEntity::class], version = 1)
abstract class GithubDatabase : RoomDatabase() {
    abstract val userDao: UserDao
    abstract val repositoryDao: RepositoryDao
}