package com.zhenghaikj.shop.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.adapter.CoinRecordListAdapter;
import com.zhenghaikj.shop.base.BaseLazyFragment;
import com.zhenghaikj.shop.base.BaseResult;
import com.zhenghaikj.shop.entity.CoinRecord;
import com.zhenghaikj.shop.entity.Data;
import com.zhenghaikj.shop.entity.GiftDetailResult;
import com.zhenghaikj.shop.mvp.contract.IntegralUseContract;
import com.zhenghaikj.shop.mvp.model.IntegralUseModel;
import com.zhenghaikj.shop.mvp.presenter.IntegralUsePresenter;
import com.zhenghaikj.shop.widget.RecyclerViewDivider;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


public class IntegralUseFragment extends BaseLazyFragment<IntegralUsePresenter, IntegralUseModel> implements IntegralUseContract.View {
    private static final String ARG_PARAM1 = "param1";//
    private static final String ARG_PARAM2 = "param2";//
    @BindView(R.id.rv_order)
    RecyclerView mRvOrder;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;

    private List<CoinRecord> coinRecordList = new ArrayList<>();

    private String mParam1;
    private CoinRecordListAdapter coinRecordListAdapter;

    public static IntegralUseFragment newInstance(String param1, String param2) {
        IntegralUseFragment fragment = new IntegralUseFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_order;
    }

    @Override
    protected void initData() {
//        mPresenter.GetUserInfoList(UserID,"1");

        getData(mParam1);
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                getData(mParam1);
                refreshLayout.finishRefresh(1000);
            }
        });
        mRefreshLayout.setEnableLoadMore(false);
    }

    @Override
    protected void initView() {
        coinRecordListAdapter = new CoinRecordListAdapter(R.layout.item_coin_record, coinRecordList);
        mRvOrder.setLayoutManager(new LinearLayoutManager(mActivity));
        mRvOrder.addItemDecoration(new RecyclerViewDivider(mActivity, LinearLayoutManager.HORIZONTAL));
        mRvOrder.setAdapter(coinRecordListAdapter);
        coinRecordListAdapter.setEmptyView(getEmptyViewCommodity());
    }

    @Override
    protected void setListener() {

    }

    public void getData(String mParam1) {
        switch (mParam1) {
            case "全部":
//                mPresenter.GetIntegralRecord("0", Integer.toString(pagaNo), "10", userKey);
                mPresenter.CoinList(UserID, "0");
                break;
            case "收入":
//                mPresenter.GetIntegralRecord("1", Integer.toString(pagaNo), "10", userKey);
                mPresenter.CoinList(UserID, "1");
                break;
            case "支出":
//                mPresenter.GetIntegralRecord("2", Integer.toString(pagaNo), "10", userKey);
                mPresenter.CoinList(UserID, "2");
                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(String name) {
        getData(name);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void GetIntegralRecord(GiftDetailResult Result) {

    }

    @Override
    public void CoinList(BaseResult<Data<List<CoinRecord>>> Result) {
        switch (Result.getStatusCode()){
            case 200:
                coinRecordList=Result.getData().getItem2();
                coinRecordListAdapter.setNewData(coinRecordList);
                break;
        }
    }
}
