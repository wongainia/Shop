package com.zhenghaikj.shop.mvp.presenter;

import com.zhenghaikj.shop.base.BaseObserver2;
import com.zhenghaikj.shop.base.BaseResult;
import com.zhenghaikj.shop.entity.Data;
import com.zhenghaikj.shop.entity.Message;
import com.zhenghaikj.shop.entity.MessageData;
import com.zhenghaikj.shop.mvp.contract.MyMessageContract;

import java.util.List;

public class MyMessagePresenter extends MyMessageContract.Presenter {


    @Override
    public void GetMessageList(String UserID, String Type,String SubType,String limit,String page) {
        mModel.GetMessageList(UserID, Type,SubType,limit,page)
                .subscribe(new BaseObserver2<MessageData<List<Message>>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<MessageData<List<Message>>> value) {
                        mView.GetMessageList(value);
                    }
                });
    }

    @Override
    public void GetReadMessageList(String UserID, String Type, String SubType, String limit, String page) {
        mModel.GetReadMessageList(UserID, Type,SubType,limit,page)
                .subscribe(new BaseObserver2<MessageData<List<Message>>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<MessageData<List<Message>>> value) {
                        mView.GetReadMessageList(value);
                    }
                });
    }

    @Override
    public void AddOrUpdatemessage(String MessageID, String IsLook) {
        mModel.AddOrUpdatemessage(MessageID,IsLook)
                .subscribe(new BaseObserver2<Data<String>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Data<String>> value) {
                        mView.AddOrUpdatemessage(value);
                    }
                });
    }
}
