package com.zhenghaikj.shop.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.ToastUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.adapter.WorkOrderAdapter;
import com.zhenghaikj.shop.base.BaseLazyFragment;
import com.zhenghaikj.shop.base.BaseResult;
import com.zhenghaikj.shop.entity.Data;
import com.zhenghaikj.shop.entity.WorkOrder;
import com.zhenghaikj.shop.mvp.contract.AllWorkOrdersContract;
import com.zhenghaikj.shop.mvp.model.AllWorkOrdersModel;
import com.zhenghaikj.shop.mvp.presenter.AllWorkOrdersPresenter;

import java.util.List;

import butterknife.BindView;

public class WorkOrderFragment extends BaseLazyFragment<AllWorkOrdersPresenter, AllWorkOrdersModel> implements AllWorkOrdersContract.View {
    private static final String ARG_PARAM1 = "param1";//
    private static final String ARG_PARAM2 = "param2";//
    @BindView(R.id.view)
    View mView;
    @BindView(R.id.rv_work_order)
    RecyclerView mRvWorkOrder;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    private String mParam1;
    private String mParam2;
    private WorkOrderAdapter mWorkOrderAdapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    public static WorkOrderFragment newInstance(String param1, String param2) {
        WorkOrderFragment fragment = new WorkOrderFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected int setLayoutId() {
        return R.layout.fragment_work_order_page;
    }

    @Override
    protected void initData() {
        getData();

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void setListener() {

    }


    public void getData() {
        switch (mParam1) {
            case "待接单":
                mPresenter.GetOrderByhmalluserid(UserID,"0");
                break;
            case "待审核":
                mPresenter.GetOrderByhmalluserid(UserID,"1");
                break;
            case "已接单":
                mPresenter.GetOrderByhmalluserid(UserID,"7");
                break;
            case "待支付":
                mPresenter.GetOrderByhmalluserid(UserID,"2");
                break;
            case "已完成":
                mPresenter.GetOrderByhmalluserid(UserID,"3");
                break;
            case "质保单":
                mPresenter.GetOrderByhmalluserid(UserID,"4");
                break;
            case "所有工单":
                mPresenter.GetOrderByhmalluserid(UserID,"5");
                break;
            case "退单处理":
                mPresenter.GetOrderByhmalluserid(UserID,"6");
                break;

        }
    }


    @Override
    public void GetOrderInfoList(BaseResult<WorkOrder> baseResult) {

    }

    @Override
    public void UpdateOrderState(BaseResult<Data<String>> baseResult) {

    }

    @Override
    public void FactoryComplaint(BaseResult<Data<String>> baseResult) {

    }

    @Override
    public void EnSureOrder(BaseResult<Data<String>> baseResult) {

    }

    @Override
    public void FactoryEnsureOrder(BaseResult<Data<String>> baseResult) {

    }

    @Override
    public void UpdateOrderFIsLook(BaseResult<Data<String>> baseResult) {

    }


    @Override
    public void GetOrderByhmalluserid(BaseResult<Data<List<WorkOrder.DataBean>>> baseResult) {
        switch (baseResult.getStatusCode()) {

            case 200:
                if (!baseResult.getData().getItem2().isEmpty()){
                    mRvWorkOrder.setLayoutManager(new LinearLayoutManager(mActivity));
                    mWorkOrderAdapter = new WorkOrderAdapter(R.layout.order_item, baseResult.getData().getItem2(), mParam1);
                    mRvWorkOrder.setAdapter(mWorkOrderAdapter);
                }else {
                    mWorkOrderAdapter.setEmptyView(getEmptyView());
                }
                break;

        }
    }
}
