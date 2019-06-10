package com.zhenghaikj.shop.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.SPUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.adapter.CouponAdapter;
import com.zhenghaikj.shop.base.BaseLazyFragment;
import com.zhenghaikj.shop.entity.UserCouponListResult;
import com.zhenghaikj.shop.mvp.contract.CouponContract;
import com.zhenghaikj.shop.mvp.model.CouponModel;
import com.zhenghaikj.shop.mvp.presenter.CouponPresenter;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class CouponFragment extends BaseLazyFragment<CouponPresenter, CouponModel> implements CouponContract.View {
    private static final String ARG_PARAM1 = "param1";//
    private static final String ARG_PARAM2 = "param2";//
    private static final String TAG = "CouponFragment";
    @BindView(R.id.rv_order)
    RecyclerView mRvOrder;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;

    private List<UserCouponListResult.CouponBean> couponBeans = new ArrayList<>();

    private SPUtils spUtils;
    private String userKey;
    private CouponAdapter couponListAdapter;

    public static CouponFragment newInstance(List<UserCouponListResult.CouponBean> param1) {
        CouponFragment fragment = new CouponFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, (Serializable) param1);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            couponBeans = (List<UserCouponListResult.CouponBean>) getArguments().getSerializable(ARG_PARAM1);
        }
    }
    @Override
    protected int setLayoutId() {
        return R.layout.fragment_coupon;
    }

    @Override
    protected void initData() {
        spUtils = SPUtils.getInstance("token");
        userKey = spUtils.getString("UserKey");

        mRefreshLayout.setEnableLoadMore(false);
        mRefreshLayout.setEnableRefresh(false);
    }

    @Override
    protected void initView() {

        couponListAdapter = new CouponAdapter(R.layout.item_coupon2, couponBeans);
        mRvOrder.setLayoutManager(new LinearLayoutManager(mActivity));
        mRvOrder.setAdapter(couponListAdapter);
        couponListAdapter.setEmptyView(getEmptyViewCommodity());
        couponListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

            }
        });


    }

    @Override
    protected void setListener() {

    }



    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(String name) {
        switch (name){
            case "待评价":
//                mPresenter.GetOrders("5", Integer.toString(pagaNo), "10", userKey);
                break;

        }
    }

    @Override
    public void GetUserCounponList(UserCouponListResult baseResult) {
        if (baseResult.isSuccess()){

        }
    }
}
