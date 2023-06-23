package com.geekymusketeers.kopag_icecream_ui.model

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Chips(
    title: String,
    selected: Boolean,
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit,
) {

    TextButton(
        onClick = { onValueChange(title) },
        shape = RoundedCornerShape(50.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (selected) Black else com.geekymusketeers.kopag_icecream_ui.ui.theme.LightGray
        ),
        modifier = modifier.padding(end = 10.dp)
    ) {
        Text(
            text = title, style = TextStyle(
                color = if (selected) Color.White else Black,
                fontWeight = FontWeight.W300,
                fontSize = 15.sp
            ),
            modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
        )
    }

}