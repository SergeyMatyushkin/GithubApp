package com.example.githubapp.data

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class GithubUser(
    @Json(name = "avatar_url")
    val avatarUrl: String?,
    @Json(name = "id")
    val id: Int?,
    @Json(name = "login")
    val login: String?,
    @Json(name = "organizations_url")
    val organizationsUrl: String?,
    @Json(name = "repos_url")
    val reposUrl: String?,
    var like: Boolean = false
) : Parcelable
