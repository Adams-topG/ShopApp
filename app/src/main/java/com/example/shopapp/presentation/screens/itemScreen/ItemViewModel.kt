package com.example.shopapp.presentation.screens.itemScreen

import androidx.lifecycle.ViewModel
import com.example.shopapp.domain.JeansInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ItemViewModel @Inject constructor(
    private val jeansInteractor: JeansInteractor
) : ViewModel() {


}