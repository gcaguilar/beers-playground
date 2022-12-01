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
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.gcaguilar.common_ui.theme.Dimen
import com.gcaguilar.common_ui.ui.CheckBoxWithText
import com.gcaguilar.common_ui.ui.SearchAppBar

@Composable
fun LongListFilter(
    itemsToFilterList: List<ItemToFilter>,
    allItemsSelected: Boolean,
    onCheckedChange: (Int) -> Unit,
    onAllChecksChange: () -> Unit,
    onTextChange: (String) -> Unit,
    onSearchClick: () -> Unit,
    onClearClick: () -> Unit,
    onActionBackClick: () -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(Dimen.medium.dp),
    ) {
        SearchAppBar(
            value = "",
            label = "",
            filtered = false,
            onTextChange = { text -> onTextChange(text) },
            onSearchClick = { onSearchClick() },
            onActionBackClick = { onActionBackClick() },
            onClearClick = { onClearClick() }
        )
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                modifier = Modifier.weight(1f),
                text = "Seleccionar todo",
                style = MaterialTheme.typography.headlineSmall
            )
            Switch(checked = allItemsSelected, onCheckedChange = { onAllChecksChange() })
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

data class ItemToFilter(
    val id: Int,
    val name: String,
    val checked: Boolean
)
