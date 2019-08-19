package com.zhenghaikj.shop.kt.bean

/**
Data:2019/8/14
Time:16:06
author:ying
 **/

data class HistoryBean(
    val Product: MutableList<Product>,
    val Success: String
)

data class Product(
    val BrowseTime: String,
    val ImagePath: String,
    val ProductId: Int,
    val ProductName: String,
    val ProductPrice: Double,
    val UserId: Int
)