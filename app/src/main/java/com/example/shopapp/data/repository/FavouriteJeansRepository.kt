package com.example.shopapp.data.repository

import com.example.shopapp.data.dao.FavouriteJeansDAO
import com.example.shopapp.data.model.FavouriteJeansRes
import com.example.shopapp.data.model.mapper.FavouriteJeansMapper
import com.example.shopapp.domain.model.Favourite
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FavouriteJeansRepository @Inject constructor(
    private val favouriteDAO: FavouriteJeansDAO,
    private val favouriteMapper: FavouriteJeansMapper
) {
    suspend fun all(): List<Favourite> = withContext(Dispatchers.IO) {
        favouriteDAO.all().let { res -> res.map { favouriteMapper.map(it) } }
    }

    suspend fun allByIds(ids: IntArray): List<Favourite> = withContext(Dispatchers.IO) {
        favouriteDAO.allByIds(ids).let { res -> res.map { favouriteMapper.map(it) } }
    }

    suspend fun insert(id: Int) = withContext(Dispatchers.IO) {
        favouriteDAO.insert(FavouriteJeansRes(id = id))
    }

    suspend fun delete(id: Int) = withContext(Dispatchers.IO) {
        favouriteDAO.delete(FavouriteJeansRes(id = id))

    }
}