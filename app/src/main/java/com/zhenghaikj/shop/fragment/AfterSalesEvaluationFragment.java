package com.zhenghaikj.shop.fragment;

import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.base.BaseLazyFragment;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class AfterSalesEvaluationFragment extends BaseLazyFragment {
    @Override
    protected int setLayoutId() {
        return R.layout.fragment_after_sales_evaluation;
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
