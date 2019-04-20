package com.zhenghaikj.shop.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gyf.barlibrary.ImmersionBar;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.adapter.SearchDetailAdapetr;
import com.zhenghaikj.shop.base.BaseActivity;
import com.zhenghaikj.shop.entity.Product;
import com.zhenghaikj.shop.entity.SearchResult;
import com.zhenghaikj.shop.mvp.contract.SearchContract;
import com.zhenghaikj.shop.mvp.model.SearchModel;
import com.zhenghaikj.shop.mvp.presenter.SearchPresenter;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchDetailActivity extends BaseActivity<SearchPresenter, SearchModel> implements View.OnClickListener, SearchContract.View {
    @BindView(R.id.view)
    View mView;
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.et_search)
    EditText mEtSearch;
    @BindView(R.id.tv_comprehensive)
    TextView mTvComprehensive;
    @BindView(R.id.ll_comprehensive)
    LinearLayout mLlComprehensive;
    @BindView(R.id.tv_sales_volume)
    TextView mTvSalesVolume;
    @BindView(R.id.ll_sales_volume)
    LinearLayout mLlSalesVolume;
    @BindView(R.id.ll_video)
    LinearLayout mLlVideo;
    @BindView(R.id.iv_list)
    ImageView mIvList;
    @BindView(R.id.ll_list)
    LinearLayout mLlList;
    @BindView(R.id.ll_filter)
    LinearLayout mLlFilter;
    @BindView(R.id.rv_search_detail)
    RecyclerView mRvSearchDetail;

    private List<SearchResult.ProductBean> searchDatailList=new ArrayList<>();
    private SearchDetailAdapetr searchDetailAdapetr;
    private int pagaNo=1;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_search_detail;
    }

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
        mPresenter.GetSearchProducts("新","",null,null,"1","1", Integer.toString(pagaNo),"20");
        searchDetailAdapetr = new SearchDetailAdapetr(R.layout.item_search_detail,searchDatailList);
        mRvSearchDetail.setLayoutManager(new LinearLayoutManager(mActivity));
        mRvSearchDetail.setAdapter(searchDetailAdapetr);
        searchDetailAdapetr.setOnItemChildClickListener((adapter, view, position) -> {
            switch (view.getId()){
                case R.id.ll_good:
                    startActivity(new Intent(mActivity,GoodsDetailActivity.class));
                    break;
                case R.id.ll_into_the_store:
                    startActivity(new Intent(mActivity,StoreActivity.class));
                    break;
            }
        });
    }

    @Override
    protected void initView() {
        mLlComprehensive.setSelected(true);
    }

    @Override
    protected void setListener() {
        mIvBack.setOnClickListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_back:
                finish();
                break;
        }
    }

    @Override
    public void GetSearchProducts(SearchResult Result) {
        if (Result.getSuccess()){
            searchDatailList=Result.getProduct();
            searchDetailAdapetr.setNewData(searchDatailList);
        }
    }
}
