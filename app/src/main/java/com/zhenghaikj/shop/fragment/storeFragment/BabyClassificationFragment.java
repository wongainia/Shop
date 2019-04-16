package com.zhenghaikj.shop.fragment.storeFragment;

import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.base.BaseLazyFragment;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


//宝贝分类
public class BabyClassificationFragment extends BaseLazyFragment {
    @Override
    protected int setLayoutId() {
        return R.layout.fragment_baby_classification;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void setListener() {

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(String name) {

    }

}
