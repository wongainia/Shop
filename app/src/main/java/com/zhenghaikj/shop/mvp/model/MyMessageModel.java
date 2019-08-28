package com.zhenghaikj.shop.mvp.model;

import com.zhenghaikj.shop.api.ApiRetrofit2;
import com.zhenghaikj.shop.base.BaseResult;
import com.zhenghaikj.shop.entity.Data;
import com.zhenghaikj.shop.entity.Message;
import com.zhenghaikj.shop.entity.MessageData;
import com.zhenghaikj.shop.mvp.contract.MyMessageContract;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MyMessageModel implements MyMessageContract.Model {

    @Override
    public Observable<BaseResult<MessageData<List<Message>>>> GetMessageList(String UserID, String Type, String SubType, String limit, String page) {
        return ApiRetrofit2.getDefault().GetMessageList(UserID, Type,SubType,limit,page,"1")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    /*已读*/
    @Override
    public Observable<BaseResult<MessageData<List<Message>>>> GetReadMessageList(String UserID, String Type, String SubType, String limit, String page) {
        return ApiRetrofit2.getDefault().GetMessageList(UserID, Type,SubType,limit,page,"2")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResult<Data<String>>> AddOrUpdatemessage(String MessageID, String IsLook) {
        return ApiRetrofit2.getDefault().AddOrUpdatemessage(MessageID,IsLook)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }
}
