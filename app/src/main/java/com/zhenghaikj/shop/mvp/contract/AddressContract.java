package com.zhenghaikj.shop.mvp.contract;

import com.zhenghaikj.shop.base.BaseModel;
import com.zhenghaikj.shop.base.BasePresenter;
import com.zhenghaikj.shop.base.BaseView;
import com.zhenghaikj.shop.entity.Address;
import com.zhenghaikj.shop.entity.RegionResult;
import com.zhenghaikj.shop.entity.ShippingAddressList;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;

public interface AddressContract {
    interface Model extends BaseModel {
        Observable<List<RegionResult>> GetAllRegion();
        Observable<List<RegionResult>> GetSubRegion(String id);
        Observable<Address> PostAddShippingAddress(String regionId,
                                                   String address,
                                                   String phone,
                                                   String shipTo,
                                                   String latitude,
                                                   String longitude,
                                                   String Userkey);
    }

    interface View extends BaseView {
        void GetAllRegion(List<RegionResult> Result);
        void GetSubRegion(List<RegionResult> Result);
        void PostAddShippingAddres(Address Result);
    }

    abstract class Presenter extends BasePresenter<View,Model> {
        public abstract void GetAllRegion();
        public abstract void GetSubRegion(String id);
        public abstract void PostAddShippingAddress(String regionId,
                                                   String address,
                                                   String phone,
                                                   String shipTo,
                                                   String latitude,
                                                   String longitude,
                                                   String Userkey);
    }
}
