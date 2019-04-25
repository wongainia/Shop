package com.zhenghaikj.shop.mvp.contract;

import com.zhenghaikj.shop.base.BaseModel;
import com.zhenghaikj.shop.base.BasePresenter;
import com.zhenghaikj.shop.base.BaseView;
import com.zhenghaikj.shop.entity.CheckMessage;
import com.zhenghaikj.shop.entity.GetImageCheckCode;
import com.zhenghaikj.shop.entity.SendMessage;

import io.reactivex.Observable;

public interface BindPhoneContract {
    interface Model extends BaseModel{
        Observable<SendMessage> GetCode(String contact,String userkey);
        Observable<CheckMessage> GetCheckPhoneOrEmailCheckCode(String contact,String checkCode,String userkey);
        Observable<GetImageCheckCode> GetImageCheckCode();
        Observable<CheckMessage> CheckUserName(String contact,String checkCode);
    }

    interface View extends BaseView{
        void GetCode(SendMessage result);
        void GetCheckPhoneOrEmailCheckCode(CheckMessage result);
        void GetImageCheckCode(GetImageCheckCode baseResult);
        void CheckUserName(CheckMessage result);
    }

    abstract class Presenter extends BasePresenter<View,Model>{
        public abstract void GetCode(String contact,String userkey);
        public abstract void GetCheckPhoneOrEmailCheckCode(String contact,String checkCode,String userkey);
        public abstract void GetImageCheckCode();
        public abstract void CheckUserName(String contact,String checkCode);
    }

}
