package com.zhenghaikj.shop.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.gyf.barlibrary.ImmersionBar;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.adapter.OrderListAdapter;
import com.zhenghaikj.shop.base.BaseActivity;
import com.zhenghaikj.shop.base.BaseResult;
import com.zhenghaikj.shop.entity.AddtoCartResult;
import com.zhenghaikj.shop.entity.ChangeOrderAddress;
import com.zhenghaikj.shop.entity.CloseOrder;
import com.zhenghaikj.shop.entity.ConfirmOrder;
import com.zhenghaikj.shop.entity.Data;
import com.zhenghaikj.shop.entity.EasyResult;
import com.zhenghaikj.shop.entity.Express;
import com.zhenghaikj.shop.entity.Order;
import com.zhenghaikj.shop.entity.UserInfo;
import com.zhenghaikj.shop.entity.WXpayInfo;
import com.zhenghaikj.shop.mvp.contract.OrderContract;
import com.zhenghaikj.shop.mvp.model.OrderModel;
import com.zhenghaikj.shop.mvp.presenter.OrderPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddOrderActivity extends BaseActivity<OrderPresenter, OrderModel> implements View.OnClickListener, OrderContract.View {
    @BindView(R.id.view)
    View mView;
    @BindView(R.id.icon_back)
    ImageView mIconBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_save)
    TextView mTvSave;
    @BindView(R.id.icon_search)
    ImageView mIconSearch;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.rv_work_order)
    RecyclerView mRvWorkOrder;
    private int pageIndex = 1;
    private String mParam1;
    private String mParam2;
    private String Title;

    private List<Order.OrdersBean> cartList = new ArrayList<>();
    private OrderListAdapter orderListAdapter;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_work_order;
    }

    /**
     * 初始化沉浸式
     */
    @Override
    protected void initImmersionBar() {
        mImmersionBar = ImmersionBar.with(this);
        //mImmersionBar.statusBarDarkFont(true, 0.2f); //原理：如果当前设备支持状态栏字体变色，会设置状态栏字体为黑色，如果当前设备不支持状态栏字体变色，会使当前状态栏加上透明度，否则不执行透明度
        mImmersionBar.statusBarView(mView);
        mImmersionBar.keyboardEnable(true);
        mImmersionBar.init();
    }

    @Override
    protected void initData() {
        orderListAdapter = new OrderListAdapter(R.layout.item_order, cartList, mParam1);
        mRvWorkOrder.setLayoutManager(new LinearLayoutManager(mActivity));
        mRvWorkOrder.setAdapter(orderListAdapter);
        orderListAdapter.setEmptyView(getEmptyViewCommodity());
        orderListAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.tv_trading_status:

                        break;
                }
            }
        });
    }

    @Override
    protected void initView() {
        Title =getIntent().getStringExtra("title");
        mTvTitle.setVisibility(View.VISIBLE);
        mTvTitle.setText(Title);
        mPresenter.GetOrders("0", Integer.toString(pageIndex), "10", userKey);

        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                mRefreshLayout.setNoMoreData(false);
                cartList.clear();
                pageIndex = 1;
                mPresenter.GetOrders("0", Integer.toString(pageIndex), "10", userKey);
                mRefreshLayout.finishRefresh(1000);

            }
        });



        mRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                pageIndex++;
                mPresenter.GetOrders("0", Integer.toString(pageIndex), "10", userKey);
            }
        });

    }

    @Override
    protected void setListener() {
        mIconBack.setOnClickListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.icon_back:
                finish();
                break;
        }
    }

    @Override
    public void GetOrders(Order result) {
        if (result.isSuccess()) {
            if (result.getOrders() != null) {
                cartList.addAll(result.getOrders());
                orderListAdapter.setNewData(cartList);
            }
            if (pageIndex != 1 && result.getOrders().size()==0) {
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

    @Override
    public void GetExpressInfo(Express Result) {

    }

    @Override
    public void GetOrderStr(BaseResult<Data<String>> baseResult) {

    }

    @Override
    public void GetWXOrderStr(BaseResult<Data<WXpayInfo>> baseResult) {

    }

    @Override
    public void MallBalancePay(BaseResult<Data<String>> baseResult) {

    }

    @Override
    public void WXNotifyManual(BaseResult<Data<String>> baseResult) {

    }

    @Override
    public void PostChangeOrderState(EasyResult baseResult) {

    }

    @Override
    public void CancelOrder(EasyResult baseResult) {

    }

    @Override
    public void GetUserInfoList(BaseResult<UserInfo> Result) {

    }

    @Override
    public void PostAddProductToCart(AddtoCartResult Result) {

    }

    @Override
    public void PostChangeOrderAddress(ChangeOrderAddress Result) {

    }

    @Override
    public void IsMallid(BaseResult<Data<String>> baseResult) {

    }
}
