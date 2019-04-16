package com.zhenghaikj.shop.fragment;

import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.adapter.StoreAdapter;
import com.zhenghaikj.shop.base.BaseLazyFragment;
import com.zhenghaikj.shop.entity.Product;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class StoreFragment extends BaseLazyFragment {

    private static final String ARG_PARAM1 = "param1";//
    private static final String ARG_PARAM2 = "param2";//
    @BindView(R.id.tv_category)
    TextView mTvCategory;
    @BindView(R.id.rv_store)
    RecyclerView mRvStore;
    Unbinder unbinder;
    private List<Product> storeList=new ArrayList<>();

    private String mParam1;
    private String mParam2;
    private StoreAdapter storeAdapter;

    public static StoreFragment newInstance(String param1, String param2) {
        StoreFragment fragment = new StoreFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_store;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(String name) {

    }

    @Override
    protected void initData() {
        for (int i=0;i<10;i++){
            storeList.add(new Product());
        }
        storeAdapter = new StoreAdapter(R.layout.item_store,storeList);
        mRvStore.setLayoutManager(new LinearLayoutManager(mActivity));
        mRvStore.setAdapter(storeAdapter);
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
