package com.zhenghaikj.shop.activity;

import android.content.Intent;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gyf.barlibrary.ImmersionBar;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.adapter.GiftRecordAdapter;
import com.zhenghaikj.shop.base.BaseActivity;
import com.zhenghaikj.shop.entity.Product;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GiftRecordActivity extends BaseActivity implements View.OnClickListener {


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
    @BindView(R.id.tv_time)
    TextView mTvTime;
    @BindView(R.id.ll_choose_time)
    LinearLayout mLlChooseTime;
    @BindView(R.id.tv_transfer_out)
    TextView mTvTransferOut;
    @BindView(R.id.tv_roger_that)
    TextView mTvRogerThat;
    @BindView(R.id.rv_gift_recode)
    RecyclerView mRvGiftRecode;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    private List<Product> list = new ArrayList<>();

    @Override
    protected int setLayoutId() {
        return R.layout.activity_gift_record;
    }

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
        for (int i = 0; i < 10; i++) {
            list.add(new Product());
        }

        GiftRecordAdapter adapter = new GiftRecordAdapter(R.layout.item_gift_record, list);
        mRvGiftRecode.setLayoutManager(new LinearLayoutManager(mActivity));
        mRvGiftRecode.setAdapter(adapter);

    }


    @Override
    protected void initView() {
        mTvTitle.setText("赠送记录");
        mTvTitle.setVisibility(View.VISIBLE);
    }

    @Override
    protected void setListener() {
        mIconBack.setOnClickListener(this);
        mTvSave.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.icon_back:
                finish();
                break;
            case R.id.tv_save:
                startActivity(new Intent(mActivity, AddBrankCardActivity.class));
                break;

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
