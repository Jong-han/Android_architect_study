package com.jh.navermovie.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Review")
data class ReviewEntity(
    @PrimaryKey
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "rate") val rate: String,
    @ColumnInfo(name = "review") var review: String,
    @ColumnInfo(name = "poster") var imageUrl: String
)