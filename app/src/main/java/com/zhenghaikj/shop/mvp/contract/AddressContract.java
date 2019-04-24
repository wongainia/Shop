package com.zhenghaikj.shop.mvp.contract;

import com.zhenghaikj.shop.base.BaseModel;
import com.zhenghaikj.shop.base.BasePresenter;
import com.zhenghaikj.shop.base.BaseView;
import com.zhenghaikj.shop.entity.RegionResult;

import java.util.List;

import io.reactivex.Observable;

public interface AddressContract {
    interface Model extends BaseModel {
        Observable<List<RegionResult>> GetAllRegion();
        Observable<List<RegionResult>> GetSubRegion(String id);
    }

    interface View extends BaseView {
        void GetAllRegion(List<RegionResult> Result);
        void GetSubRegion(List<RegionResult> Result);
    }

    abstract class Presenter extends BasePresenter<View,Model> {
        public abstract void GetAllRegion();
        public abstract void GetSubRegion(String id);
    }
}
