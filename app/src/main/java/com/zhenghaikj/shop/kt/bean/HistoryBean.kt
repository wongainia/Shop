package com.zhenghaikj.shop.kt.bean
import java.io.Serializable
/**
Data:2019/8/14
Time:16:06
author:ying
 **/

data class HistoryBean(
    var Product: List<Product>,
    var Success: String
):Serializable

data class Product(
    var BrowseTime: String,
    var ImagePath: String,
    var ProductId: Int,
    var ProductName: String,
    var ProductPrice: Double,
    var UserId: Int
): Serializable