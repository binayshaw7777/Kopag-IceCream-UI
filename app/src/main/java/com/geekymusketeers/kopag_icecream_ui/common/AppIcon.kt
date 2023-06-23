package com.geekymusketeers.kopag_icecream_ui.common

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource

@Composable
fun AppIcon(
    modifier: Modifier = Modifier,
    @DrawableRes icon: Int,
    tint: Color = Color.Unspecified,
    background: Color = Color.White,
    onClick:()->Unit  = {}
) {
    Box(
        modifier = modifier
            .background(background)
            .clickable { onClick() }
        ,
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription ="",
            tint = tint,
            modifier = modifier
        )
    }
}