package com.zhenghaikj.shop.fragment.orderFragment;

import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.base.BaseLazyFragment;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

//待付款
public class PendingPaymentFragment extends BaseLazyFragment {
    @Override
    protected int setLayoutId() {
        return R.layout.fragment_pending_payment;
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(String name) {

    }

}
