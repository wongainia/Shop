package com.zhenghaikj.shop.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.blankj.utilcode.util.SPUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.activity.GoodsDetailActivity;
import com.zhenghaikj.shop.adapter.StoreDetailGoodsAdapter;
import com.zhenghaikj.shop.base.BaseLazyFragment;
import com.zhenghaikj.shop.entity.GetStoreSortResult;
import com.zhenghaikj.shop.entity.PostattentionResult;
import com.zhenghaikj.shop.entity.StoreDetailGoodsEntity;
import com.zhenghaikj.shop.entity.StoreDetailResult;
import com.zhenghaikj.shop.mvp.contract.StoreDetailContract;
import com.zhenghaikj.shop.mvp.model.StoreDetailModel;
import com.zhenghaikj.shop.mvp.presenter.StoreDetailPresenter;
import com.zhenghaikj.shop.utils.GlideImageLoader;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;


//全部订单
public class StoreDetailGoodsFragment extends BaseLazyFragment<StoreDetailPresenter, StoreDetailModel>implements StoreDetailContract.View {
    private static final String ARG_PARAM1 = "param1";//
    private static final String TAG = "StoreDetailGoodsFragment";
    @BindView(R.id.rv)
    RecyclerView rv;
    private StoreDetailGoodsAdapter storeDetailGoodsAdapter;
    private String Userkey;
    private SPUtils spUtils = SPUtils.getInstance("token");

    public static StoreDetailGoodsFragment newInstance(String param1) {
        StoreDetailGoodsFragment fragment = new StoreDetailGoodsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_storedetailgoods;
    }

    @Override
    protected void initData() {

        Userkey = spUtils.getString("UserKey");
        mPresenter.GetVShop(getActivity().getIntent().getStringExtra("VShopId"),Userkey);
    }

    @Override
    protected void initView() {


    }

    @Override
    protected void setListener() {

    }


    @Override
    public void GetVShop(StoreDetailResult result) {
        if (result.getSuccess().equals("True")){
            if (!result.getProducts().isEmpty()){
                Log.d("======>shop","ok");
                rv.setLayoutManager(new LinearLayoutManager(mActivity));
                storeDetailGoodsAdapter=new StoreDetailGoodsAdapter(R.layout.item_store_goods,result.getProducts());
                rv.setAdapter(storeDetailGoodsAdapter);

                storeDetailGoodsAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                    @Override
                    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                        switch (view.getId()){
                            case R.id.ll_store_goods:
                                Intent intent=new Intent(mActivity, GoodsDetailActivity.class);
                                intent.putExtra("id",((StoreDetailResult.ProductsBean)adapter.getItem(position)).getId());
                                startActivity(intent);
                                break;
                        }

                    }
                });
            }

        }
    }

    @Override
    public void PostAddFavoriteShop(PostattentionResult result) {

    }

    @Override
    public void GetVShopCategory(GetStoreSortResult result) {

    }
}
