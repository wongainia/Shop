package com.zhenghaikj.shop.activity;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.gyf.barlibrary.ImmersionBar;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.utils.DataCleanManager;
import com.zhenghaikj.shop.base.BaseActivity;
import com.zhenghaikj.shop.dialog.CommonDialog_Home;
import com.zhenghaikj.shop.widget.CircleImageView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SettingActivity extends BaseActivity implements View.OnClickListener {
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
    @BindView(R.id.iv_avatar)
    CircleImageView mIvAvatar;
    @BindView(R.id.tv_nickname)
    TextView mTvNickname;
    @BindView(R.id.ll_address)
    LinearLayout mLlAddress;
    @BindView(R.id.ll_modify_mobile_number)
    LinearLayout mLlModifyMobileNumber;
    @BindView(R.id.ll_modify_the_login_password)
    LinearLayout mLlModifyTheLoginPassword;
    @BindView(R.id.ll_clear_cache)
    LinearLayout mLlClearCache;
    @BindView(R.id.ll_feedback)
    LinearLayout mLlFeedback;
    @BindView(R.id.ll_about_us)
    LinearLayout mLlAboutUs;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_setting;
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

    }

    @Override
    protected void initView() {
        mTvTitle.setText("设置");
        mTvTitle.setVisibility(View.VISIBLE);
    }

    @Override
    protected void setListener() {
        mIconBack.setOnClickListener(this);
        mLlAddress.setOnClickListener(this);
        mLlModifyTheLoginPassword.setOnClickListener(this);
        mLlClearCache.setOnClickListener(this);
        mLlAboutUs.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.icon_back:
                finish();
                break;
            case R.id.ll_address:
                startActivity(new Intent(mActivity,ShippingAddressActivity.class));
                break;
            case R.id.ll_modify_the_login_password:
                startActivity(new Intent(mActivity,ChagePasswordActivity.class));
                break;
            case R.id.ll_clear_cache:
                final CommonDialog_Home dialog_home=new CommonDialog_Home(mActivity);
                dialog_home.setMessage("当前缓存："+getCacheSize())
                        .setTitle("确认清除缓存吗？")
                        .setSingle(false).setOnClickBottomListener(new CommonDialog_Home.OnClickBottomListener() {
                    @Override
                    public void onPositiveClick() {
                        cleanCache();
                        Toast.makeText(mActivity,"清除成功",Toast.LENGTH_LONG).show();
                        dialog_home.dismiss();
                    }

                    @Override
                    public void onNegtiveClick() {
                        dialog_home.dismiss();
                    }
                }).show();
                break;
            case R.id.ll_about_us:
                startActivity(new Intent(mActivity,AboutUsActivity.class));
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    //获取缓存大小
    private String getCacheSize(){
        String str="";
        try {
            str= DataCleanManager.getTotalCacheSize(mActivity);
        }catch (Exception e){
            e.printStackTrace();
        }
        return str ;
    }

    //清空缓存
    private void cleanCache(){
        DataCleanManager.clearAllCache(mActivity);
    }

}
