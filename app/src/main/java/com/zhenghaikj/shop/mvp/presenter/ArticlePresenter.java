package com.zhenghaikj.shop.mvp.presenter;



import com.zhenghaikj.shop.base.BaseObserver2;
import com.zhenghaikj.shop.base.BaseResult;
import com.zhenghaikj.shop.entity.Message;
import com.zhenghaikj.shop.entity.MessageData;
import com.zhenghaikj.shop.entity.UserInfo;
import com.zhenghaikj.shop.mvp.contract.ArticleContract;

import java.util.List;

public class ArticlePresenter extends ArticleContract.Presenter {


    @Override
    public void GetOrderMessageList(String UserID, String SubType, String limit, String page) {
        mModel.GetOrderMessageList(UserID,SubType,limit,page)
                .subscribe(new BaseObserver2<MessageData<List<Message>>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<MessageData<List<Message>>> value) {
                        mView.GetOrderMessageList(value);
                    }
                });
    }

    @Override
    public void GetTransactionMessageList(String UserID, String SubType, String limit, String page) {
        mModel.GetTransactionMessageList(UserID,SubType,limit,page)
                .subscribe(new BaseObserver2<MessageData<List<Message>>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<MessageData<List<Message>>> value) {
                        mView.GetTransactionMessageList(value);
                    }
                });
    }

    @Override
    public void GetUserInfoList(String userName, String limit) {
        mModel.GetUserInfoList(userName, limit)
                .subscribe(new BaseObserver2<UserInfo>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<UserInfo> value) {
                        mView.GetUserInfoList(value);
                    }
                });
    }
}
