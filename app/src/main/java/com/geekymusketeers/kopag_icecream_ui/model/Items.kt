package com.geekymusketeers.kopag_icecream_ui.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Items(
    val id: Int,
    val name: String,
    val price: Int,
    val image: String,
    val category: String,
    val stock: Int
) : Parcelable
