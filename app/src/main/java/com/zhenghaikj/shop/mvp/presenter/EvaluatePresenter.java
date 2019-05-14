package com.zhenghaikj.shop.mvp.presenter;

import com.zhenghaikj.shop.base.BaseObserver;
import com.zhenghaikj.shop.entity.EvaluateResult;
import com.zhenghaikj.shop.mvp.contract.AllCommentsContract;
import com.zhenghaikj.shop.mvp.contract.EvaluateContract;

public class EvaluatePresenter extends EvaluateContract.Presenter {


    @Override
    public void GetComment(String orderId, String userkey) {
        mModel.GetComment(orderId,userkey)
                .subscribe(new BaseObserver<EvaluateResult>() {
                    @Override
                    protected void onHandleSuccess(EvaluateResult value) {
                        mView.GetComment(value);
                    }
                });
    }

    @Override
    public void UploadPicEvaluate(String picStr) {
        mModel.UploadPicEvaluate(picStr)
                .subscribe(new BaseObserver<String>() {
                    @Override
                    protected void onHandleSuccess(String value) {
                        mView.UploadPicEvaluate(value);
                    }
                });
    }
}
