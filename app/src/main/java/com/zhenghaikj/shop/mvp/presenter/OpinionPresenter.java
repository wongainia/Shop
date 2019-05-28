package com.zhenghaikj.shop.mvp.presenter;


import com.zhenghaikj.shop.base.BaseObserver2;
import com.zhenghaikj.shop.base.BaseResult;
import com.zhenghaikj.shop.entity.Data;
import com.zhenghaikj.shop.mvp.contract.OpinionContract;

public class OpinionPresenter extends OpinionContract.Presenter {

    @Override
    public void AddOpinion(String UserID, String BackType, String Content) {
        mModel.AddOpinion(UserID, BackType, Content)
                .subscribe(new BaseObserver2<Data<String>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Data<String>> value) {
                        mView.AddOpinion(value);
                    }
                });
    }
}
