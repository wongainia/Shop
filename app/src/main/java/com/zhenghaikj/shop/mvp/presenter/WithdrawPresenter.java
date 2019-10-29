package com.zhenghaikj.shop.mvp.presenter;

import com.zhenghaikj.shop.base.BaseObserver2;
import com.zhenghaikj.shop.base.BaseResult;
import com.zhenghaikj.shop.entity.BankCard;
import com.zhenghaikj.shop.entity.Data;
import com.zhenghaikj.shop.entity.UserInfo;
import com.zhenghaikj.shop.mvp.contract.WithdrawContract;

import java.util.List;

public class WithdrawPresenter extends WithdrawContract.Presenter {
    @Override
    public void WithDraw(String DrawMoney, String CardNo, String UserID,String CardName) {
        mModel.WithDraw(DrawMoney, CardNo, UserID,CardName)
                .subscribe(new BaseObserver2<Data<String>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Data<String>> value) {
                        mView.WithDraw(value);
                    }
                });
    }

    @Override
    public void GetUserInfoList(String userName, String limit) {
        mModel.GetUserInfoList(userName, limit)
                .subscribe(new BaseObserver2<UserInfo>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<UserInfo> value) {
                        mView.GetUserInfoList(value);
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
