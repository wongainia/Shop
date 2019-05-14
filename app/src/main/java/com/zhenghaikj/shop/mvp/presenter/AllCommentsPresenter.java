package com.zhenghaikj.shop.mvp.presenter;

import com.zhenghaikj.shop.base.BaseObserver;
import com.zhenghaikj.shop.entity.AddtoCartResult;
import com.zhenghaikj.shop.entity.CollectResult;
import com.zhenghaikj.shop.entity.DetailResult;
import com.zhenghaikj.shop.entity.GetCommentResult;
import com.zhenghaikj.shop.entity.GetGoodSKu;
import com.zhenghaikj.shop.mvp.contract.AllCommentsContract;
import com.zhenghaikj.shop.mvp.contract.DetailContract;

public class AllCommentsPresenter extends AllCommentsContract.Presenter {

    @Override
    public void GetProductComment(String pId, String pageNo, String pageSize, String commentType) {
        mModel.GetProductComment(pId,pageNo,pageSize,commentType)
                .subscribe(new BaseObserver<String>() {
                    @Override
                    protected void onHandleSuccess(String value) {
                        mView.GetProductComment(value);
                    }
                });
    }
}
