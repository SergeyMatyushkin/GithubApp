package com.example.githubapp.data

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
class UsersRepository(
    //@Json(name = "forks_count")
    //val forksCount: Int?,
    //@Json(name = "full_name")
    //val fullName: String?,
    @Json(name = "html_url")
    val htmlUrl: String?,
    @Json(name = "id")
    val id: Int?,
    var userId: Int?,
    @Json(name = "name")
    val name: String?,
    //@Json(name = "node_id")
    //val nodeId: String?,
    //@Json(name = "stargazers_count")
    //val stargazersCount: Int?,
    //@Json(name = "url")
    //val url: String?,
    //@Json(name = "watchers_count")
    //val watchersCount: Int?,
    @Json(name = "description")
    val description: String?,
    var likeCounter:Int=0
):Parcelable