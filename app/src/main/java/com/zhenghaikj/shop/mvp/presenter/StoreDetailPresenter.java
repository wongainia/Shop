package com.zhenghaikj.shop.mvp.presenter;

import com.zhenghaikj.shop.base.BaseObserver;
import com.zhenghaikj.shop.entity.Cart;
import com.zhenghaikj.shop.entity.CartResult;
import com.zhenghaikj.shop.entity.GetShopCoupResult;
import com.zhenghaikj.shop.entity.ShopCoupResult;
import com.zhenghaikj.shop.entity.StoreDetailResult;
import com.zhenghaikj.shop.mvp.contract.CartContract;
import com.zhenghaikj.shop.mvp.contract.StoreDetailContract;

public class StoreDetailPresenter extends StoreDetailContract.Presenter {

    @Override
    public void GetVShop(String id, String Userkey) {
        mModel.GetVShop(id,Userkey)
                .subscribe(new BaseObserver<StoreDetailResult>() {
                    @Override
                    protected void onHandleSuccess(StoreDetailResult value) {
                        mView.GetVShop(value);
                    }
                });
    }
}
