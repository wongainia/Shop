package com.zhenghaikj.shop.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.adapter.CommodityAdapter;
import com.zhenghaikj.shop.base.BaseLazyFragment;
import com.zhenghaikj.shop.entity.CollectionProduct;
import com.zhenghaikj.shop.entity.Product;
import com.zhenghaikj.shop.mvp.contract.CollectionProductContract;
import com.zhenghaikj.shop.mvp.model.CollectionProductModel;
import com.zhenghaikj.shop.mvp.presenter.CollectionProductPresenter;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class CommodityFragment extends BaseLazyFragment<CollectionProductPresenter, CollectionProductModel> implements CollectionProductContract.View {
    private static final String ARG_PARAM1 = "param1";//
    private static final String ARG_PARAM2 = "param2";//
    @BindView(R.id.tv_category)
    TextView mTvCategory;
    @BindView(R.id.ll_category)
    LinearLayout mLlCategory;
    @BindView(R.id.tv_status)
    TextView mTvStatus;
    @BindView(R.id.ll_status)
    LinearLayout mLlStatus;
    @BindView(R.id.rv_commodity)
    RecyclerView mRvCommodity;
    Unbinder unbinder;
    @BindView(R.id.cb_circle_management)
    CheckBox mCbCircleManagement;
    @BindView(R.id.tv_smart_cleaning)
    TextView mTvSmartCleaning;
    @BindView(R.id.tv_delete)
    TextView mTvDelete;
    @BindView(R.id.ll_management)
    LinearLayout mLlManagement;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    private ArrayList<CollectionProduct.DataBean> commodityList = new ArrayList<>();

    private String mParam1;
    private String mParam2;
    private CommodityAdapter commodityAdapter;
    private int pagaNo = 1;
    private SPUtils spUtils;
    private String userKey;

    public static CommodityFragment newInstance(String param1, String param2) {
        CommodityFragment fragment = new CommodityFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_commodity;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(String name) {

    }

    @Override
    protected void initData() {
        spUtils = SPUtils.getInstance("token");
        userKey = spUtils.getString("UserKey");
        mPresenter.GetUserCollectionProduct(Integer.toString(pagaNo),"10",userKey);

    }

    @Override
    protected void initView() {

        commodityAdapter = new CommodityAdapter(R.layout.item_commodity, commodityList);
        mRvCommodity.setLayoutManager(new LinearLayoutManager(mActivity));
        mRvCommodity.setAdapter(commodityAdapter);
    }

    @Override
    protected void setListener() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void GetUserCollectionProduct(CollectionProduct result) {
        if (result.isSuccess()){
            commodityList.addAll(result.getData());
            commodityAdapter.setNewData(commodityList);
        }
    }
}
