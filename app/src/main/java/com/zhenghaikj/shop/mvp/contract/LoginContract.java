package com.zhenghaikj.shop.mvp.contract;

import com.zhenghaikj.shop.base.BaseModel;
import com.zhenghaikj.shop.base.BasePresenter;
import com.zhenghaikj.shop.base.BaseResult;
import com.zhenghaikj.shop.base.BaseView;
import com.zhenghaikj.shop.entity.Data;
import com.zhenghaikj.shop.entity.GetCode;
import com.zhenghaikj.shop.entity.LoginResult;

import io.reactivex.Observable;

public interface LoginContract {
    interface Model extends BaseModel {
        Observable<LoginResult> GetUser(String userName, String password/*,String oauthType,String oauthOpenId,String oauthNickName*/);
        Observable<BaseResult<Data<String>>> LoginOn(String userName, String passWord);
//        Observable<BaseResult<UserInfo>> GetUserInfoList(String userName, String limit);
        Observable<BaseResult<Data<String>>> AddAndUpdatePushAccount(String token,String type,String UserID);

        Observable<BaseResult<Data<String>>> GettokenbyUserid(String UserID);
        Observable<GetCode> GetPhoneCode(String contact);
        Observable<LoginResult> GetUserWithoutPassword(String checkCode,String contact);

        Observable<BaseResult<String>> ValidateUserName(String UserID);

    }

    interface View extends BaseView {
        void GetUser(LoginResult Result);
        void LoginOn(BaseResult<Data<String>> Result);
        void AddAndUpdatePushAccount(BaseResult<Data<String>> Result);
//        void GetUserInfoList(BaseResult<UserInfo> Result);

        void GettokenbyUserid(BaseResult<Data<String>> Result);

        void GetPhoneCode(GetCode Result);
        void GetUserWithoutPassword(LoginResult result);
        void ValidateUserName(BaseResult<String> Result);
    }

    abstract class Presenter extends BasePresenter<View,Model> {
        public abstract void GetUser(String userName, String password/*,String oauthType,String oauthOpenId,String oauthNickName*/);
        public abstract void LoginOn(String userName, String password);
        public abstract void AddAndUpdatePushAccount(String token,String type,String UserID);
        public abstract void GettokenbyUserid(String UserID);
//        public abstract void GetUserInfoList(String userName, String limit);
        public abstract void GetPhoneCode(String contact);
        public abstract void GetUserWithoutPassword(String checkCode,String contact);
        public abstract void ValidateUserName(String UserID);
    }
}
