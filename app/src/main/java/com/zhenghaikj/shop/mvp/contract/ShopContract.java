package com.zhenghaikj.shop.mvp.contract;

import com.zhenghaikj.shop.base.BaseModel;
import com.zhenghaikj.shop.base.BasePresenter;
import com.zhenghaikj.shop.base.BaseView;
import com.zhenghaikj.shop.entity.Announcement;
import com.zhenghaikj.shop.entity.GiftAds;
import com.zhenghaikj.shop.entity.Shop;
import com.zhenghaikj.shop.entity.ShopResult;

import java.util.List;

import io.reactivex.Observable;


public interface ShopContract {
    interface Model extends BaseModel{
        Observable<Shop> index();
        Observable<ShopResult> IndexJson();
        Observable<List<GiftAds>> GetSlideAds();
        Observable<Announcement> GetList(String categoryId, String rows, String page, String userkey);
    }

    interface View extends BaseView{
        void index(Shop result);
        void IndexJson(ShopResult result);
        void GetSlideAds(List<GiftAds> result);
        void GetList(Announcement result);
    }

    abstract class Presenter extends BasePresenter<View,Model>{
        public abstract void index();
        public abstract void IndexJson();
        public abstract void GetSlideAds();
        public abstract void GetList(String categoryId,String rows, String page, String userkey);
    }
}
