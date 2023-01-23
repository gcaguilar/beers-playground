package com.gcaguilar.common_ui.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.FilterListOff
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType

@Composable
fun SearchAppBar(
    value: String,
    label: String,
    filtered: Boolean,
    onTextChange: (String) -> Unit,
    onSearchClick: () -> Unit,
    onActionBackClick: () -> Unit,
    onClearClick: () -> Unit = {},
    onFocusChanged: (FocusState) -> Unit = {},
    onFilterClick: () -> Unit = {},
) {
    TopAppBar(
        title = {
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .onFocusChanged { onFocusChanged(it) },
                value = value,
                onValueChange = { query ->
                    onTextChange(query)
                },
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.3f)
                ),
                label = { Text(text = label) },
                textStyle = MaterialTheme.typography.bodyMedium,
                singleLine = true,
                leadingIcon = {
                    if (value.isNotEmpty()) {
                        IconButton(onClick = { onSearchClick() }) {
                            Icon(imageVector = Icons.Filled.Search, contentDescription = "Search")
                        }
                    }
                },
                trailingIcon = {
                    if (value.isNotEmpty()) {
                        IconButton(onClick = { onClearClick() }) {
                            Icon(imageVector = Icons.Filled.Clear, contentDescription = "Clear")
                        }
                    }
                },
                keyboardActions = KeyboardActions(onSearch = { onSearchClick() }),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Search, keyboardType = KeyboardType.Text
                )
            )
        },
        navigationIcon = {
            IconButton(
                onClick = { onActionBackClick() },
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back"
                )
            }
        },
        actions = {
            IconButton(
                onClick = { onFilterClick() },
            ) {
                Icon(
                    imageVector = if (filtered) Icons.Default.FilterListOff else Icons.Default.FilterList,
                    contentDescription = "Filter"
                )
            }
        }
    )
}