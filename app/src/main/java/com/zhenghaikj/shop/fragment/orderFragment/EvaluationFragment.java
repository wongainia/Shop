package com.zhenghaikj.shop.fragment.orderFragment;

import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.base.BaseLazyFragment;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

//待评价
public class EvaluationFragment extends BaseLazyFragment {
    @Override
    protected int setLayoutId() {
        return R.layout.fragment_evaluation;
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
