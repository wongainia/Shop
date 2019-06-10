package com.zhenghaikj.shop.fragment;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gyf.barlibrary.ImmersionBar;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.activity.SearchDetailActivity;
import com.zhenghaikj.shop.activity.SearchPreDetailActivity;
import com.zhenghaikj.shop.adapter.CategoryAdapterMall;
import com.zhenghaikj.shop.base.BaseLazyFragment;
import com.zhenghaikj.shop.entity.CategoryMall;
import com.zhenghaikj.shop.mvp.contract.CategoryContract;
import com.zhenghaikj.shop.mvp.model.CategoryModel;
import com.zhenghaikj.shop.mvp.presenter.CategoryPresenter;
import com.zhenghaikj.shop.widget.RecyclerViewDivider;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class ClassificationFragment extends BaseLazyFragment<CategoryPresenter, CategoryModel> implements CategoryContract.View, View.OnClickListener {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    //    @BindView(R.id.refreshLayout)
//    SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.iv_scan)
    ImageView mIvScan;
    @BindView(R.id.iv_search)
    ImageView mIvSearch;
    @BindView(R.id.tv_search)
    TextView mTvSearch;
    @BindView(R.id.iv_message)
    ImageView mIvMessage;
    @BindView(R.id.rv_left)
    RecyclerView mRvLeft;
    @BindView(R.id.rv_right)
    RecyclerView mRvRight;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.ll_search)
    LinearLayout mLlSearch;
    @BindView(R.id.view)
    View mView;
    private int childCount;
    private int middlechild;
    private LinearLayoutManager linearLayoutManager;

    public static ClassificationFragment newInstance(String param1, String param2) {
        ClassificationFragment fragment = new ClassificationFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    private CategoryAdapterMall firstAdapter;
    private CategoryAdapterMall secondAdapter;
    private List<CategoryMall.CategoryBean> firstList = new ArrayList<>();
    private List<CategoryMall.CategoryBean> secondList = new ArrayList<>();

    @Override
    protected void initView() {

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
    protected int setLayoutId() {
        return R.layout.fragment_classification;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(String name) {

    }

    @Override
    protected void initData() {
        firstAdapter = new CategoryAdapterMall(firstList);
        linearLayoutManager = new LinearLayoutManager(mActivity);
        mRvLeft.setLayoutManager(linearLayoutManager);
        mRvLeft.addItemDecoration(new RecyclerViewDivider(mActivity, LinearLayoutManager.HORIZONTAL));
        mRvLeft.setAdapter(firstAdapter);

        firstAdapter.setOnItemClickListener((adapter, view, position) -> {
            scrollToMiddleH(view, position);
            setSelect(position);
            for (int i = 0; i < firstList.size(); i++) {
                if (i == position) {
                    secondList = firstList.get(position).getSubCategories();
                    for (int j = 0; j < secondList.size(); j++) {
                        secondList.get(j).setItemType(1);
                    }
                    secondAdapter.setNewData(secondList);
                }
            }
        });

        secondAdapter = new CategoryAdapterMall(secondList);
        mRvRight.setLayoutManager(new LinearLayoutManager(mActivity));
        mRvRight.setAdapter(secondAdapter);

        mPresenter.GetCategories();
        /*mRefreshLayout.setEnableLoadMore(false);
        mRefreshLayout.setOnRefreshListener(refreshlayout ->
                {
                    mPresenter.GetCategories();
                    refreshlayout.finishRefresh(1000);
                }
        );*/
    }

    @Override
    protected void setListener() {
        mLlSearch.setOnClickListener(this);
    }

    @Override
    public void GetCategories(CategoryMall Result) {
        if (Result.getSuccess()) {
            firstList = Result.getCategory();
            firstAdapter.setNewData(firstList);
            setSelect(0);
            secondList = firstList.get(0).getSubCategories();
            for (int i = 0; i < secondList.size(); i++) {
                secondList.get(i).setItemType(1);
            }
            secondAdapter.setNewData(secondList);
        }
    }

    public void setSelect(int position) {
        for (int i = 0; i < firstList.size(); i++) {
            if (i == position) {
                firstList.get(i).setSelected(true);
            } else {
                firstList.get(i).setSelected(false);
            }
        }
        firstAdapter.setNewData(firstList);
    }

    private void scrollToMiddleH(View view, int position) {

        int vHeight = view.getHeight();

        Rect rect = new Rect();

        mRvLeft.getGlobalVisibleRect(rect);

//        int reHeight = rect.top- rect.bottom - vHeight;
        int reHeight = rect.bottom - rect.top - vHeight;


        final int firstPosition = linearLayoutManager.findFirstVisibleItemPosition();

        int top = mRvLeft.getChildAt(position - firstPosition).getTop();

        int half = reHeight / 2;

        mRvLeft.smoothScrollBy(0, top - half);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_search:
                //startActivity(new Intent(mActivity, SearchDetailActivity.class));
                startActivity(new Intent(mActivity, SearchPreDetailActivity.class));
                break;
            default:
                break;
        }
    }
}
