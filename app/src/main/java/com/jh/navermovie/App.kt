package com.jh.navermovie

import android.app.Application
import androidx.room.Room
import com.jh.navermovie.db.MovieDB

class App: Application() {

    val db by lazy { Room.databaseBuilder(applicationContext, MovieDB::class.java, "MovieDB.db").build() }

}