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

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.gyf.barlibrary.ImmersionBar;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.adapter.GiftAdapter;
import com.zhenghaikj.shop.base.BaseActivity;
import com.zhenghaikj.shop.entity.Product;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GiftActivity extends BaseActivity implements View.OnClickListener {


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
    @BindView(R.id.ll_gift)
    LinearLayout mLlGift;
    @BindView(R.id.tv_all_friends)
    TextView mTvAllFriends;
    @BindView(R.id.rv_friends)
    RecyclerView mRvFriends;
    private List<Product> list = new ArrayList<>();

    @Override
    protected int setLayoutId() {
        return R.layout.activity_gift;
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

        GiftAdapter adapter = new GiftAdapter(R.layout.item_friends, list);
        mRvFriends.setLayoutManager(new LinearLayoutManager(mActivity));
        mRvFriends.setAdapter(adapter);
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()){
                    case R.id.ll_account:
                        startActivity(new Intent(mActivity,GiftDetailActivity.class));
                        break;
                }
            }
        });

    }


    @Override
    protected void initView() {
        mTvTitle.setText("赠送西瓜币");
        mTvTitle.setVisibility(View.VISIBLE);
        mTvSave.setVisibility(View.VISIBLE);
        mTvSave.setText("赠送记录");
    }

    @Override
    protected void setListener() {
        mIconBack.setOnClickListener(this);
        mTvSave.setOnClickListener(this);
        mLlGift.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.icon_back:
                finish();
                break;
            case R.id.tv_save:
                startActivity(new Intent(mActivity, GiftRecordActivity.class));
                break;
            case R.id.ll_gift:
                startActivity(new Intent(mActivity, GiftAccountActivity.class));
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
