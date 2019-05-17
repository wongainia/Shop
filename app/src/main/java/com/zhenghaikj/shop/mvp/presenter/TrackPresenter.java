package com.zhenghaikj.shop.mvp.presenter;

import com.zhenghaikj.shop.base.BaseObserver2;
import com.zhenghaikj.shop.base.BaseResult;
import com.zhenghaikj.shop.entity.Track;
import com.zhenghaikj.shop.mvp.contract.TrackContract;

import java.util.List;

public class TrackPresenter extends TrackContract.Presenter {
    @Override
    public void GetOrderRecordByOrderID(String OrderId) {
        mModel.GetOrderRecordByOrderID(OrderId)
                .subscribe(new BaseObserver2<List<Track>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<List<Track>> value) {
                        mView.GetOrderRecordByOrderID(value);
                    }
                });
    }
}
