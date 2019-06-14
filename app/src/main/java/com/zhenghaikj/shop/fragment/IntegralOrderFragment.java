package com.zhenghaikj.shop.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.activity.GiftOrderDetailActivity;
import com.zhenghaikj.shop.adapter.GiftOrderListAdapter;
import com.zhenghaikj.shop.base.BaseLazyFragment;
import com.zhenghaikj.shop.entity.ConfirmOrderOverResult;
import com.zhenghaikj.shop.entity.GiftDetailResult;
import com.zhenghaikj.shop.entity.GiftOrder;
import com.zhenghaikj.shop.entity.GiftOrderDetail;
import com.zhenghaikj.shop.mvp.contract.IntegralOrderContract;
import com.zhenghaikj.shop.mvp.model.IntegralOrderModel;
import com.zhenghaikj.shop.mvp.presenter.IntegralOrderPresenter;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


//全部订单
public class IntegralOrderFragment extends BaseLazyFragment<IntegralOrderPresenter, IntegralOrderModel> implements IntegralOrderContract.View {
    private static final String ARG_PARAM1 = "param1";//
    private static final String ARG_PARAM2 = "param2";//
    private static final String TAG = "IntegralOrderFragment";
    @BindView(R.id.rv_order)
    RecyclerView mRvOrder;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;

    private List<GiftOrder.ModelsBean> giftOrderList = new ArrayList<>();

    private RecyclerView.LayoutManager manager;
    private SPUtils spUtils;
    private String userKey;
    private int pagaNo = 1;
    private String mParam1;
    private GiftOrderListAdapter orderListAdapter;
    private int receiptPos;

    public static IntegralOrderFragment newInstance(String param1, String param2) {
        IntegralOrderFragment fragment = new IntegralOrderFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_order;
    }

    @Override
    protected void initData() {
        spUtils = SPUtils.getInstance("token");
        userKey = spUtils.getString("UserKey");
//        userName = spUtils.getString("userName2");
//        mPresenter.GetUserInfoList(userName,"1");
        giftOrderList.clear();
        pagaNo = 1;
        getData(mParam1);
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                mRefreshLayout.setNoMoreData(false);
                giftOrderList.clear();
                pagaNo = 1;
                getData(mParam1);
            }
        });
        mRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                pagaNo++;
                getData(mParam1);
            }
        });
    }

    @Override
    protected void initView() {
        orderListAdapter = new GiftOrderListAdapter(R.layout.item_gift_order, giftOrderList);
        mRvOrder.setLayoutManager(new LinearLayoutManager(mActivity));
        mRvOrder.setAdapter(orderListAdapter);
        orderListAdapter.setEmptyView(getEmptyViewOrder());
        orderListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(mActivity, GiftOrderDetailActivity.class);
                intent.putExtra("id",giftOrderList.get(position).getId());
                startActivity(intent);
            }
        });
        orderListAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.tv_confirm_receipt:
                        receiptPos =position;
                        mPresenter.ConfirmOrderOver(giftOrderList.get(position).getId(),userKey);
                        break;
                }
            }
        });


    }

    @Override
    protected void setListener() {
        mRefreshLayout.setEnableLoadMoreWhenContentNotFull(false);
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                mRefreshLayout.setNoMoreData(false);
                giftOrderList.clear();
                pagaNo = 1;
                getData(mParam1);
                mRefreshLayout.finishRefresh(1000);

            }
        });
        mRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                pagaNo++;
                getData(mParam1);
            }
        });
    }
    /*<li data-status="0">全部订单</li>
    <li data-status="2">待发货</li>
    <li data-status="3">待收货</li>
    <li data-status="5">已完成</li>*/
    public void getData(String mParam1) {
        switch (mParam1) {
            case "全部":
                mPresenter.GetMyOrderList("0", Integer.toString(pagaNo),  userKey);
                break;
            case "待发货":
                mPresenter.GetMyOrderList("2", Integer.toString(pagaNo),  userKey);
                break;
            case "待收货":
                mPresenter.GetMyOrderList("3", Integer.toString(pagaNo),  userKey);
                break;
            case "已完成":
                mPresenter.GetMyOrderList("5", Integer.toString(pagaNo),  userKey);
                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(String name) {
        getData(name);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void GetMyOrderList(GiftOrder Result) {
        if (Result.getModels() != null) {
            giftOrderList.addAll(Result.getModels());
            orderListAdapter.setNewData(giftOrderList);
        }
        if (pagaNo != 1 && Result.getModels().size()==0) {
            mRefreshLayout.finishLoadMoreWithNoMoreData();
        } else {
            mRefreshLayout.finishLoadMore();
        }
    }

    @Override
    public void GetOrderCount(GiftDetailResult Result) {

    }

    @Override
    public void ConfirmOrderOver(ConfirmOrderOverResult Result) {
        if (Result.isSuccess()){
            giftOrderList.clear();
            pagaNo=1;
            getData(mParam1);
        }
        ToastUtils.showShort(Result.getMsg());
    }

    @Override
    public void GetOrder(GiftOrderDetail Result) {

    }
}
