package com.zhenghaikj.shop.mvp.contract;

import com.zhenghaikj.shop.base.BaseModel;
import com.zhenghaikj.shop.base.BasePresenter;
import com.zhenghaikj.shop.base.BaseResult;
import com.zhenghaikj.shop.base.BaseView;
import com.zhenghaikj.shop.entity.AddtoCartResult;
import com.zhenghaikj.shop.entity.CollectResult;
import com.zhenghaikj.shop.entity.Comment;
import com.zhenghaikj.shop.entity.DetailResult;
import com.zhenghaikj.shop.entity.GetCommentResult;
import com.zhenghaikj.shop.entity.GetGoodSKu;
import com.zhenghaikj.shop.entity.GetShopCoupResult;
import com.zhenghaikj.shop.entity.ShopCoupResult;
import com.zhenghaikj.shop.entity.UserInfo;

import io.reactivex.Observable;

public interface DetailContract {
    interface Model extends BaseModel {
        Observable<DetailResult> GetProductDetail(String id, String Userkey);

        Observable<AddtoCartResult> PostAddProductToCart(String skuId,String count,String Userkey);

        Observable<GetGoodSKu> GetSKUInfo(String productId);

        Observable<CollectResult> PostAddFavoriteProduct(String productId,String Userkey);


        Observable<GetCommentResult> GetProductCommentShow(String id, String userkey);

        Observable<Comment> ProductComment(String pId,
                                           String pageNo,
                                           String pageSize,
                                           String commentType);
        Observable<ShopCoupResult> GetShopCouponList(String shopId);
        Observable<GetShopCoupResult> PostAcceptCoupon(String vshopId,String couponId,String Userkey);
        Observable<BaseResult<UserInfo>> GetUserInfoList(String userName, String limit);
    }

    interface View extends BaseView {
        void GetProductDetail(DetailResult Result);


        void PostAddProductToCart(AddtoCartResult Result);

        void GetSKUInfo(GetGoodSKu Result);
        void PostAddFavoriteProduct(CollectResult Result);
        void GetProductCommentShow(GetCommentResult result);
        void ProductComment(Comment Result);
        void GetShopCouponList(ShopCoupResult Result);
        void PostAcceptCoupon(GetShopCoupResult Result);
        void GetUserInfoList(BaseResult<UserInfo> Result);

    }

    abstract class Presenter extends BasePresenter<View,Model> {
        public abstract void GetProductDetail(String id,String Userkey);
        public abstract void PostAddProductToCart(String skuId,String count,String Userkey);
        public abstract void GetSKUInfo(String productId);
        public abstract void PostAddFavoriteProduct(String productId,String Userkey);
        public abstract void GetProductCommentShow(String id,String userkey);
        public abstract void ProductComment(String pId,
                                            String pageNo,
                                            String pageSize,
                                            String commentType);
        public abstract void GetShopCouponList(String shopId);
        public abstract void PostAcceptCoupon(String vshopId,String couponId,String Userkey);
        public abstract void GetUserInfoList(String userName, String limit);
    }
}
