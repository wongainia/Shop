package com.zhenghaikj.shop.mvp.contract;

import com.zhenghaikj.shop.base.BaseModel;
import com.zhenghaikj.shop.base.BasePresenter;
import com.zhenghaikj.shop.base.BaseView;
import com.zhenghaikj.shop.entity.Logout;
import com.zhenghaikj.shop.entity.PersonalInformation;

import io.reactivex.Observable;

public interface SettingContract {
    interface Model extends BaseModel{
        Observable<Logout> PostLogout();
        Observable<PersonalInformation> PersonalInformation(String UserKey);
    }

    interface View extends BaseView{
        void PostLogout(Logout result);
        void PersonalInformation(PersonalInformation result);
    }

    abstract class Presenter extends BasePresenter<View, Model> {
        public abstract void PostLogout();
        public abstract void PersonalInformation(String UserKey);
    }
}
