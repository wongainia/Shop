package com.zhenghaikj.shop.mvp.presenter;

import com.zhenghaikj.shop.base.BaseObserver;
import com.zhenghaikj.shop.entity.Announcement;
import com.zhenghaikj.shop.entity.GiftAds;
import com.zhenghaikj.shop.entity.Shop;
import com.zhenghaikj.shop.entity.ShopResult;
import com.zhenghaikj.shop.mvp.contract.ShopContract;

import java.util.List;

public class ShopPresenter extends ShopContract.Presenter {
    @Override
    public void GetSlideAds() {
        mModel.GetSlideAds().subscribe(new BaseObserver<List<GiftAds>>() {
            @Override
            protected void onHandleSuccess(List<GiftAds> value) {
                mView.GetSlideAds(value);
            }
        });
    }
    @Override
    public void index() {
        mModel.index().subscribe(new BaseObserver<Shop>() {
            @Override
            protected void onHandleSuccess(Shop value) {
                mView.index(value);
            }
        });
    }

    @Override
    public void IndexJson(String page) {
        mModel.IndexJson(page).subscribe(new BaseObserver<ShopResult>() {
            @Override
            protected void onHandleSuccess(ShopResult value) {
                mView.IndexJson(value);
            }
        });
    }

    @Override
    public void GetList(String categoryId,String rows, String page, String userkey) {
        mModel.GetList(categoryId,rows, page, userkey)
                .subscribe(new BaseObserver<Announcement>() {
                    @Override
                    protected void onHandleSuccess(Announcement value) {
                        mView.GetList(value);
                    }
                });
    }

}
