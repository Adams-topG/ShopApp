package com.example.shopapp.data.repository

import com.example.shopapp.data.model.JeansRes
import com.example.shopapp.data.model.mapper.JeansMapper
import com.example.shopapp.domain.retrofit.RetrofitService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class JeansRepository @Inject constructor(
    private val jeansMapper: JeansMapper,
    private val retrofitService: RetrofitService
) {
    suspend fun getJeans() = withContext(Dispatchers.IO){
        retrofitService.getJeans().let{res->res.map{jeansMapper.map(it)}}
    }
}