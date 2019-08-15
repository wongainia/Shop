package com.zhenghaikj.shop.kt.net.exception

import java.lang.Exception

/**
Data:2019/8/14
Time:15:03
author:ying
 **/
class ApiException(var code:Int,var msg:String?):Exception() {
}