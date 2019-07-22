package com.zhenghaikj.shop.mvp.presenter;

import com.zhenghaikj.shop.base.BaseObserver;
import com.zhenghaikj.shop.entity.Announcement;
import com.zhenghaikj.shop.entity.GetSerachListResult;
import com.zhenghaikj.shop.entity.GiftAds;
import com.zhenghaikj.shop.entity.Shop;
import com.zhenghaikj.shop.entity.ShopResult;
import com.zhenghaikj.shop.mvp.contract.GetSerachListContract;
import com.zhenghaikj.shop.mvp.contract.ShopContract;

import java.util.List;

public class GetSerachListPresenter extends GetSerachListContract.Presenter {


    @Override
    public void GetSerachList(String skey) {
        mModel.GetSerachList(skey).subscribe(new BaseObserver<GetSerachListResult>() {
            @Override
            protected void onHandleSuccess(GetSerachListResult value) {
                mView.GetSerachList(value);
            }
        });
    }
}
