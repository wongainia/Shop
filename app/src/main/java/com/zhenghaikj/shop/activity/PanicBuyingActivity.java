package com.zhenghaikj.shop.activity;

import android.os.Bundle;
import com.google.android.material.tabs.TabLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.gyf.barlibrary.ImmersionBar;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.fragment.PanicBuyingFragment;
import com.zhenghaikj.shop.adapter.PanicBuyingAdapter;
import com.zhenghaikj.shop.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/*限时抢购*/
public class PanicBuyingActivity extends BaseActivity implements View.OnClickListener{
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
    @BindView(R.id.tabLayout)
    TabLayout mTabLayout;
    @BindView(R.id.viewPager)
    ViewPager mViewPager;

    private List<Fragment> fragmentList = new ArrayList<>();//fragment列表
    private List<String> stringList = new ArrayList<>();//tab名的列表
    private PanicBuyingAdapter adapter;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_panic_buying;
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

        fragmentList.add(new PanicBuyingFragment());
        fragmentList.add(new PanicBuyingFragment());
        fragmentList.add(new PanicBuyingFragment());
        fragmentList.add(new PanicBuyingFragment());

        stringList.add("16:00\n拍卖结束");
        stringList.add("17:00\n疯狂抢购中");
        stringList.add("18:00\n即将开始");
        stringList.add("19:00\n即将开始");


        for (int i = 0; i < stringList.size(); i++) {
            mTabLayout.addTab(mTabLayout.newTab().setText(stringList.get(i)));
        }

        adapter = new PanicBuyingAdapter(getSupportFragmentManager(), fragmentList, stringList);
        mViewPager.setAdapter(adapter);

        mTabLayout.setupWithViewPager(mViewPager);
        mViewPager.setCurrentItem(1);

    }

    @Override
    protected void initView() {
        mTvTitle.setText("限时抢购");
        mTvTitle.setVisibility(View.VISIBLE);
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
        switch (v.getId()){
            case  R.id.icon_back:
                finish();
                break;
        }
    }
}
