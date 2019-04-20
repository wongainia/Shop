package com.zhenghaikj.shop.fragment;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.adapter.CategoryAdapter;
import com.zhenghaikj.shop.base.BaseLazyFragment;
import com.zhenghaikj.shop.entity.Category;
import com.zhenghaikj.shop.mvp.contract.CategoryContract;
import com.zhenghaikj.shop.mvp.model.CategoryModel;
import com.zhenghaikj.shop.mvp.presenter.CategoryPresenter;
import com.zhenghaikj.shop.widget.RecyclerViewDivider;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.Unbinder;

public class ClassificationFragment extends BaseLazyFragment<CategoryPresenter, CategoryModel> implements CategoryContract.View {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
//    @BindView(R.id.refreshLayout)
//    SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.iv_scan)
    ImageView mIvScan;
    @BindView(R.id.iv_search)
    ImageView mIvSearch;
    @BindView(R.id.tv_search)
    EditText mTvSearch;
    @BindView(R.id.iv_message)
    ImageView mIvMessage;
    @BindView(R.id.rv_left)
    RecyclerView mRvLeft;
    @BindView(R.id.rv_right)
    RecyclerView mRvRight;

    private CategoryAdapter firstAdapter;
    private CategoryAdapter secondAdapter;
    private List<Category.CategoryBean> firstList=new ArrayList<>();
    private List<Category.CategoryBean> secondList=new ArrayList<>();

    @Override
    protected void initView() {

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
        firstAdapter=new CategoryAdapter(firstList);
        mRvLeft.setLayoutManager(new LinearLayoutManager(mActivity));
        mRvLeft.addItemDecoration(new RecyclerViewDivider(mActivity, LinearLayoutManager.HORIZONTAL));
        mRvLeft.setAdapter(firstAdapter);

        firstAdapter.setOnItemClickListener((adapter, view, position) -> {
            setSelect(position);
            for (int i = 0; i < firstList.size(); i++) {
                if (i==position){
                    secondList=firstList.get(position).getSubCategories();
                    for (int j = 0; j < secondList.size(); j++) {
                        secondList.get(j).setItemType(1);
                    }
                    secondAdapter.setNewData(secondList);
                }
            }
        });

        secondAdapter=new CategoryAdapter(secondList);
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

    }

    @Override
    public void GetCategories(Category Result) {
        if (Result.getSuccess()){
            firstList=Result.getCategory();
            firstAdapter.setNewData(firstList);
            setSelect(0);
            secondList=firstList.get(0).getSubCategories();
            for (int i = 0; i < secondList.size(); i++) {
                secondList.get(i).setItemType(1);
            }
            secondAdapter.setNewData(secondList);
        }
    }
    public void setSelect(int position){
        for (int i = 0; i < firstList.size(); i++) {
            if (i==position){
                firstList.get(i).setSelected(true);
            }else{
                firstList.get(i).setSelected(false);
            }
        }
        firstAdapter.setNewData(firstList);
    }
}
