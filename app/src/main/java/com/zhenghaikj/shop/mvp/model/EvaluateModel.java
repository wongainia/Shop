package com.zhenghaikj.shop.mvp.model;

import com.blankj.utilcode.util.TimeUtils;
import com.zhenghaikj.shop.api.ApiRetrofit;
import com.zhenghaikj.shop.entity.EvaluatePhotoEntity;
import com.zhenghaikj.shop.entity.EvaluateResult;
import com.zhenghaikj.shop.entity.PostPostAddComment;
import com.zhenghaikj.shop.mvp.contract.AllCommentsContract;
import com.zhenghaikj.shop.mvp.contract.EvaluateContract;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class EvaluateModel implements EvaluateContract.Model {

    private Map<String, String> map;
    private String sign;
    private String timestamp;


    @Override
    public Observable<EvaluateResult> GetComment(String orderId, String userkey) {
        map = new HashMap<>();
        map.put("orderid",orderId);
        map.put("userkey",userkey);
        map.put("app_key","himalltest");
        timestamp=TimeUtils.getNowString(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        map.put("timestamp",timestamp);
        sign = ApiRetrofit.SignTopRequest(map);
        return ApiRetrofit.getDefault().GetComment(orderId,userkey,"himalltest", timestamp,sign)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<EvaluatePhotoEntity> UploadPicEvaluate(String picStr) {
        map = new HashMap<>();
        map.put("picstr",picStr);
        map.put("app_key","himalltest");
        timestamp=TimeUtils.getNowString(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        map.put("timestamp",timestamp);
        sign = ApiRetrofit.SignTopRequest(map);
        return ApiRetrofit.getDefault().UploadPicEvaluate(picStr,"himalltest", timestamp,sign)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<PostPostAddComment> PostAddComment(String userkey, String jsonstr) {
        map = new HashMap<>();
        map.put("userkey",userkey);
        map.put("jsonstr",jsonstr);
        map.put("app_key","himalltest");
        timestamp=TimeUtils.getNowString(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        map.put("timestamp",timestamp);
        sign = ApiRetrofit.SignTopRequest(map);

        return ApiRetrofit.getDefault().PostAddComment(userkey,jsonstr,"himalltest",timestamp,sign)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());

    }
}
