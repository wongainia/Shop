package com.zhenghaikj.shop.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.blankj.utilcode.util.SPUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.activity.AfterSaleDetailActivity;
import com.zhenghaikj.shop.activity.AfterSalesTypeActivity;
import com.zhenghaikj.shop.adapter.ReturnGoodsAdapter;
import com.zhenghaikj.shop.adapter.SaleAdapter;
import com.zhenghaikj.shop.base.BaseLazyFragment;
import com.zhenghaikj.shop.entity.Product;
import com.zhenghaikj.shop.entity.Refund;
import com.zhenghaikj.shop.mvp.contract.AfterSaleContract;
import com.zhenghaikj.shop.mvp.model.AfterSaleModel;
import com.zhenghaikj.shop.mvp.presenter.AfterSalePresenter;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

/*退货退款的fragment*/
public class AfterSaleFragment extends BaseLazyFragment<AfterSalePresenter, AfterSaleModel> implements AfterSaleContract.View {
    private static final String ARG_PARAM1 = "param1";//
    private static final String ARG_PARAM2 = "param2";//
    private static final String TAG = "AfterSaleFragment";
    private SPUtils spUtils=SPUtils.getInstance("token");
    private String userKey;
    @BindView(R.id.rv_sale)
    RecyclerView mRvSale;
    @BindView(R.id.smartrefresh)
    SmartRefreshLayout smartrefresh;
    private String mParam1;
    private Intent intent;

    private ReturnGoodsAdapter returnGoodsAdapter;

    List<Refund.DataBean> list=new ArrayList<>();
    private int pagaNo = 1;
    public static AfterSaleFragment newInstance(String param1, String param2) {
        AfterSaleFragment fragment = new AfterSaleFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    protected int setLayoutId() {
        return R.layout.fragment_after_sale;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(String name) {
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    protected void initData() {
        userKey=spUtils.getString("UserKey");
        getData();

        mRvSale.setLayoutManager(new LinearLayoutManager(mActivity));
        returnGoodsAdapter=new ReturnGoodsAdapter(R.layout.item_returngoods,list,mActivity);
        mRvSale.setAdapter(returnGoodsAdapter);
        returnGoodsAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()){
                    case R.id.ll_shop:
                     Intent intent=new Intent(mActivity, AfterSaleDetailActivity.class);
                     intent.putExtra("Id",((Refund.DataBean)adapter.getItem(position)).getId());
                     intent.putExtra("OrderId",((Refund.DataBean)adapter.getItem(position)).getOrderId());
                     startActivity(intent);
                     break;
                }
            }
        });
    }
            public void getData() {
        switch (mParam1) {//目前 没状态 四个都用一个
            case "售后申请":
                mPresenter.GetRefundList(String.valueOf(pagaNo),"999",userKey);
            case "处理中":
                mPresenter.GetRefundList(String.valueOf(pagaNo),"999",userKey);
            case "售后评价":
                mPresenter.GetRefundList(String.valueOf(pagaNo),"999",userKey);
            case "申请记录":
                mPresenter.GetRefundList(String.valueOf(pagaNo),"999",userKey);
                break;
        }



    }
    @Override
    protected void initView() {

    }

    @Override
    protected void setListener() {
        smartrefresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                pagaNo=1;
                mPresenter.GetRefundList(String.valueOf(pagaNo),"999",userKey);
                smartrefresh.finishRefresh();
            }
        });
      smartrefresh.setEnableLoadMoreWhenContentNotFull(false);
    }


    @Override
    public void GetRefundList(Refund result) {
     if (result.isSuccess()){
             list.clear();
             list.addAll(result.getData());
             returnGoodsAdapter.notifyDataSetChanged();
     }

    }
}
