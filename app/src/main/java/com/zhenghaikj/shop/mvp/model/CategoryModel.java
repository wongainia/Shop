package com.zhenghaikj.shop.mvp.model;

import com.blankj.utilcode.util.TimeUtils;
import com.zhenghaikj.shop.api.ApiRetrofit;
import com.zhenghaikj.shop.entity.Category;
import com.zhenghaikj.shop.mvp.contract.CategoryContract;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class CategoryModel implements CategoryContract.Model {

    private Map<String, String> map;
    private String sign;

    @Override
    public Observable<Category> GetCategories() {
        map = new HashMap<>();
        map.put("app_key","himalltest");
        map.put("timestamp",TimeUtils.getNowString(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")));
        sign = ApiRetrofit.SignTopRequest(map);
        return ApiRetrofit.getDefault().GetCategories("himalltest", TimeUtils.getNowString(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")),sign)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }
}
