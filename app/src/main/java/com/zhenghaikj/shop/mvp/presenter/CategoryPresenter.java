package com.zhenghaikj.shop.mvp.presenter;

import com.zhenghaikj.shop.base.BaseObserver;
import com.zhenghaikj.shop.entity.CategoryMall;
import com.zhenghaikj.shop.mvp.contract.CategoryContract;

public class CategoryPresenter extends CategoryContract.Presenter {
    @Override
    public void GetCategories() {
        mModel.GetCategories()
                .subscribe(new BaseObserver<CategoryMall>() {
                    @Override
                    protected void onHandleSuccess(CategoryMall value) {
                        mView.GetCategories(value);
                    }
                });
    }
}
