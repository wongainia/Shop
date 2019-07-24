package com.zhenghaikj.shop.mvp.presenter;

import com.zhenghaikj.shop.base.BaseObserver;
import com.zhenghaikj.shop.entity.Comment;
import com.zhenghaikj.shop.mvp.contract.AllCommentsContract;

public class AllCommentsPresenter extends AllCommentsContract.Presenter {

    @Override
    public void GetProductComment(String pId, String pageNo, String pageSize, String commentType) {
        mModel.GetProductComment(pId,pageNo,pageSize,commentType)
                .subscribe(new BaseObserver<Comment>() {
                    @Override
                    protected void onHandleSuccess(Comment value) {
                        mView.GetProductComment(value);
                    }
                });
    }
}
