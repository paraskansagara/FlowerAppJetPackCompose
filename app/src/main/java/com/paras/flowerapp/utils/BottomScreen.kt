package com.paras.flowerapp.utils

import com.paras.flowerapp.R


sealed class BottomScreen(
val route: String,
val title: String,
val icon: Int
) {
    object Home : BottomScreen("home", "Home", R.drawable.app_logo)
    object Cart : BottomScreen("cart", "Cart", R.drawable.ic_shopping_cart)
    object Settings : BottomScreen("settings", "Settings", R.drawable.app_logo)
}