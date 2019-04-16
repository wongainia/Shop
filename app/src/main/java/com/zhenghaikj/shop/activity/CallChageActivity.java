package com.zhenghaikj.shop.activity;

import android.os.Bundle;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.gyf.barlibrary.ImmersionBar;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.adapter.CallChageAdapter;
import com.zhenghaikj.shop.base.BaseActivity;
import com.zhenghaikj.shop.entity.CallChage;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CallChageActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.et_phone_number)
    EditText mEtPhoneNumber;
    @BindView(R.id.rv_phone_money)
    RecyclerView mRvPhoneMoney;
    @BindView(R.id.view)
    View mView;
    private List<CallChage> callChageList = new ArrayList<>();
    private String[] callChage = new String[]{"10元", "30元", "50元", "100元", "200元", "300元"};
    private String[] callChagePrice = new String[]{"售价：9.98", "售价：29.94", "售价：49.90", "售价：99.80", "售价：199.60", "售价：299.40"};
    private CallChageAdapter callChageAdapter;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_call_charge;
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
        for (int i = 0; i < 6; i++) {
            callChageList.add(new CallChage(callChage[i], callChagePrice[i]));
        }
        callChageAdapter = new CallChageAdapter(R.layout.item_phone_money, callChageList);
        mRvPhoneMoney.setLayoutManager(new GridLayoutManager(mActivity, 3));
        mRvPhoneMoney.setAdapter(callChageAdapter);


    }

    @Override
    protected void initView() {

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
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
        }
    }
}
