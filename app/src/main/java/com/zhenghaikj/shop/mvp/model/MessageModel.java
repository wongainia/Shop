package com.zhenghaikj.shop.mvp.model;

import com.blankj.utilcode.util.TimeUtils;
import com.zhenghaikj.shop.api.ApiRetrofit;
import com.zhenghaikj.shop.entity.Announcement;
import com.zhenghaikj.shop.entity.AnnouncementDetail;
import com.zhenghaikj.shop.entity.EasyResult;
import com.zhenghaikj.shop.mvp.contract.MessageContract;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MessageModel implements MessageContract.Model {
    private Map<String, String> map;
    private String sign;
    private String timestamp;
    @Override
    public Observable<EasyResult> AddArticlRead(String UserId, String CategoryId, String HiMallArticleId) {
        map = new HashMap<>();
        map.put("userkey",UserId);
        map.put("categoryid",CategoryId);
        map.put("himallarticleid",HiMallArticleId);
        map.put("app_key","himalltest");
        timestamp= TimeUtils.getNowString(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        map.put("timestamp",timestamp);
        sign = ApiRetrofit.SignTopRequest(map);
        return ApiRetrofit.getDefault().AddArticlRead(UserId,CategoryId,HiMallArticleId,"himalltest",timestamp,sign)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }
    @Override
    public Observable<Announcement> GetList(String categoryId,String rows, String page, String userkey) {
        map = new HashMap<>();
        map.put("categoryid",categoryId);
        map.put("rows",rows);
        map.put("page",page);
        map.put("userkey",userkey);
        map.put("app_key","himalltest");
        timestamp= TimeUtils.getNowString(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        map.put("timestamp",timestamp);
        sign = ApiRetrofit.SignTopRequest(map);
        return ApiRetrofit.getDefault().GetList(categoryId,rows,page,userkey,"himalltest",timestamp,sign)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<AnnouncementDetail> GetDetail(String id) {
        map = new HashMap<>();
        map.put("id",id);
        map.put("app_key","himalltest");
        timestamp= TimeUtils.getNowString(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        map.put("timestamp",timestamp);
        sign = ApiRetrofit.SignTopRequest(map);
        return ApiRetrofit.getDefault().GetDetail(id,"himalltest",timestamp,sign)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }
}
