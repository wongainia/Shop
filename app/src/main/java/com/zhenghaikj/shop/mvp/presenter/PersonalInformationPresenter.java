package com.zhenghaikj.shop.mvp.presenter;

import com.zhenghaikj.shop.base.BaseObserver;
import com.zhenghaikj.shop.entity.PersonalInformation;
import com.zhenghaikj.shop.entity.UploadImgResult;
import com.zhenghaikj.shop.mvp.contract.PersonalInformationContract;

public class PersonalInformationPresenter extends PersonalInformationContract.Presenter {
    @Override
    public void PersonalInformation(String UserKey) {
        mModel.PersonalInformation(UserKey)
                .subscribe(new BaseObserver<PersonalInformation>() {
                    @Override
                    protected void onHandleSuccess(PersonalInformation value) {
                        mView.PersonalInformation(value);
                    }
                });
    }
    @Override
    public void UploadPic(String picStr,String UserKey) {
        mModel.UploadPic(picStr,UserKey)
                .subscribe(new BaseObserver<UploadImgResult>() {
                    @Override
                    protected void onHandleSuccess(UploadImgResult value) {
                        mView.UploadPic(value);
                    }
                });
    }
}
