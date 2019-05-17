package com.zhenghaikj.shop.activity;

import android.app.AlertDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.gyf.barlibrary.ImmersionBar;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.adapter.WorkOrderAdapter;
import com.zhenghaikj.shop.base.BaseActivity;
import com.zhenghaikj.shop.base.BaseResult;
import com.zhenghaikj.shop.entity.Data;
import com.zhenghaikj.shop.entity.WorkOrder;
import com.zhenghaikj.shop.mvp.contract.AllWorkOrdersContract;
import com.zhenghaikj.shop.mvp.model.AllWorkOrdersModel;
import com.zhenghaikj.shop.mvp.presenter.AllWorkOrdersPresenter;
import com.zhenghaikj.shop.utils.MyUtils;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class WorkOrderActivity extends BaseActivity<AllWorkOrdersPresenter, AllWorkOrdersModel> implements View.OnClickListener, AllWorkOrdersContract.View {
    @BindView(R.id.view)
    View mView;
    @BindView(R.id.icon_back)
    ImageView mIconBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_save)
    TextView mTvSave;
    @BindView(R.id.icon_search)
    ImageView mIconSearch;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.rv_work_order)
    RecyclerView mRvWorkOrder;
    private SPUtils spUtils;
    private String userKey;
    private int pageIndex = 1;
    private String mParam1;
    private String mParam2;
    private WorkOrder workOrder;
    private WorkOrderAdapter mWorkOrderAdapter;
    private List<WorkOrder.DataBean> workOrderList = new ArrayList<>();
    private ClipData myClip;
    private ClipboardManager myClipboard;
    private View complaint_view;
    private TextView title;
    private Button btn_negtive;
    private Button btn_positive;
    private EditText et_content;
    private AlertDialog complaint_dialog;
    private String content;
    private String UserID;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_work_order;
    }

    /**
     * 初始化沉浸式
     */
    @Override
    protected void initImmersionBar() {
        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar.statusBarDarkFont(true, 0.2f); //原理：如果当前设备支持状态栏字体变色，会设置状态栏字体为黑色，如果当前设备不支持状态栏字体变色，会使当前状态栏加上透明度，否则不执行透明度
        mImmersionBar.statusBarView(mView);
        mImmersionBar.keyboardEnable(true);
        mImmersionBar.init();
    }

    @Override
    protected void initData() {
        mRvWorkOrder.setLayoutManager(new LinearLayoutManager(mActivity));
        mWorkOrderAdapter = new WorkOrderAdapter(R.layout.order_item, workOrderList, mParam1);
        mWorkOrderAdapter.setEmptyView(getEmptyView());
        mRvWorkOrder.setAdapter(mWorkOrderAdapter);
        mWorkOrderAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, final int position) {
                switch (view.getId()) {
                    case R.id.tv_complaint:
                        complaint_view = LayoutInflater.from(mActivity).inflate(R.layout.customdialog_complaint, null);
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
                                content = et_content.getText().toString().trim();
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
                        mPresenter.UpdateOrderState(orderId, "-2");
                        workOrderList.clear();
                        mRefreshLayout.finishRefresh();
                        break;
                }
            }
        });
    }

    @Override
    protected void initView() {
        mTvTitle.setVisibility(View.VISIBLE);
        mTvTitle.setText("安装维修");

        spUtils = SPUtils.getInstance("token");
        userKey = spUtils.getString("UserKey");
        UserID = spUtils.getString("userName2");
        mPresenter.GetOrderInfoList(UserID,"5", Integer.toString(pageIndex), "3");

        /*下拉刷新*/
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
          /*      if (!list.isEmpty()){ //当有数据的时候
                    ll_empty.setVisibility(View.INVISIBLE);//隐藏空的界面
                }*/
                pageIndex = 1;
                workOrderList.clear();
                mPresenter.GetOrderInfoList(UserID,"5", Integer.toString(pageIndex), "3");
                refreshlayout.finishRefresh();
                mRefreshLayout.setNoMoreData(false);
            }
        });


        //没满屏时禁止上拉
//        mRefreshLayout.setEnableLoadMoreWhenContentNotFull(false);

        //上拉加载更多
        mRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                pageIndex++; //页数加1
                mPresenter.GetOrderInfoList(UserID,"5", Integer.toString(pageIndex), "3");
                refreshlayout.finishLoadmore();
            }
        });

    }

    @Override
    protected void setListener() {
        mIconBack.setOnClickListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.icon_back:
                finish();
                break;
        }
    }

    @Override
    public void GetOrderInfoList(BaseResult<WorkOrder> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
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
                break;
            case 401:
                ToastUtils.showShort(baseResult.getInfo());
                break;
        }
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
}