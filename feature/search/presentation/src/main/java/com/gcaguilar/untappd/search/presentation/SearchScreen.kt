@file:OptIn(
    ExperimentalMaterial3Api::class,
    ExperimentalMaterial3Api::class,
    ExperimentalMaterial3Api::class,
    ExperimentalComposeUiApi::class
)

package com.gcaguilar.untappd.search.presentation

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.FilterListOff
import androidx.compose.material.icons.filled.RateReview
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.AssistChip
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.gcaguilar.common_ui.theme.Dimen
import com.gcaguilar.untappd.domain.Beer
import com.gcaguilar.untappd.domain.Brewery
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

@OptIn(ExperimentalComposeUiApi::class)
@ExperimentalMaterial3Api
@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    viewModel: SearchViewModel = hiltViewModel(),
    bottomNavigation: @Composable () -> Unit
) {
    val state = viewModel.state.collectAsState()
    val (dialogOpen, showDialog) = remember { mutableStateOf(false) }
    val listState = rememberLazyListState()
    val showFabButton by remember {
        derivedStateOf {
            !listState.isScrollInProgress
        }
    }

    Scaffold(
        modifier = modifier,
        floatingActionButton = {
            FilterFloatingButton(
                showFabButton = showFabButton,
                showDialog = { showDialog(it) },
                filtered = false
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier.padding(
                    top = Dimen.medium.dp,
                    start = Dimen.medium.dp,
                    end = Dimen.medium.dp,
                    bottom = paddingValues.calculateBottomPadding()
                )
            ) {
                SearchAppBar(
                    modifier = Modifier.fillMaxWidth(),
                    value = state.value.searchText,
                    label = state.value.hint,
                    onClearClick = { viewModel.clearSearchText() },
                    onTextChange = { text -> viewModel.updateTextSearch(text) },
                    onFocusChanged = {},
                    onSearchClick = { viewModel.search() }
                )
                Spacer(modifier = Modifier.height(Dimen.medium.dp))
                ResultList(
                    state = listState,
                    modifier = Modifier,
                    beersResult = state.value.beers,
                    onClick = { id ->
                        viewModel.onClickedBeer(id)
                    }
                )
                if (dialogOpen) {
                    FilterDialog(showDialog = showDialog)
                }
            }
        },
        bottomBar = bottomNavigation
    )
}

@Composable
fun FilterFloatingButton(
    showFabButton: Boolean,
    showDialog: (Boolean) -> Unit,
    filtered: Boolean
) {
    if (showFabButton) {
        FloatingActionButton(onClick = {
            showDialog(true)
        }) {
            val icon = if (filtered) Icons.Default.FilterListOff else Icons.Default.FilterList
            Icon(icon, contentDescription = "filter")
        }
    }
}

@Composable
fun ResultList(
    beersResult: List<Beer>,
    onClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
    state: LazyListState
) {
    LazyColumn(
        state = state,
        verticalArrangement = Arrangement.spacedBy(Dimen.small.dp),
        modifier = modifier,
    ) {
        items(items = beersResult, key = { beer -> beer.bid }) { beer ->
            ResultItem(modifier = modifier, beer = beer, onClick = { onClick(beer.bid) })
        }
    }
}

@Composable
fun ResultItem(
    beer: Beer,
    onClick: () -> Unit,
    modifier: Modifier,
) {
    ElevatedCard(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick() },
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = Dimen.medium.dp,
                    start = Dimen.medium.dp,
                    end = Dimen.medium.dp
                ),
        ) {
            AsyncImage(
                model = beer.image,
                placeholder = painterResource(id = androidx.appcompat.R.drawable.abc_ic_menu_copy_mtrl_am_alpha),
                contentDescription = "Beer image",
                modifier = Modifier.clip(RectangleShape)
            )
            Spacer(modifier = Modifier.width(Dimen.medium.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = beer.name,
                    style = MaterialTheme.typography.titleLarge,
                )
                Text(
                    text = beer.brewery.name,
                    style = MaterialTheme.typography.titleMedium,
                )
                Text(
                    text = beer.style,
                    style = MaterialTheme.typography.titleSmall,
                )
            }
        }
        Row(
            modifier = Modifier
                .padding(
                    top = Dimen.medium.dp,
                    start = Dimen.medium.dp,
                    end = Dimen.medium.dp,
                    bottom = Dimen.medium.dp,
                )
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            IconButton(
                modifier = Modifier.size(Dimen.xl.dp),
                onClick = {}
            ) {
                Icon(
                    imageVector = Icons.Default.Share,
                    contentDescription = "Share"
                )
            }
            Spacer(modifier = Modifier.width(Dimen.xl.dp))
            IconButton(
                modifier = Modifier.size(Dimen.xl.dp),
                onClick = { /*TODO*/ }) {
                Icon(
                    modifier = Modifier.size(Dimen.xl.dp),
                    imageVector = Icons.Default.Favorite,
                    contentDescription = "Share"
                )
            }
            Spacer(modifier = Modifier.width(Dimen.xl.dp))
            IconButton(
                modifier = Modifier.size(Dimen.xl.dp),
                onClick = { /*TODO*/ }) {
                Icon(
                    modifier = Modifier.size(Dimen.xl.dp),
                    imageVector = Icons.Default.RateReview,
                    contentDescription = "Share"
                )k
            }
        }
    }
}

@Preview
@Composable
fun PreviewElevatedCard() {
    ResultItem(
        modifier = Modifier,
        beer = Beer(
            bid = 1,
            name = "Guinness",
            style = "",
            brewery = Brewery(1, "Guinness SL"),
            image = "",
            points = 1.0,

            ),
        onClick = {}
    )
}

@ExperimentalMaterial3Api
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
            .clip(RoundedCornerShape(16.dp))
            .onFocusChanged { onFocusChanged(it) },
        value = value,
        onValueChange = { query ->
            onTextChange(query)
        },

        label = { Text(text = label) },
        textStyle = MaterialTheme.typography.bodyMedium,
        singleLine = true,
        leadingIcon = {
            IconButton(onClick = { onSearchClick() }) {
                Icon(imageVector = Icons.Default.Search, contentDescription = "Search")
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
}

@ExperimentalMaterial3Api
@ExperimentalComposeUiApi
@Composable
fun FilterDialog(
    showDialog: (Boolean) -> Unit,
) {
    Dialog(
        properties = DialogProperties(usePlatformDefaultWidth = false),
        onDismissRequest = { showDialog(false) }
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            AssistChip(label = {
                Text(
                    text = "IPA", maxLines = 1, overflow = TextOverflow.Ellipsis
                )
            }, onClick = {})
            Spacer(modifier = Modifier.width(Dimen.small.dp))
            AssistChip(label = {
                Text(
                    text = "APA", maxLines = 1, overflow = TextOverflow.Ellipsis
                )
            }, onClick = {})
        }
    }
}
