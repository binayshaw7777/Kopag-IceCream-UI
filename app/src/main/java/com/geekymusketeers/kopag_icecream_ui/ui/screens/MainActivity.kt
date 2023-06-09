package com.geekymusketeers.kopag_icecream_ui.ui.screens

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.geekymusketeers.kopag_icecream_ui.R
import com.geekymusketeers.kopag_icecream_ui.common.AppBar
import com.geekymusketeers.kopag_icecream_ui.common.SearchBar
import com.geekymusketeers.kopag_icecream_ui.model.Chips
import com.geekymusketeers.kopag_icecream_ui.model.Items
import com.geekymusketeers.kopag_icecream_ui.ui.theme.KopagIcecreamUITheme
import com.geekymusketeers.kopag_icecream_ui.ui.theme.MidBlue
import com.geekymusketeers.kopag_icecream_ui.ui.theme.Purple80
import com.geekymusketeers.kopag_icecream_ui.ui.theme.RegularFont
import com.geekymusketeers.kopag_icecream_ui.ui.theme.SemiBoldFont
import com.geekymusketeers.kopag_icecream_ui.ui.theme.UltraLightGray
import com.geekymusketeers.kopag_icecream_ui.utils.ItemsGenerator
import com.geekymusketeers.kopag_icecream_ui.utils.Logger

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KopagIcecreamUITheme {
                // A surface container using the 'background' color from the theme
                MainPreview()
            }
        }
    }
}


@Composable
fun MainPreview() {

    var search by remember {
        mutableStateOf("")
    }

    val category = ItemsGenerator.getAllCategories()
    var selected by remember { mutableStateOf(category[0].name) }
    val allItems = ItemsGenerator.getAllItems()
    val filteredItems = remember { mutableStateListOf<Items>() }
    filteredItems.addAll(allItems)

    val mContext = LocalContext.current
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(White),
        color = White,
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(White)
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AppBar(
                title = "Kopag",
                trailingIcon = R.drawable.shopping_bag_icon,
                trailingIconOnClick = {
                    Toast.makeText(mContext, "Cart clicked", Toast.LENGTH_SHORT).show()
                })
            Spacer(modifier = Modifier.height(16.dp))
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(60.dp))
                    .background(Purple80)
            ) {
                SearchBar(value = search, onValueChange = { search = it }, { search = "" })
            }
            LazyRow(
                modifier = Modifier.padding(top = 20.dp)
            ) {
                items(category) {
                    Chips(title = it.name, selected = it.name == selected) { data ->
                        selected = data
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Menu",
                    style = TextStyle(
                        color = Black,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = RegularFont,
                    )
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            ItemList(items = filteredItems) {
                val intent = Intent(mContext, PreviewScreen::class.java)
                intent.putExtra("currentItem", it)
                mContext.startActivity(intent)
            }
        }

        // Update the filteredItems list whenever the search query changes
        LaunchedEffect(search, selected) {
            filteredItems.clear()
            if (search.isEmpty().not()) {
                allItems.forEach { item ->
                    if (item.name.contains(search, ignoreCase = true)) {
                        filteredItems.add(item)
                    }
                }
            } else if ((selected == "🍘 All").not()) {
                allItems.forEach { item ->
                    Logger.debugLog("Item Category: ${item.category} and ${selected.substring(2)}")
                    if (item.category == selected.substring(3)) {
                        filteredItems.add(item)
                    }
                }
            } else filteredItems.addAll(allItems)
        }
    }
}


@Composable
fun ItemList(items: List<Items>, gotoPreviewScreen: (Items) -> Unit) {

    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 140.dp),
    ) {
        itemsIndexed(items) { _, item ->

            Row(modifier = Modifier
                .padding(5.dp)
                .clickable { gotoPreviewScreen(item) }) {
                Card(
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = Transparent,
                    ),

                    ) {
                    Column(
                        modifier = Modifier
                            .padding(0.dp)
                            .border(
                                1.dp,
                                UltraLightGray,
                                RoundedCornerShape(8.dp)
                            )
                    ) {
                        Image(
                            painter = rememberAsyncImagePainter(item.image),
                            contentDescription = item.name,
                            modifier = Modifier
                                .fillMaxWidth()
                                .aspectRatio(1f)
                                .height(200.dp)
                                .width(200.dp),
                            contentScale = ContentScale.Crop
                        )
                        Text(
                            text = item.name,
                            fontFamily = SemiBoldFont,
                            fontSize = 16.sp,
                            modifier = Modifier
                                .padding(start = 10.dp, end = 10.dp, top = 10.dp)
                        )
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "Rs. ${item.price}",
                                fontSize = 12.sp,
                                fontFamily = SemiBoldFont,
                                color = MidBlue
                            )
                            Text(
                                text = "Stock : ${item.stock}",
                                fontSize = 12.sp,
                                fontFamily = RegularFont
                            )
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun Main() {
    KopagIcecreamUITheme {
        MainPreview()
    }
}