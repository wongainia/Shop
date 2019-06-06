package com.zhenghaikj.shop.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.bumptech.glide.Glide;
import com.gyf.barlibrary.ImmersionBar;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.base.BaseActivity;
import com.zhenghaikj.shop.base.BaseResult;
import com.zhenghaikj.shop.dialog.CommonDialog_Home;
import com.zhenghaikj.shop.entity.Logout;
import com.zhenghaikj.shop.entity.PersonalInformation;
import com.zhenghaikj.shop.entity.UserInfo;
import com.zhenghaikj.shop.mvp.contract.SettingContract;
import com.zhenghaikj.shop.mvp.model.SettingModel;
import com.zhenghaikj.shop.mvp.presenter.SettingPresenter;
import com.zhenghaikj.shop.utils.DataCleanManager;
import com.zhenghaikj.shop.widget.CircleImageView;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import androidx.appcompat.widget.Toolbar;
import butterknife.BindView;
import butterknife.ButterKnife;

public class SettingActivity extends BaseActivity<SettingPresenter, SettingModel> implements View.OnClickListener, SettingContract.View {
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
    @BindView(R.id.btn_exit)
    Button mBtnExit;
    @BindView(R.id.tv_member_name)
    TextView mTvMemberName;
    @BindView(R.id.ll_brand)
    LinearLayout mLlBrand;
    @BindView(R.id.ll_modify_payment_password)
    LinearLayout mLlModifyPaymentPassword;
    @BindView(R.id.ll_person)
    LinearLayout mLlPerson;
    private SPUtils spUtils;
    private String userKey;
    private String userName;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_setting;
    }

    @Override
    protected void initImmersionBar() {
        mImmersionBar = ImmersionBar.with(this);
//        mImmersionBar.statusBarDarkFont(true, 0.2f); //原理：如果当前设备支持状态栏字体变色，会设置状态栏字体为黑色，如果当前设备不支持状态栏字体变色，会使当前状态栏加上透明度，否则不执行透明度
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

        spUtils = SPUtils.getInstance("token");
        userKey = spUtils.getString("UserKey");
        userName = spUtils.getString("userName2");
        mPresenter.PersonalInformation(userKey);
        mPresenter.GetUserInfoList(userName,"1");
    }

    @Override
    protected void setListener() {
        mIconBack.setOnClickListener(this);
        mLlAddress.setOnClickListener(this);
        mLlModifyTheLoginPassword.setOnClickListener(this);
        mLlClearCache.setOnClickListener(this);
        mLlAboutUs.setOnClickListener(this);
        mLlModifyMobileNumber.setOnClickListener(this);
        mBtnExit.setOnClickListener(this);
        mLlBrand.setOnClickListener(this);
        mLlPerson.setOnClickListener(this);
        mLlModifyPaymentPassword.setOnClickListener(this);
        mLlFeedback.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_brand:
                startActivity(new Intent(mActivity, BrandActivity.class));
                break;
            case R.id.icon_back:
                finish();
                break;
            case R.id.ll_address:
                startActivity(new Intent(mActivity, ShippingAddressActivity.class));
                break;
            case R.id.ll_modify_the_login_password:
                startActivity(new Intent(mActivity, ChagePasswordActivity.class));
                break;
            case R.id.ll_clear_cache:
                final CommonDialog_Home dialog_home = new CommonDialog_Home(mActivity);
                dialog_home.setMessage("当前缓存：" + getCacheSize())
                        .setTitle("确认清除缓存吗？")
                        .setSingle(false).setOnClickBottomListener(new CommonDialog_Home.OnClickBottomListener() {
                    @Override
                    public void onPositiveClick() {
                        cleanCache();
                        Toast.makeText(mActivity, "清除成功", Toast.LENGTH_LONG).show();
                        dialog_home.dismiss();
                    }

                    @Override
                    public void onNegtiveClick() {
                        dialog_home.dismiss();
                    }
                }).show();
                break;
            case R.id.ll_about_us:
                startActivity(new Intent(mActivity, AboutUsActivity.class));
                break;
            case R.id.ll_modify_mobile_number:
                startActivity(new Intent(mActivity, BindPhoneActivity.class));
                break;
            case R.id.btn_exit:
                spUtils.clear();
//                mPresenter.PostLogout();
                ActivityUtils.finishAllActivities();

                startActivity(new Intent(mActivity, MainActivity.class));

                break;
            case R.id.ll_person:
                startActivity(new Intent(mActivity, PersonalInformationActivity.class));
                break;
            case R.id.ll_modify_payment_password:
               // startActivity(new Intent(mActivity, ChagePayActivity.class));
                startActivity(new Intent(mActivity,SettingPayPasswordActivity.class));


                break;
            case R.id.ll_feedback:
                startActivity(new Intent(mActivity,OpinionActivity.class));
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
    private String getCacheSize() {
        String str = "";
        try {
            str = DataCleanManager.getTotalCacheSize(mActivity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }

    //清空缓存
    private void cleanCache() {
        DataCleanManager.clearAllCache(mActivity);
    }

    @Override
    public void PostLogout(Logout result) {
        if (result.isSuccess()) {
            ToastUtils.showShort("退出成功");
        }
    }

    @Override
    public void PersonalInformation(PersonalInformation result) {
        if (result.isSuccess()) {
            String mobile = result.getUserName();
            String maskNumber = mobile.substring(0,3)+"****"+mobile.substring(7,mobile.length());
            mTvMemberName.setText("会员名：" + maskNumber);
            /*设置头像*/
            if (result.getPhoto() == null || "".equals(result.getPhoto())) {//显示默认头像
                return;
            } else {
//                byte[] decode;
//                decode = Base64.decode(result.getPhoto(), Base64.DEFAULT);
                Glide.with(mActivity).asBitmap().load(result.getPhoto()).into(mIvAvatar);
            }
        }
    }

    @Override
    public void GetUserInfoList(BaseResult<UserInfo> Result) {
        switch (Result.getStatusCode()){
            case 200:
                if (Result.getData().getData().get(0).getNickName().equals(Result.getData().getData().get(0).getId())){
                    mTvNickname.setText("未设置昵称");
                }else{
                    mTvNickname.setText(Result.getData().getData().get(0).getNickName());
                }

                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(String name) {
        switch (name){
            case "UserName":
                mPresenter.PersonalInformation(userKey);
                mPresenter.GetUserInfoList(userName,"1");
                break;
        }

    }
}
