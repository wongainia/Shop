package com.zhenghaikj.shop.mvp.presenter;

import com.zhenghaikj.shop.base.BaseObserver;
import com.zhenghaikj.shop.entity.Announcement;
import com.zhenghaikj.shop.entity.AnnouncementDetail;
import com.zhenghaikj.shop.mvp.contract.MessageContract;

public class MessagePresenter extends MessageContract.Presenter {
    @Override
    public void GetList(String rows, String page, String userkey) {
        mModel.GetList(rows, page, userkey)
                .subscribe(new BaseObserver<Announcement>() {
                    @Override
                    protected void onHandleSuccess(Announcement value) {
                        mView.GetList(value);
                    }
                });
    }

    @Override
    public void GetDetail(String id) {
        mModel.GetDetail(id)
                .subscribe(new BaseObserver<AnnouncementDetail>() {
                    @Override
                    protected void onHandleSuccess(AnnouncementDetail value) {
                        mView.GetDetail(value);
                    }
                });
    }
}
