package com.example.shopapp.presentation.screens.itemScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopapp.domain.JeansInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavouriteProductViewModel @Inject constructor(
    private val jeansInteractor: JeansInteractor
) : ViewModel() {
    fun favourite(id: Int) {
        viewModelScope.launch {
            jeansInteractor.favourite(id)
        }
    }

    fun unFavourite(id: Int) {
        viewModelScope.launch {
            jeansInteractor.unFavourite(id)
        }
    }
}