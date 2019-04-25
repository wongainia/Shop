package com.zhenghaikj.shop.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blankj.utilcode.util.SPUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.adapter.OrderAdapter;
import com.zhenghaikj.shop.adapter.OrderListAdapter;
import com.zhenghaikj.shop.base.BaseLazyFragment;
import com.zhenghaikj.shop.entity.Bean;
import com.zhenghaikj.shop.entity.Cbean;
import com.zhenghaikj.shop.entity.Order;
import com.zhenghaikj.shop.entity.Product;
import com.zhenghaikj.shop.mvp.contract.OrderContract;
import com.zhenghaikj.shop.mvp.model.OrderModel;
import com.zhenghaikj.shop.mvp.presenter.OrderPresenter;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


//全部订单
public class OrderFragment extends BaseLazyFragment<OrderPresenter, OrderModel> implements OrderContract.View {
    private static final String ARG_PARAM1 = "param1";//
    private static final String ARG_PARAM2 = "param2";//
    private static final String TAG = "OrderFragment";
    @BindView(R.id.rv_order)
    RecyclerView mRvOrder;
    Unbinder unbinder;
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
//        cartList = new ArrayList<>();
//        //第一个店铺的数据
//        cbeanList = new ArrayList<>();
//        Cbean c = new Cbean();
//        c.setText("商品");
//        c.setIscheck(false);
//        Cbean c1 = new Cbean();
//        c1.setText("商品1");
//        c1.setIscheck(false);
//        cbeanList.add(c);
//        cbeanList.add(c1);
//        Bean b = new Bean();
//        b.setIscheck(false);
//        b.setText("店名");
//        b.setList(cbeanList);
//
//        //第二个店铺的数据
//        cbeanListcp = new ArrayList<>();
//        Cbean c2 = new Cbean();
//        c2.setText("商品2");
//        c2.setIscheck(false);
//        Cbean c3 = new Cbean();
//        c3.setText("商品3");
//        c3.setIscheck(false);
//        cbeanListcp.add(c2);
//        cbeanListcp.add(c3);
//        Bean b1 = new Bean();
//        b1.setIscheck(false);
//        b1.setText("店名1");
//        b1.setList(cbeanListcp);
//
//        //不能添加有重复变量的数据
//        cartList.add(b);
//        cartList.add(b1);



        orderListAdapter = new OrderListAdapter(R.layout.item_order,cartList,mParam1);
        mRvOrder.setLayoutManager(new LinearLayoutManager(mActivity));
        mRvOrder.setAdapter(orderListAdapter);
        getData();
//        manager = new LinearLayoutManager(mActivity);
//        mRvOrder.setLayoutManager(manager);
//        mRvOrder.setHasFixedSize(true);
//        orderAdapter = new OrderAdapter(cartList, mParam1);
//        mRvOrder.setAdapter(orderAdapter);
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
    protected void initView() {
        spUtils = SPUtils.getInstance("token");
        userKey = spUtils.getString("UserKey");
//        mPresenter.GetOrders("2", Integer.toString(pagaNo), "10", userKey);
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
                mPresenter.GetOrders("7", Integer.toString(pagaNo), "10", userKey);
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
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void GetOrders(Order result) {
        if (result.isSuccess()){
            Log.d(TAG,"00000"+result.getOrders());
            if (result.getOrders()!=null){
                cartList.addAll(result.getOrders());
//            orderAdapter = new OrderAdapter(cartList, mParam1);
                orderListAdapter.setNewData(cartList);
            }
            mRefreshLayout.finishRefresh();
        }
    }
}
