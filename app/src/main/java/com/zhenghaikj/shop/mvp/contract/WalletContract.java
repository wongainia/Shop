package com.zhenghaikj.shop.mvp.contract;

import com.zhenghaikj.shop.base.BaseModel;
import com.zhenghaikj.shop.base.BasePresenter;
import com.zhenghaikj.shop.base.BaseResult;
import com.zhenghaikj.shop.base.BaseView;
import com.zhenghaikj.shop.entity.BankCard;
import com.zhenghaikj.shop.entity.Bill;
import com.zhenghaikj.shop.entity.Data;
import com.zhenghaikj.shop.entity.UserInfo;

import java.util.List;

import io.reactivex.Observable;


public interface WalletContract {
    interface Model extends BaseModel {
        Observable<BaseResult<UserInfo>> GetUserInfoList(String UserId, String limit);
        Observable<BaseResult<Data<Bill>>> AccountBill(String UserID, String state);
        Observable<BaseResult<List<BankCard>>> GetAccountPayInfoList(String UserId);
    }

    interface View extends BaseView {
        void GetUserInfoList(BaseResult<UserInfo> baseResult);
        void AccountBill(BaseResult<Data<Bill>> baseResult);
        void GetAccountPayInfoList(BaseResult<List<BankCard>> baseResult);
    }

    abstract class Presenter extends BasePresenter<View,Model> {
        public abstract void GetUserInfoList(String UserId,String limit);
        public abstract void AccountBill(String UserID,String state);
        public abstract void GetAccountPayInfoList(String UserId);
    }
}
