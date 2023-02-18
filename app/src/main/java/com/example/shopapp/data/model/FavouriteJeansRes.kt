package com.example.shopapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favourite")
data class FavouriteJeansRes(
    @PrimaryKey
    val id: Int
)