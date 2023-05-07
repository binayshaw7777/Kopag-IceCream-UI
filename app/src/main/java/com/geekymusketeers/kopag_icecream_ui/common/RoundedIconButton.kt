package com.geekymusketeers.kopag_icecream_ui.common

import android.nfc.Tag
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun RoundedIconButton(
    asset: Int,
    iconSize: Int = 24,
    parentModifier: Modifier = Modifier,
    background: Color = Color.Transparent,
    onClick: () -> Unit = {}
) {
    Column(
        modifier = parentModifier
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