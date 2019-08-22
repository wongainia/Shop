package com.zhenghaikj.shop.fragment;

import android.app.AlertDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.activity.WorkOrderDetailActivity;
import com.zhenghaikj.shop.adapter.WorkOrderAdapter;
import com.zhenghaikj.shop.base.BaseLazyFragment;
import com.zhenghaikj.shop.base.BaseResult;
import com.zhenghaikj.shop.entity.Data;
import com.zhenghaikj.shop.entity.WorkOrder;
import com.zhenghaikj.shop.mvp.contract.AllWorkOrdersContract;
import com.zhenghaikj.shop.mvp.model.AllWorkOrdersModel;
import com.zhenghaikj.shop.mvp.presenter.AllWorkOrdersPresenter;
import com.zhenghaikj.shop.utils.MyUtils;

import java.util.ArrayList;
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
    private TextView title;
    private Button btn_negtive;
    private Button btn_positive;
    private EditText et_content;
    private AlertDialog complaint_dialog;
    private List<WorkOrder.DataBean> workOrderList = new ArrayList<>();
    private ClipData myClip;
    private ClipboardManager myClipboard;
    private WorkOrder workOrder;
    private int pageIndex=1;

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
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                mRefreshLayout.setNoMoreData(false);
                workOrderList.clear();
                pageIndex = 1;
                getData();
                mRefreshLayout.finishRefresh(1000);
                mRefreshLayout.setNoMoreData(false);
            }
        });
        mRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                pageIndex++;
                getData();
            }
        });
        getData();
        myClipboard = (ClipboardManager) mActivity.getSystemService(Context.CLIPBOARD_SERVICE);
        mRvWorkOrder.setLayoutManager(new LinearLayoutManager(mActivity));
        mWorkOrderAdapter = new WorkOrderAdapter(R.layout.order_item, workOrderList, mParam1);
        mWorkOrderAdapter.setEmptyView(getEmptyView());
        mRvWorkOrder.setAdapter(mWorkOrderAdapter);
        myClipboard = (ClipboardManager) mActivity.getSystemService(Context.CLIPBOARD_SERVICE);
        mWorkOrderAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, final int position) {
                switch (view.getId()) {
                    case R.id.tv_complaint:
                        View complaint_view = LayoutInflater.from(mActivity).inflate(R.layout.customdialog_complaint, null);
                        title = complaint_view.findViewById(R.id.title);
                        btn_negtive = complaint_view.findViewById(R.id.negtive);
                        btn_positive = complaint_view.findViewById(R.id.positive);
                        et_content = complaint_view.findViewById(R.id.et_content);
                        title.setText("投诉");
                        complaint_dialog = new AlertDialog.Builder(mActivity)
                                .setView(complaint_view)
                                .create();
                        complaint_dialog.show();
                        btn_negtive.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                complaint_dialog.dismiss();
                            }
                        });
                        btn_positive.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String content = et_content.getText().toString().trim();
                                if ("".equals(content)) {
                                    MyUtils.showToast(mActivity, "请输入投诉原因");
                                } else {
                                    mPresenter.FactoryComplaint(workOrderList.get(position).getOrderID(), content);
                                }
                            }
                        });
                        break;
                    case R.id.tv_leave_message:

                        break;
                    case R.id.tv_see_detail:
                        mPresenter.UpdateOrderFIsLook(workOrderList.get(position).getOrderID(), "2", "2");
                        Intent intent1 = new Intent(mActivity, WorkOrderDetailActivity.class);
                        intent1.putExtra("OrderID", workOrderList.get(position).getOrderID());
                        startActivity(intent1);
                        break;
                    case R.id.iv_copy:
                        String id = workOrderList.get(position).getOrderID();
                        myClip = ClipData.newPlainText("", id);
                        myClipboard.setPrimaryClip(myClip);
                        ToastUtils.showShort("复制成功");
                        break;
                    case R.id.tv_obsolete:
                        String orderId = workOrderList.get(position).getOrderID();
//                                    mPresenter.UpdateOrderState(orderId, "-2");
                        mPresenter.ApplyCancelOrder(orderId);
                        workOrderList.clear();
                        break;
                }
            }
        });
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

//            case "待接单":
//                mPresenter.GetOrderInfoList(UserID,"0", Integer.toString(pageIndex), "3");
//                break;
//            case "待审核":
//                mPresenter.GetOrderInfoList(UserID,"1", Integer.toString(pageIndex), "3");
//                break;
//            case "已接单":
//                mPresenter.GetOrderInfoList(UserID,"7", Integer.toString(pageIndex), "3");
//                break;
//            case "待支付":
//                mPresenter.GetOrderInfoList(UserID,"2", Integer.toString(pageIndex), "3");
//                break;
//            case "已完成":
//                mPresenter.GetOrderInfoList(UserID,"3", Integer.toString(pageIndex), "3");
//                break;
//            case "质保单":
//                mPresenter.GetOrderInfoList(UserID,"4", Integer.toString(pageIndex), "3");
//                break;
//            case "所有工单":
//                mPresenter.GetOrderInfoList(UserID,"5", Integer.toString(pageIndex), "3");
//                break;
//            case "退单处理":
//                mPresenter.GetOrderInfoList(UserID,"6", Integer.toString(pageIndex), "3");
//                break;

        }
    }


    @Override
    public void GetOrderInfoList(BaseResult<WorkOrder> baseResult) {
        workOrder = baseResult.getData();
                if (workOrder.getData()!=null){
                    workOrderList.addAll(workOrder.getData());
                    mWorkOrderAdapter.setNewData(workOrderList);
                }
                mRefreshLayout.finishRefresh();
                if (pageIndex!=1&&"0".equals(workOrder.getCount())){
                    mRefreshLayout.finishLoadMoreWithNoMoreData();
                }else{
                    mRefreshLayout.finishLoadMore();
                }
    }

    @Override
    public void UpdateOrderState(BaseResult<Data<String>> baseResult) {

    }

    @Override
    public void FactoryComplaint(BaseResult<Data<String>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                Data<String> data = baseResult.getData();
                if (data.isItem1()) {
                    ToastUtils.showShort(data.getItem2());
                    complaint_dialog.dismiss();
                } else {
                    ToastUtils.showShort(data.getItem2());
                }
                break;
            default:
                break;
        }
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
                workOrderList.clear();
                if (baseResult.getData().getItem2()!=null){
                    workOrderList.addAll(baseResult.getData().getItem2());
                    mWorkOrderAdapter.setNewData(workOrderList);

                }
                if (pageIndex!=1&&baseResult.getData().getItem2()==null){
                    mRefreshLayout.finishLoadMoreWithNoMoreData();
                }else{
                    mRefreshLayout.finishLoadMore();
                }
                break;
            case 401:
                ToastUtils.showShort(baseResult.getInfo());
                break;

        }
    }

    @Override
    public void ApplyCancelOrder(BaseResult<Data<String>> baseResult) {
        switch (baseResult.getStatusCode()){
            case 200:
                ToastUtils.showShort(baseResult.getData().getItem2());
                break;
        }
    }
}
