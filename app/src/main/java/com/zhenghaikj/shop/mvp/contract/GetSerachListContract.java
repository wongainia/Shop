package com.zhenghaikj.shop.mvp.contract;

import com.zhenghaikj.shop.base.BaseModel;
import com.zhenghaikj.shop.base.BasePresenter;
import com.zhenghaikj.shop.base.BaseView;
import com.zhenghaikj.shop.entity.Announcement;
import com.zhenghaikj.shop.entity.GetSerachListResult;
import com.zhenghaikj.shop.entity.GiftAds;
import com.zhenghaikj.shop.entity.Shop;
import com.zhenghaikj.shop.entity.ShopResult;

import java.util.List;

import io.reactivex.Observable;


public interface GetSerachListContract {
    interface Model extends BaseModel{
        Observable<GetSerachListResult> GetSerachList(String skey);
    }

    interface View extends BaseView{
        void GetSerachList(GetSerachListResult result);
    }

    abstract class Presenter extends BasePresenter<View,Model>{

        public abstract void GetSerachList(String skey);
    }
}
