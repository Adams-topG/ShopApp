package com.example.shopapp.domain

import androidx.lifecycle.MutableLiveData
import com.example.shopapp.data.repository.FavouriteJeansRepository
import com.example.shopapp.data.repository.JeansRepository
import com.example.shopapp.domain.model.Jeans
import com.example.shopapp.utils.asLiveData
import javax.inject.Inject

class JeansInteractor @Inject constructor(
    private val jeansRepo: JeansRepository,
    private val favouriteJeans: FavouriteJeansRepository
    ) {

        private val _jeans = MutableLiveData<String>()
        val jeans = _jeans.asLiveData()

        suspend fun getJeans(): List<Jeans> {
            val favourites = favouriteJeans.all()
            val jeans = jeansRepo.getJeans()
            val favouriteJeans: MutableList<Jeans> = ArrayList(jeans.size)

            for (jean in jeans) {
                var isFavourite = false

                for (favouriteJean in favourites) {
                    if (jean.id == favouriteJean.id) {
                        isFavourite = true
                        break
                    }
                }
                favouriteJeans.add(jean.copy(favourite = isFavourite))

            }
            return favouriteJeans
        }

        suspend fun favourite(id: Int) {
            favouriteJeans.insert(id)
        }
        suspend fun unFavourite(id: Int) {
            favouriteJeans.delete(id)
        }
}