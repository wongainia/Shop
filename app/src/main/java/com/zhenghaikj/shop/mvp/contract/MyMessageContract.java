package com.zhenghaikj.shop.mvp.contract;

import com.zhenghaikj.shop.base.BaseModel;
import com.zhenghaikj.shop.base.BasePresenter;
import com.zhenghaikj.shop.base.BaseResult;
import com.zhenghaikj.shop.base.BaseView;
import com.zhenghaikj.shop.entity.Data;
import com.zhenghaikj.shop.entity.Message;
import com.zhenghaikj.shop.entity.MessageData;

import java.util.List;

import io.reactivex.Observable;


public interface MyMessageContract {
    interface Model extends BaseModel {

     Observable<BaseResult<MessageData<List<Message>>>> GetMessageList(String UserID, String Type, String SubType, String limit, String page);

        Observable<BaseResult<MessageData<List<Message>>>> GetReadMessageList(String UserID, String Type, String SubType, String limit, String page);

     /*更改为已读*/
     Observable<BaseResult<Data<String>>> AddOrUpdatemessage(String MessageID, String IsLook);


    }

    interface View extends BaseView {
        void GetMessageList(BaseResult<MessageData<List<Message>>> baseResult);

        void GetReadMessageList(BaseResult<MessageData<List<Message>>> baseResult);
        void AddOrUpdatemessage(BaseResult<Data<String>> baseResult);

    }

    abstract class Presenter extends BasePresenter<View,Model> {

        public abstract void GetMessageList(String UserID,String Type,String SubType,String limit,String page);
        public abstract void GetReadMessageList(String UserID,String Type,String SubType,String limit,String page);
        public abstract void AddOrUpdatemessage(String MessageID,String IsLook);

    }
}
