package com.example.shopapp.data.model.mapper

import com.example.shopapp.data.model.JeansRes
import com.example.shopapp.domain.model.Jeans
import javax.inject.Inject

class JeansMapper @Inject constructor(){
    fun map(jeansRes: JeansRes): Jeans = Jeans(
        id = jeansRes.id ,
        title = jeansRes.title,
        price = jeansRes.price,
        image = jeansRes.image,
        new = jeansRes.new
    )
}