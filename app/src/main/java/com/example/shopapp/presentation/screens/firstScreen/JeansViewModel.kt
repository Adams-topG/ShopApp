package com.example.shopapp.presentation.screens.firstScreen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopapp.domain.JeansInteractor
import com.example.shopapp.domain.model.Jeans
import com.example.shopapp.utils.asLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class JeansViewModel @Inject constructor(
    private val jeansInteractor: JeansInteractor
) : ViewModel() {

    private val _jeans = MutableLiveData<List<Jeans>>()
    val jeans = _jeans.asLiveData()

    fun getJeans() = viewModelScope.launch {
        _jeans.postValue(jeansInteractor.getJeans())
    }

}