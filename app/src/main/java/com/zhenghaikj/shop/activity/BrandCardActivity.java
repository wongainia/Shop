package com.zhenghaikj.shop.activity;

import android.content.Intent;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.gyf.barlibrary.ImmersionBar;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.adapter.BrankCardAdapter;
import com.zhenghaikj.shop.base.BaseActivity;
import com.zhenghaikj.shop.entity.Product;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BrandCardActivity extends BaseActivity implements View.OnClickListener {


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
    @BindView(R.id.rv_brank_card)
    RecyclerView mRvBrankCard;
    @BindView(R.id.fl_add_card)
    FrameLayout mFlAddCard;

    private List<Product> list = new ArrayList<>();

    @Override
    protected int setLayoutId() {
        return R.layout.activity_bank_card;
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

        BrankCardAdapter adapter = new BrankCardAdapter(R.layout.item_brank_card, list);
        mRvBrankCard.setLayoutManager(new LinearLayoutManager(mActivity));
        mRvBrankCard.setAdapter(adapter);
    }

    @Override
    protected void initView() {
        mTvTitle.setText("我的银行卡");
        mTvTitle.setVisibility(View.VISIBLE);
    }

    @Override
    protected void setListener() {
        mIconBack.setOnClickListener(this);
        mFlAddCard.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.icon_back:
                finish();
                break;
            case R.id.fl_add_card:
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
