package com.zhenghaikj.shop.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.gyf.barlibrary.ImmersionBar;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.base.BaseActivity;
import com.zhenghaikj.shop.base.BaseResult;
import com.zhenghaikj.shop.entity.Data;
import com.zhenghaikj.shop.mvp.contract.GiftContract;
import com.zhenghaikj.shop.mvp.model.GiftModel;
import com.zhenghaikj.shop.mvp.presenter.GiftPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GiftAccountActivity extends BaseActivity<GiftPresenter, GiftModel> implements View.OnClickListener, GiftContract.View {


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
    @BindView(R.id.et_account)
    EditText mEtAccount;
    @BindView(R.id.btn_next_step)
    Button mBtnNextStep;
    @BindView(R.id.ll_toolbar)
    LinearLayout mLlToolbar;
    @BindView(R.id.et_money)
    EditText mEtMoney;
    @BindView(R.id.et_remarks)
    EditText mEtRemarks;
    @BindView(R.id.btn_confirm_transfer)
    Button mBtnConfirmTransfer;
    private String ToUserID;
    private String Connum;
    private String Msg;
    private SPUtils spUtil;
    private String UserID;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_gift_account;
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
        spUtil = SPUtils.getInstance("token");
        UserID = spUtil.getString("userName2");
    }


    @Override
    protected void initView() {
        mTvTitle.setText("赠送账户");
        mTvTitle.setVisibility(View.VISIBLE);
    }

    @Override
    protected void setListener() {
        mIconBack.setOnClickListener(this);
        mTvSave.setOnClickListener(this);
        mBtnConfirmTransfer.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.icon_back:
                finish();
                break;
            case R.id.btn_confirm_transfer:
                ToUserID =mEtAccount.getText().toString();
                Connum =mEtMoney.getText().toString();
                Msg =mEtRemarks.getText().toString();
                if ("".equals(ToUserID)){
                    ToastUtils.showShort("请输入对方账号！");
                    return;
                }
                if ("".equals(Connum)){
                    ToastUtils.showShort("请输入赠送数量！");
                    return;
                }
                mPresenter.FAddCon(UserID,ToUserID,Msg,Connum);
                break;
//            case R.id.tv_save:
//                startActivity(new Intent(mActivity, AddBrankCardActivity.class));
//                break;

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void FAddCon(BaseResult<Data<String>> baseResult) {
        switch (baseResult.getStatusCode()){
            case 200:
                if (baseResult.getData().isItem1()){
                    finish();
                }else{
                }
                ToastUtils.showShort(baseResult.getData().getItem2());
                break;
        }
    }
}
