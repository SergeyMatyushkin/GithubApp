package com.example.githubapp.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GithubUser(
    var login: String,
    var like: Boolean = false
) : Parcelable