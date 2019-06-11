package com.zhenghaikj.shop.mvp.presenter;

import com.zhenghaikj.shop.base.BaseObserver;
import com.zhenghaikj.shop.base.BaseObserver2;
import com.zhenghaikj.shop.base.BaseResult;
import com.zhenghaikj.shop.entity.CoinRecord;
import com.zhenghaikj.shop.entity.Data;
import com.zhenghaikj.shop.entity.GiftDetailResult;
import com.zhenghaikj.shop.mvp.contract.IntegralUseContract;

import java.util.List;

public class IntegralUsePresenter extends IntegralUseContract.Presenter {
    @Override
    public void GetIntegralRecord(String page,String type,String userkey) {
        mModel.GetIntegralRecord(page, type, userkey)
                .subscribe(new BaseObserver<GiftDetailResult>() {
                    @Override
                    protected void onHandleSuccess(GiftDetailResult value) {
                        mView.GetIntegralRecord(value);
                    }
                });
    }
    @Override
    public void CoinList(String UserID,String state) {
        mModel.CoinList(UserID, state)
                .subscribe(new BaseObserver2<Data<List<CoinRecord>>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Data<List<CoinRecord>>> value) {
                        mView.CoinList(value);
                    }
                });
    }
}
