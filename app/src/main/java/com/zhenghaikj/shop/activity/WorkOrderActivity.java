package com.zhenghaikj.shop.activity;

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
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.flyco.tablayout.SlidingTabLayout;
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
import com.zhenghaikj.shop.fragment.WorkOrderFragment;
import com.zhenghaikj.shop.mvp.contract.AllWorkOrdersContract;
import com.zhenghaikj.shop.mvp.model.AllWorkOrdersModel;
import com.zhenghaikj.shop.mvp.presenter.AllWorkOrdersPresenter;
import com.zhenghaikj.shop.utils.MyUtils;

import java.util.ArrayList;
import java.util.List;

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
    @BindView(R.id.tab_receiving_layout)
    SlidingTabLayout mTabreceiving_layout;
    @BindView(R.id.view_pager)
    ViewPager mViewpager;


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

    private String[] mTitleDataList = new String[]{
            "所有工单","待接单","已接单","待审核", "待支付", "已完成", "质保单","退单处理"
    };

    private MyPagerAdapter mAdapter;
    private ArrayList<Fragment> mWorkOrderFragmentList=new ArrayList<>();
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
        //mImmersionBar.statusBarDarkFont(true, 0.2f); //原理：如果当前设备支持状态栏字体变色，会设置状态栏字体为黑色，如果当前设备不支持状态栏字体变色，会使当前状态栏加上透明度，否则不执行透明度
        mImmersionBar.statusBarView(mView);
        mImmersionBar.keyboardEnable(true);
        mImmersionBar.init();
    }

    @Override
    protected void initView() {
        for (int i = 0; i < 8; i++) {
            mWorkOrderFragmentList.add(WorkOrderFragment.newInstance(mTitleDataList[i], ""));
        }
        mAdapter = new MyPagerAdapter(getSupportFragmentManager());
        mViewpager.setAdapter(mAdapter);
        mViewpager.setOffscreenPageLimit(mWorkOrderFragmentList.size());
        mTabreceiving_layout.setViewPager(mViewpager);
        setSwipeBackEnable(false);


    }




    @Override
    protected void initData() {


/*
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
                        break;
                }
            }
        });*/
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
//                workOrder = baseResult.getData();
//                if (workOrder.getData()!=null){
//                    workOrderList.addAll(workOrder.getData());
//                    mWorkOrderAdapter.setNewData(workOrderList);
//                }
//                mRefreshLayout.finishRefresh();
//                if (pageIndex!=1&&"0".equals(workOrder.getCount())){
//                    mRefreshLayout.finishLoadMoreWithNoMoreData();
//                }else{
//                    mRefreshLayout.finishLoadMore();
//                }
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

    @Override
    public void GetOrderByhmalluserid(BaseResult<Data<List<WorkOrder.DataBean>>> baseResult) {
       /* switch (baseResult.getStatusCode()) {
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
        }*/
    }



    private class MyPagerAdapter extends FragmentPagerAdapter {
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return mWorkOrderFragmentList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitleDataList[position];
        }

        @Override
        public Fragment getItem(int position) {
            return mWorkOrderFragmentList.get(position);
        }
    }
}
