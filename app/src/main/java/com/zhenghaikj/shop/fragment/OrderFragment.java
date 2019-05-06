package com.zhenghaikj.shop.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.blankj.utilcode.util.SPUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.adapter.OrderListAdapter;
import com.zhenghaikj.shop.base.BaseLazyFragment;
import com.zhenghaikj.shop.entity.CloseOrder;
import com.zhenghaikj.shop.entity.ConfirmOrder;
import com.zhenghaikj.shop.entity.Order;
import com.zhenghaikj.shop.mvp.contract.OrderContract;
import com.zhenghaikj.shop.mvp.model.OrderModel;
import com.zhenghaikj.shop.mvp.presenter.OrderPresenter;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;


//全部订单
public class OrderFragment extends BaseLazyFragment<OrderPresenter, OrderModel> implements OrderContract.View {
    private static final String ARG_PARAM1 = "param1";//
    private static final String ARG_PARAM2 = "param2";//
    private static final String TAG = "OrderFragment";
    @BindView(R.id.rv_order)
    RecyclerView mRvOrder;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;

    private List<Order.OrdersBean> cartList = new ArrayList<>();

    private RecyclerView.LayoutManager manager;
    //    private OrderAdapter orderAdapter;
    private SPUtils spUtils;
    private String userKey;
    private int pagaNo = 1;
    private String mParam1;
    private OrderListAdapter orderListAdapter;

    public static OrderFragment newInstance(String param1, String param2) {
        OrderFragment fragment = new OrderFragment();
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

        getData();
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                mRefreshLayout.setNoMoreData(false);
                cartList.clear();
                pagaNo = 1;
                getData();
            }
        });
        mRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                pagaNo++;
                getData();
            }
        });

//        mPresenter.PostCloseOrder("2017021489566321",userKey);
    }

    @Override
    protected void initView() {

        orderListAdapter = new OrderListAdapter(R.layout.item_order, cartList, mParam1);
        mRvOrder.setLayoutManager(new LinearLayoutManager(mActivity));
        mRvOrder.setAdapter(orderListAdapter);
        orderListAdapter.setEmptyView(getEmptyViewCommodity());
        orderListAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.tv_trading_status:
//                        startActivity(new Intent(mActivity, OrderDetailActivity.class));
                        mPresenter.PostCloseOrder(cartList.get(position).getId(),userKey);
                        break;
                    case R.id.tv_delete_order:
                        Log.d(TAG,"编号："+cartList.get(position).getId());
                        mPresenter.PostCloseOrder(cartList.get(position).getId(),userKey);
                        break;
                    case R.id.tv_confirm_receipt:
                        mPresenter.PostConfirmOrder(cartList.get(position).getId(),userKey);
                        break;
                }
            }
        });

        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                mRefreshLayout.setNoMoreData(false);
                cartList.clear();
                pagaNo = 1;
                getData();
            }
        });
        mRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                pagaNo++;
                getData();
            }
        });
    }

    @Override
    protected void setListener() {

    }

    public void getData() {
        switch (mParam1) {
            case "全部":
                mPresenter.GetOrders("0", Integer.toString(pagaNo), "10", userKey);
                break;
            case "待付款":
                mPresenter.GetOrders("1", Integer.toString(pagaNo), "10", userKey);
                break;
            case "待发货":
                mPresenter.GetOrders("2", Integer.toString(pagaNo), "10", userKey);
                break;
            case "待收货":
                mPresenter.GetOrders("3", Integer.toString(pagaNo), "10", userKey);
                break;
            case "待评价":
                mPresenter.GetOrders("5", Integer.toString(pagaNo), "10", userKey);
                break;
        }
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(String name) {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }


    @Override
    public void GetOrders(Order result) {
        if (result.isSuccess()) {
            Log.d(TAG, "00000" + result.getOrders());
            if (result.getOrders() != null) {
                cartList.addAll(result.getOrders());
//            orderAdapter = new OrderAdapter(cartList, mParam1);
                orderListAdapter.setNewData(cartList);
            }
            mRefreshLayout.finishRefresh();
            if (pagaNo != 1 && "0".equals(result.getOrders().size())) {
                mRefreshLayout.finishLoadMoreWithNoMoreData();
            } else {
                mRefreshLayout.finishLoadMore();
            }
        }
    }

    @Override
    public void PostCloseOrder(CloseOrder Result) {

    }

    @Override
    public void PostConfirmOrder(ConfirmOrder Result) {

    }
}
