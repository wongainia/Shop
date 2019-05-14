package com.zhenghaikj.shop.mvp.contract;

import com.zhenghaikj.shop.base.BaseModel;
import com.zhenghaikj.shop.base.BasePresenter;
import com.zhenghaikj.shop.base.BaseView;
import com.zhenghaikj.shop.entity.EvaluateResult;

import io.reactivex.Observable;

public interface EvaluateContract {
    interface Model extends BaseModel {
        Observable<EvaluateResult> GetComment(String orderId, String userkey);
        Observable<String> UploadPicEvaluate(String picStr);
    }

    interface View extends BaseView {
        void GetComment(EvaluateResult Result);
        void UploadPicEvaluate(String Result);
    }

    abstract class Presenter extends BasePresenter<View,Model> {
        public abstract void  GetComment(String orderId, String userkey);
        public abstract void  UploadPicEvaluate(String picStr);
    }
}
