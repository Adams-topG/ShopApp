package com.example.shopapp.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.shopapp.data.model.FavouriteJeansRes

@Dao
interface FavouriteJeansDAO {
    @Query("SELECT * FROM favourite")
    suspend fun all(): List<FavouriteJeansRes>

    @Query("SELECT * FROM favourite WHERE id in (:ids)")
    suspend fun allByIds(ids : IntArray) : List<FavouriteJeansRes>

    @Insert
    suspend fun insert(favourite: FavouriteJeansRes)

    @Delete
    suspend fun delete(favourite: FavouriteJeansRes)

}