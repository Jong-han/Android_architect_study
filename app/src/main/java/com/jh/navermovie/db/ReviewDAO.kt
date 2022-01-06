package com.jh.navermovie.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ReviewDAO {

    @Query("SELECT * FROM Review")
    fun getAll(): List<ReviewEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert( reviewEntity: ReviewEntity )

    @Query("SELECT * FROM Review WHERE title = :title")
    fun getRate(title: String): ReviewEntity?

}