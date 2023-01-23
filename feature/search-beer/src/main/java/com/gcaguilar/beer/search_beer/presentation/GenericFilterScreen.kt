package com.gcaguilar.beer.search_beer.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.gcaguilar.common_ui.theme.Dimen
import com.gcaguilar.common_ui.ui.RatingBar
import kotlin.math.roundToInt

@Composable
fun GenericFilterScreen(
    abv: Float?,
    ibu: Float?,
    rate: Int?,
    onRateChange: (Int) -> Unit,
    onAbvChange: (Float) -> Unit,
    onIbuChange: (Float) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = (Dimen.medium.dp)),
        verticalArrangement = Arrangement.spacedBy(Dimen.small.dp)
    ) {
        Text(
            text = "Rate",
            style = MaterialTheme.typography.titleMedium,
        )
        RatingBar(
            rating = rate ?: 0,
            onRateChange = { onRateChange(it) }
        )
        Text(
            text = "ABV: $abv",
            style = MaterialTheme.typography.titleMedium,

            )
        Slider(
            value = abv ?: 0.0F,
            onValueChange = { onAbvChange(it.roundToInt().toFloat()) },
            valueRange = 0f..40f
        )
        Text(
            text = "IBU: $ibu",
            style = MaterialTheme.typography.titleMedium,
        )
        Slider(
            value = ibu ?: 0.0F,
            onValueChange = { onIbuChange(it.roundToInt().toFloat()) },
            valueRange = 0f..100f
        )
    }
}