package com.zhenghaikj.shop.mvp.model;

import com.blankj.utilcode.util.TimeUtils;
import com.zhenghaikj.shop.api.ApiRetrofit;
import com.zhenghaikj.shop.entity.GetStoreSortResult;
import com.zhenghaikj.shop.entity.PostattentionResult;
import com.zhenghaikj.shop.entity.SearchResult;
import com.zhenghaikj.shop.entity.StoreCommodityResult;
import com.zhenghaikj.shop.entity.StoreDetailResult;
import com.zhenghaikj.shop.mvp.contract.SearchDetailShopDetailContract;
import com.zhenghaikj.shop.mvp.contract.StoreDetailContract;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class SearchDetailShopDetailModel implements SearchDetailShopDetailContract.Model {
    private Map<String, String> map;
    private String timestamp;
    private String sign;


    @Override
    public Observable<StoreCommodityResult> GetProductList( String PageSize,
                                                            String pageNo,
                                                            String shopCategoryId,
                                                            String shopId,
                                                            String shopBranchId) {
        map = new HashMap<>();
        map.put("pagesize",PageSize);
        map.put("pageno",pageNo);
        map.put("shopcategoryid",shopCategoryId);
        map.put("shopid",shopId);
        map.put("shopbranchid",shopBranchId);
        map.put("app_key","himalltest");
        timestamp = TimeUtils.getNowString(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        map.put("timestamp", timestamp);
        sign = ApiRetrofit.SignTopRequest(map);
        return ApiRetrofit.getDefault().GetProductList(PageSize,pageNo,shopCategoryId,shopId,shopBranchId,"himalltest",timestamp,sign)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<SearchResult> GetSearchProducts(String keywords, String cid, String b_id, String a_id, String orderKey, String orderType, String pageNo, String pageSize, String sid) {
        map = new HashMap<>();
        map.put("keywords",keywords);
        map.put("cid",cid);
        map.put("b_id",b_id);
        map.put("a_id",a_id);
        map.put("orderkey",orderKey);
        map.put("ordertype",orderType);
        map.put("pageno",pageNo);
        map.put("pagesize",pageSize);
        map.put("sid",sid);

        map.put("app_key","himalltest");
        timestamp=TimeUtils.getNowString(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        map.put("timestamp",timestamp);
        sign = ApiRetrofit.SignTopRequest(map);
        return ApiRetrofit.getDefault().GetSearchProducts(keywords,cid,b_id,a_id,orderKey,orderType,pageNo,pageSize,sid,"himalltest", timestamp,sign)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }
}
