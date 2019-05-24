package com.zhenghaikj.shop.mvp.presenter;

import com.zhenghaikj.shop.base.BaseObserver;
import com.zhenghaikj.shop.entity.AddtoCartResult;
import com.zhenghaikj.shop.entity.CollectResult;
import com.zhenghaikj.shop.entity.Comment;
import com.zhenghaikj.shop.entity.DetailResult;
import com.zhenghaikj.shop.entity.GetCommentResult;
import com.zhenghaikj.shop.entity.GetGoodSKu;
import com.zhenghaikj.shop.entity.GetShopCoupResult;
import com.zhenghaikj.shop.entity.ShopCoupResult;
import com.zhenghaikj.shop.mvp.contract.DetailContract;

public class DetailPresenter extends DetailContract.Presenter {
    @Override
    public void GetProductDetail(String id,String Userkey) {
        mModel.GetProductDetail(id,Userkey)
                .subscribe(new BaseObserver<DetailResult>() {
                    @Override
                    protected void onHandleSuccess(DetailResult value) {
                        mView.GetProductDetail(value);
                    }
                });
    }

    @Override
    public void PostAddProductToCart(String skuId, String count, String Userkey) {
        mModel.PostAddProductToCart(skuId,count,Userkey)
                .subscribe(new BaseObserver<AddtoCartResult>() {
                    @Override
                    protected void onHandleSuccess(AddtoCartResult value) {
                        mView.PostAddProductToCart(value);
                    }
                });
    }

    @Override
    public void GetSKUInfo(String productId) {
        mModel.GetSKUInfo(productId)
                .subscribe(new BaseObserver<GetGoodSKu>() {
                    @Override
                    protected void onHandleSuccess(GetGoodSKu value) {
                        mView.GetSKUInfo(value);
                    }
                });
    }

    @Override
    public void PostAddFavoriteProduct(String productId, String Userkey) {
        mModel.PostAddFavoriteProduct(productId,Userkey)
                .subscribe(new BaseObserver<CollectResult>() {
                    @Override
                    protected void onHandleSuccess(CollectResult value) {
                        mView.PostAddFavoriteProduct(value);
                    }
                });
    }

    @Override
    public void GetProductCommentShow(String id,String userkey) {
        mModel.GetProductCommentShow(id,userkey)
                .subscribe(new BaseObserver<GetCommentResult>() {
                    @Override
                    protected void onHandleSuccess(GetCommentResult value) {
                        mView.GetProductCommentShow(value);
                    }
                });
    }

    @Override
    public void ProductComment(String pId, String pageNo, String pageSize, String commentType) {
        mModel.ProductComment(pId, pageNo, pageSize, commentType)
                .subscribe(new BaseObserver<Comment>() {
                    @Override
                    protected void onHandleSuccess(Comment value) {
                        mView.ProductComment(value);
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
}
