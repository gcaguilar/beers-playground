package com.gcaguilar.common_ui.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.gcaguilar.common_ui.theme.Dimen

@Composable
fun CheckBoxWithText(
    id: Int,
    name: String,
    checked: Boolean,
    onCheckedChange: (Int) -> Unit
) {
    Row(
        modifier = Modifier
            .padding(Dimen.medium.dp)
            .fillMaxWidth()
            .clickable { onCheckedChange(id) },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.weight(1f),
            text = name,
            style = MaterialTheme.typography.headlineSmall
        )
        Checkbox(
            checked = checked,
            onCheckedChange = { onCheckedChange(id) })
    }
}