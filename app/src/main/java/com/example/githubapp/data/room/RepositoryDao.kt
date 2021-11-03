package com.example.githubapp.data.room

import androidx.room.*
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

@Dao
interface RepositoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: GithubRepositoryEntity): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg users: GithubRepositoryEntity):Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(users: List<GithubRepositoryEntity>):Completable

    @Update
    fun update(user: GithubRepositoryEntity):Completable

    @Update
    fun update(vararg users: GithubRepositoryEntity):Completable

    @Update
    fun update(users: List<GithubRepositoryEntity>):Completable

    @Delete
    fun delete(user: GithubRepositoryEntity):Completable

    @Delete
    fun delete(vararg users: GithubRepositoryEntity):Completable

    @Delete
    fun delete(users: List<GithubRepositoryEntity>):Completable

    @Query("SELECT * FROM GithubRepositoryEntity")
    fun getAll(): Single<List<GithubRepositoryEntity>>

    @Query("SELECT * FROM GithubRepositoryEntity WHERE userId = :userId")
    fun findForUser(userId: String): Single<List<GithubRepositoryEntity>>

}