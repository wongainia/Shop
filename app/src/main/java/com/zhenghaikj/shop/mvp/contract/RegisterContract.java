package com.zhenghaikj.shop.mvp.contract;

import com.zhenghaikj.shop.base.BaseModel;
import com.zhenghaikj.shop.base.BasePresenter;
import com.zhenghaikj.shop.base.BaseResult;
import com.zhenghaikj.shop.base.BaseView;
import com.zhenghaikj.shop.entity.CheckMessage;
import com.zhenghaikj.shop.entity.Data;
import com.zhenghaikj.shop.entity.GetImageCheckCode;
import com.zhenghaikj.shop.entity.LoginResult;
import com.zhenghaikj.shop.entity.RegisterResult;
import com.zhenghaikj.shop.entity.SendMessage;

import io.reactivex.Observable;

public interface RegisterContract {
    interface Model extends BaseModel{
        Observable<RegisterResult> Reg(String userName, String password,String oauthType,String email,String code,String oauthOpenId ,String oauthNickName);
        Observable<GetImageCheckCode> GetImageCheckCode();
        Observable<LoginResult> GetUser(String userName, String password/*, String oauthType, String oauthOpenId, String oauthNickName*/);
        Observable<BaseResult<Data<String>>> LoginOn(String userName,String passWord);
        Observable<BaseResult<Data<String>>> AddAndUpdatePushAccount(String token,String type,String UserID);
        Observable<BaseResult<Data>> AddFactoryBrand(String UserID, String FBrandName);
        Observable<SendMessage> GetCode(String contact,String userkey);
        Observable<CheckMessage> GetCheckPhoneOrEmailCheckCode(String contact,String checkCode,String userkey);
        Observable<CheckMessage> CheckUserName(String contact,String checkCode);

    }

    interface View extends BaseView{
        void Reg(RegisterResult baseResult);
        void GetImageCheckCode(GetImageCheckCode baseResult);
        void GetUser(LoginResult Result);
        void LoginOn(BaseResult<Data<String>> Result);
        void AddAndUpdatePushAccount(BaseResult<Data<String>> Result);
        void AddFactoryBrand(BaseResult<Data> baseResult);
        void GetCode(SendMessage result);
        void GetCheckPhoneOrEmailCheckCode(CheckMessage result);
        void CheckUserName(CheckMessage result);
    }

    abstract class Presenter extends BasePresenter<View,Model>{
        public abstract void Reg(String userName, String password,String oauthType,String email,String code,String oauthOpenId ,String oauthNickName);
        public abstract void GetImageCheckCode();
        public abstract void GetUser(String userName, String password/*,String oauthType,String oauthOpenId,String oauthNickName*/);
        public abstract void LoginOn(String userName, String password);
        public abstract void AddAndUpdatePushAccount(String token,String type,String UserID);
        public abstract void AddFactoryBrand(String UserID, String FBrandName);
        public abstract void GetCode(String contact,String userkey);
        public abstract void GetCheckPhoneOrEmailCheckCode(String contact,String checkCode,String userkey);
        public abstract void CheckUserName(String contact,String checkCode);


    }
}
