package com.zhenghaikj.shop.mvp.contract;

import com.zhenghaikj.shop.base.BaseModel;
import com.zhenghaikj.shop.base.BasePresenter;
import com.zhenghaikj.shop.base.BaseResult;
import com.zhenghaikj.shop.base.BaseView;
import com.zhenghaikj.shop.entity.Data;
import com.zhenghaikj.shop.entity.PersonalInformation;
import com.zhenghaikj.shop.entity.UploadImgResult;
import com.zhenghaikj.shop.entity.UserInfo;

import io.reactivex.Observable;

public interface PersonalInformationContract {
    interface Model extends BaseModel {
        Observable<PersonalInformation> PersonalInformation(String UserKey);
        Observable<UploadImgResult> UploadPic(String picStr, String UserKey);
        Observable<BaseResult<UserInfo>> GetUserInfoList(String UserID, String limit);
        Observable<BaseResult<Data>> UpdateSex(String UserID, String Sex);
    }

    interface View extends BaseView {
        void PersonalInformation(PersonalInformation result);
        void UploadPic(UploadImgResult result);
        void GetUserInfoList(BaseResult<UserInfo> baseResult);
        void UpdateSex(BaseResult<Data> baseResult);
    }

    abstract class Presenter extends BasePresenter<View,Model> {
        public abstract void PersonalInformation(String UserKey);
        public abstract void UploadPic(String picStr,String UserKey);
        public abstract void GetUserInfoList(String UserID,String limit);
        public abstract void UpdateSex(String UserID,String Sex);
    }
}
