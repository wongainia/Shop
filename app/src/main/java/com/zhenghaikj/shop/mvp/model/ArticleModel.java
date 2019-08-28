package com.zhenghaikj.shop.mvp.model;


import com.zhenghaikj.shop.api.ApiRetrofit2;
import com.zhenghaikj.shop.base.BaseResult;
import com.zhenghaikj.shop.entity.Message;
import com.zhenghaikj.shop.entity.MessageData;
import com.zhenghaikj.shop.entity.UserInfo;
import com.zhenghaikj.shop.mvp.contract.ArticleContract;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class ArticleModel implements ArticleContract.Model {




    @Override
    public Observable<BaseResult<MessageData<List<Message>>>> GetOrderMessageList(String UserID, String SubType, String limit, String page) {
        return ApiRetrofit2.getDefault().GetMessageList(UserID, "2",SubType,limit,page,"1")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResult<MessageData<List<Message>>>> GetTransactionMessageList(String UserID, String SubType, String limit, String page) {
        return ApiRetrofit2.getDefault().GetMessageList(UserID, "1",SubType,limit,page,"1")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }
    @Override
    public Observable<BaseResult<UserInfo>> GetUserInfoList(String userName, String limit) {
        return ApiRetrofit2.getDefault().GetUserInfoList(userName, limit)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

}
