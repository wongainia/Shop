package com.zhenghaikj.shop.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.gyf.barlibrary.ImmersionBar;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.adapter.BrankCardAdapter;
import com.zhenghaikj.shop.base.BaseActivity;
import com.zhenghaikj.shop.base.BaseResult;
import com.zhenghaikj.shop.entity.BankCard;
import com.zhenghaikj.shop.entity.Data;
import com.zhenghaikj.shop.entity.UserInfo;
import com.zhenghaikj.shop.mvp.contract.CardContract;
import com.zhenghaikj.shop.mvp.model.CardModel;
import com.zhenghaikj.shop.mvp.presenter.CardPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BrandCardActivity extends BaseActivity<CardPresenter, CardModel> implements View.OnClickListener, CardContract.View {


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
    @BindView(R.id.rv_brank_card)
    RecyclerView mRvBrankCard;
    @BindView(R.id.fl_add_card)
    FrameLayout mFlAddCard;
    @BindView(R.id.ll_add_card)
    LinearLayout mLlAddCard;
    @BindView(R.id.tv_tips)
    TextView mTvTips;

    private List<BankCard> list = new ArrayList<>();
    private BrankCardAdapter adapter;
    private String endNum;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_bank_card;
    }

    @Override
    protected void initImmersionBar() {
        mImmersionBar = ImmersionBar.with(this);
        //mImmersionBar.statusBarDarkFont(true, 0.2f); //原理：如果当前设备支持状态栏字体变色，会设置状态栏字体为黑色，如果当前设备不支持状态栏字体变色，会使当前状态栏加上透明度，否则不执行透明度
        mImmersionBar.statusBarView(mView);
        mImmersionBar.keyboardEnable(true);
        mImmersionBar.init();
    }

    @Override
    protected void initData() {
        mPresenter.GetAccountPayInfoList(UserID);

//        for (int i = 0; i < 10; i++) {
//            list.add(new Product());
//        }

        adapter = new BrankCardAdapter(R.layout.item_brank_card, list,mActivity);
//        adapter.setEmptyView(getEmptyViewCommodity());
        mRvBrankCard.setLayoutManager(new LinearLayoutManager(mActivity));
        mRvBrankCard.setAdapter(adapter);
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()){
                    case R.id.fl_card:
                        int length = list.get(position).getPayNo().length();
                        if (length > 4) {
                            endNum = list.get(position).getPayNo().substring(length - 4, length);
                        }
                        Intent intent = new Intent();
                        intent.putExtra("bankName", list.get(position).getPayInfoName());
                        intent.putExtra("bankNo", endNum);
                        setResult(2000, intent);
                        finish();
                        break;
                }
            }
        });
    }

    @Override
    protected void initView() {
        mTvTitle.setText("我的银行卡");
        mTvTitle.setVisibility(View.VISIBLE);
        mTvSave.setVisibility(View.VISIBLE);
        mTvSave.setText("添加银行卡");
    }

    @Override
    protected void setListener() {
        mIconBack.setOnClickListener(this);
        mFlAddCard.setOnClickListener(this);
        mTvSave.setOnClickListener(this);
        mLlAddCard.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.icon_back:
                finish();
                break;
            case R.id.tv_save:
            case R.id.ll_add_card:
            case R.id.fl_add_card:
                startActivityForResult(new Intent(this, AddBrankCardActivity.class), 2002);
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void GetUserInfoList(BaseResult<UserInfo> baseResult) {

    }

    @Override
    public void AddorUpdateAccountPayInfo(BaseResult<Data<String>> baseResult) {

    }

    @Override
    public void GetAccountPayInfoList(BaseResult<List<BankCard>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                if (baseResult.getData() == null) {
                    return;
                } else {
                    list.addAll(baseResult.getData());
                    adapter.setNewData(list);

                }
                break;
            default:
                break;
        }
    }

    @Override
    public void GetBankNameByCardNo(BaseResult<Data<String>> baseResult) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 2000) {
            if (requestCode == 2002) {//添加卡的请求
                mPresenter.GetAccountPayInfoList(UserID);
            }
        }
    }
}
