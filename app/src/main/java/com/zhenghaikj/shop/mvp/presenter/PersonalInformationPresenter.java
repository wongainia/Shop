package com.zhenghaikj.shop.mvp.presenter;

import com.zhenghaikj.shop.base.BaseObserver;
import com.zhenghaikj.shop.base.BaseObserver2;
import com.zhenghaikj.shop.base.BaseResult;
import com.zhenghaikj.shop.entity.Data;
import com.zhenghaikj.shop.entity.PersonalInformation;
import com.zhenghaikj.shop.entity.UploadImgResult;
import com.zhenghaikj.shop.entity.UserInfo;
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

    @Override
    public void GetUserInfoList(String UserID,String limit) {
        mModel.GetUserInfoList(UserID,limit)
                .subscribe(new BaseObserver2<UserInfo>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<UserInfo> value) {
                        mView.GetUserInfoList(value);
                    }
                });
    }


    @Override
    public void UpdateSex(String UserID, String Sex) {
        mModel.UpdateSex(UserID,Sex)
                .subscribe(new BaseObserver2<Data>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Data> value) {
                        mView.UpdateSex(value);
                    }
                });
    }

}
