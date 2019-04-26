package com.zhenghaikj.shop.mvp.contract;

import com.zhenghaikj.shop.base.BaseModel;
import com.zhenghaikj.shop.base.BasePresenter;
import com.zhenghaikj.shop.base.BaseView;
import com.zhenghaikj.shop.entity.AddtoCartResult;
import com.zhenghaikj.shop.entity.CollectResult;
import com.zhenghaikj.shop.entity.DetailResult;
import com.zhenghaikj.shop.entity.GetGoodSKu;
import com.zhenghaikj.shop.entity.SearchResult;

import io.reactivex.Observable;

public interface DetailContract {
    interface Model extends BaseModel {
        Observable<DetailResult> GetProductDetail(String id, String Userkey);

        Observable<AddtoCartResult> PostAddProductToCart(String skuId,String count,String Userkey);

        Observable<GetGoodSKu> GetSKUInfo(String productId);

        Observable<CollectResult> PostAddFavoriteProduct(String productId,String Userkey);
    }

    interface View extends BaseView {
        void GetProductDetail(DetailResult Result);


        void PostAddProductToCart(AddtoCartResult Result);

        void GetSKUInfo(GetGoodSKu Result);
        void PostAddFavoriteProduct(CollectResult Result);
    }

    abstract class Presenter extends BasePresenter<View,Model> {
        public abstract void GetProductDetail(String id,String Userkey);
        public abstract void PostAddProductToCart(String skuId,String count,String Userkey);
        public abstract void GetSKUInfo(String productId);

        public abstract void PostAddFavoriteProduct(String productId,String Userkey);
    }
}
