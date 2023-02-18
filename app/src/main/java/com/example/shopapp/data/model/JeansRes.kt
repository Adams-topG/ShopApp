package com.example.shopapp.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class JeansRes(
    @Json(name = "id")
    val id: Int,
    @Json(name = "image")
    val image: String,
    @Json(name = "new")
    val new: Boolean,
    @Json(name = "price")
    val price: Double,
    @Json(name = "title")
    val title: String
)