package com.zhenghaikj.shop.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.activity.GoodsDetailActivity;
import com.zhenghaikj.shop.activity.SimilarActivity;
import com.zhenghaikj.shop.adapter.CommodityAdapter;
import com.zhenghaikj.shop.base.BaseLazyFragment;
import com.zhenghaikj.shop.dialog.CommonDialog_Home;
import com.zhenghaikj.shop.entity.CollectResult;
import com.zhenghaikj.shop.entity.CollectionProduct;
import com.zhenghaikj.shop.mvp.contract.CollectionProductContract;
import com.zhenghaikj.shop.mvp.model.CollectionProductModel;
import com.zhenghaikj.shop.mvp.presenter.CollectionProductPresenter;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class CommodityFragment extends BaseLazyFragment<CollectionProductPresenter, CollectionProductModel> implements CollectionProductContract.View {
    private static final String ARG_PARAM1 = "param1";//
    private static final String ARG_PARAM2 = "param2";//
    private static final String TAG = "CommodityFragment";//
    @BindView(R.id.tv_category)
    TextView mTvCategory;
    @BindView(R.id.ll_category)
    LinearLayout mLlCategory;
    @BindView(R.id.tv_status)
    TextView mTvStatus;
    @BindView(R.id.ll_status)
    LinearLayout mLlStatus;
    @BindView(R.id.rv_commodity)
    RecyclerView mRvCommodity;

    @BindView(R.id.cb_circle_management)
    CheckBox mCbCircleManagement;
    @BindView(R.id.tv_smart_cleaning)
    TextView mTvSmartCleaning;
    @BindView(R.id.tv_delete)
    TextView mTvDelete;
   @BindView(R.id.ll_management)
    LinearLayout mLlManagement;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    private ArrayList<CollectionProduct.DataBean> commodityList = new ArrayList<>();
    private List<CollectResult.DataBean>  idList=new ArrayList<>();
    private String mParam1;
    private String mParam2;
    private CommodityAdapter commodityAdapter;
    private int pagaNo = 1;
    private Intent intent;

    public static CommodityFragment newInstance(String param1, String param2) {
        CommodityFragment fragment = new CommodityFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_commodity;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(String name) {

    }

    @Override
    protected void initData() {
        mPresenter.GetUserCollectionProduct(Integer.toString(pagaNo),"10",userKey);

    }

    @Override
    protected void initView() {

        commodityAdapter = new CommodityAdapter(R.layout.item_commodity, commodityList);
        mRvCommodity.setLayoutManager(new LinearLayoutManager(mActivity));
        mRvCommodity.setAdapter(commodityAdapter);
        commodityAdapter.setEmptyView(getEmptyViewCommodity());
        commodityAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(final BaseQuickAdapter adapter, View view, final int position) {
                switch (view.getId()){
                    case R.id.tv_delete:
                        Log.d(TAG, "onItemChildClick: "+((CollectionProduct.DataBean)adapter.getItem(position)).getId());
                        mPresenter.PostAddFavoriteProduct((((CollectionProduct.DataBean)adapter.getItem(position)).getId()),userKey);
//                        mRefreshLayout.autoRefresh();
                        adapter.remove(position);
                        break;
                    case R.id.ll_commodity:
                        Intent intent=new Intent(mActivity, GoodsDetailActivity.class);
                        intent.putExtra("id", commodityList.get(position).getId());
                        startActivity(intent);
                        break;
                    case R.id.tv_find_similar:
                        intent =new Intent(mActivity, SimilarActivity.class);
                        intent.putExtra("productId", commodityList.get(position).getId());
                        intent.putExtra("categoryId", commodityList.get(position).getCategoryId());
                        startActivity(intent);
                        break;
                }
            }
        });

        commodityAdapter.setOnItemChildLongClickListener(new BaseQuickAdapter.OnItemChildLongClickListener() {
            @Override
            public boolean onItemChildLongClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()){
                    case R.id.ll_commodity:
                        final CommonDialog_Home dialog = new CommonDialog_Home(getActivity());
                        dialog.setMessage("是否删除商品")
                                //.setImageResId(R.mipmap.ic_launcher)
                                .setTitle("提示")
                                .setSingle(false).setOnClickBottomListener(new CommonDialog_Home.OnClickBottomListener() {
                            @Override
                            public void onPositiveClick() {//拨打电话
                                dialog.dismiss();
                                mPresenter.PostAddFavoriteProduct((((CollectionProduct.DataBean)adapter.getItem(position)).getId()),userKey);
//                                mRefreshLayout.autoRefresh();
                                adapter.remove(position);
                            }

                            @Override
                            public void onNegtiveClick() {//取消
                                dialog.dismiss();
                                // Toast.makeText(MainActivity.this,"ssss",Toast.LENGTH_SHORT).show();
                            }
                        }).show();
                        break;
                }
                return true;
            }
        });

        /*下拉刷新*/
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
          /*      if (!list.isEmpty()){ //当有数据的时候
                    ll_empty.setVisibility(View.INVISIBLE);//隐藏空的界面
                }*/
                pagaNo=1;
                commodityList.clear();
                mPresenter.GetUserCollectionProduct(Integer.toString(pagaNo),"10",userKey);
                commodityAdapter.notifyDataSetChanged();
                refreshlayout.finishRefresh(1000);
                mRefreshLayout.setNoMoreData(false);
            }
        });


        //没满屏时禁止上拉
        mRefreshLayout.setEnableLoadMoreWhenContentNotFull(false);
        //上拉加载更多


        mRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                pagaNo++; //页数加1
                mPresenter.GetUserCollectionProduct(Integer.toString(pagaNo),"10",userKey);
                commodityAdapter.notifyDataSetChanged();
                refreshLayout.finishLoadMore();
            }
        });

    }

    @Override
    protected void setListener() {

    }

    @Override
    public void GetUserCollectionProduct(CollectionProduct result) {
        if (result.isSuccess()){
            commodityList.addAll(result.getData());
            commodityAdapter.setNewData(commodityList);
        }
    }

    @Override
    public void PostAddFavoriteProduct(CollectResult Result) {
        if (Result.isSuccess()){
            idList=Result.getData();
//            idList.addAll(Result.getData());
            ToastUtils.showShort("删除成功");
        }
    }
}
