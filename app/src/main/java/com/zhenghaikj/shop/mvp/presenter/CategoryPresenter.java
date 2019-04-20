package com.zhenghaikj.shop.mvp.presenter;

import com.zhenghaikj.shop.base.BaseObserver;
import com.zhenghaikj.shop.entity.Category;
import com.zhenghaikj.shop.mvp.contract.CategoryContract;

public class CategoryPresenter extends CategoryContract.Presenter {
    @Override
    public void GetCategories() {
        mModel.GetCategories()
                .subscribe(new BaseObserver<Category>() {
                    @Override
                    protected void onHandleSuccess(Category value) {
                        mView.GetCategories(value);
                    }
                });
    }
}
