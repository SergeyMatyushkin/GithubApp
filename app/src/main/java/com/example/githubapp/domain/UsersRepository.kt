package com.example.githubapp.domain

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
class UsersRepository(
    @SerializedName("name")
    val name: String,
    @SerializedName("description")
    val description: String,
    var likeCounter:Int=0
):Parcelable