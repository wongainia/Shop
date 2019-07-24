package com.zhenghaikj.shop.mvp.presenter;



import com.zhenghaikj.shop.base.BaseObserver2;
import com.zhenghaikj.shop.base.BaseResult;
import com.zhenghaikj.shop.entity.BankCard;
import com.zhenghaikj.shop.entity.Data;
import com.zhenghaikj.shop.entity.UserInfo;
import com.zhenghaikj.shop.mvp.contract.CardContract;

import java.util.List;

public class CardPresenter extends CardContract.Presenter {
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
    public void AddorUpdateAccountPayInfo(String UserId, String PayInfoCode, String PayInfoName,String PayNo) {
        mModel.AddorUpdateAccountPayInfo(UserId, PayInfoCode,PayInfoName,PayNo)
                .subscribe(new BaseObserver2<Data<String>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Data<String>> value) {
                        mView.AddorUpdateAccountPayInfo(value);
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

    @Override
    public void GetBankNameByCardNo(String CardNo) {
        mModel.GetBankNameByCardNo(CardNo)
                .subscribe(new BaseObserver2<Data<String>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Data<String>> value) {
                        mView.GetBankNameByCardNo(value);
                    }
                });
    }


}
