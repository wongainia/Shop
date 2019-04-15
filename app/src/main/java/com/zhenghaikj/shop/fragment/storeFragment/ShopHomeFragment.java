package com.zhenghaikj.shop.fragment.storeFragment;

import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.base.BaseLazyFragment;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


//店铺首页
public class ShopHomeFragment extends BaseLazyFragment {
    @Override
    protected int setLayoutId() {
        return R.layout.fragment_shop_home;
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(String name) {

    }

}
