package com.gcaguilar.beer_detail.presentation

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.gcaguilar.beer_detail.domain.BeerDetail

@Composable
fun BeerDetailScreen(
    modifier: Modifier = Modifier,
    bid: Int,
    beerDetailViewModel: BeerDetailViewModel = hiltViewModel()
) {
    beerDetailViewModel.getBeer(bid)
    val state = beerDetailViewModel.state.collectAsState()
    Column {
        Log.d("Interceptor", "Paint")
        BeerDetail(
            modifier = modifier,
            beerDetail = state.value
        )
    }
}

@Composable
fun BeerDetail(
    modifier: Modifier,
    beerDetail: BeerDetail
) {
    Column(
        modifier = modifier
    ) {
        AsyncImage(
            modifier = Modifier.fillMaxWidth(),
            model = beerDetail,
            contentDescription = "Beer image"
        )
    }
}