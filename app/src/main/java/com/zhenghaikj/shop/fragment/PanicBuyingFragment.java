package com.zhenghaikj.shop.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.adapter.LimitedTimeAdapter;
import com.zhenghaikj.shop.base.BaseLazyFragment;
import com.zhenghaikj.shop.entity.Product;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/*限时抢购*/
public class PanicBuyingFragment extends BaseLazyFragment {
    @BindView(R.id.rv_panic_buying)
    RecyclerView mRvPanicBuying;
    Unbinder unbinder;

    private ArrayList<Product> limitedTimeList=new ArrayList<>();

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_panic_buying;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(String name) {

    }

    @Override
    protected void initData() {
        super.initData();
        for (int i=0;i<10;i++){
            limitedTimeList.add(new Product());
        }
        LimitedTimeAdapter limitedTimeAdapter=new LimitedTimeAdapter(R.layout.item_panic_buying,limitedTimeList);
        mRvPanicBuying.setLayoutManager(new LinearLayoutManager(mActivity));
        mRvPanicBuying.setAdapter(limitedTimeAdapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
