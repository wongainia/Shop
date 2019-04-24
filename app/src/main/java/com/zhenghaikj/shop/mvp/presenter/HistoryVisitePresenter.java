package com.zhenghaikj.shop.mvp.presenter;

import com.zhenghaikj.shop.base.BaseObserver;
import com.zhenghaikj.shop.entity.HistoryVisite;
import com.zhenghaikj.shop.mvp.contract.HistoryVisiteContract;

public class HistoryVisitePresenter extends HistoryVisiteContract.Presenter {
    @Override
    public void GetHistoryVisite(String userkey) {
        mModel.GetHistoryVisite(userkey)
                .subscribe(new BaseObserver<HistoryVisite>() {
                    @Override
                    protected void onHandleSuccess(HistoryVisite value) {
                        mView.GetHistoryVisite(value);
                    }
                });
    }
}
