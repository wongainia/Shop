package com.zhenghaikj.shop.mvp.presenter;

import com.zhenghaikj.shop.base.BaseObserver;
import com.zhenghaikj.shop.entity.Comment;
import com.zhenghaikj.shop.mvp.contract.ProductCommentContract;

public class ProductCommentPresenter extends ProductCommentContract.Presenter {
    @Override
    public void ProductComment(String pId, String pageNo, String pageSize, String commentType) {
        mModel.ProductComment(pId, pageNo, pageSize, commentType)
                .subscribe(new BaseObserver<Comment>() {
                    @Override
                    protected void onHandleSuccess(Comment value) {
                        mView.ProductComment(value);
                    }
                });
    }
}
