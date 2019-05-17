package com.zhenghaikj.shop.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.PopupWindow;

import com.blankj.utilcode.util.SPUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.adapter.OrderListAdapter;
import com.zhenghaikj.shop.base.BaseLazyFragment;
import com.zhenghaikj.shop.entity.Order;
import com.zhenghaikj.shop.entity.UserCouponListResult;
import com.zhenghaikj.shop.mvp.contract.CouponContract;
import com.zhenghaikj.shop.mvp.model.CouponModel;
import com.zhenghaikj.shop.mvp.presenter.CouponPresenter;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

public class CouponFragment extends BaseLazyFragment<CouponPresenter, CouponModel> implements CouponContract.View {
    private static final String ARG_PARAM1 = "param1";//
    private static final String ARG_PARAM2 = "param2";//
    private static final String TAG = "CouponFragment";
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
    private View popupWindow_view;
    private PopupWindow mPopupWindow;
    private int receipt_position;
    public static CouponFragment newInstance(String param1, String param2) {
        CouponFragment fragment = new CouponFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_coupon;
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
        orderListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

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
            case "0":
                mPresenter.GetUserCounponList(userKey);
                break;
            case "1":
                mPresenter.GetUserCounponList(userKey);
                break;
        }
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(String name) {
        switch (name){
            case "evaluate":
//                mPresenter.GetOrders("5", Integer.toString(pagaNo), "10", userKey);
                break;

        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public void GetUserCounponList(UserCouponListResult baseResult) {

    }
}
