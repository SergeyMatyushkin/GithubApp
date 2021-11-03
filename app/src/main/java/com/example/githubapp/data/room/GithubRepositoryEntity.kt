package com.example.githubapp.data.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [ForeignKey(
        entity = GithubUserEntity::class,
        parentColumns = ["id"],
        childColumns = ["userId"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class GithubRepositoryEntity (
    @ColumnInfo(name = "html_url")
    val htmlUrl: String?,
    @PrimaryKey
    val id: Int?,
    var userId: String?,
    @ColumnInfo(name = "name")
    val name: String?,
    @ColumnInfo(name = "description")
    val description: String?,
    var likeCounter:Int=0
)