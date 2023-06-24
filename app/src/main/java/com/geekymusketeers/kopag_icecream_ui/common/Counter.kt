package com.geekymusketeers.kopag_icecream_ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.geekymusketeers.kopag_icecream_ui.R
import com.geekymusketeers.kopag_icecream_ui.ui.theme.MidBlue

@Composable
fun Counter(initialCount: Int, maxLimit: Int, updatedCount: (Int) -> Unit) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Image(
            modifier = Modifier
                .clickable { if (initialCount > 0) updatedCount(initialCount - 1) }
                .size(20.dp),
            painter = painterResource(id = R.drawable.subtract_icon),
            contentDescription = stringResource(R.string.decrement)
        )
        Spacer(modifier = Modifier.width(20.dp))
        Text(text = initialCount.toString(), fontSize = 20.sp, fontWeight = FontWeight.Black)
        Spacer(modifier = Modifier.width(20.dp))
        Image(
            modifier = Modifier
                .clickable {
                    if (initialCount < maxLimit) updatedCount(
                        initialCount + 1
                    )
                }
                .size(20.dp),
            painter = painterResource(id = R.drawable.add_icon),
            contentDescription = stringResource(R.string.increment),
            colorFilter = ColorFilter.tint(MidBlue)
        )
    }
}