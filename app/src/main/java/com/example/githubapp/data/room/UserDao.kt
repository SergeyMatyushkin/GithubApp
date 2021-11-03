package com.example.githubapp.data.room

import androidx.room.*
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: GithubUserEntity): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg users: GithubUserEntity):Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(users: List<GithubUserEntity>):Completable

    @Update
    fun update(user: GithubUserEntity):Completable

    @Update
    fun update(vararg users: GithubUserEntity):Completable

    @Update
    fun update(users: List<GithubUserEntity>):Completable

    @Delete
    fun delete(user: GithubUserEntity):Completable

    @Delete
    fun delete(vararg users: GithubUserEntity):Completable

    @Delete
    fun delete(users: List<GithubUserEntity>):Completable

    @Query("SELECT * FROM GithubUserEntity")
    fun getAll(): Single<List<GithubUserEntity>>

    @Query("SELECT * FROM GithubUserEntity WHERE login = :login LIMIT 1")
    fun findByLogin(login: String): Single<GithubUserEntity>
}