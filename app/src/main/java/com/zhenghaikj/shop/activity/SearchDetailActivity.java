package com.zhenghaikj.shop.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.gyf.barlibrary.ImmersionBar;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.adapter.SearchDetailAdapetr;
import com.zhenghaikj.shop.adapter.SearchDetailWaterFallAdapetr;
import com.zhenghaikj.shop.base.BaseActivity;
import com.zhenghaikj.shop.entity.CategoryMall;
import com.zhenghaikj.shop.entity.SearchResult;
import com.zhenghaikj.shop.mvp.contract.SearchContract;
import com.zhenghaikj.shop.mvp.model.SearchModel;
import com.zhenghaikj.shop.mvp.presenter.SearchPresenter;
import com.zhenghaikj.shop.utils.MyUtils;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchDetailActivity extends BaseActivity<SearchPresenter, SearchModel> implements View.OnClickListener, SearchContract.View {


    @BindView(R.id.view)
    View mView;
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.et_search)
    EditText mEtSearch;
    @BindView(R.id.iv_search)
    ImageView mIvSearch;
    @BindView(R.id.ll_filter)
    LinearLayout mLlFilter;
    @BindView(R.id.tv_default)
    TextView mTvDefault;
    @BindView(R.id.ll_default)
    LinearLayout mLlDefault;
    @BindView(R.id.tv_price)
    TextView mTvPrice;
    @BindView(R.id.iv_up)
    ImageView mIvUp;
    @BindView(R.id.ll_price)
    LinearLayout mLlPrice;
    @BindView(R.id.ll_sales_volume)
    LinearLayout mLlSalesVolume;
    @BindView(R.id.tv_comment_count)
    TextView mTvCommentCount;
    @BindView(R.id.ll_comment_count)
    LinearLayout mLlCommentCount;
    @BindView(R.id.iv_list)
    ImageView mIvList;
    @BindView(R.id.ll_list)
    LinearLayout mLlList;
    @BindView(R.id.rv_search_detail)
    RecyclerView mRvSearchDetail;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;

    @BindView(R.id.tv_serach)
    TextView mTvserach;
    private List<SearchResult.ProductBean> searchDatailList = new ArrayList<>();
    private SearchDetailAdapetr searchDetailAdapetr;
    private SearchDetailWaterFallAdapetr searchDetailWaterFallAdapetr;
    private int pagaNo = 1;
    private String keywords;
    private String orderType = "1";
    private CategoryMall.CategoryBean categoryBean;


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


        if (!"".equals(getIntent().getStringExtra("search"))){
            clear();
            mPresenter.GetSearchProducts(getIntent().getStringExtra("search"), "", null, null, "1", orderType, Integer.toString(pagaNo), "5");
            mEtSearch.setText(getIntent().getStringExtra("search"));
        }

        categoryBean =(CategoryMall.CategoryBean)getIntent().getSerializableExtra("tag");
        if (categoryBean!=null){
            mPresenter.GetSearchProducts("", "", categoryBean.getId(), null, "1", "1", Integer.toString(pagaNo), "5");
        }
        searchDetailAdapetr = new SearchDetailAdapetr(R.layout.item_search_detail, searchDatailList);
        searchDetailAdapetr.setEmptyView(getEmptyView());
        mRvSearchDetail.setLayoutManager(new LinearLayoutManager(mActivity));
        mRvSearchDetail.setAdapter(searchDetailAdapetr);


        searchDetailWaterFallAdapetr = new SearchDetailWaterFallAdapetr(R.layout.item_recommend, searchDatailList);
        searchDetailWaterFallAdapetr.setEmptyView(getEmptyView());

        searchDetailAdapetr.setOnItemChildClickListener((adapter, view, position) -> {
            switch (view.getId()) {
                case R.id.ll_good:
                    Intent intent=new Intent(mActivity, GoodsDetailActivity.class);
                    intent.putExtra("id",searchDatailList.get(position).getProductId());
                    startActivity(intent);
                    break;
                case R.id.ll_into_the_store:
                    startActivity(new Intent(mActivity, StoreActivity.class));
                    break;
            }
        });
        searchDetailWaterFallAdapetr.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent=new Intent(mActivity, GoodsDetailActivity.class);
                intent.putExtra("id",searchDatailList.get(position).getProductId());
                startActivity(intent);
            }
        });
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                clear();
                if (categoryBean!=null){
                    mPresenter.GetSearchProducts("", "", categoryBean.getId(), null, "1", "1", Integer.toString(pagaNo), "5");
                }else{
                    mPresenter.GetSearchProducts(keywords, "", null, null, "1", "1", Integer.toString(pagaNo), "5");
                }
                mRefreshLayout.finishRefresh(1000);
            }
        });
        mRefreshLayout.setOnLoadMoreListener(refreshLayout -> {
            pagaNo++;
            if (categoryBean!=null){
                mPresenter.GetSearchProducts("", "", categoryBean.getId(), null, "1", "1", Integer.toString(pagaNo), "5");
            }else{
                mPresenter.GetSearchProducts(keywords, "", null, null, "1", "1", Integer.toString(pagaNo), "5");
            }
//            refreshLayout.finishLoadMore(1000);
        });
    }

    @Override
    protected void initView() {
        mLlDefault.setSelected(true);
    }

    @Override
    protected void setListener() {
        mIvBack.setOnClickListener(this);

        mIvSearch.setOnClickListener(this);
        mLlFilter.setOnClickListener(this);

        mLlDefault.setOnClickListener(this);
        mLlSalesVolume.setOnClickListener(this);
        mLlPrice.setOnClickListener(this);
        mLlCommentCount.setOnClickListener(this);
        mLlList.setOnClickListener(this);
        mTvserach.setOnClickListener(this);


        mEtSearch.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH){//搜索按键action
                hideSoftKeyBoard();
                keywords = mEtSearch.getText().toString();
                if (TextUtils.isEmpty(keywords)){
                    return true;
                }
                clear();
                categoryBean=null;
                mPresenter.GetSearchProducts(keywords, "", null, null, "1", "1", Integer.toString(pagaNo), "5");
                return true;
            }
            return false;
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    public void setSelected(LinearLayout ll) {
        mLlDefault.setSelected(false);
        mLlPrice.setSelected(false);
        mLlSalesVolume.setSelected(false);
        mLlCommentCount.setSelected(false);

        ll.setSelected(true);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_search:
            case R.id.tv_serach:
                keywords = mEtSearch.getText().toString().trim();
                if ("".equals(keywords)) {
                    MyUtils.showToast(mActivity, "请输入搜索内容");
                    return;
                }
                clear();
                categoryBean=null;
                mPresenter.GetSearchProducts(keywords, "", null, null, "1", orderType, Integer.toString(pagaNo), "5");
                break;
            case R.id.ll_default:
                setSelected(mLlDefault);
                if ("1".equals(orderType)) {
                    orderType = "2";
                } else {
                    orderType = "1";
                }
                clear();
                if (categoryBean!=null){
                    mPresenter.GetSearchProducts("", "", categoryBean.getId(), null, "1", orderType, Integer.toString(pagaNo), "5");
                }else{
                    mPresenter.GetSearchProducts(keywords, "", null, null, "1", orderType, Integer.toString(pagaNo), "5");
                }
//                mPresenter.GetSearchProducts(keywords, "", null, null, "1", orderType, Integer.toString(pagaNo), "5");
                break;
            case R.id.ll_price:
                setSelected(mLlPrice);
                if ("1".equals(orderType)) {
                    orderType = "2";
                } else {
                    orderType = "1";
                }
                clear();
                if (categoryBean!=null){
                    mPresenter.GetSearchProducts("", "", categoryBean.getId(), null, "3", orderType, Integer.toString(pagaNo), "5");
                }else{
                    mPresenter.GetSearchProducts(keywords, "", null, null, "3", orderType, Integer.toString(pagaNo), "5");
                }
//                mPresenter.GetSearchProducts(keywords, "", null, null, "3", orderType, Integer.toString(pagaNo), "5");
                break;
            case R.id.ll_sales_volume:
                setSelected(mLlSalesVolume);
                if ("1".equals(orderType)) {
                    orderType = "2";
                } else {
                    orderType = "1";
                }
                clear();
                if (categoryBean!=null){
                    mPresenter.GetSearchProducts("", "", categoryBean.getId(), null, "2", orderType, Integer.toString(pagaNo), "5");
                }else{
                    mPresenter.GetSearchProducts(keywords, "", null, null, "2", orderType, Integer.toString(pagaNo), "5");
                }
//                mPresenter.GetSearchProducts(keywords, "", null, null, "2", orderType, Integer.toString(pagaNo), "5");
                break;
            case R.id.ll_comment_count:
                setSelected(mLlCommentCount);
                if ("1".equals(orderType)) {
                    orderType = "2";
                } else {
                    orderType = "1";
                }
                clear();
                if (categoryBean!=null){
                    mPresenter.GetSearchProducts("", "", categoryBean.getId(), null, "4", orderType, Integer.toString(pagaNo), "5");
                }else{
                    mPresenter.GetSearchProducts(keywords, "", null, null, "4", orderType, Integer.toString(pagaNo), "5");
                }
//                mPresenter.GetSearchProducts(keywords, "", null, null, "4", orderType, Integer.toString(pagaNo), "5");
                break;
            case R.id.ll_list:
                if (mIvList.isSelected()) {
                    mIvList.setSelected(false);
                    mRvSearchDetail.setLayoutManager(new LinearLayoutManager(mActivity));
                    mRvSearchDetail.setAdapter(searchDetailAdapetr);
                } else {
                    mIvList.setSelected(true);
                    mRvSearchDetail.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
                    mRvSearchDetail.setAdapter(searchDetailWaterFallAdapetr);
                }
                break;
            case R.id.ll_filter:
                finish();
                break;
        }
    }

    @Override
    public void GetSearchProducts(SearchResult Result) {
        if (Result.getSuccess()) {
            searchDatailList.addAll(Result.getProduct());
            searchDetailAdapetr.setNewData(searchDatailList);
            searchDetailWaterFallAdapetr.setNewData(searchDatailList);
            if (pagaNo != 1 && Result.getProduct().size()==0) {
                mRefreshLayout.finishLoadMoreWithNoMoreData();
            } else {
                mRefreshLayout.finishLoadMore();
            }
        }
    }
    public void clear() {
        pagaNo = 1;
        searchDatailList.clear();
        mRefreshLayout.setNoMoreData(false);
    }
}
