package com.example.shopapp.data.model.mapper

import com.example.shopapp.data.model.FavouriteJeansRes
import com.example.shopapp.domain.model.Favourite
import javax.inject.Inject

class FavouriteJeansMapper @Inject constructor()  {
    fun map(favouriteJeansRes: FavouriteJeansRes): Favourite = Favourite(
        id = favouriteJeansRes.id
    )
    fun unmap(favourite: Favourite): FavouriteJeansRes = FavouriteJeansRes(id = favourite.id)
}