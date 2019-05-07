package com.zhenghaikj.shop.adapter;

import com.zhenghaikj.shop.fragment.PhotoFragment;

import java.util.ArrayList;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class PhotoPagerAdapter extends FragmentPagerAdapter {
    private final ArrayList<String> urlList;
    public PhotoPagerAdapter(FragmentManager fm,ArrayList<String> urlList) {
        super(fm);
        this.urlList=urlList;
    }

    @Override
    public Fragment getItem(int position) {
        return PhotoFragment.newInstance(urlList.get(position));
    }

    @Override
    public int getCount() {
        return urlList.size();
    }
}
