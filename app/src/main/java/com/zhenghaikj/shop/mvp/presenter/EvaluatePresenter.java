package com.zhenghaikj.shop.mvp.presenter;

import com.zhenghaikj.shop.base.BaseObserver;
import com.zhenghaikj.shop.entity.EvaluatePhotoEntity;
import com.zhenghaikj.shop.entity.EvaluateResult;
import com.zhenghaikj.shop.entity.PostPostAddComment;
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
                .subscribe(new BaseObserver<EvaluatePhotoEntity>() {
                    @Override
                    protected void onHandleSuccess(EvaluatePhotoEntity value) {
                        mView.UploadPicEvaluate(value);
                    }
                });
    }

    @Override
    public void PostAddComment(String userkey, String jsonstr) {
        mModel.PostAddComment(userkey,jsonstr)
                .subscribe(new BaseObserver<PostPostAddComment>() {
                    @Override
                    protected void onHandleSuccess(PostPostAddComment value) {
                        mView.PostAddComment(value);
                    }
                });
    }

}
