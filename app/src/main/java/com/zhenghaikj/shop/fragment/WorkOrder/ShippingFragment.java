package com.zhenghaikj.shop.fragment.WorkOrder;

import android.os.Bundle;
import android.widget.TextView;

import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.adapter.LogisticsAdapter;
import com.zhenghaikj.shop.base.BaseLazyFragment;
import com.zhenghaikj.shop.base.BaseResult;
import com.zhenghaikj.shop.entity.Data;
import com.zhenghaikj.shop.entity.Logistics;
import com.zhenghaikj.shop.entity.WorkOrder;
import com.zhenghaikj.shop.mvp.contract.ExpressInfoContract;
import com.zhenghaikj.shop.mvp.model.ExpressInfoModel;
import com.zhenghaikj.shop.mvp.presenter.ExpressInfoPresenter;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

public class ShippingFragment extends BaseLazyFragment<ExpressInfoPresenter, ExpressInfoModel> implements ExpressInfoContract.View {
    private static final String ARG_PARAM1 = "param1";//
    private static final String ARG_PARAM2 = "param2";//
    @BindView(R.id.courier_company_tv)
    TextView mCourierCompanyTv;
    @BindView(R.id.tv_number)
    TextView mTvNumber;
    @BindView(R.id.shipping_rv)
    RecyclerView mShippingRv;

    private String mParam1;
    private String mParam2;
    private String orderId;
    private List<Logistics> list = new ArrayList<>();
    private WorkOrder.DataBean data;
    private LogisticsAdapter adapter;

    public static ShippingFragment newInstance(String param1, String param2) {
        ShippingFragment fragment = new ShippingFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_shipping;
    }

    @Override
    protected void initData() {
        orderId = mParam1;
        mPresenter.GetOrderInfo(orderId);

        adapter = new LogisticsAdapter(R.layout.logistics_recycle_item,list);
        mShippingRv.setLayoutManager(new LinearLayoutManager(mActivity));
        mShippingRv.setAdapter(adapter);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void setListener() {

    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(String name) {

    }

    @Override
    public void GetExpressInfo(BaseResult<Data<List<Logistics>>> baseResult) {
        switch (baseResult.getStatusCode()){
            case 200:
                    if (baseResult.getData().getItem2()!=null){
                        list.addAll(baseResult.getData().getItem2());
                        adapter.setNewData(list);
                    }

                break;
        }
    }

    @Override
    public void GetOrderInfo(BaseResult<WorkOrder.DataBean> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                data = baseResult.getData();
                if (data.getOrderAccessroyDetail().size()>0){
                    if (!"".equals(data.getOrderAccessroyDetail().get(0).getExpressNo())){
                        mPresenter.GetExpressInfo(data.getOrderAccessroyDetail().get(0).getExpressNo());
                    }
                    mTvNumber.setText(data.getOrderAccessroyDetail().get(0).getExpressNo());
                }
                break;
        }
    }
}
