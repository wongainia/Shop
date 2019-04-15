package com.zhenghaikj.shop.fragment.storeFragment;

import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.base.BaseLazyFragment;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


//全部宝贝
public class AllbabyFragment extends BaseLazyFragment {
    @Override
    protected int setLayoutId() {
        return R.layout.fragment_all_baby;
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(String name) {

    }

}
