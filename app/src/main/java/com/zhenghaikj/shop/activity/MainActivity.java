package com.zhenghaikj.shop.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.base.BaseActivity;
import com.zhenghaikj.shop.fragment.CartFragment;
import com.zhenghaikj.shop.fragment.ClassificationFragment;
import com.zhenghaikj.shop.fragment.HomeFragment;
import com.zhenghaikj.shop.fragment.MineFragment;
import com.zhenghaikj.shop.fragment.ShopFragment;
import com.zhenghaikj.shop.widget.CustomViewPager;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements View.OnClickListener, ViewPager.OnPageChangeListener{

    @BindView(R.id.viewPager)
    CustomViewPager mViewPager;
    @BindView(R.id.img_home)
    ImageView mImgHome;
    @BindView(R.id.tv_home)
    TextView mTvHome;
    @BindView(R.id.ll_home)
    LinearLayout mLlHome;
    @BindView(R.id.img_cate)
    ImageView mImgCate;
    @BindView(R.id.tv_cate)
    TextView mTvCate;
    @BindView(R.id.ll_category)
    LinearLayout mLlCategory;
    @BindView(R.id.img_car)
    ImageView mImgCar;
    @BindView(R.id.tv_car)
    TextView mTvCar;
    @BindView(R.id.ll_car)
    LinearLayout mLlCar;
    @BindView(R.id.img_shop)
    ImageView mImgShop;
    @BindView(R.id.tv_shop)
    TextView mTvShop;
    @BindView(R.id.ll_shop)
    LinearLayout mLlShop;
    @BindView(R.id.img_my)
    ImageView mImgMy;
    @BindView(R.id.tv_my)
    TextView mTvMy;
    @BindView(R.id.ll_mine)
    LinearLayout mLlMine;
    @BindView(R.id.tab_menu)
    LinearLayout mTabMenu;
    private ArrayList<Fragment> mFragments;
    private HomeFragment homeFragment;
    private ClassificationFragment classificationFragment;
    private CartFragment cartFragment;
    private ShopFragment shopFragment;
    private MineFragment mineFragment;

    private long mExittime;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {
        mFragments = new ArrayList<>();
        homeFragment = new HomeFragment();
        classificationFragment = new ClassificationFragment();
        cartFragment = new CartFragment();
        shopFragment = new ShopFragment();
        mineFragment = new MineFragment();
        mFragments.add(homeFragment);
        mFragments.add(classificationFragment);
        mFragments.add(cartFragment);
        mFragments.add(shopFragment);
        mFragments.add(mineFragment);

        mViewPager.setCurrentItem(0);
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
        mLlCategory.setOnClickListener(this);
        mLlCar.setOnClickListener(this);
        mLlShop.setOnClickListener(this);
        mLlMine.setOnClickListener(this);
        mViewPager.addOnPageChangeListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
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
                tabSelected(mLlCategory);
                break;
            case 2:
                tabSelected(mLlCar);
                break;
            case 3:
                tabSelected(mLlShop);
                break;
            case 4:
                tabSelected(mLlMine);
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case  R.id.ll_home:
                mViewPager.setCurrentItem(0);
                tabSelected(mLlHome);
                break;
            case  R.id.ll_category:
                mViewPager.setCurrentItem(1);
                tabSelected(mLlCategory);
                break;
            case  R.id.ll_car:
                mViewPager.setCurrentItem(2);
                tabSelected(mLlCar);
                break;
            case  R.id.ll_shop:
                mViewPager.setCurrentItem(3);
                tabSelected(mLlShop);
                break;
            case  R.id.ll_mine:
                mViewPager.setCurrentItem(4);
                tabSelected(mLlMine);
                break;
        }
    }

    private void tabSelected(LinearLayout linearLayout) {
        mLlHome.setSelected(false);
        mLlCategory.setSelected(false);
        mLlCar.setSelected(false);
        mLlShop.setSelected(false);
        mLlMine.setSelected(false);
        linearLayout.setSelected(true);
    }

    public void setCurrentItem(int i) {
        mViewPager.setCurrentItem(i,true);
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

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        /*  两秒之内再按一下退出*/
        //判断用户是否点击了返回键
        if (keyCode==KeyEvent.KEYCODE_BACK){
            //与上次点击返回键作差
            if ((System.currentTimeMillis()- mExittime)>2000){
                //大于2秒认为是误操作
                Toast.makeText(this,"再按一次退出程序",Toast.LENGTH_SHORT).show();
                //并记录记录下本次点击返回键的时刻
                mExittime =System.currentTimeMillis();
            }else {
                //小于2秒 则用户希望退出
                System.exit(0);
            }
            return true;

        }

        return super.onKeyDown(keyCode, event);
    }

}
