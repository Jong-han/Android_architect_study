package com.jh.navermovie

import android.app.Application
import androidx.room.Room
import com.jh.navermovie.db.MovieDB
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App: Application() {

    companion object {
        lateinit var db: MovieDB
    }

    override fun onCreate() {
        super.onCreate()
        db = Room.databaseBuilder(applicationContext, MovieDB::class.java, "MovieDB.db").build()
    }

}