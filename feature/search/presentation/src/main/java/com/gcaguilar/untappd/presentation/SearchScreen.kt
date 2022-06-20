package com.gcaguilar.untappd.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.gcaguilar.common_ui.theme.Dimen
import com.gcaguilar.untappd.domain.Beer

@Composable
fun SearchScreen(
    modifier: Modifier,
    openBeerDetail: (Int) -> Unit,
    searchViewModel: SearchViewModel = hiltViewModel()
) {
    val state = searchViewModel.state.collectAsState()

    Column(
        modifier = modifier
    ) {
        SearchAppBar(
            value = state.value.searchText,
            label = state.value.hint,
            onTextChange = { searchViewModel.updateTextSearch(it) },
            onSearchClick = { searchViewModel.search() })

        ResultList(
            beersResult = state.value.beers,
            onClick = { id ->
                openBeerDetail(id)
            }
        )
    }
}


@Composable
fun ResultList(
    modifier: Modifier = Modifier,
    beersResult: List<Beer>,
    onClick: (Int) -> Unit
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(horizontal = Dimen.small.dp, vertical = Dimen.small.dp)
    ) {
        items(items = beersResult, key = { beer -> beer.bid }) { beer ->
            ResultItem(
                beer = beer,
                onClick = { onClick(beer.bid) })
        }
    }
}

@Composable
fun ResultItem(
    modifier: Modifier = Modifier,
    beer: Beer,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(PaddingValues(vertical = Dimen.small.dp))
            .clickable { onClick() }
    ) {
        Row(
            modifier = modifier
                .padding(PaddingValues(vertical = Dimen.small.dp)),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                modifier = Modifier
                    .padding(PaddingValues(vertical = Dimen.small.dp)),
                model = beer.image, contentDescription = "Beer image"
            )
            Column(
                modifier = Modifier.padding(start = Dimen.small.dp)
            ) {
                Text(beer.name)
                Text(beer.brewery.name)
                Text(beer.style)
            }
        }
    }
}

@Composable
fun SearchAppBar(
    modifier: Modifier = Modifier,
    value: String,
    label: String,
    onClearClick: () -> Unit = {},
    onFocusChanged: (FocusState) -> Unit = {},
    onTextChange: (String) -> Unit,
    onSearchClick: () -> Unit,
) {

    TextField(
        modifier = modifier
            .fillMaxWidth()
            .onFocusChanged { onFocusChanged(it) },
        value = value,
        onValueChange = { query ->
            onTextChange(query)
        },

        label = { Text(text = label) },
        textStyle = MaterialTheme.typography.subtitle1,
        singleLine = true,
        trailingIcon = {
            IconButton(onClick = { onClearClick() }) {
                Icon(imageVector = Icons.Filled.Clear, contentDescription = "Clear")
            }
        },
        keyboardActions = KeyboardActions(onDone = { onSearchClick() }),
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done,
            keyboardType = KeyboardType.Text
        )
    )
}