package com.zhenghaikj.shop.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.gyf.barlibrary.ImmersionBar;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.adapter.SearchDetailAdapetr;
import com.zhenghaikj.shop.base.BaseActivity;
import com.zhenghaikj.shop.entity.Product;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchDetailActivity extends BaseActivity implements View.OnClickListener{
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

    private ArrayList<Product> searchDatailList=new ArrayList<>();
    private SearchDetailAdapetr searchDetailAdapetr;

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
        for (int i=0;i<10;i++){
            searchDatailList.add(new Product());
        }

        searchDetailAdapetr = new SearchDetailAdapetr(R.layout.item_search_detail,searchDatailList);
        mRvSearchDetail.setLayoutManager(new LinearLayoutManager(mActivity));
        mRvSearchDetail.setAdapter(searchDetailAdapetr);
        searchDetailAdapetr.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()){
                    case R.id.ll_good:
                        startActivity(new Intent(mActivity,GoodsDetailActivity.class));
                        break;
                }
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
}
