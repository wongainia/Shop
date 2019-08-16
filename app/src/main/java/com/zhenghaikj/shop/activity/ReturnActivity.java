package com.zhenghaikj.shop.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.google.android.material.tabs.TabLayout;
import com.gyf.barlibrary.ImmersionBar;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.fragment.AfterSaleFragment;
import com.zhenghaikj.shop.adapter.MyPagerAdapter;
import com.zhenghaikj.shop.base.BaseActivity;
import com.zhenghaikj.shop.entity.Refund;
import com.zhenghaikj.shop.mvp.contract.RefundContract;
import com.zhenghaikj.shop.mvp.model.RefundModel;
import com.zhenghaikj.shop.mvp.presenter.RefundPresent;
import com.zhenghaikj.shop.widget.CustomViewPager;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


/*退换、售后*/
public class ReturnActivity extends BaseActivity<RefundPresent, RefundModel> implements View.OnClickListener, RefundContract.View {
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
    @BindView(R.id.tab_return_layout)
    TabLayout mTabReturnLayout;
    @BindView(R.id.et_search)
    EditText mEtSearch;
    @BindView(R.id.iv_search)
    ImageView mIvSearch;
    @BindView(R.id.ll_search)
    LinearLayout mLlSearch;
    @BindView(R.id.ll_filter)
    LinearLayout mLlFilter;
    @BindView(R.id.viewpager_return)
    CustomViewPager mViewpagerReturn;

    private int pagaNo=1;

    private ArrayList<Fragment> fragmentsList=new ArrayList<>();
    private ArrayList<String> title=new ArrayList<>();
//    private String[] title=new String[]{"售后申请","处理中","售后评价","申请记录"};


    @Override
    protected int setLayoutId() {
        return R.layout.activity_return;
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
       // mPresenter.GetRefundList(String.valueOf(pagaNo),"8",userKey);

        title.add("售后申请");
        title.add("处理中");
        title.add("售后评价");
        title.add("申请记录");

        fragmentsList.add(AfterSaleFragment.newInstance("售后申请",""));
        fragmentsList.add(AfterSaleFragment.newInstance("处理中",""));
        fragmentsList.add(AfterSaleFragment.newInstance("售后评价",""));
        fragmentsList.add(AfterSaleFragment.newInstance("申请记录",""));
        MyPagerAdapter myPagerAdapter = new MyPagerAdapter(getSupportFragmentManager(), fragmentsList, title);
        mTabReturnLayout.setTabMode(TabLayout.MODE_FIXED);
        mViewpagerReturn.setAdapter(myPagerAdapter);
        mTabReturnLayout.setupWithViewPager(mViewpagerReturn);
        mViewpagerReturn.setCurrentItem(0);
    }

    @Override
    protected void initView() {
        mTvTitle.setVisibility(View.VISIBLE);
        mTvTitle.setText("退换/售后");
    }

    @Override
    protected void setListener() {
        mIconBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.icon_back:
                finish();
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
    public void GetRefundList(Refund result) {

    }
}
