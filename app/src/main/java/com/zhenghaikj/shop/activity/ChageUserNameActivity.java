package com.zhenghaikj.shop.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.gyf.barlibrary.ImmersionBar;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.base.BaseActivity;
import com.zhenghaikj.shop.base.BaseResult;
import com.zhenghaikj.shop.entity.Data;
import com.zhenghaikj.shop.mvp.contract.ChageUserNameContract;
import com.zhenghaikj.shop.mvp.model.ChageUserNameModel;
import com.zhenghaikj.shop.mvp.presenter.ChageUserNamePresenter;

import org.greenrobot.eventbus.EventBus;

import androidx.appcompat.widget.Toolbar;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ChageUserNameActivity extends BaseActivity<ChageUserNamePresenter, ChageUserNameModel> implements View.OnClickListener, ChageUserNameContract.View {

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
    @BindView(R.id.et_username)
    EditText mEtUsername;
    @BindView(R.id.tv_change_username)
    TextView mTvChangeUsername;
    private String userkey;
    private String userName;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_change_username;
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
        SPUtils spUtils = SPUtils.getInstance("token");
        userkey = spUtils.getString("UserKey");
        userName = spUtils.getString("userName2");
    }

    @Override
    protected void initView() {
        mTvTitle.setVisibility(View.VISIBLE);
        mTvTitle.setText("修改昵称");
    }

    @Override
    protected void setListener() {
        mIconBack.setOnClickListener(this);
        mTvChangeUsername.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.icon_back:
                finish();
                break;
            case R.id.tv_change_username:
                String name=mEtUsername.getText().toString();
                if (name.isEmpty()){
                    ToastUtils.showShort("请输入修改的昵称");
                }else {
                    mPresenter.UpdateAccountNickName(userName,name);
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

    @Override
    public void UpdateAccountNickName(BaseResult<Data> baseResult) {
        switch (baseResult.getStatusCode()){
            case 200:
                EventBus.getDefault().post("UserName");
                ToastUtils.showShort("修改成功");
                finish();
                break;
        }
    }
}
