package com.jh.navermovie.data.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ReviewDAO {

    @Query("SELECT * FROM Review")
    fun getAll(): Flow<List<ReviewEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert( reviewEntity: ReviewEntity)

    @Query("SELECT * FROM Review WHERE title = :title")
    fun getReview(title: String): ReviewEntity?

}