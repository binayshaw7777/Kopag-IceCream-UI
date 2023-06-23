package com.geekymusketeers.kopag_icecream_ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.geekymusketeers.kopag_icecream_ui.R
import com.geekymusketeers.kopag_icecream_ui.ui.theme.LightGray
import com.geekymusketeers.kopag_icecream_ui.ui.theme.RegularFont


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    value: String,
    onValueChange: (String) -> Unit,
    onClearText: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {

    val showClearIcon = remember { mutableStateOf(false) }

    TextField(
        value = value,
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(0.dp, 60.dp, 60.dp, 0.dp))
            .background(Color.Transparent),
        onValueChange = {
            showClearIcon.value = it.isNotEmpty()
            onValueChange(it)
        },
        placeholder = {
            Text(
                text = "Search", style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W500,
                    color = Color.Gray,
                    fontFamily = RegularFont
                )
            )
        },
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            containerColor = LightGray
        ),
        leadingIcon = {
            AppIcon(
                icon = R.drawable.search_icon,
                background = Color.Transparent,
                tint = Color.Gray
            )
        },
        trailingIcon = {
            if (showClearIcon.value) {
                AppIcon(
                    modifier = modifier
                        .clickable {
                            onClearText(true)
                            showClearIcon.value = false
                        },
                    icon = R.drawable.close_icon,
                    background = Color.Transparent,
                    tint = Color.Gray
                )
            }
        },
        singleLine = true,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
    )
}