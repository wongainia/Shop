package com.zhenghaikj.shop.mvp.contract;

import com.zhenghaikj.shop.base.BaseModel;
import com.zhenghaikj.shop.base.BasePresenter;
import com.zhenghaikj.shop.base.BaseResult;
import com.zhenghaikj.shop.base.BaseView;
import com.zhenghaikj.shop.entity.Data;

import io.reactivex.Observable;

public interface ChageUserNameContract {
    interface Model extends BaseModel {
        Observable<BaseResult<Data>> UpdateAccountNickName(String UserID, String NickName);
    }

    interface View extends BaseView {
        void UpdateAccountNickName(BaseResult<Data> baseResult);
    }

    abstract class Presenter extends BasePresenter<View,Model> {
        public abstract void UpdateAccountNickName(String UserID,String NickName);
    }
}
