package com.zhenghaikj.shop.activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.base.BaseActivity;
import com.zhenghaikj.shop.fragment.CartFragment;
import com.zhenghaikj.shop.fragment.ClassificationFragment;
import com.zhenghaikj.shop.fragment.HomeFragment;
import com.zhenghaikj.shop.fragment.MineFragment;
import com.zhenghaikj.shop.fragment.ShopFragment;
import com.zhenghaikj.shop.fragment.ShopFragment2;
import com.zhenghaikj.shop.widget.CustomViewPager;
import com.zhenghaikj.shop.widget.StarBarView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

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
    private ShopFragment2 shopFragment;
    private MineFragment mineFragment;

    private long mExittime;
    private View view;
    private TextView tv_orderid;
    private TextView tv_serach;
    private TextView tv_totle_grade;
    private StarBarView good_star;
    private TextView tv_good_content;
    private StarBarView shangmen_star;
    private TextView tv_shangmen_content;
    private StarBarView weixiu_star;
    private TextView tv_weixiu_content;
    private StarBarView fuwu_star;
    private TextView tv_fuwu_content;
    private AlertDialog EvalateDialog;
    private Window window;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {
        setSwipeBackEnable(false);
        mFragments = new ArrayList<>();
        homeFragment = new HomeFragment();
        classificationFragment = new ClassificationFragment();
        cartFragment = new CartFragment();
        shopFragment = new ShopFragment2();
        mineFragment = new MineFragment();
        mFragments.add(HomeFragment.newInstance("",""));
        mFragments.add(ClassificationFragment.newInstance("",""));
        mFragments.add(CartFragment.newInstance("",""));
        mFragments.add(ShopFragment2.newInstance("",""));
        mFragments.add(MineFragment.newInstance("",""));

        mViewPager.setCurrentItem(0);
    }

    @Override
    protected void initView() {
        mViewPager.setAdapter(new MyAdapter(getSupportFragmentManager()));
        mViewPager.setOffscreenPageLimit(5);
        mViewPager.setScroll(false);
        mLlHome.setSelected(true);

//        showOrderEvaluate();
    }

    /*弹出确认工单评价*/
    public void showOrderEvaluate() {
        view = LayoutInflater.from(mActivity).inflate(R.layout.dialog_evaluate, null);
        ImageView iv_close = view.findViewById(R.id.iv_close);
        tv_orderid = view.findViewById(R.id.tv_orderid);
        tv_serach = view.findViewById(R.id.tv_serach);

        tv_totle_grade = view.findViewById(R.id.tv_totle_grade);
        good_star = view.findViewById(R.id.good_star);
        tv_good_content = view.findViewById(R.id.tv_good_content);
//        tv_totle_grade.setText(""+good_star.getStar());
        shangmen_star = view.findViewById(R.id.shangmen_star);
        tv_shangmen_content = view.findViewById(R.id.tv_shangmen_content);
        weixiu_star = view.findViewById(R.id.weixiu_star);
        tv_weixiu_content = view.findViewById(R.id.tv_weixiu_content);
        fuwu_star = view.findViewById(R.id.fuwu_star);
        tv_fuwu_content = view.findViewById(R.id.tv_fuwu_content);
        good_star.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        iv_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EvalateDialog.dismiss();
            }
        });
        EvalateDialog = new AlertDialog.Builder(mActivity).setView(view).create();
        EvalateDialog.show();
        window = EvalateDialog.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        window.setAttributes(lp);
        window.setBackgroundDrawable(new ColorDrawable());
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
                if (!isLogin){
                    startActivity(new Intent(mActivity,LoginActivity.class));
                    return;
                }
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
                if (!isLogin){
                    startActivity(new Intent(mActivity,LoginActivity.class));
                    return;
                }
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

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(String name) {
        if ("更新登录信息".equals(name)){
            getLoginMsg();
        }
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
