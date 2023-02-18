package com.example.shopapp.domain.retrofit

import com.example.shopapp.data.model.JeansRes
import retrofit2.http.GET

interface RetrofitService {
    @GET("tests/jeans/jeans-default.json")
    suspend fun getJeans(): List<JeansRes>
}