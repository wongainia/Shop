package com.zhenghaikj.shop.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.gyf.barlibrary.ImmersionBar;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.adapter.CommentAdapter;
import com.zhenghaikj.shop.adapter.CommentCategoryAdapter;
import com.zhenghaikj.shop.base.BaseActivity;
import com.zhenghaikj.shop.entity.Comment;
import com.zhenghaikj.shop.entity.CommentCategory;
import com.zhenghaikj.shop.mvp.contract.ProductCommentContract;
import com.zhenghaikj.shop.mvp.model.ProductCommentModel;
import com.zhenghaikj.shop.mvp.presenter.ProductCommentPresenter;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class EvaluationDetailsActivity extends BaseActivity<ProductCommentPresenter, ProductCommentModel> implements View.OnClickListener, ProductCommentContract.View {
    private static final String TAG = "EvaluationDetailsActivity";
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
    @BindView(R.id.rv_list)
    RecyclerView mRvList;
    @BindView(R.id.rv_evaluation)
    RecyclerView mRvEvaluation;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    private String productId;
    private int pageIndex = 1;  //默认当前页数为1
    private String[] faceValues = new String[]{"全部", "好评", "中评", "差评", "有图", "追加"};
    private List<CommentCategory> CommentCategoryList = new ArrayList<>();
    private CommentCategoryAdapter commentCategoryAdapter;
    private Comment data = new Comment();

    private List<Comment.listData> CommentList = new ArrayList<>();
    private CommentAdapter commentAdapter;
    private int pagaNo;
    private CommentCategory comment;
    private CommentCategory comment1;
    private CommentCategory comment2;
    private CommentCategory comment3;
    private CommentCategory comment4;
    private CommentCategory comment5;
    private int size;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_evaluation_details;
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


    }

    @SuppressLint("LongLogTag")
    @Override
    protected void initView() {
        mTvTitle.setText("商品评论");
        mTvTitle.setVisibility(View.VISIBLE);
        productId = getIntent().getStringExtra("productId");
        CommentList.clear();

//        for (int i = 0; i < faceValues.length; i++) {
//            CommentCategoryList.add(new CommentCategory(faceValues[i], false));
//        }
//        CommentCategoryList.get(0).setSelect(true);
//        CommentCategoryList.clear();
        commentCategoryAdapter = new CommentCategoryAdapter(R.layout.item_list, CommentCategoryList);
        mRvList.setLayoutManager(new GridLayoutManager(mActivity, 5));
        mRvList.setAdapter(commentCategoryAdapter);
        commentCategoryAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                for (int i = 0; i < CommentCategoryList.size(); i++) {
                    if (i == position) {
                        CommentCategoryList.get(i).setSelect(true);
                        CommentList.clear();
                        mPresenter.ProductComment(productId, String.valueOf(pageIndex), "10", String.valueOf(position));
                        size = position;
                    } else {
                        CommentCategoryList.get(i).setSelect(false);
                    }
                }
                commentCategoryAdapter.notifyDataSetChanged();

            }
        });

        commentAdapter = new CommentAdapter(R.layout.item_evaluation, CommentList);
        mRvEvaluation.setLayoutManager(new LinearLayoutManager(mActivity));
        mRvEvaluation.setAdapter(commentAdapter);
        commentAdapter.setEmptyView(getEmptyViewComment());
        mPresenter.ProductComment(productId, String.valueOf(pageIndex), "10", "0");

        mRefreshLayout.setOnRefreshListener(refreshLayout -> {
            pageIndex = 1;

            refreshLayout.setNoMoreData(false);
            refreshLayout.finishRefresh(1000);
        });
        mRefreshLayout.setOnLoadMoreListener(refreshLayout -> {
            pageIndex++;
            refreshLayout.finishLoadMore(1000);
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
    public void ProductComment(Comment Result) {
        data = Result;
        CommentCategoryList.clear();
        comment = new CommentCategory("全部(" + data.getAllCommentCount() + ")", false);
        comment1 = new CommentCategory("好评(" + data.getGoodCount() + ")", false);
        comment2 = new CommentCategory("中评(" + data.getMediumCount() + ")", false);
        comment3 = new CommentCategory("差评(" + data.getBadCount() + ")", false);
        comment4 = new CommentCategory("有图(" + data.getImageCount() + ")", false);
        comment5 = new CommentCategory("追加(" + data.getAppendCount() + ")", false);
        CommentCategoryList.add(comment);
        CommentCategoryList.add(comment1);
        CommentCategoryList.add(comment2);
        CommentCategoryList.add(comment3);
        CommentCategoryList.add(comment4);
        CommentCategoryList.add(comment5);
//        Log.d(TAG,"参数"+size);
        commentCategoryAdapter.setNewData(CommentCategoryList);
        CommentCategoryList.get(size).setSelect(true);
        CommentList.addAll(Result.getResult());
        commentAdapter.setNewData(CommentList);
    }
}
