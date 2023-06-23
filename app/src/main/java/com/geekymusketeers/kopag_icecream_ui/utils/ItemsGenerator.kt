package com.geekymusketeers.kopag_icecream_ui.utils

import com.geekymusketeers.kopag_icecream_ui.model.Items


object ItemsGenerator {

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

}