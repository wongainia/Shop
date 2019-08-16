package com.zhenghaikj.shop.mvp.contract;

import com.zhenghaikj.shop.base.BaseModel;
import com.zhenghaikj.shop.base.BasePresenter;
import com.zhenghaikj.shop.base.BaseResult;
import com.zhenghaikj.shop.base.BaseView;
import com.zhenghaikj.shop.entity.Data;
import com.zhenghaikj.shop.entity.UserInfo;

import io.reactivex.Observable;

public interface ChangePayPasswordContract {
    interface Model extends BaseModel {
        Observable<BaseResult<UserInfo>> GetUserInfoList(String UserId, String limit);
        Observable<BaseResult<Data>> ChangePayPassword(String UserID, String OldPayPassword, String PayPassword);
    }

    interface View extends BaseView {
        void GetUserInfoList(BaseResult<UserInfo> baseResult);
        void ChangePayPassword(BaseResult<Data> baseResult);
    }

    abstract class Presenter extends BasePresenter<View,Model> {
        public abstract void GetUserInfoList(String UserId,String limit);
        public abstract void ChangePayPassword(String UserID, String OldPayPassword, String PayPassword);
    }
}
