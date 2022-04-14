package com.gcaguilar.untappd.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.ImeOptions
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.gcaguilar.untappd.domain.Beer

@Composable
fun SearchScreen(navController: NavController, searchViewModel: SearchViewModel) {
    val searchState = searchViewModel.state.collectAsState()

    Scaffold(topBar = {
        SearchAppBar(
            text = searchState.value.searchText,
            hint = searchState.value.hint,
            onTextChange = { searchViewModel.updateTextSearch(it) },
            onSearchClick = { searchViewModel.search() }
        )
    }) {
        ResultList(searchState.value.beers)
    }
}

@Composable
fun ResultList(beersResult: List<Beer>) {
    LazyColumn { items(beersResult) { beer -> ResultItem(beer = beer, onClick = {}) } }
}

@Composable
fun ResultItem(
    beer: Beer,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.clickable { onClick() }
    ) {
        Column {
            AsyncImage(model = beer.image, contentDescription = "Beer image")
            Text(beer.name)
            Text(beer.brewery.name)
        }
    }
}

@Composable
fun SearchAppBar(
    text: String,
    hint: String,
    onTextChange: (String) -> Unit,
    onSearchClick: () -> Unit,
) {
    Surface(
        modifier = Modifier
            .wrapContentWidth()
            .fillMaxWidth(),
        elevation = AppBarDefaults.TopAppBarElevation,
        color = MaterialTheme.colors.primary
    ) {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = text,
            onValueChange = {
                onTextChange(it)
            },
            placeholder = {
                Text(hint)
            },
            leadingIcon = {
                IconButton(modifier = Modifier.alpha(ContentAlpha.medium),
                    onClick = { onSearchClick() }) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search button",
                        tint = Color.White
                    )
                }
            },
            trailingIcon = {
                IconButton(onClick = {
                    if (text.isNotEmpty()) {
                        onTextChange("")
                    }
                }) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Clear button",
                        tint = Color.White
                    )
                }
            }, singleLine = true, colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
                cursorColor = Color.White.copy(alpha = ContentAlpha.medium)
            ),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = { onSearchClick() }
            )
        )
    }
}