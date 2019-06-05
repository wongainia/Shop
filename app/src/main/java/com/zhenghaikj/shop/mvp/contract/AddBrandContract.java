package com.zhenghaikj.shop.mvp.contract;


import com.zhenghaikj.shop.base.BaseModel;
import com.zhenghaikj.shop.base.BasePresenter;
import com.zhenghaikj.shop.base.BaseResult;
import com.zhenghaikj.shop.base.BaseView;
import com.zhenghaikj.shop.entity.Brand;
import com.zhenghaikj.shop.entity.CategoryData;
import com.zhenghaikj.shop.entity.Data;

import java.util.List;

import io.reactivex.Observable;


public interface AddBrandContract {
    interface Model extends BaseModel {
        Observable<BaseResult<Data>> AddFactoryBrand(String UserID, String FBrandName);
        Observable<BaseResult<CategoryData>> GetFactoryCategory(String ParentID);
        Observable<BaseResult<CategoryData>> GetChildFactoryCategory(String ParentId);
        Observable<BaseResult<Data<List<Brand>>>> GetBrand(String UserId);
        Observable<BaseResult<Data>> DeleteFactoryBrand(String FBrandID);

    }

    interface View extends BaseView {
        void AddFactoryBrand(BaseResult<Data> baseResult);
        void GetFactoryCategory(BaseResult<CategoryData> baseResult);
        void GetChildFactoryCategory(BaseResult<CategoryData> baseResult);
        void GetBrand(BaseResult<Data<List<Brand>>> baseResult);
        void DeleteFactoryBrand(BaseResult<Data> baseResult);
    }

    abstract class Presenter extends BasePresenter<View,Model> {
        public abstract void AddFactoryBrand(String UserID, String FBrandName);
        public abstract void GetFactoryCategory(String ParentID);
        public abstract void GetChildFactoryCategory(String ParentId);
        public abstract void GetBrand(String UserId);
        public abstract void DeleteFactoryBrand(String FBrandID);
    }
}
