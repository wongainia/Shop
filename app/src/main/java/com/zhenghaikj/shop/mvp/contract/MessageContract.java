package com.zhenghaikj.shop.mvp.contract;

import com.zhenghaikj.shop.base.BaseModel;
import com.zhenghaikj.shop.base.BasePresenter;
import com.zhenghaikj.shop.base.BaseView;
import com.zhenghaikj.shop.entity.Announcement;
import com.zhenghaikj.shop.entity.AnnouncementDetail;

import io.reactivex.Observable;

public interface MessageContract {
    interface Model extends BaseModel{
        Observable<Announcement> GetList(String categoryId, String rows, String page, String userkey);
        Observable<AnnouncementDetail> GetDetail(String id);
    }

    interface View extends BaseView{
        void GetList(Announcement result);
        void GetDetail(AnnouncementDetail result);
    }

    abstract class Presenter extends BasePresenter<View,Model>{
        public abstract void GetList(String categoryId,String rows, String page, String userkey);
        public abstract void GetDetail(String id);
    }
}
