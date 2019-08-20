package com.zhenghaikj.shop.mvp.contract;

import com.zhenghaikj.shop.base.BaseModel;
import com.zhenghaikj.shop.base.BasePresenter;
import com.zhenghaikj.shop.base.BaseView;
import com.zhenghaikj.shop.entity.Distribution;
import com.zhenghaikj.shop.entity.PersonalInformation;

import java.util.List;

import io.reactivex.Observable;

public interface DistributionContract {
    interface Model extends BaseModel{
        Observable<List<Distribution>> ProductList(String gentUserId, String userkey);
        Observable<PersonalInformation> PersonalInformation(String UserKey);
    }

    interface View extends BaseView{
        void ProductList(List<Distribution> Result);
        void PersonalInformation(PersonalInformation result);
    }

    abstract class Presenter extends BasePresenter<View,Model>{
        public abstract void ProductList(String gentUserId,String userkey);
        public abstract void PersonalInformation(String UserKey);
    }

}
