package com.zhenghaikj.shop.mvp.contract;

import com.zhenghaikj.shop.base.BaseModel;
import com.zhenghaikj.shop.base.BasePresenter;
import com.zhenghaikj.shop.base.BaseView;
import com.zhenghaikj.shop.entity.CategoryMall;

import io.reactivex.Observable;

public interface CategoryContract {
    interface Model extends BaseModel {
        Observable<CategoryMall> GetCategories();
    }

    interface View extends BaseView {
        void GetCategories(CategoryMall Result);
    }

    abstract class Presenter extends BasePresenter<View,Model> {
        public abstract void GetCategories();
    }
}
