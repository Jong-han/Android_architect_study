package com.jh.navermovie.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [ReviewEntity::class],
    version = 1
)
abstract class MovieDB : RoomDatabase() {
    abstract fun reviewDAO(): ReviewDAO
}