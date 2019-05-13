package com.zhenghaikj.shop.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.gyf.barlibrary.ImmersionBar;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.base.BaseActivity;
import com.zhenghaikj.shop.entity.OrderDetail;
import com.zhenghaikj.shop.utils.GlideUtil;
import com.zhenghaikj.shop.widget.AdderView;

import androidx.appcompat.widget.Toolbar;
import butterknife.BindView;
import butterknife.ButterKnife;

public class AfterSalesTypeActivity extends BaseActivity implements View.OnClickListener {
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
    @BindView(R.id.tv_store_name)
    TextView mTvStoreName;
    @BindView(R.id.iv_goods_picture)
    ImageView mIvGoodsPicture;
    @BindView(R.id.tv_goods_name)
    TextView mTvGoodsName;
    @BindView(R.id.tv_price)
    TextView mTvPrice;
    @BindView(R.id.tv_number)
    TextView mTvNumber;
    @BindView(R.id.adderview)
    AdderView mAdderview;
    @BindView(R.id.ll_service)
    LinearLayout mLlService;
    @BindView(R.id.ll_return)
    LinearLayout mLlReturn;
    @BindView(R.id.ll_exchange)
    LinearLayout mLlExchange;
    @BindView(R.id.ll_contact_customer_service)
    LinearLayout mLlContactCustomerService;
    @BindView(R.id.ll_dial_number)
    LinearLayout mLlDialNumber;
    @BindView(R.id.ll_install)
    LinearLayout mLlInstall;
    private OrderDetail.OrderItemBean bean;
    private String storeName;
    private Intent intent;
    private OrderDetail.OrderBean order;


    @Override
    protected int setLayoutId() {
        return R.layout.activity_after_sales_type;
    }

    @Override
    protected void initData() {
        bean = (OrderDetail.OrderItemBean) getIntent().getSerializableExtra("product");
        order = (OrderDetail.OrderBean) getIntent().getSerializableExtra("order");
        storeName =  getIntent().getStringExtra("storeName");
        mTvGoodsName.setText(bean.getProductName());
        GlideUtil.loadImageViewLodingRadius(mActivity,bean.getProductImage(),mIvGoodsPicture,R.drawable.image_loading,R.drawable.image_loading,10);
        mTvPrice.setText("价格：￥"+bean.getPrice());
        mTvNumber.setText("数量："+bean.getCount());
        mTvStoreName.setText(storeName);
    }

    @Override
    protected void initView() {
        mTvTitle.setText("选择售后类型");
        mTvTitle.setVisibility(View.VISIBLE);
    }

    @Override
    protected void setListener() {
        mIconBack.setOnClickListener(this);
        mLlService.setOnClickListener(this);
        mLlInstall.setOnClickListener(this);
        mLlReturn.setOnClickListener(this);
        mLlExchange.setOnClickListener(this);
        mAdderview.setOnValueChangeListene(new AdderView.OnValueChangeListener() {
            @Override
            public void onValueChange(int value) {
                if (value>Integer.parseInt(bean.getCount())){
                    ToastUtils.showShort("申请数量不能大于商品数量!");
                    mAdderview.setValue(Integer.parseInt(bean.getCount()));
                }
            }
        });
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
    public void onClick(View v) {
        intent = new Intent(mActivity, ServiceActivity.class);
        switch (v.getId()) {
            case R.id.icon_back:
                finish();
                break;
            case R.id.ll_install:
                intent.putExtra("title", "安装");
                intent.putExtra("storeName", storeName);
                intent.putExtra("product", bean);
                intent.putExtra("order", order);
                intent.putExtra("num", mAdderview.getValue()+"");
                startActivity(intent);
                break;
            case R.id.ll_service:
                intent.putExtra("title", "维修");
                intent.putExtra("storeName", storeName);
                intent.putExtra("product", bean);
                intent.putExtra("order", order);
                intent.putExtra("num", mAdderview.getValue()+"");
                startActivity(intent);
                break;
            case R.id.ll_return:
                intent.putExtra("title", "退货");
                intent.putExtra("storeName", storeName);
                intent.putExtra("product", bean);
                intent.putExtra("order", order);
                intent.putExtra("num", mAdderview.getValue()+"");
                startActivity(intent);
                break;
            case R.id.ll_exchange:
                intent.putExtra("title", "换货");
                intent.putExtra("storeName", storeName);
                intent.putExtra("product", bean);
                intent.putExtra("order", order);
                intent.putExtra("num", mAdderview.getValue()+"");
                startActivity(intent);
                break;
//            case R.id.ll_return:
//                startActivity(new Intent(mActivity, ServiceActivity.class));
//                break;
//            case R.id.ll_exchange:
//                startActivity(new Intent(mActivity, ServiceActivity.class));
//                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
