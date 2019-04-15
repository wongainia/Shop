package com.zhenghaikj.shop.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gyf.barlibrary.ImmersionBar;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.adapter.SearchAdapter;
import com.zhenghaikj.shop.base.BaseActivity;
import com.zhenghaikj.shop.entity.Search;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.tv_search)
    TextView mTvSearch;
    @BindView(R.id.iv_see)
    ImageView mIvSee;
    @BindView(R.id.rv_search_discovery)
    RecyclerView mRvSearchDiscovery;
    @BindView(R.id.view)
    View mView;
    @BindView(R.id.ll_speak)
    LinearLayout mLlSpeak;
    @BindView(R.id.atv_search)
    AutoCompleteTextView mAtvSearch;
    private ArrayList<Search> searchArrayList = new ArrayList<>();
    private String[] title = new String[]{"鞋子", "袜子", "衣服", "裤子", "裙子", "帽子"};
    private SearchAdapter searchAdapter;

    private static final String[] data = new String[]{
            "冰箱", "冰柜", "冰淇淋"
    };
    @Override
    protected int setLayoutId() {
        return R.layout.activity_search;
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
        for (int i = 0; i < title.length; i++) {
            searchArrayList.add(new Search(title[i]));
        }
        searchAdapter = new SearchAdapter(R.layout.item_search_discovery, searchArrayList);
        mRvSearchDiscovery.setLayoutManager(new GridLayoutManager(mActivity, 2));
        mRvSearchDiscovery.setAdapter(searchAdapter);


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(mActivity, android.R.layout.simple_dropdown_item_1line, data);
        mAtvSearch.setAdapter(adapter);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void setListener() {
        mIvBack.setOnClickListener(this);
        mTvSearch.setOnClickListener(this);
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
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_search:
                startActivity(new Intent(mActivity, SearchDetailActivity.class));
                break;
        }
    }
}
