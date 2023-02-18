package com.example.shopapp.domain.`interface`

import com.example.shopapp.domain.model.Favourite

interface FavouriteInterface {
    fun favourite(jean: Favourite)
    fun unFavourite(jean: Favourite)

}