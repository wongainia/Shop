package com.zhenghaikj.shop.activity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.gyf.barlibrary.ImmersionBar;
import com.lwkandroid.widget.stateframelayout.StateFrameLayout;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.adapter.OrderDetailAdapter;
import com.zhenghaikj.shop.base.BaseActivity;
import com.zhenghaikj.shop.entity.OrderDetail;
import com.zhenghaikj.shop.mvp.contract.OrderDetailContract;
import com.zhenghaikj.shop.mvp.model.OrderDetailModel;
import com.zhenghaikj.shop.mvp.presenter.OrderDetailPresenter;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class OrderDetailActivity extends BaseActivity<OrderDetailPresenter, OrderDetailModel> implements View.OnClickListener, OrderDetailContract.View {

    private static final String TAG = "OrderDetailActivity";
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
    @BindView(R.id.tv_ship)
    TextView mTvShip;
    @BindView(R.id.tv_time_of_receipt)
    TextView mTvTimeOfReceipt;
    @BindView(R.id.tv_logistics)
    TextView mTvLogistics;
    @BindView(R.id.tv_time)
    TextView mTvTime;
    @BindView(R.id.tv_name)
    TextView mTvName;
    @BindView(R.id.tv_phone)
    TextView mTvPhone;
    @BindView(R.id.tv_address)
    TextView mTvAddress;
    @BindView(R.id.tv_store_name)
    TextView mTvStoreName;
    @BindView(R.id.ll_store)
    LinearLayout mLlStore;
    @BindView(R.id.rv_order_list)
    RecyclerView mRvOrderList;
    @BindView(R.id.tv_total_price_of_goods)
    TextView mTvTotalPriceOfGoods;
    @BindView(R.id.tv_freight)
    TextView mTvFreight;
    @BindView(R.id.tv_freight_insurance)
    TextView mTvFreightInsurance;
    @BindView(R.id.tv_total_order_price)
    TextView mTvTotalOrderPrice;
    @BindView(R.id.tv_real_payment)
    TextView mTvRealPayment;
    @BindView(R.id.tv_order_number)
    TextView mTvOrderNumber;
    @BindView(R.id.tv_copy)
    TextView mTvCopy;
    @BindView(R.id.tv_creation_time)
    TextView mTvCreationTime;
    @BindView(R.id.tv_payment_time)
    TextView mTvPaymentTime;
    @BindView(R.id.tv_delivery_time)
    TextView mTvDeliveryTime;
    @BindView(R.id.tv_delete_order)
    TextView mTvDeleteOrder;
    @BindView(R.id.tv_view_logistics)
    TextView mTvViewLogistics;
    @BindView(R.id.tv_evaluation)
    TextView mTvEvaluation;
    @BindView(R.id.ll_all_orders)
    LinearLayout mLlAllOrders;
    @BindView(R.id.tv_see_logistics)
    TextView mTvSeeLogistics;
    @BindView(R.id.tv_extended_receipt)
    TextView mTvExtendedReceipt;
    @BindView(R.id.tv_confirm_receipt)
    TextView mTvConfirmReceipt;
    @BindView(R.id.ll_pending_receipt)
    LinearLayout mLlPendingReceipt;
    @BindView(R.id.tv_friend_pay)
    TextView mTvFriendPay;
    @BindView(R.id.tv_payment)
    TextView mTvPayment;
    @BindView(R.id.tv_buy_again)
    TextView mTvBuyAgain;
    @BindView(R.id.ll_pending_payment)
    LinearLayout mLlPendingPayment;
    @BindView(R.id.tv_watch_logistics)
    TextView mTvWatchLogistics;
    @BindView(R.id.tv_change_address)
    TextView mTvChangeAddress;
    @BindView(R.id.tv_buy)
    TextView mTvBuy;
    @BindView(R.id.ll_to_be_delivered)
    LinearLayout mLlToBeDelivered;
    @BindView(R.id.stateLayout)
    StateFrameLayout mStateLayout;
    private String userKey;
    private SPUtils spUtils;

    private ClipboardManager myClipboard;
    private ClipData myClip;


    private List<OrderDetail.OrderBean> orderBeans = new ArrayList<>();
    private List<OrderDetail.OrderItemBean> orderItemBeans = new ArrayList<>();
    private OrderDetailAdapter adapter;
    private String id;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_order_detail;
    }

    /**
     * 初始化沉浸式
     */
    @Override
    protected void initImmersionBar() {
        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar.statusBarDarkFont(true, 0.2f); //原理：如果当前设备支持状态栏字体变色，会设置状态栏字体为黑色，如果当前设备不支持状态栏字体变色，会使当前状态栏加上透明度，否则不执行透明度
        mImmersionBar.statusBarView(mView);
        mImmersionBar.keyboardEnable(true);
        mImmersionBar.init();
    }

    @Override
    protected void initData() {
        mTvTitle.setVisibility(View.VISIBLE);
        mTvTitle.setText("订单详情");

        spUtils = SPUtils.getInstance("token");
        userKey = spUtils.getString("UserKey");
        id = getIntent().getStringExtra("orderId");
        Log.d(TAG, "订单单号：" + id);
        mPresenter.GetOrderDetail(id, userKey);

        myClipboard = (ClipboardManager) mActivity.getSystemService(Context.CLIPBOARD_SERVICE);
    }

    @Override
    protected void initView() {
        mStateLayout.changeState(StateFrameLayout.LOADING);
        //是否在展示内容布局的时候开启动画（200ms的Alpha动画）
        mStateLayout.enableContentAnim(false);

        //设置网络错误重试监听【不传netRetryId的话需要在对应布局中设置触发控件的id为android:id="@id/id_sfl_net_error_retry"】
        mStateLayout.setOnNetErrorRetryListener(new StateFrameLayout.OnNetErrorRetryListener()
        {
            @Override
            public void onNetErrorRetry()
            {
                //TODO 在这里相应重试操作
                mPresenter.GetOrderDetail(id, userKey);
            }
        });
        //设置空数据重试监听【不传emptyRetryId的话需要在对应布局中设置触发控件的id为android:id="@id/id_sfl_empty_retry"】
        mStateLayout.setOnEmptyRetryListener(new StateFrameLayout.OnEmptyRetryListener()
        {
            @Override
            public void onEmptyRetry()
            {
                //TODO 在这里相应重试操作
                mPresenter.GetOrderDetail(id, userKey);
            }
        });
        adapter = new OrderDetailAdapter(R.layout.item_order_list, orderItemBeans);
        adapter.setEmptyView(getEmptyView());
        mRvOrderList.setLayoutManager(new LinearLayoutManager(mActivity));
        mRvOrderList.setAdapter(adapter);
    }

    @Override
    protected void setListener() {
        mIconBack.setOnClickListener(this);
        mTvCopy.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.icon_back:
                finish();
                break;
            case R.id.tv_copy:
                String id = mTvOrderNumber.getText().toString();
                myClip = ClipData.newPlainText("", id);
                myClipboard.setPrimaryClip(myClip);
                ToastUtils.showShort("复制成功");
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void GetOrderDetail(OrderDetail result) {

        if (result.isSuccess()) {
//           orderBeans.addAll(result.getOrder());
            mTvShip.setText(result.getOrder().getStatus());
            mTvName.setText(result.getOrder().getShipTo());
            mTvAddress.setText(result.getOrder().getAddress());
            mTvPhone.setText(result.getOrder().getPhone());
            mTvStoreName.setText(result.getOrder().getShopName());
            mTvTotalPriceOfGoods.setText(result.getOrder().getRealTotalAmount());
            mTvTotalOrderPrice.setText(result.getOrder().getRealTotalAmount());
            mTvRealPayment.setText(result.getOrder().getRealTotalAmount());
            mTvOrderNumber.setText(result.getOrder().getId());
            mTvCreationTime.setText(result.getOrder().getOrderDate());
            orderItemBeans.addAll(result.getOrderItem());
            adapter.setNewData(orderItemBeans);
//           mTvShip.setText(orderBeans.get(0).getStatus());

            if ("5".equals(result.getOrder().getOrderStatus())) {
                mLlPendingPayment.setVisibility(View.INVISIBLE);
                mLlPendingReceipt.setVisibility(View.INVISIBLE);
                mLlAllOrders.setVisibility(View.VISIBLE);
                mLlToBeDelivered.setVisibility(View.INVISIBLE);
            }

            if ("1".equals(result.getOrder().getOrderStatus())) {
                mLlPendingPayment.setVisibility(View.VISIBLE);
                mLlPendingReceipt.setVisibility(View.INVISIBLE);
                mLlAllOrders.setVisibility(View.INVISIBLE);
                mLlToBeDelivered.setVisibility(View.INVISIBLE);
            }

            if ("2".equals(result.getOrder().getOrderStatus())) {
                mLlPendingPayment.setVisibility(View.INVISIBLE);
                mLlPendingReceipt.setVisibility(View.INVISIBLE);
                mLlAllOrders.setVisibility(View.INVISIBLE);
                mLlToBeDelivered.setVisibility(View.VISIBLE);
            }

            if ("3".equals(result.getOrder().getOrderStatus())) {
                mLlPendingPayment.setVisibility(View.INVISIBLE);
                mLlPendingReceipt.setVisibility(View.VISIBLE);
                mLlAllOrders.setVisibility(View.INVISIBLE);
                mLlToBeDelivered.setVisibility(View.INVISIBLE);
            }
        }
        mStateLayout.changeState(StateFrameLayout.SUCCESS);
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(Throwable e) {
        mStateLayout.changeState(StateFrameLayout.NET_ERROR);
    }
}
