package com.geekymusketeers.kopag_icecream_ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun RoundedIconButton(
    modifier: Modifier = Modifier,
    asset: Int,
    iconSize: Int = 24,
    background: Color = Color.Transparent,
    onClick: () -> Unit = {}
) {
    Column(
        modifier = modifier
            .background(background)
            .clickable(onClick = {
                // Action to perform
                onClick()
            }),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = asset),
            contentDescription = "",
            modifier = Modifier
                .padding(iconSize.dp),
        )
    }
}