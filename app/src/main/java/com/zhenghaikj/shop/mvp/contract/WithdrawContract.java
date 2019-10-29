package com.zhenghaikj.shop.mvp.contract;

import com.zhenghaikj.shop.base.BaseModel;
import com.zhenghaikj.shop.base.BasePresenter;
import com.zhenghaikj.shop.base.BaseResult;
import com.zhenghaikj.shop.base.BaseView;
import com.zhenghaikj.shop.entity.BankCard;
import com.zhenghaikj.shop.entity.Data;
import com.zhenghaikj.shop.entity.UserInfo;

import java.util.List;

import io.reactivex.Observable;

public interface WithdrawContract {
    interface Model extends BaseModel {
        Observable<BaseResult<Data<String>>> WithDraw(String DrawMoney, String CardNo, String UserID,String CardName);
        Observable<BaseResult<UserInfo>> GetUserInfoList(String userName, String limit);
        Observable<BaseResult<List<BankCard>>> GetAccountPayInfoList(String UserId);

    }

    interface View extends BaseView {
        void WithDraw(BaseResult<Data<String>> baseResult);
        void GetUserInfoList(BaseResult<UserInfo> Result);
        void GetAccountPayInfoList(BaseResult<List<BankCard>> baseResult);

    }

    abstract class Presenter extends BasePresenter<View,Model> {
        public abstract void WithDraw(String DrawMoney,String CardNo,String UserID,String CardName);
        public abstract void GetUserInfoList(String userName, String limit);
        public abstract void GetAccountPayInfoList(String UserId);

    }
}
