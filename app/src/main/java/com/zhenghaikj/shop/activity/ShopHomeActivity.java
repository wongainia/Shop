package com.zhenghaikj.shop.activity;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.base.BaseActivity;
import com.zhenghaikj.shop.fragment.storeFragment.AllbabyFragment;
import com.zhenghaikj.shop.fragment.storeFragment.BabyClassificationFragment;
import com.zhenghaikj.shop.fragment.storeFragment.ContactCustomerServiceFragment;
import com.zhenghaikj.shop.fragment.storeFragment.ShopHomeFragment;
import com.zhenghaikj.shop.fragment.storeFragment.ShopMicroAmoyFragment;
import com.zhenghaikj.shop.widget.CustomViewPager;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShopHomeActivity extends BaseActivity implements View.OnClickListener , ViewPager.OnPageChangeListener{
    @BindView(R.id.viewPager)
    CustomViewPager mViewPager;
    @BindView(R.id.img_home)
    ImageView mImgHome;
    @BindView(R.id.tv_home)
    TextView mTvHome;
    @BindView(R.id.ll_home)
    LinearLayout mLlHome;
    @BindView(R.id.img_all_baby)
    ImageView mImgAllBaby;
    @BindView(R.id.tv_all_baby)
    TextView mTvAllBaby;
    @BindView(R.id.ll_all_baby)
    LinearLayout mLlAllBaby;
    @BindView(R.id.img_shop_micro_amoy)
    ImageView mImgShopMicroAmoy;
    @BindView(R.id.tv_shop_micro_amoy)
    TextView mTvShopMicroAmoy;
    @BindView(R.id.ll_shop_micro_amoy)
    LinearLayout mLlShopMicroAmoy;
    @BindView(R.id.img_classification)
    ImageView mImgClassification;
    @BindView(R.id.tv_classification)
    TextView mTvClassification;
    @BindView(R.id.ll_classification)
    LinearLayout mLlClassification;
    @BindView(R.id.img_contact_customer_service)
    ImageView mImgContactCustomerService;
    @BindView(R.id.tv_contact_customer_service)
    TextView mTvContactCustomerService;
    @BindView(R.id.ll_contact_customer_service)
    LinearLayout mLlContactCustomerService;
    @BindView(R.id.tab_menu)
    LinearLayout mTabMenu;

    private ArrayList<Fragment> mFragments=new ArrayList<>();
    private ShopHomeFragment shopHomeFragment;
    private AllbabyFragment allbabyFragment;
    private ShopMicroAmoyFragment shopMicroAmoyFragment;
    private BabyClassificationFragment babyClassificationFragment;
    private ContactCustomerServiceFragment contactCustomerServiceFragment;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_shop_home;
    }

    @Override
    protected void initData() {
        shopHomeFragment = new ShopHomeFragment();
        allbabyFragment = new AllbabyFragment();
        shopMicroAmoyFragment = new ShopMicroAmoyFragment();
        babyClassificationFragment = new BabyClassificationFragment();
        contactCustomerServiceFragment = new ContactCustomerServiceFragment();
        mFragments.add(shopHomeFragment);
        mFragments.add(allbabyFragment);
        mFragments.add(shopMicroAmoyFragment);
        mFragments.add(babyClassificationFragment);
        mFragments.add(contactCustomerServiceFragment);
    }

    @Override
    protected void initView() {
        mViewPager.setAdapter(new MyAdapter(getSupportFragmentManager()));
        mViewPager.setOffscreenPageLimit(5);
        mViewPager.setScroll(false);
        mLlHome.setSelected(true);
    }

    @Override
    protected void setListener() {
        mLlHome.setOnClickListener(this);
        mLlAllBaby.setOnClickListener(this);
        mLlShopMicroAmoy.setOnClickListener(this);
        mLlClassification.setOnClickListener(this);
        mLlContactCustomerService.setOnClickListener(this);
        mViewPager.addOnPageChangeListener(this);
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
            case R.id.ll_home:
                mViewPager.setCurrentItem(0);
                tabSelected(mLlHome);
                break;
            case R.id.ll_all_baby:
                mViewPager.setCurrentItem(1);
                tabSelected(mLlAllBaby);
                break;
            case R.id.ll_shop_micro_amoy:
                mViewPager.setCurrentItem(2);
                tabSelected(mLlShopMicroAmoy);
                break;
            case R.id.ll_classification:
                mViewPager.setCurrentItem(3);
                tabSelected(mLlClassification);
                break;
            case R.id.ll_contact_customer_service:
                mViewPager.setCurrentItem(4);
                tabSelected(mLlContactCustomerService);
                break;

        }
    }

    private void tabSelected(LinearLayout linearLayout) {
        mLlHome.setSelected(false);
        mLlAllBaby.setSelected(false);
        mLlShopMicroAmoy.setSelected(false);
        mLlClassification.setSelected(false);
        mLlContactCustomerService.setSelected(false);
        linearLayout.setSelected(true);
    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int i) {
        switch (i){
            case 0:
                tabSelected(mLlHome);
                break;
            case 1:
                tabSelected(mLlAllBaby);
                break;
            case 2:
                tabSelected(mLlShopMicroAmoy);
                break;
            case 3:
                tabSelected(mLlClassification);
                break;
            case 4:
                tabSelected(mLlContactCustomerService);
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }

    private class MyAdapter extends FragmentPagerAdapter {
        MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

    }
}
