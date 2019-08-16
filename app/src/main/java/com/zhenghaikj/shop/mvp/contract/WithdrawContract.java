package com.zhenghaikj.shop.mvp.contract;

import com.zhenghaikj.shop.base.BaseModel;
import com.zhenghaikj.shop.base.BasePresenter;
import com.zhenghaikj.shop.base.BaseResult;
import com.zhenghaikj.shop.base.BaseView;
import com.zhenghaikj.shop.entity.Data;
import com.zhenghaikj.shop.entity.UserInfo;

import io.reactivex.Observable;

public interface WithdrawContract {
    interface Model extends BaseModel {
        Observable<BaseResult<Data<String>>> WithDraw(String DrawMoney, String CardNo, String UserID);
        Observable<BaseResult<UserInfo>> GetUserInfoList(String userName, String limit);
    }

    interface View extends BaseView {
        void WithDraw(BaseResult<Data<String>> baseResult);
        void GetUserInfoList(BaseResult<UserInfo> Result);
    }

    abstract class Presenter extends BasePresenter<View,Model> {
        public abstract void WithDraw(String DrawMoney,String CardNo,String UserID);
        public abstract void GetUserInfoList(String userName, String limit);
    }
}
