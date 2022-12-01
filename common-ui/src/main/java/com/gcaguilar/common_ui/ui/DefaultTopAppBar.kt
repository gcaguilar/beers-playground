package com.gcaguilar.common_ui.ui

import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable

@Composable
fun DefaultTopBar(
    title: String,
    action: @Composable () -> Unit,
) {
    TopAppBar(
        title = {
            Text(text = title)
        },
        actions = {
           action()
        }
    )
}