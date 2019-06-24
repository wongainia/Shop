package com.zhenghaikj.shop.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.activity.SearchDetailShopDetailActivity;
import com.zhenghaikj.shop.adapter.StoreSortAdapter;
import com.zhenghaikj.shop.base.BaseLazyFragment;
import com.zhenghaikj.shop.entity.GetShopCoupResult;
import com.zhenghaikj.shop.entity.GetStoreSortResult;
import com.zhenghaikj.shop.entity.PostattentionResult;
import com.zhenghaikj.shop.entity.ShopCoupResult;
import com.zhenghaikj.shop.entity.StoreCommodityResult;
import com.zhenghaikj.shop.entity.StoreDetailResult;
import com.zhenghaikj.shop.mvp.contract.StoreDetailContract;
import com.zhenghaikj.shop.mvp.model.StoreDetailModel;
import com.zhenghaikj.shop.mvp.presenter.StoreDetailPresenter;

import butterknife.BindView;

public class StoreDetailSortFragment extends BaseLazyFragment<StoreDetailPresenter, StoreDetailModel>implements StoreDetailContract.View ,StoreSortAdapter.OnItemSortClickListner{
    private static final String ARG_PARAM1 = "param1";//
    private static final String TAG = "StoreDetailSortFragment";

    @BindView(R.id.rv)
    RecyclerView mRv;


    private String userKey;
    private String VShopId;
    private String ShopId;

    private StoreSortAdapter storeSortAdapter;

    public static StoreDetailSortFragment newInstance(String param1) {
        StoreDetailSortFragment fragment = new StoreDetailSortFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_storedetailsort;
    }

    @Override
    protected void initData() {
        VShopId=getActivity().getIntent().getStringExtra("VShopId");
        mPresenter.GetVShopCategory(VShopId);




    }

    @Override
    protected void initView() {

    }

    @Override
    protected void setListener() {

    }

    @Override
    public void GetVShop(StoreDetailResult result) {

    }

    @Override
    public void PostAddFavoriteShop(PostattentionResult result) {

    }

    /*获取分类*/
    @Override
    public void GetVShopCategory(GetStoreSortResult result) {
     if ("True".equals(result.getSuccess())){

         ShopId=result.getVShopId();
         mRv.setLayoutManager(new LinearLayoutManager(mActivity));
         storeSortAdapter=new StoreSortAdapter(R.layout.item_store_sort,result.getShopCategories());
         storeSortAdapter.setOnItemSortClickListner(this);

         storeSortAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
             @Override
             public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

             }
         });
         mRv.setAdapter(storeSortAdapter);
     }

    }

    @Override
    public void GetProductList(StoreCommodityResult result) {
    }

    @Override
    public void GetShopCouponList(ShopCoupResult Result) {

    }

    @Override
    public void PostAcceptCoupon(GetShopCoupResult Result) {

    }


    /*获取点击的item*/
    @Override
    public void OnItemSortClick(int id,String name) {
        //Toast.makeText(mActivity,"选择了: "+String.valueOf(id),Toast.LENGTH_SHORT).show();
        //mPresenter.GetProductList("10","1",String.valueOf(id),ShopId,"");
        Intent intent=new Intent(mActivity, SearchDetailShopDetailActivity.class);
        intent.putExtra("shopCategoryId",String.valueOf(id));
        intent.putExtra("shopid",ShopId);
        intent.putExtra("sname",name);
        startActivity(intent);
    }
}
