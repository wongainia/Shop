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
import com.zhenghaikj.shop.adapter.RecordingAdapter;
import com.zhenghaikj.shop.base.BaseLazyFragment;
import com.zhenghaikj.shop.base.BaseResult;
import com.zhenghaikj.shop.entity.BankCard;
import com.zhenghaikj.shop.entity.Bill;
import com.zhenghaikj.shop.entity.Data;
import com.zhenghaikj.shop.entity.UserInfo;
import com.zhenghaikj.shop.mvp.contract.WalletContract;
import com.zhenghaikj.shop.mvp.model.WalletModel;
import com.zhenghaikj.shop.mvp.presenter.WalletPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class RecordFragment extends BaseLazyFragment<WalletPresenter, WalletModel> implements WalletContract.View {
    private static final String ARG_PARAM1 = "param1";//
    private static final String ARG_PARAM2 = "param2";//
    @BindView(R.id.rv_record)
    RecyclerView mRvRecord;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    private String mParam1;

    private RecordingAdapter wallet_record_adapter;
    private List<Bill.DataBean> recharge_list = new ArrayList<>();//充值记录
    private List<Bill.DataBean> withdraw_list = new ArrayList<>();//提现记录
    private List<Bill.DataBean> expend_income_list = new ArrayList<>();//收入支出记录


    public static RecordFragment newInstance(String param1, String param2) {
        RecordFragment fragment = new RecordFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_record;
    }

    @Override
    protected void initView() {
    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void initData() {
        getData(mParam1);
        mRvRecord.setLayoutManager(new LinearLayoutManager(mActivity));
                              /*  recyclerView.setHasFixedSize(true);
                                recyclerView.setNestedScrollingEnabled(false);*/
        wallet_record_adapter = new RecordingAdapter(R.layout.item_recording, recharge_list);
        mRvRecord.setAdapter(wallet_record_adapter);
        wallet_record_adapter.setEmptyView(getEmptyViewCommodity());
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                getData(mParam1);
                refreshLayout.finishRefresh(1000);
            }
        });
        mRefreshLayout.setEnableLoadMore(false);
    }

    public void getData(String mParam1) {
        switch (mParam1) {
            case "收支":
//                mPresenter.GetIntegralRecord("0", Integer.toString(pagaNo), "10", userKey);
                mPresenter.AccountBill(UserID, "2,5,6,7");
                break;
            case "提现":
//                mPresenter.GetIntegralRecord("1", Integer.toString(pagaNo), "10", userKey);
                mPresenter.AccountBill(UserID, "3");//提现

                break;
            case "充值":
//                mPresenter.GetIntegralRecord("2", Integer.toString(pagaNo), "10", userKey);
                mPresenter.AccountBill(UserID, "1");//充值

                break;
        }
    }

    @Override
    public void GetUserInfoList(BaseResult<UserInfo> baseResult) {

    }

    @Override
    public void AccountBill(BaseResult<Data<Bill>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                if (baseResult.getData().isItem1()) {
                    if (baseResult.getData().getItem2().getData() != null) {
                        switch (baseResult.getData().getItem2().getData().get(0).getState()) {
                            case "1"://充值
                                recharge_list.clear();
                                recharge_list.addAll(baseResult.getData().getItem2().getData());
                                wallet_record_adapter.setNewData(recharge_list);
//                                mRvRecord.setLayoutManager(new LinearLayoutManager(mActivity));
//                              /*  recyclerView.setHasFixedSize(true);
//                                recyclerView.setNestedScrollingEnabled(false);*/
//                                wallet_record_adapter = new RecordingAdapter(R.layout.item_recording, recharge_list);
//                                mRvRecord.setAdapter(wallet_record_adapter);
//                                wallet_record_adapter.setEmptyView(getEmptyViewCommodity());

                                break;
                            case "2"://支出
                            case "5"://收入
                            case "6":
                            case "7":
//                                expend_income_list.clear();
//                                expend_income_list.addAll(baseResult.getData().getItem2().getData());
//                                mRvRecord.setLayoutManager(new LinearLayoutManager(mActivity));
//                        /*        recyclerView.setHasFixedSize(true);
//                                recyclerView.setNestedScrollingEnabled(false);*/
//                                wallet_record_adapter = new RecordingAdapter(R.layout.item_recording, expend_income_list);
//                                mRvRecord.setAdapter(wallet_record_adapter);
//                                wallet_record_adapter.setEmptyView(getEmptyViewCommodity());
                                recharge_list.clear();
                                recharge_list.addAll(baseResult.getData().getItem2().getData());
                                wallet_record_adapter.setNewData(recharge_list);
                                break;
                            case "3"://提现
//                                withdraw_list.clear();
//                                withdraw_list.addAll(baseResult.getData().getItem2().getData());
//                                mRvRecord.setLayoutManager(new LinearLayoutManager(mActivity));
//                      /*          recyclerView.setHasFixedSize(true);
//                                recyclerView.setNestedScrollingEnabled(false);*/
//                                wallet_record_adapter = new RecordingAdapter(R.layout.item_recording, withdraw_list);
//                                mRvRecord.setAdapter(wallet_record_adapter);
//                                wallet_record_adapter.setEmptyView(getEmptyViewCommodity());
                                recharge_list.clear();
                                recharge_list.addAll(baseResult.getData().getItem2().getData());
                                wallet_record_adapter.setNewData(recharge_list);
                                break;
                            case "4"://待支付
                                break;
                            default:
                                break;

                        }

                    }

                }
                break;
        }
    }

    @Override
    public void GetAccountPayInfoList(BaseResult<List<BankCard>> baseResult) {

    }
}
