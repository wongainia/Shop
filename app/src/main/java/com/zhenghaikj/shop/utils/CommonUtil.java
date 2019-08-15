package com.zhenghaikj.shop.utils;

import android.content.Context;

/**
 * Data:2019/8/15
 * Time:10:25
 * author:ying
 **/
public class CommonUtil {

    public static int dp2px(Context context, float dpValue) {
        if (null == context) {
            return 0;
        }
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }


    public static int getScreenWidth(Context context) {
        if (null == context) {
            return 0;
        }
        return context.getResources().getDisplayMetrics().widthPixels;
    }
}
