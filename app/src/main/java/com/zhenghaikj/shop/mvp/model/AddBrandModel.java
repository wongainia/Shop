package com.zhenghaikj.shop.mvp.model;

import com.zhenghaikj.shop.api.ApiRetrofit2;
import com.zhenghaikj.shop.base.BaseResult;
import com.zhenghaikj.shop.entity.Brand;
import com.zhenghaikj.shop.entity.CategoryData;
import com.zhenghaikj.shop.entity.Data;
import com.zhenghaikj.shop.mvp.contract.AddBrandContract;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class AddBrandModel implements AddBrandContract.Model {


    @Override
    public Observable<BaseResult<Data>> AddFactoryBrand(String UserID, String FBrandName) {
        return ApiRetrofit2.getDefault().AddFactoryBrand(UserID, FBrandName)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResult<CategoryData>> GetFactoryCategory(String ParentID) {
        return ApiRetrofit2.getDefault().GetFactoryCategory(ParentID)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<BaseResult<CategoryData>> GetChildFactoryCategory(String ParentId) {
        return ApiRetrofit2.getDefault().GetChildFactoryCategory(ParentId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }


    @Override
    public Observable<BaseResult<Data<List<Brand>>>> GetBrand(String UserId) {
        return ApiRetrofit2.getDefault().GetFactoryBrand(UserId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }


    @Override
    public Observable<BaseResult<Data>> DeleteFactoryBrand(String FBrandID) {
        return ApiRetrofit2.getDefault().DeleteFactoryBrand(FBrandID)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }
}
