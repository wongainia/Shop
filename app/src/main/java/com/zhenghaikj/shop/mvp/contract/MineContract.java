package com.zhenghaikj.shop.mvp.contract;

import com.zhenghaikj.shop.base.BaseModel;
import com.zhenghaikj.shop.base.BasePresenter;
import com.zhenghaikj.shop.base.BaseResult;
import com.zhenghaikj.shop.base.BaseView;
import com.zhenghaikj.shop.entity.HistoryVisite;
import com.zhenghaikj.shop.entity.PersonalInformation;
import com.zhenghaikj.shop.entity.UserInfo;

import io.reactivex.Observable;

public interface MineContract {
    interface Model extends BaseModel {
        Observable<PersonalInformation> PersonalInformation(String UserKey);
        Observable<HistoryVisite> GetHistoryVisite(String userkey);

        Observable<BaseResult<UserInfo>> GetUserInfoList(String userName, String limit);
    }

    interface View extends BaseView {
        void PersonalInformation(PersonalInformation result);
        void GetHistoryVisite(HistoryVisite result);
        void GetUserInfoList(BaseResult<UserInfo> Result);
    }

    abstract class Presenter extends BasePresenter<View,Model> {
        public abstract void PersonalInformation(String UserKey);
        public abstract void GetHistoryVisite(String userkey);

        public abstract void GetUserInfoList(String userName, String limit);
    }

}
