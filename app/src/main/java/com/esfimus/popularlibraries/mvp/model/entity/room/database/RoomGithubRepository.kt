package com.esfimus.popularlibraries.mvp.model.entity.room.database

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [ForeignKey(
        entity = RoomGithubUser::class,
        parentColumns = ["id"],
        childColumns = ["userId"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class RoomGithubRepository(
    @PrimaryKey var id: String,
    var userId: String = "",
    var name: String,
    var description: String,
    var created: String,
    var updated: String,
    var homepage: String,
    var language: String,
    var forks: String,
)