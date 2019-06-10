package com.zhenghaikj.shop.mvp.contract;


import com.zhenghaikj.shop.base.BaseModel;
import com.zhenghaikj.shop.base.BasePresenter;
import com.zhenghaikj.shop.base.BaseView;
import com.zhenghaikj.shop.entity.GetStoreSortResult;
import com.zhenghaikj.shop.entity.PostattentionResult;
import com.zhenghaikj.shop.entity.SearchResult;
import com.zhenghaikj.shop.entity.StoreCommodityResult;
import com.zhenghaikj.shop.entity.StoreDetailResult;

import io.reactivex.Observable;


public interface SearchDetailShopDetailContract {
    interface Model extends BaseModel {
        Observable<StoreCommodityResult> GetProductList(
                String PageSize,
                String pageNo,
                String shopCategoryId,
                String shopId,
                String shopBranchId

        );
        Observable<SearchResult> GetSearchProducts(
                String keywords,
                String cid,
                String b_id,
                String a_id,
                String orderKey,
                String orderType,
                String pageNo,
                String pageSize,
                String sid
        );

    }
    interface View extends BaseView {
        void GetProductList(StoreCommodityResult result);
        void GetSearchProducts(SearchResult Result);
    }

    abstract class Presenter extends BasePresenter<View,Model> {
        public abstract void GetProductList( String PageSize,
                                             String pageNo,
                                             String shopCategoryId,
                                             String shopId,
                                             String shopBranchId);
        public abstract void GetSearchProducts(
                String keywords,
                String cid,
                String b_id,
                String a_id,
                String orderKey,
                String orderType,
                String pageNo,
                String pageSize,
                String sid
        );
    }
}
