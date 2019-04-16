package com.zhenghaikj.shop.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

public class FoundGoodGoodsAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragmentList;//fragment列表
    private List<String> stringList;//tab名的列表

    public FoundGoodGoodsAdapter(FragmentManager fm, List<Fragment> fragmentList, List<String> stringList) {
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
