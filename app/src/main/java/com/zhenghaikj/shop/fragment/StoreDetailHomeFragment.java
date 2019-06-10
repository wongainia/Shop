package com.zhenghaikj.shop.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.blankj.utilcode.util.SPUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.activity.GoodsDetailActivity;
import com.zhenghaikj.shop.adapter.StoreDetailGoodsAdapter;
import com.zhenghaikj.shop.base.BaseLazyFragment;
import com.zhenghaikj.shop.entity.GetStoreSortResult;
import com.zhenghaikj.shop.entity.PostattentionResult;
import com.zhenghaikj.shop.entity.StoreCommodityResult;
import com.zhenghaikj.shop.entity.StoreDetailGoodsEntity;
import com.zhenghaikj.shop.entity.StoreDetailResult;
import com.zhenghaikj.shop.mvp.contract.StoreDetailContract;
import com.zhenghaikj.shop.mvp.model.StoreDetailModel;
import com.zhenghaikj.shop.mvp.presenter.StoreDetailPresenter;
import com.zhenghaikj.shop.utils.GlideImageLoader;
import com.zhenghaikj.shop.widget.GlideRoundCropTransform;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;


public class StoreDetailHomeFragment extends BaseLazyFragment<StoreDetailPresenter, StoreDetailModel> implements StoreDetailContract.View {
    private static final String ARG_PARAM1 = "param1";//
    private static final String TAG = "StoreDetailHomeFragment";
    @BindView(R.id.rv)
    RecyclerView rv;
    private String Userkey;
    private SPUtils spUtils = SPUtils.getInstance("token");

    private StoreDetailGoodsAdapter storeDetailGoodsAdapter;



    private View bannerview;
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
        bannerview= LayoutInflater.from(mActivity).inflate(R.layout.item_banner,null);

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
               rv.setLayoutManager(new LinearLayoutManager(mActivity));
               storeDetailGoodsAdapter=new StoreDetailGoodsAdapter(R.layout.item_store_goods,result.getProducts());

               if (!result.getSlideImgs().isEmpty()){
                   storeDetailGoodsAdapter.setHeaderView(bannerview);
                   Banner mBannerHome=storeDetailGoodsAdapter.getHeaderLayout().findViewById(R.id.banner_storedetail);
                   List<String> images = new ArrayList<>();
                   for (int i = 0; i < result.getSlideImgs().size(); i++) {
                       images.add(result.getSlideImgs().get(i).getImageUrl());
                   }
                   mBannerHome.setImageLoader(new GlideImageLoader());
                   mBannerHome.setImages(images);
                   mBannerHome.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
                   mBannerHome.setIndicatorGravity(BannerConfig.CENTER);
                   mBannerHome.setDelayTime(5000);
                   mBannerHome.start();
               }
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


           }else {

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

}
