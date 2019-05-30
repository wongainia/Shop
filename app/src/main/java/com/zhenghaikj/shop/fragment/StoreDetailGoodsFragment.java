package com.zhenghaikj.shop.fragment;

import android.os.Bundle;

import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.adapter.StoreDetailGoodsAdapter;
import com.zhenghaikj.shop.base.BaseLazyFragment;
import com.zhenghaikj.shop.entity.StoreDetailGoodsEntity;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;


//全部订单
public class StoreDetailGoodsFragment extends BaseLazyFragment{
    private static final String ARG_PARAM1 = "param1";//
    private static final String TAG = "StoreDetailGoodsFragment";
    @BindView(R.id.rv)
    RecyclerView rv;

    private StoreDetailGoodsAdapter storeDetailGoodsAdapter;

    private List<StoreDetailGoodsEntity> list=new ArrayList<>();
    public static StoreDetailGoodsFragment newInstance(String param1) {
        StoreDetailGoodsFragment fragment = new StoreDetailGoodsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_storedetailgoods;
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





}
