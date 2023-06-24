package com.geekymusketeers.kopag_icecream_ui.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.geekymusketeers.kopag_icecream_ui.ui.theme.MidBlue
import com.geekymusketeers.kopag_icecream_ui.utils.Logger

@Composable
fun BottomBar(
    modifier: Modifier,
    itemPrice: Int,
    initialValue: Int,
    maxLimit: Int,
    onValueChanged: (Int) -> Unit
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Counter(initialCount = initialValue, maxLimit = maxLimit, updatedCount = { onValueChanged(it) } )
        Row {
            Button(
                onClick = { Logger.debugLog("Added to card") },
                colors = ButtonDefaults.textButtonColors(
                    containerColor = MidBlue
                )
            ) {
                Text(text = "Add to card: Rs.$itemPrice", color = Color.White)
            }
        }
    }
}