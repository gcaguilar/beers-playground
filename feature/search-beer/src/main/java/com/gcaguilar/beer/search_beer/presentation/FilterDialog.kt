package com.gcaguilar.beer.search_beer.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties

@Composable
fun FilterDialog(
    hideDialog: () -> Unit,
    abv: Double,
    ibu: Int,
    rate: Int
) {
    Dialog(
        properties = DialogProperties(usePlatformDefaultWidth = false),
        onDismissRequest = { hideDialog() }
    ) {
        Column {
            Text(
                text = "Estilo",
                style = MaterialTheme.typography.titleMedium,
            )
            Text(
                text = "Rate",
                style = MaterialTheme.typography.titleMedium,
            )
            Slider(
                value = rate.toFloat(),
                onValueChange = {},
                valueRange = 0f..10f
            )
            Text(
                text = "ABV",
                style = MaterialTheme.typography.titleMedium,

                )
            Slider(
                value = abv.toFloat(),
                onValueChange = {},
                valueRange = 0f..10f
            )
            Slider(value = abv.toFloat(), onValueChange = {})
            Text(
                text = "IBU",
                style = MaterialTheme.typography.titleMedium,
            )
            Slider(
                value = ibu.toFloat(),
                onValueChange = {},
                valueRange = 0f..100f
            )
        }

    }
}

@Preview
@Composable
fun FilterPreview() {
    FilterDialog(
        hideDialog = {},
        abv = 0.0,
        ibu = 0,
        rate = 0
    )
}