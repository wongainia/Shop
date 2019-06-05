package com.zhenghaikj.shop.mvp.presenter;


import com.zhenghaikj.shop.base.BaseObserver2;
import com.zhenghaikj.shop.base.BaseResult;
import com.zhenghaikj.shop.entity.Brand;
import com.zhenghaikj.shop.entity.CategoryData;
import com.zhenghaikj.shop.entity.Data;
import com.zhenghaikj.shop.mvp.contract.AddBrandContract;

import java.util.List;

public class AddBrandPresenter extends AddBrandContract.Presenter {


    @Override
    public void AddFactoryBrand(String UserID, String FBrandName) {
        mModel.AddFactoryBrand(UserID, FBrandName)
                .subscribe(new BaseObserver2<Data>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Data> value) {
                        mView.AddFactoryBrand(value);
                    }
                });
    }

    @Override
    public void GetFactoryCategory(String ParentID) {
        mModel.GetFactoryCategory(ParentID)
                .subscribe(new BaseObserver2<CategoryData>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<CategoryData> value) {
                        mView.GetFactoryCategory(value);
                    }
                });
    }

    @Override
    public void GetChildFactoryCategory(String ParentId) {
        mModel.GetChildFactoryCategory(ParentId)
                .subscribe(new BaseObserver2<CategoryData>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<CategoryData> value) {
                        mView.GetChildFactoryCategory(value);
                    }
                });
    }

    @Override
    public void GetBrand(String UserId) {
        mModel.GetBrand(UserId)
                .subscribe(new BaseObserver2<Data<List<Brand>>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Data<List<Brand>>> value) {
                        mView.GetBrand(value);
                    }
                });
    }

    @Override
    public void DeleteFactoryBrand(String FBrandID) {
        mModel.DeleteFactoryBrand(FBrandID)
                .subscribe(new BaseObserver2<Data>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Data> value) {
                        mView.DeleteFactoryBrand(value);
                    }
                });
    }
}
