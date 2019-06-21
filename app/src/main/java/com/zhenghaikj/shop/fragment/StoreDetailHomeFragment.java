package com.zhenghaikj.shop.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.activity.GoodsDetailActivity;
import com.zhenghaikj.shop.adapter.ShopCouponAdapter;
import com.zhenghaikj.shop.adapter.StoreDetailGoodsAdapter;
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

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


public class StoreDetailHomeFragment extends BaseLazyFragment<StoreDetailPresenter, StoreDetailModel> implements StoreDetailContract.View {
    private static final String ARG_PARAM1 = "param1";//
    private static final String TAG = "StoreDetailHomeFragment";
    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.rv_coupon)
    RecyclerView mRvCoupon;

    private StoreDetailGoodsAdapter storeDetailGoodsAdapter;
    private List<ShopCoupResult.CouponBean> couponBeanList=new ArrayList<>();

    private View bannerview;
    private ShopCouponAdapter adapter;
    private StoreDetailResult sdrResult;

    public static StoreDetailHomeFragment newInstance(String param1) {
        StoreDetailHomeFragment fragment = new StoreDetailHomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_storedetailhome;
    }

    @Override
    protected void initData() {
        bannerview = LayoutInflater.from(mActivity).inflate(R.layout.item_banner, null);
        mPresenter.GetVShop(getActivity().getIntent().getStringExtra("VShopId"), userKey);
    }

    @Override
    protected void initView() {

//        for (int i=0;i<3;i++){
//            couponBeanList.add(new UserCouponListResult.CouponBean());
//        }
        adapter = new ShopCouponAdapter(R.layout.item_coupon3,couponBeanList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mActivity);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRvCoupon.setLayoutManager(linearLayoutManager);
        mRvCoupon.setAdapter(adapter);
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()){
                    case R.id.tv_shop:
                        mPresenter.PostAcceptCoupon(((ShopCoupResult.CouponBean) adapter.getData().get(position)).getVShopId(), ((ShopCoupResult.CouponBean) adapter.getData().get(position)).getCouponId(), userKey);
                        break;
                }
            }
        });

    }

    @Override
    protected void setListener() {

    }


    @Override
    public void GetVShop(StoreDetailResult result) {
        if (result.getSuccess().equals("True")) {
            sdrResult =result;
            mPresenter.GetShopCouponList(String.valueOf(result.getVShop().getShopId()));
            if (!result.getProducts().isEmpty()) {
                rv.setLayoutManager(new GridLayoutManager(mActivity,2));
                storeDetailGoodsAdapter = new StoreDetailGoodsAdapter(R.layout.item_home, result.getProducts());

//                if (!result.getSlideImgs().isEmpty()) {
//                    storeDetailGoodsAdapter.setHeaderView(bannerview);
//                    Banner mBannerHome = storeDetailGoodsAdapter.getHeaderLayout().findViewById(R.id.banner_storedetail);
//                    List<String> images = new ArrayList<>();
//                    for (int i = 0; i < result.getSlideImgs().size(); i++) {
//                        images.add(result.getSlideImgs().get(i).getImageUrl());
//                    }
//                    mBannerHome.setImageLoader(new GlideImageLoader());
//                    mBannerHome.setImages(images);
//                    mBannerHome.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
//                    mBannerHome.setIndicatorGravity(BannerConfig.CENTER);
//                    mBannerHome.setDelayTime(5000);
//                    mBannerHome.start();
//                }
                rv.setAdapter(storeDetailGoodsAdapter);

                storeDetailGoodsAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                    @Override
                    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                        switch (view.getId()) {
                            case R.id.ll_item:
                                Intent intent = new Intent(mActivity, GoodsDetailActivity.class);
                                intent.putExtra("id", ((StoreDetailResult.ProductsBean) adapter.getItem(position)).getId());
                                startActivity(intent);
                                break;
                        }

                    }
                });


            } else {

            }


        }


    }

    @Override
    public void PostAddFavoriteShop(PostattentionResult result) {

    }

    @Override
    public void GetVShopCategory(GetStoreSortResult result) {

    }

    @Override
    public void GetProductList(StoreCommodityResult result) {

    }

    @Override
    public void GetShopCouponList(ShopCoupResult Result) {
        if ("true".equals(Result.getSuccess())) {
            couponBeanList.clear();
            couponBeanList.addAll(Result.getCoupon());
            adapter.setNewData(couponBeanList);
        } else {
            Toast.makeText(mActivity, Result.getErrorMsg(), Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    public void PostAcceptCoupon(GetShopCoupResult Result) {
        if ("true".equals(Result.getSuccess())) {
            mPresenter.GetShopCouponList(String.valueOf(sdrResult.getVShop().getShopId()));
            Toast.makeText(mActivity, "领取成功", Toast.LENGTH_SHORT).show();
            EventBus.getDefault().post("UpdateOrderCount");//更新个人中心数量
        } else {
            Toast.makeText(mActivity, Result.getErrorMsg(), Toast.LENGTH_SHORT).show();
        }
    }

}
