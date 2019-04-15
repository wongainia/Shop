package com.zhenghaikj.shop.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.adapter.CommodityAdapter;
import com.zhenghaikj.shop.base.BaseLazyFragment;
import com.zhenghaikj.shop.entity.Product;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class CommodityFragment extends BaseLazyFragment {
    private static final String ARG_PARAM1 = "param1";//
    private static final String ARG_PARAM2 = "param2";//
    @BindView(R.id.tv_category)
    TextView mTvCategory;
    @BindView(R.id.ll_category)
    LinearLayout mLlCategory;
    @BindView(R.id.tv_status)
    TextView mTvStatus;
    @BindView(R.id.ll_status)
    LinearLayout mLlStatus;
    @BindView(R.id.rv_commodity)
    RecyclerView mRvCommodity;
    Unbinder unbinder;
    private ArrayList<Product> commodityList=new ArrayList<>();

    private String mParam1;
    private String mParam2;
    private CommodityAdapter commodityAdapter;

    public static CommodityFragment newInstance(String param1, String param2) {
        CommodityFragment fragment = new CommodityFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_commodity;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(String name) {

    }

    @Override
    protected void initData() {
        super.initData();
        for (int i=0;i<10;i++){
            commodityList.add(new Product());
        }
        commodityAdapter = new CommodityAdapter(R.layout.item_commodity,commodityList);
        mRvCommodity.setLayoutManager(new LinearLayoutManager(mActivity));
        mRvCommodity.setAdapter(commodityAdapter);
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
