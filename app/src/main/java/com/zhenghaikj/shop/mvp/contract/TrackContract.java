package com.zhenghaikj.shop.mvp.contract;

import com.zhenghaikj.shop.base.BaseModel;
import com.zhenghaikj.shop.base.BasePresenter;
import com.zhenghaikj.shop.base.BaseResult;
import com.zhenghaikj.shop.base.BaseView;
import com.zhenghaikj.shop.entity.Track;

import java.util.List;

import io.reactivex.Observable;

public interface TrackContract {
    interface Model extends BaseModel {
        Observable<BaseResult<List<Track>>> GetOrderRecordByOrderID(String OrderId);
    }

    interface View extends BaseView {
        void GetOrderRecordByOrderID(BaseResult<List<Track>> baseResult);
    }

    abstract class Presenter extends BasePresenter<View,Model> {
        public abstract void GetOrderRecordByOrderID(String OrderId);
    }
}
