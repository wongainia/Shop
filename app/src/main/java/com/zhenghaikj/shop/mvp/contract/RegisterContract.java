package com.zhenghaikj.shop.mvp.contract;

import com.zhenghaikj.shop.base.BaseModel;
import com.zhenghaikj.shop.base.BasePresenter;
import com.zhenghaikj.shop.base.BaseResult;
import com.zhenghaikj.shop.base.BaseView;
import com.zhenghaikj.shop.entity.Data;
import com.zhenghaikj.shop.entity.GetImageCheckCode;
import com.zhenghaikj.shop.entity.LoginResult;
import com.zhenghaikj.shop.entity.RegisterResult;

import io.reactivex.Observable;

public interface RegisterContract {
    interface Model extends BaseModel{
        Observable<RegisterResult> Reg(String userName, String password,String oauthType,String email,String code,String oauthOpenId ,String oauthNickName);
        Observable<GetImageCheckCode> GetImageCheckCode();
        Observable<LoginResult> GetUser(String userName, String password, String oauthType, String oauthOpenId, String oauthNickName);
        Observable<BaseResult<Data<String>>> LoginOn(String userName,String passWord);
        Observable<BaseResult<Data<String>>> AddAndUpdatePushAccount(String token,String type,String UserID);

    }

    interface View extends BaseView{
        void Reg(RegisterResult baseResult);
        void GetImageCheckCode(GetImageCheckCode baseResult);
        void GetUser(LoginResult Result);
        void LoginOn(BaseResult<Data<String>> Result);
        void AddAndUpdatePushAccount(BaseResult<Data<String>> Result);
    }

    abstract class Presenter extends BasePresenter<View,Model>{
        public abstract void Reg(String userName, String password,String oauthType,String email,String code,String oauthOpenId ,String oauthNickName);
        public abstract void GetImageCheckCode();
        public abstract void GetUser(String userName, String password,String oauthType,String oauthOpenId,String oauthNickName);
        public abstract void LoginOn(String userName, String password);
        public abstract void AddAndUpdatePushAccount(String token,String type,String UserID);
    }
}
