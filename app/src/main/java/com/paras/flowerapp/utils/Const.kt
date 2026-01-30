package com.paras.flowerapp.utils

import com.paras.flowerapp.R
import com.paras.flowerapp.model.PopularItemsClass

object Const {

    const val KEY_IS_LOGIN = "LoginOrNot"

    val categoryList = listOf(
        R.drawable.ic_chinese_plum_flower,
        R.drawable.ic_flat_flower,
        R.drawable.ic_giftbox,
        R.drawable.ic_wedding_arch
    )

    val popularFlowerList = listOf(
        PopularItemsClass(
            name = "Pink Flower",
            price = "$240",
            icon = R.drawable.popular_flower1
        ), PopularItemsClass(
            name = "Begonia Flower",
            price = "$240",
            icon = R.drawable.popular_flower2
        ), PopularItemsClass(
            name = "Red Flower",
            price = "$240",
            icon = R.drawable.popular_flower3
        ), PopularItemsClass(
            name = "Pink Flower",
            price = "$240",
            icon = R.drawable.popular_flower4
        ), PopularItemsClass(
            name = "Pink Flower",
            price = "$240",
            icon = R.drawable.popular_flower5
        ), PopularItemsClass(
            name = "Yellow Flower",
            price = "$240",
            icon = R.drawable.popular_flower6
        ), PopularItemsClass(
            name = "Red Flower",
            price = "$240",
            icon = R.drawable.popular_flower7
        )
    )

}