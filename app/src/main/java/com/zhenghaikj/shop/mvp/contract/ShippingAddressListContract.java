package com.zhenghaikj.shop.mvp.contract;

import com.zhenghaikj.shop.base.BaseModel;
import com.zhenghaikj.shop.base.BasePresenter;
import com.zhenghaikj.shop.base.BaseView;
import com.zhenghaikj.shop.entity.ShippingAddressList;

import io.reactivex.Observable;

public interface ShippingAddressListContract {
    interface Model extends BaseModel{
        Observable<ShippingAddressList> GetShippingAddressList(String userkey);
    }

    interface View extends BaseView{
        void GetShippingAddressList(ShippingAddressList result);
    }

    abstract class Presenter extends BasePresenter<View,Model>{
        public abstract void GetShippingAddressList(String userkey);
    }
}
