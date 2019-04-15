package com.zhenghaikj.shop.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

public class GoodsdailyShopAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragmentList;//fragment列表
    private List<String> stringList;//tab名的列表

    public GoodsdailyShopAdapter(FragmentManager fm, List<Fragment> fragmentList, List<String> stringList) {
        super(fm);
        if (fragmentList.size() != stringList.size()) {
            try {
                throw new Exception("fragmentList.size must equals stringList.size!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        this.fragmentList = fragmentList;
        this.stringList = stringList;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return stringList.size();
    }

    //此方法用来显示tab上的名字
    @Override
    public CharSequence getPageTitle(int position) {
        return stringList.get(position % stringList.size());
    }

}
