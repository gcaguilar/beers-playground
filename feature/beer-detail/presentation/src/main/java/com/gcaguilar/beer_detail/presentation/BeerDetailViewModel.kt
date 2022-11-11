package com.gcaguilar.beer_detail.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gcaguilar.beer_detail.domain.BeerDetail
import com.gcaguilar.beer_detail.domain.GetBeerByBid
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BeerDetailViewModel @Inject constructor(
    private val getBeerByBid: GetBeerByBid
) : ViewModel() {
    private val _state = MutableStateFlow(BeerDetail(0, "", "", "", "", 0.0))
    val state: StateFlow<BeerDetail> = _state

    fun getBeer(bid: Int) {
        Log.d("Interceptor", "Get Beer")

        viewModelScope.launch {
            _state.value = getBeerByBid(bid)
        }
    }
}