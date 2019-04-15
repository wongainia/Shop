package com.zhenghaikj.shop.fragment;

import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.base.BaseLazyFragment;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class ApplicationRecordFragment extends BaseLazyFragment {
    @Override
    protected int setLayoutId() {
        return R.layout.fragment_application_record;
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(String name) {

    }

}
