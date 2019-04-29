package com.zhenghaikj.shop.mvp.contract;

import com.zhenghaikj.shop.base.BaseModel;
import com.zhenghaikj.shop.base.BasePresenter;
import com.zhenghaikj.shop.base.BaseView;
import com.zhenghaikj.shop.entity.HistoryVisite;
import com.zhenghaikj.shop.entity.PersonalInformation;

import io.reactivex.Observable;

public interface MineContract {
    interface Model extends BaseModel {
        Observable<PersonalInformation> PersonalInformation(String UserKey);
        Observable<HistoryVisite> GetHistoryVisite(String userkey);
    }

    interface View extends BaseView {
        void PersonalInformation(PersonalInformation result);
        void GetHistoryVisite(HistoryVisite result);
    }

    abstract class Presenter extends BasePresenter<View,Model> {
        public abstract void PersonalInformation(String UserKey);
        public abstract void GetHistoryVisite(String userkey);
    }
}
