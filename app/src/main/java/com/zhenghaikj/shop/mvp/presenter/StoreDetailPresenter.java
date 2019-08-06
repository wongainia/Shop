package com.zhenghaikj.shop.mvp.presenter;

import com.zhenghaikj.shop.base.BaseObserver;
import com.zhenghaikj.shop.base.BaseObserver2;
import com.zhenghaikj.shop.base.BaseResult;
import com.zhenghaikj.shop.entity.Cart;
import com.zhenghaikj.shop.entity.CartResult;
import com.zhenghaikj.shop.entity.GetShopCoupResult;
import com.zhenghaikj.shop.entity.GetStoreSortResult;
import com.zhenghaikj.shop.entity.PostattentionResult;
import com.zhenghaikj.shop.entity.ShopCoupResult;
import com.zhenghaikj.shop.entity.StoreCommodityResult;
import com.zhenghaikj.shop.entity.StoreDetailResult;
import com.zhenghaikj.shop.entity.UserInfo;
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

    @Override
    public void PostAddFavoriteShop(String shopId, String Userkey) {
        mModel.PostAddFavoriteShop(shopId,Userkey)
                .subscribe(new BaseObserver<PostattentionResult>() {
                    @Override
                    protected void onHandleSuccess(PostattentionResult value) {
                        mView.PostAddFavoriteShop(value);
                    }
                });
    }

    @Override
    public void GetVShopCategory(String id) {
        mModel.GetVShopCategory(id)
                .subscribe(new BaseObserver<GetStoreSortResult>() {
                    @Override
                    protected void onHandleSuccess(GetStoreSortResult value) {
                        mView.GetVShopCategory(value);
                    }
                });
    }

    @Override
    public void GetProductList(String PageSize, String pageNo, String shopCategoryId, String shopId, String shopBranchId) {
        mModel.GetProductList(PageSize,  pageNo, shopCategoryId, shopId, shopBranchId)
                .subscribe(new BaseObserver<StoreCommodityResult>() {
                    @Override
                    protected void onHandleSuccess(StoreCommodityResult value) {
                        mView.GetProductList(value);
                    }
                });
    }

    @Override
    public void GetShopCouponList(String shopId) {
        mModel.GetShopCouponList(shopId)
                .subscribe(new BaseObserver<ShopCoupResult>() {
                    @Override
                    protected void onHandleSuccess(ShopCoupResult value) {
                        mView.GetShopCouponList(value);
                    }
                });
    }

    @Override
    public void PostAcceptCoupon(String vshopId, String couponId, String Userkey) {
        mModel.PostAcceptCoupon(vshopId,couponId,Userkey)
                .subscribe(new BaseObserver<GetShopCoupResult>() {
                    @Override
                    protected void onHandleSuccess(GetShopCoupResult value) {
                        mView.PostAcceptCoupon(value);
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
}
