package com.geekymusketeers.kopag_icecream_ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.geekymusketeers.kopag_icecream_ui.common.AppBar
import com.geekymusketeers.kopag_icecream_ui.common.AppIcon
import com.geekymusketeers.kopag_icecream_ui.model.Categories
import com.geekymusketeers.kopag_icecream_ui.model.Chips
import com.geekymusketeers.kopag_icecream_ui.model.Items
import com.geekymusketeers.kopag_icecream_ui.ui.theme.KopagIcecreamUITheme
import com.geekymusketeers.kopag_icecream_ui.ui.theme.MidBlue
import com.geekymusketeers.kopag_icecream_ui.ui.theme.Purple80
import com.geekymusketeers.kopag_icecream_ui.ui.theme.RegularFont
import com.geekymusketeers.kopag_icecream_ui.ui.theme.SemiBoldFont
import com.geekymusketeers.kopag_icecream_ui.ui.theme.UltraLightGray

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

    val category = getAllCategories()
    var selected by remember { mutableStateOf("All") }

    val mContext = LocalContext.current
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(White),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AppBar(
                title = "Kopag",
                trailingIcon = Icons.Filled.ShoppingCart,
                trailingIconOnClick = {
                    Toast.makeText(mContext, "Cart clicked", Toast.LENGTH_SHORT).show()
                })
            Spacer(modifier = Modifier.height(16.dp))
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(60.dp))
                    .background(Purple80)
            ) {
                SearchBar(value = search, onValueChange = { search = it })
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
            ItemList(items = getAllItems())
        }
    }
}

@Composable
fun ItemList(items: List<Items>) {

    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 140.dp),
    ) {
        itemsIndexed(items) { _, item ->
            Row(modifier = Modifier.padding(5.dp)) {
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


fun getAllCategories(): List<Categories> {
    return listOf(
        Categories(0, "🍘 All"),
        Categories(1, "🍦 Ice Cream"),
        Categories(2, "🍰 Cakes"),
        Categories(3, "🍪 Cookies"),
        Categories(4, "🧁 Cupcakes"),
        Categories(5, "🍩 Doughnuts"),
        Categories(6, "🥧 Pies"),
        Categories(7, "🍮 Puddings"),
        Categories(8, "🍡 Sweets"),
        Categories(9, "🧇 Waffles")
    )
}

fun getAllItems(): List<Items> {
    return listOf(
        Items(
            0,
            "Butter Scotch",
            200,
            "https://saltandbaker.com/wp-content/uploads/2022/05/Butterscotch-Ice-Cream-8.jpg",
            "Ice Cream",
            20
        ),
        Items(
            1,
            "Chocolate",
            250,
            "https://restaurantclicks.com/wp-content/uploads/2023/02/Best-Chocolate-Ice-Cream-1024x576.jpg",
            "Cake",
            15
        ),
        Items(
            2,
            "Vanilla",
            150,
            "https://desertfoodfeed.com/wp-content/uploads/2021/05/Vanilla-Ice-Cream-Recipe-800x620.jpg",
            "Cake",
            10
        ),
        Items(
            3,
            "Strawberry",
            180,
            "https://driscolls.imgix.net/-/media/assets/recipes/balsamic-roasted-strawberry-ice-cream-recipe.ashx?w=926&h=695&fit=crop&crop=entropy&q=50&auto=format,compress&cs=srgb&ixlib=imgixjs-3.4.2",
            "Ice Cream",
            18
        ),
        Items(
            4,
            "Blueberry Cheesecake",
            300,
            "https://www.chelseasmessyapron.com/wp-content/uploads/2014/01/Mini-Lemon-Blueberry-Cheesecakes-6.jpg",
            "Cake",
            20
        ),
        Items(
            5,
            "Mint Chocolate Chip",
            220,
            "https://www.browneyedbaker.com/wp-content/uploads/2020/06/Mint-chocolate-chip-ice-cream-14-1200-1024x1536.jpg",
            "Ice Cream",
            22
        ),
        Items(
            6,
            "Red Velvet",
            280,
            "https://upload.wikimedia.org/wikipedia/commons/thumb/b/b2/Red_Velvet_Cake_Waldorf_Astoria.jpg/1024px-Red_Velvet_Cake_Waldorf_Astoria.jpg",
            "Cake",
            25
        ),
        Items(
            7,
            "Coffee",
            190,
            "https://media.30seconds.com/tip/lg/Coffee-Ice-Cream-Well-All-Scream-on-National-Coffee-Ice-Cr-14687-1970c95ae0-1504645088.jpg",
            "Ice Cream",
            19
        ),
        Items(
            8,
            "Carrot Cake",
            260,
            "https://static01.nyt.com/images/2020/11/01/dining/Carrot-Cake-textless/Carrot-Cake-textless-master768.jpg?w=1280&q=75",
            "Cake",
            30
        ),
        Items(
            9,
            "Pistachio",
            210,
            "https://domesticgothess.com/wp-content/uploads/2021/06/vegan-pistachio-ice-cream.jpg",
            "Ice Cream",
            21
        )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {

    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(60.dp))
            .background(Transparent)
    ) {
        TextField(
            value = value,
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(0.dp, 60.dp, 60.dp, 0.dp))
                .background(Transparent),
            onValueChange = { onValueChange(it) },
            placeholder = {
                Text(
                    text = "Search", style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.W500,
                        color = Gray,
                        fontFamily = RegularFont
                    )
                )
            },
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Transparent,
                unfocusedIndicatorColor = Transparent
            ),
            leadingIcon = {
                AppIcon(icon = R.drawable.search, background = Transparent, tint = Black)
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
        )
    }
}


@Preview(showBackground = true)
@Composable
fun Main() {
    KopagIcecreamUITheme {
        MainPreview()
    }
}