package com.zhenghaikj.shop.mvp.presenter;

import com.zhenghaikj.shop.base.BaseObserver;
import com.zhenghaikj.shop.entity.GetStoreSortResult;
import com.zhenghaikj.shop.entity.PostattentionResult;
import com.zhenghaikj.shop.entity.SearchResult;
import com.zhenghaikj.shop.entity.SearchShopResult;
import com.zhenghaikj.shop.entity.StoreCommodityResult;
import com.zhenghaikj.shop.entity.StoreDetailResult;
import com.zhenghaikj.shop.mvp.contract.SearchDetailShopDetailContract;
import com.zhenghaikj.shop.mvp.contract.StoreDetailContract;

public class SearchDetailShopDetailPresenter extends SearchDetailShopDetailContract.Presenter {




    @Override
    public void GetProductList(String PageSize, String pageNo, String shopCategoryId, String shopId, String shopBranchId) {
        mModel.GetProductList(PageSize,  pageNo, shopCategoryId, shopId, shopBranchId)
                .subscribe(new BaseObserver<StoreCommodityResult>() {
                    @Override
                    protected void onHandleSuccess(StoreCommodityResult value) {
                        mView.GetProductList(value);
                    }
                });
    }

    @Override
    public void GetSearchProducts(String keywords, String cid, String b_id, String a_id, String orderKey, String orderType, String pageNo, String pageSize, String sid) {
        mModel.GetSearchProducts(keywords,cid,b_id,a_id,orderKey,orderType,pageNo,pageSize,sid)
                .subscribe(new BaseObserver<SearchResult>() {
                    @Override
                    protected void onHandleSuccess(SearchResult value) {
                        mView.GetSearchProducts(value);
                    }
                });
    }

    @Override
    public void GetProducts(String keywords, String cid, String pageNo, String pageSize) {
        mModel.GetProducts(keywords, cid, pageNo, pageSize)
                .subscribe(new BaseObserver<SearchResult>() {
                    @Override
                    protected void onHandleSuccess(SearchResult value) {
                        mView.GetProducts(value);
                    }
                });
    }

    @Override
    public void GetVShopSearchProducts(String vshopId, String keywords, String exp_keywords, String cid, String b_id, String a_id, String orderKey, String orderType, String pageNo, String pageSize) {
        mModel.GetVShopSearchProducts(vshopId, keywords, exp_keywords, cid, b_id, a_id, orderKey, orderType, pageNo, pageSize)
                .subscribe(new BaseObserver<SearchShopResult>() {
                    @Override
                    protected void onHandleSuccess(SearchShopResult value) {
                        mView.GetVShopSearchProducts(value);
                    }
                });
    }


}
