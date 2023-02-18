package com.example.shopapp.domain.model

data class Jeans(
    val id: Int?,
    val image: String?,
    val new: Boolean?,
    val price: Double?,
    val title: String?,
    var favourite: Boolean? = false
)