package com.zhenghaikj.shop.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.blankj.utilcode.util.ToastUtils;
import com.gyf.barlibrary.ImmersionBar;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.base.BaseActivity;
import com.zhenghaikj.shop.base.BaseResult;
import com.zhenghaikj.shop.entity.Data;
import com.zhenghaikj.shop.mvp.contract.OpinionContract;
import com.zhenghaikj.shop.mvp.model.OpinionModel;
import com.zhenghaikj.shop.mvp.presenter.OpinionPresenter;
import com.zhenghaikj.shop.utils.MyUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OpinionActivity extends BaseActivity<OpinionPresenter, OpinionModel> implements View.OnClickListener, OpinionContract.View {


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
    @BindView(R.id.tv_account_problem)
    TextView mTvAccountProblem;
    @BindView(R.id.tv_payment_issues)
    TextView mTvPaymentIssues;
    @BindView(R.id.tv_other_questions)
    TextView mTvOtherQuestions;
    @BindView(R.id.et_opinion)
    EditText mEtOpinion;
    @BindView(R.id.tv_word_count)
    TextView mTvWordCount;
    @BindView(R.id.btn_opinion)
    Button mBtnOpinion;
    private String type = "";
    private String content;
    private int MAX_COUNT = 200;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_opinion;
    }

    /**
     * 初始化沉浸式
     */
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
    }

    @Override
    protected void initView() {
        mTvTitle.setText("意见反馈");
        mTvTitle.setVisibility(View.VISIBLE);
        type = "1";
        mTvAccountProblem.setSelected(true);
        mTvPaymentIssues.setSelected(false);
        mTvOtherQuestions.setSelected(false);
        mEtOpinion.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                mTvWordCount.setText(editable.length() + "/200");
            }
        });
    }

    @Override
    protected void setListener() {
        mIconBack.setOnClickListener(this);
        mTvAccountProblem.setOnClickListener(this);
        mTvPaymentIssues.setOnClickListener(this);
        mTvOtherQuestions.setOnClickListener(this);
        mBtnOpinion.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.icon_back:
                OpinionActivity.this.finish();
                break;
            case R.id.tv_account_problem:
                type = "1";
                mTvAccountProblem.setSelected(true);
                mTvPaymentIssues.setSelected(false);
                mTvOtherQuestions.setSelected(false);
                break;
            case R.id.tv_payment_issues:
                type = "2";
                mTvAccountProblem.setSelected(false);
                mTvPaymentIssues.setSelected(true);
                mTvOtherQuestions.setSelected(false);
                break;
            case R.id.tv_other_questions:
                type = "3";
                mTvAccountProblem.setSelected(false);
                mTvPaymentIssues.setSelected(false);
                mTvOtherQuestions.setSelected(true);
                break;
            case R.id.btn_opinion:
                content = mEtOpinion.getText().toString().trim();
                if ("".equals(type)) {
                    MyUtils.showToast(mActivity, "请选择问题类型");
                    return;
                }
                if ("".equals(content)) {
                    MyUtils.showToast(mActivity, "请输入反馈内容");
                    return;
                }
                mPresenter.AddOpinion(UserID, type, content);
                break;

        }

    }

    @Override
    public void AddOpinion(BaseResult<Data<String>> baseResult) {
        switch (baseResult.getStatusCode()) {
            case 200:
                Data<String> data = baseResult.getData();
                if (data.isItem1()) {
//                    ToastUtils.showShort(data.getItem2());
                    ToastUtils.showShort("反馈成功");
                    mEtOpinion.setText("");
                    type = "";
                    mTvAccountProblem.setSelected(false);
                    mTvPaymentIssues.setSelected(false);
                    mTvOtherQuestions.setSelected(false);
                } else {
                    ToastUtils.showShort(data.getItem2());
                }
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
