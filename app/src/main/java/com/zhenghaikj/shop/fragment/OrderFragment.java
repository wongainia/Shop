package com.zhenghaikj.shop.fragment;

import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blankj.utilcode.util.SPUtils;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.adapter.OrderAdapter;
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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


//全部订单
public class OrderFragment extends BaseLazyFragment<OrderPresenter, OrderModel> implements OrderContract.View {
    private static final String ARG_PARAM1 = "param1";//
    private static final String ARG_PARAM2 = "param2";//
    @BindView(R.id.rv_order)
    RecyclerView mRvOrder;
    Unbinder unbinder;
    private List<Bean> cartList = new ArrayList<>();
    private List<Cbean> cbeanList, cbeanListcp;
    private List<Product> shopTypeList = new ArrayList<>();

    private RecyclerView.LayoutManager manager;
    private OrderAdapter orderAdapter;
    private SPUtils spUtils;
    private String userKey;
    private int pagaNo = 1;

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
        cartList = new ArrayList<>();
        //第一个店铺的数据
        cbeanList = new ArrayList<>();
        Cbean c = new Cbean();
        c.setText("商品");
        c.setIscheck(false);
        Cbean c1 = new Cbean();
        c1.setText("商品1");
        c1.setIscheck(false);
        cbeanList.add(c);
        cbeanList.add(c1);
        Bean b = new Bean();
        b.setIscheck(false);
        b.setText("店名");
        b.setList(cbeanList);

        //第二个店铺的数据
        cbeanListcp = new ArrayList<>();
        Cbean c2 = new Cbean();
        c2.setText("商品2");
        c2.setIscheck(false);
        Cbean c3 = new Cbean();
        c3.setText("商品3");
        c3.setIscheck(false);
        cbeanListcp.add(c2);
        cbeanListcp.add(c3);
        Bean b1 = new Bean();
        b1.setIscheck(false);
        b1.setText("店名1");
        b1.setList(cbeanListcp);

        //不能添加有重复变量的数据
        cartList.add(b);
        cartList.add(b1);


        manager = new LinearLayoutManager(mActivity);
        mRvOrder.setLayoutManager(manager);
        mRvOrder.setHasFixedSize(true);
        orderAdapter = new OrderAdapter(cartList);
        mRvOrder.setAdapter(orderAdapter);
    }

    @Override
    protected void initView() {
        spUtils = SPUtils.getInstance("token");
        userKey = spUtils.getString("UserKey");
        mPresenter.GetOrders("2", Integer.toString(pagaNo), "10",userKey);
    }

    @Override
    protected void setListener() {

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(String name) {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void GetOrders(Order result) {

    }
}
