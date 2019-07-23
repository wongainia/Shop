package com.zhenghaikj.shop.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.gyf.barlibrary.ImmersionBar;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.adapter.GetSerachListAdapter;
import com.zhenghaikj.shop.api.Config;
import com.zhenghaikj.shop.base.BaseActivity;
import com.zhenghaikj.shop.entity.GetSerachListResult;
import com.zhenghaikj.shop.mvp.contract.GetSerachListContract;
import com.zhenghaikj.shop.mvp.model.GetSerachListModel;
import com.zhenghaikj.shop.mvp.presenter.GetSerachListPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/*积分商城搜索列表页面*/
public class GetSerachListActivity extends BaseActivity<GetSerachListPresenter, GetSerachListModel> implements GetSerachListContract.View, View.OnClickListener {
    @BindView(R.id.view)
    View mView;
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.tv_search_txt)
    TextView mTvSearchTxt;
    @BindView(R.id.img_cancle)
    ImageView mImgCancle;
    @BindView(R.id.ll_serach_txt)
    LinearLayout mLlSerachTxt;
    @BindView(R.id.et_search)
    EditText mEtSearch;
    @BindView(R.id.ll_bg_serach)
    LinearLayout mLlBgSerach;
    @BindView(R.id.tv_serach)
    TextView mTvSerach;
    @BindView(R.id.ll_filter)
    LinearLayout mLlFilter;
    @BindView(R.id.ll_serach)
    LinearLayout mLlSerach;
    @BindView(R.id.rv)
    RecyclerView mRv;

    private String skey;
    private GetSerachListAdapter getSerachListAdapter;
    private List<GetSerachListResult.DataListBean> list=new ArrayList<>();
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
        return R.layout.activity_getserachlist;
    }

    @Override
    protected void initData() {
        skey = getIntent().getStringExtra("skey");
        getSerachListAdapter=new GetSerachListAdapter(R.layout.item_shop,list);
        StaggeredGridLayoutManager staggeredGridLayoutManager=new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        mRv.setLayoutManager(staggeredGridLayoutManager);
        mRv.setAdapter(getSerachListAdapter);
        mPresenter.GetSerachList(skey);
        mTvSearchTxt.setText(skey);




    }

    @Override
    protected void initView() {

    }

    @Override
    protected void setListener() {
        mIvBack.setOnClickListener(this);
        mLlSerachTxt.setOnClickListener(this);
        mLlBgSerach.setOnClickListener(this);
        mTvSerach.setOnClickListener(this);

        getSerachListAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()){
                    case R.id.ll_item:
                        Intent intent=new Intent(mActivity,GiftsDetailActivity.class);
                        intent.putExtra("giftId",((GetSerachListResult.DataListBean)adapter.getData().get(position)).getId()+"");
                        startActivity(intent);
                        break;


                }

            }
        });
    }

    @Override
    public void GetSerachList(GetSerachListResult result) {

        if (result.getDataList().isEmpty()) {
            getSerachListAdapter.setEmptyView(getEmptyView());
        } else {
            list.clear();
            list.addAll(result.getDataList());
            getSerachListAdapter.notifyDataSetChanged();

        }

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

            case R.id.tv_serach:
                mPresenter.GetSerachList(skey);
                break;

            case R.id.iv_back:
                this.finish();
                break;


            case R.id.ll_serach_txt://点击商品删除全部内容返回
                Intent intent=new Intent();
                intent.putExtra("searchresult","");
                setResult(Config.SEARCH_RESULT,intent);
                this.finish();
                break;
            case R.id.ll_bg_serach:  //点击背景返回但不删除
                Intent intent2=new Intent();
                intent2.putExtra("searchresult",skey);
                setResult(Config.SEARCH_RESULT,intent2);
                this.finish();
                break;
        }
    }
}
