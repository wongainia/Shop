package com.zhenghaikj.shop.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by hackware on 2016/9/10.
 */

public class MyPagerAdapter extends FragmentPagerAdapter {


    private List<Fragment> fragments;
    private List<String> titles;

    /**
     * 构造函数（关联fragments,viewpager,title）
     * @param fm 碎片管理者
     * @param fragments 碎片集合
     * @param titles    标题集合
     */
    public MyPagerAdapter(FragmentManager fm,List<Fragment> fragments,List<String> titles) {
        super(fm);
        this.fragments = fragments;
        this.titles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
    //获取当前的标题
    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }

}
