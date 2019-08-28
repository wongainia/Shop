package com.zhenghaikj.shop.mvp.contract;



import com.zhenghaikj.shop.base.BaseModel;
import com.zhenghaikj.shop.base.BasePresenter;
import com.zhenghaikj.shop.base.BaseResult;
import com.zhenghaikj.shop.base.BaseView;
import com.zhenghaikj.shop.entity.Message;
import com.zhenghaikj.shop.entity.MessageData;
import com.zhenghaikj.shop.entity.UserInfo;

import java.util.List;

import io.reactivex.Observable;


public interface ArticleContract {
    interface Model extends BaseModel {
        Observable<BaseResult<MessageData<List<Message>>>> GetOrderMessageList(String UserID, String SubType, String limit, String page);
        Observable<BaseResult<MessageData<List<Message>>>> GetTransactionMessageList(String UserID, String SubType, String limit, String page);
        Observable<BaseResult<UserInfo>> GetUserInfoList(String userName, String limit);

    }

    interface View extends BaseView {

        void GetOrderMessageList(BaseResult<MessageData<List<Message>>> baseResult);
        void GetTransactionMessageList(BaseResult<MessageData<List<Message>>> baseResult);
        void GetUserInfoList(BaseResult<UserInfo> Result);
    }

    abstract class Presenter extends BasePresenter<View,Model> {
        public abstract void GetOrderMessageList(String UserID,String SubType,String limit,String page);
        public abstract void GetTransactionMessageList(String UserID,String SubType,String limit,String page);
        public abstract void GetUserInfoList(String userName, String limit);
    }
}
