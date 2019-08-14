package com.zhenghaikj.shop.mvp.presenter;


import com.zhenghaikj.shop.base.BaseObserver2;
import com.zhenghaikj.shop.base.BaseResult;
import com.zhenghaikj.shop.entity.BankCard;
import com.zhenghaikj.shop.entity.Bill;
import com.zhenghaikj.shop.entity.Data;
import com.zhenghaikj.shop.entity.UserInfo;
import com.zhenghaikj.shop.mvp.contract.WalletContract;

import java.util.List;

public class WalletPresenter extends WalletContract.Presenter {
    @Override
    public void GetUserInfoList(String UserId, String limit) {
        mModel.GetUserInfoList(UserId, limit)
                .subscribe(new BaseObserver2<UserInfo>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<UserInfo> value) {
                        mView.GetUserInfoList(value);
                    }
                });
    }

    @Override
    public void AccountBill(String UserID,String state) {
        mModel.AccountBill(UserID,state)
                .subscribe(new BaseObserver2<Data<Bill>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Data<Bill>> value) {
                        mView.AccountBill(value);
                    }
                });
    }

    @Override
    public void GetAccountPayInfoList(String UserId) {
        mModel.GetAccountPayInfoList(UserId)
                .subscribe(new BaseObserver2<List<BankCard>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<List<BankCard>> value) {
                        mView.GetAccountPayInfoList(value);
                    }
                });
    }
}
