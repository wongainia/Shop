package com.zhenghaikj.shop.fragment;

import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.adapter.FoundGoodsAdapter;
import com.zhenghaikj.shop.base.BaseLazyFragment;
import com.zhenghaikj.shop.entity.Product;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

//发现好货
public class GoodDailyFragment extends BaseLazyFragment {

    Unbinder unbinder;
    @BindView(R.id.rv_good_daily)
    RecyclerView mRvGoodDaily;
    private List<Product> foundGoodsList = new ArrayList<>();

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_good_daily;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(String name) {
    }

    @Override
    protected void initData() {
        for (int i = 0; i < 20; i++) {
            foundGoodsList.add(new Product());
        }

        FoundGoodsAdapter foundGoodsAdapter = new FoundGoodsAdapter(R.layout.item_good_daily_shop, foundGoodsList);
        mRvGoodDaily.setLayoutManager(new LinearLayoutManager(mActivity));
        mRvGoodDaily.setAdapter(foundGoodsAdapter);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void setListener() {

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
