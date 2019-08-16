package com.zhenghaikj.shop.mvp.contract;

import com.zhenghaikj.shop.base.BaseModel;
import com.zhenghaikj.shop.base.BasePresenter;
import com.zhenghaikj.shop.base.BaseView;
import com.zhenghaikj.shop.entity.HistoryVisite;
import com.zhenghaikj.shop.entity.SimilarProduct;

import java.util.List;

import io.reactivex.Observable;

public interface HistoryVisiteContract  {
    interface Model extends BaseModel {
        Observable<HistoryVisite> GetHistoryVisite(String rows, String page, String userkey);
        Observable<List<SimilarProduct>> GetHotProduct(String productId, String categoryId);
    }

    interface View extends BaseView {
        void GetHistoryVisite(HistoryVisite result);
        void GetHotProduct(List<SimilarProduct> result);
    }

    abstract class Presenter extends BasePresenter<View,Model> {
        public abstract void GetHistoryVisite(String rows,String page,String userkey);
        public abstract void GetHotProduct(String productId,String categoryId);
    }
}
