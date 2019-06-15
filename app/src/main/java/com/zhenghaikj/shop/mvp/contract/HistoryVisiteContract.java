package com.zhenghaikj.shop.mvp.contract;

import com.zhenghaikj.shop.base.BaseModel;
import com.zhenghaikj.shop.base.BasePresenter;
import com.zhenghaikj.shop.base.BaseView;
import com.zhenghaikj.shop.entity.HistoryVisite;

import io.reactivex.Observable;

public interface HistoryVisiteContract  {
    interface Model extends BaseModel{
        Observable<HistoryVisite> GetHistoryVisite(String rows,String page,String userkey);
    }

    interface View extends BaseView{
        void GetHistoryVisite(HistoryVisite result);
    }

    abstract class Presenter extends BasePresenter<View,Model>{
        public abstract void GetHistoryVisite(String rows,String page,String userkey);
    }
}
