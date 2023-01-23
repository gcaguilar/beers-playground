package com.gcaguilar.beer.search_beer.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons.Filled
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import com.gcaguilar.beer.search_beer.data.model.ItemToFilter
import com.gcaguilar.common_ui.theme.Dimen
import com.gcaguilar.common_ui.ui.CheckBoxWithText
import com.gcaguilar.common_ui.ui.SearchAppBar

@Composable
fun LongListFilter(
    searchItem: String,
    itemsToFilterList: List<ItemToFilter>,
    allItemsSelected: Boolean,
    onCheckedChange: (Int) -> Unit,
    onAllChecksChange: (selected: Boolean) -> Unit,
    onTextChange: (String) -> Unit,
    onSearchClick: () -> Unit,
    onClearClick: () -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(Dimen.medium.dp),
    ) {
        TextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = searchItem,
            onValueChange = { query ->
                onTextChange(query)
            },
            colors = TextFieldDefaults.textFieldColors(
                containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.3f)
            ),
            label = { Text(text = "") },
            textStyle = MaterialTheme.typography.bodyMedium,
            singleLine = true,
            leadingIcon = {
                IconButton(onClick = { onSearchClick() }) {
                    Icon(imageVector = Filled.Search, contentDescription = "Search")
                }

            },
            trailingIcon = {
                if (searchItem.isNotEmpty()) {
                    IconButton(onClick = { onClearClick() }) {
                        Icon(imageVector = Filled.Clear, contentDescription = "Clear")
                    }
                }
            },
            keyboardActions = KeyboardActions(onSearch = { onSearchClick() }),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search, keyboardType = KeyboardType.Text
            )
        )

        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                modifier = Modifier.weight(1f),
                text = "Seleccionar todo",
                style = MaterialTheme.typography.headlineSmall
            )
            Switch(checked = allItemsSelected, onCheckedChange = { onAllChecksChange(it) })
        }
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(Dimen.medium.dp),
            content = {
                items(
                    itemsToFilterList.size,
                    key = { index -> itemsToFilterList[index].id }) { index ->
                    CheckBoxWithText(
                        id = itemsToFilterList[index].id,
                        name = itemsToFilterList[index].name,
                        checked = itemsToFilterList[index].checked,
                        onCheckedChange = { id -> onCheckedChange(id) })
                }
            })
    }
}

