package com.example.shopapp.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.shopapp.data.dao.FavouriteJeansDAO
import com.example.shopapp.data.model.FavouriteJeansRes

@Database(entities = [FavouriteJeansRes::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun favouriteDao():FavouriteJeansDAO

    companion object{
        fun build(context: Context): AppDatabase{
            return Room.databaseBuilder(
                context,
                AppDatabase::class.java, "app"
            ).build()
        }
    }
}