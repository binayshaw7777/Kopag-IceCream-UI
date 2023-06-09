package com.geekymusketeers.kopag_icecream_ui.ui.screens

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.geekymusketeers.kopag_icecream_ui.R
import com.geekymusketeers.kopag_icecream_ui.common.RoundedIconButton
import com.geekymusketeers.kopag_icecream_ui.model.Chips
import com.geekymusketeers.kopag_icecream_ui.model.Items
import com.geekymusketeers.kopag_icecream_ui.ui.screens.ui.theme.KopagIcecreamUITheme
import com.geekymusketeers.kopag_icecream_ui.ui.theme.MidBlue
import com.geekymusketeers.kopag_icecream_ui.ui.theme.RegularFont
import com.geekymusketeers.kopag_icecream_ui.ui.theme.SemiBoldFont

class PreviewScreen : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KopagIcecreamUITheme {
                // A surface container using the 'background' color from the theme
                PreviewProduct()
            }
        }
    }
}


fun Context.findActivity(): Activity? = when (this) {
    is Activity -> this
    is ContextWrapper -> baseContext.findActivity()
    else -> null
}

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun PreviewProduct() {

    val mContext = LocalContext.current
    var selectedCupSize by remember { mutableStateOf("Small") }
    var selectedIceLevel by remember { mutableStateOf("30%") }
    var selectedSugarLevel by remember { mutableStateOf("30%") }

    val activity = mContext.findActivity()
    val intent = activity?.intent

    val selectedItem = intent?.getParcelableExtra<Items>("currentItem")

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        color = Color.White
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Image(
                    painter = if (selectedItem != null)
                        rememberAsyncImagePainter(selectedItem.image)
                    else painterResource(
                        id = R.drawable.butterscotch_preview
                    ),
                    contentDescription = selectedItem?.name,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp),
                    contentScale = ContentScale.Crop
                )
                RoundedIconButton(
                    modifier = Modifier
                        .padding(24.dp)
                        .clip(CircleShape)
                        .size(50.dp),
                    asset = R.drawable.back_icon,
                    iconSize = 10,
                    background = Color.White,
                    onClick = {
                        activity?.finish()
                    }
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {
                Text(
                    text = selectedItem?.name ?: "No data",
                    fontFamily = SemiBoldFont,
                    fontSize = 20.sp
                )
                Spacer(modifier = Modifier.height(20.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Rs: ${selectedItem?.price}",
                        fontFamily = SemiBoldFont,
                        color = MidBlue
                    )
                    Text(
                        text = "Stock: ${selectedItem?.stock}",
                        fontFamily = RegularFont,
                        color = Color.LightGray
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = "Cup Size",
                    fontFamily = SemiBoldFont,
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.height(20.dp))
                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    items(getAllCupSizes()) {
                        Chips(title = it, selected = it == selectedCupSize) { data ->
                            selectedCupSize = data
                        }
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = "Ice Level",
                    fontFamily = SemiBoldFont,
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.height(20.dp))
                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    items(getAllLevels()) {
                        Chips(title = it, selected = it == selectedIceLevel) { data ->
                            selectedIceLevel = data
                        }
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = "Sugar Level",
                    fontFamily = SemiBoldFont,
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.height(20.dp))
                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    items(getAllLevels()) {
                        Chips(title = it, selected = it == selectedSugarLevel) { data ->
                            selectedSugarLevel = data
                        }
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = "Note",
                    fontFamily = SemiBoldFont,
                    fontSize = 16.sp
                )
            }
        }
    }
}

fun getAllCupSizes(): List<String> {
    return listOf(
        "Small",
        "Medium",
        "Large"
    )
}

fun getAllLevels(): List<String> {
    return listOf(
        "30%",
        "60%",
        "100%"
    )
}


@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Preview(showBackground = true)
@Composable
fun PreviewProductPreview() {
    KopagIcecreamUITheme {
        PreviewProduct()
    }
}