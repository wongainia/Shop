package com.zhenghaikj.shop.mvp.contract;


import com.zhenghaikj.shop.base.BaseModel;
import com.zhenghaikj.shop.base.BasePresenter;
import com.zhenghaikj.shop.base.BaseResult;
import com.zhenghaikj.shop.base.BaseView;
import com.zhenghaikj.shop.entity.Brand;
import com.zhenghaikj.shop.entity.CategoryData;
import com.zhenghaikj.shop.entity.Data;
import com.zhenghaikj.shop.entity.GetStoreSortResult;
import com.zhenghaikj.shop.entity.PostattentionResult;
import com.zhenghaikj.shop.entity.StoreDetailResult;

import java.util.List;

import io.reactivex.Observable;


public interface StoreDetailContract {
    interface Model extends BaseModel {

        Observable<StoreDetailResult> GetVShop(String id,String Userkey);
        Observable<PostattentionResult> PostAddFavoriteShop(String shopId,String Userkey);

        Observable<GetStoreSortResult> GetVShopCategory(String id);
    }

    interface View extends BaseView {
        void GetVShop(StoreDetailResult result);
        void PostAddFavoriteShop(PostattentionResult result);
        void GetVShopCategory(GetStoreSortResult result);
    }

    abstract class Presenter extends BasePresenter<View,Model> {
        public abstract void GetVShop(String id, String Userkey);
        public abstract void PostAddFavoriteShop(String shopId, String Userkey);
        public abstract void GetVShopCategory(String id);
    }
}