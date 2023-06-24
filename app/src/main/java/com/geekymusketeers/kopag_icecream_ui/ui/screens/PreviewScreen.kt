package com.geekymusketeers.kopag_icecream_ui.ui.screens

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.geekymusketeers.kopag_icecream_ui.R
import com.geekymusketeers.kopag_icecream_ui.common.BottomBar
import com.geekymusketeers.kopag_icecream_ui.common.Counter
import com.geekymusketeers.kopag_icecream_ui.common.RoundedIconButton
import com.geekymusketeers.kopag_icecream_ui.model.Chips
import com.geekymusketeers.kopag_icecream_ui.model.Items
import com.geekymusketeers.kopag_icecream_ui.ui.theme.KopagIcecreamUITheme
import com.geekymusketeers.kopag_icecream_ui.ui.theme.LightGray
import com.geekymusketeers.kopag_icecream_ui.ui.theme.MidBlue
import com.geekymusketeers.kopag_icecream_ui.ui.theme.RegularFont
import com.geekymusketeers.kopag_icecream_ui.ui.theme.SemiBoldFont
import com.geekymusketeers.kopag_icecream_ui.utils.ItemsGenerator
import com.geekymusketeers.kopag_icecream_ui.utils.Logger

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

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun PreviewProduct() {

    val mContext = LocalContext.current
    var selectedCupSize by remember { mutableStateOf("Small") }
    var selectedIceLevel by remember { mutableStateOf("30%") }
    var selectedSugarLevel by remember { mutableStateOf("30%") }
    var noteTextFieldValue by remember { mutableStateOf("Add note here") }
    var numberOfOrder by remember {
        mutableStateOf(2)
    }

    val activity = mContext.findActivity()
    val intent = activity?.intent

    val selectedItem = intent?.getParcelableExtra<Items>("currentItem")


    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(White),
        color = White
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
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
                        .size(40.dp),
                    asset = R.drawable.back_icon,
                    iconSize = 10,
                    background = White,
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
                    text = selectedItem?.name ?: stringResource(R.string.no_data),
                    fontFamily = SemiBoldFont,
                    fontSize = 18.sp
                )
                Spacer(modifier = Modifier.height(10.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Bottom
                ) {
                    Text(
                        text = "Rs: ${selectedItem?.price}",
                        fontFamily = SemiBoldFont,
                        color = MidBlue,
                        fontSize = 30.sp
                    )
                    Text(
                        text = "Stock: ${selectedItem?.stock}",
                        fontFamily = RegularFont,
                        color = Color.Gray
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = stringResource(R.string.cup_size),
                    fontFamily = SemiBoldFont,
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.height(20.dp))
                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    items(ItemsGenerator.getAllCupSizes()) {
                        Chips(title = it, selected = it == selectedCupSize) { data ->
                            selectedCupSize = data
                        }
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = stringResource(R.string.ice_level),
                    fontFamily = SemiBoldFont,
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.height(20.dp))
                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    items(ItemsGenerator.getAllLevels()) {
                        Chips(title = it, selected = it == selectedIceLevel) { data ->
                            selectedIceLevel = data
                        }
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = stringResource(R.string.sugar_level),
                    fontFamily = SemiBoldFont,
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.height(20.dp))
                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    items(ItemsGenerator.getAllLevels()) {
                        Chips(title = it, selected = it == selectedSugarLevel) { data ->
                            selectedSugarLevel = data
                        }
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = stringResource(R.string.note),
                    fontFamily = SemiBoldFont,
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.height(10.dp))
                TextField(
                    modifier = Modifier
                        .height(100.dp)
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(15.dp))
                        .background(Color.LightGray),
                    colors = TextFieldDefaults.textFieldColors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        containerColor = LightGray
                    ),
                    value = noteTextFieldValue,
                    onValueChange = { noteTextFieldValue = it },
                )
                Spacer(modifier = Modifier.height(20.dp))
                BottomBar(
                    modifier = Modifier.fillMaxWidth(),
                    itemPrice = (selectedItem?.price ?: 0) * numberOfOrder,
                    initialValue = numberOfOrder,
                    maxLimit = selectedItem?.stock ?: 2,
                    onValueChanged = { numberOfOrder = it }
                )
            }
        }
    }
}



@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Preview(showBackground = true)
@Composable
fun PreviewProductPreview() {
    KopagIcecreamUITheme {
        PreviewProduct()
    }
}
   