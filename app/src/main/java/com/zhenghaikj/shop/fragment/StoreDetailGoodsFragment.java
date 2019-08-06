package com.zhenghaikj.shop.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.activity.GoodsDetailActivity;
import com.zhenghaikj.shop.adapter.ShopDetailGoodsAdapter;
import com.zhenghaikj.shop.base.BaseLazyFragment;
import com.zhenghaikj.shop.base.BaseResult;
import com.zhenghaikj.shop.entity.GetShopCoupResult;
import com.zhenghaikj.shop.entity.GetStoreSortResult;
import com.zhenghaikj.shop.entity.PostattentionResult;
import com.zhenghaikj.shop.entity.ShopCoupResult;
import com.zhenghaikj.shop.entity.StoreCommodityResult;
import com.zhenghaikj.shop.entity.StoreDetailResult;
import com.zhenghaikj.shop.entity.UserInfo;
import com.zhenghaikj.shop.mvp.contract.StoreDetailContract;
import com.zhenghaikj.shop.mvp.model.StoreDetailModel;
import com.zhenghaikj.shop.mvp.presenter.StoreDetailPresenter;
import com.zyao89.view.zloading.ZLoadingDialog;
import com.zyao89.view.zloading.Z_TYPE;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


//全部订单
public class StoreDetailGoodsFragment extends BaseLazyFragment<StoreDetailPresenter, StoreDetailModel>implements StoreDetailContract.View {
    private static final String ARG_PARAM1 = "param1";//
    private static final String TAG = "StoreDetailGoodsFragment";
    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.smartrefresh)
    SmartRefreshLayout mSmartRefreshLayout;
    private ShopDetailGoodsAdapter shopDetailGoodsAdapter;

    private String ShopId;
    private String VShopId;
    private List<StoreCommodityResult.ProductListBean> list=new ArrayList<>();
    private ZLoadingDialog dialog;
    private int pageNo=1;

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
        dialog = new ZLoadingDialog(mActivity);
        VShopId=getActivity().getIntent().getStringExtra("VShopId");
        mPresenter.GetVShop(VShopId,userKey);
        showLoading();
        rv.setLayoutManager(new GridLayoutManager(mActivity,2));
        shopDetailGoodsAdapter=new ShopDetailGoodsAdapter(R.layout.item_home,list);
        rv.setAdapter(shopDetailGoodsAdapter);

    }

    @Override
    protected void initView() {


    }

    @Override
    protected void setListener() {
        mSmartRefreshLayout.setEnableLoadMoreWhenContentNotFull(false);
        mSmartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                pageNo++;
                mPresenter.GetProductList("10",String.valueOf(pageNo),"",ShopId,"");
                mSmartRefreshLayout.finishLoadMore();
            }
        });




    }


    @Override
    public void GetVShop(StoreDetailResult result) {
        if ("True".equals(result.getSuccess())){
            ShopId= String.valueOf(result.getVShop().getShopId());
            mPresenter.GetProductList("10",String.valueOf(pageNo),"", String.valueOf(result.getVShop().getShopId()),"");
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
         if (result.isSuccess()){
             cancleLoading();
             if (result.getTotal()==0){
                 shopDetailGoodsAdapter.setEmptyView(getEmptyView());
             }else {
               if (pageNo==1){
               list.clear();
               list.addAll(result.getProductList());
               shopDetailGoodsAdapter.notifyDataSetChanged();
               }
               else {
               list.addAll(result.getProductList());
               shopDetailGoodsAdapter.setNewData(list);
                shopDetailGoodsAdapter.notifyDataSetChanged();
               }
                 shopDetailGoodsAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                     @Override
                     public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                         switch (view.getId()){
                             case R.id.ll_item:
                                 Intent intent=new Intent(mActivity, GoodsDetailActivity.class);
                                 intent.putExtra("id",((StoreCommodityResult.ProductListBean)adapter.getItem(position)).getId());
                                 startActivity(intent);
                                 break;
                         }
                     }
                 });
             }



         }
    }

    @Override
    public void GetShopCouponList(ShopCoupResult Result) {

    }

    @Override
    public void PostAcceptCoupon(GetShopCoupResult Result) {

    }

    @Override
    public void GetUserInfoList(BaseResult<UserInfo> Result) {

    }

    public void showLoading() {
        dialog.setLoadingBuilder(Z_TYPE.ROTATE_CIRCLE)//设置类型
                .setLoadingColor(Color.BLACK)//颜色
                .setHintText("加载中请稍后...")
                .setHintTextSize(14) // 设置字体大小 dp
                .setHintTextColor(Color.BLACK)  // 设置字体颜色
                .setDurationTime(1) // 设置动画时间百分比 - 0.5倍
                .setCanceledOnTouchOutside(false)//点击外部无法取消
                .show();
    }
    public void cancleLoading() {
        if (dialog!=null){
            dialog.dismiss();
        }
    }


}
