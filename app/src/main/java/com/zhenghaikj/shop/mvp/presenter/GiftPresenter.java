package com.zhenghaikj.shop.mvp.presenter;


import com.zhenghaikj.shop.base.BaseObserver2;
import com.zhenghaikj.shop.base.BaseResult;
import com.zhenghaikj.shop.entity.Data;
import com.zhenghaikj.shop.mvp.contract.GiftContract;

public class GiftPresenter extends GiftContract.Presenter {


    @Override
    public void FAddCon(String UserID, String ToUserID,String Msg,String Connum) {
        mModel.FAddCon(UserID, ToUserID, Msg, Connum)
                .subscribe(new BaseObserver2<Data<String>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Data<String>> value) {
                        mView.FAddCon(value);
                    }
                });
    }
}
