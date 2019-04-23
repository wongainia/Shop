package com.zhenghaikj.shop.activity;

import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.gyf.barlibrary.ImmersionBar;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.api.Config;
import com.zhenghaikj.shop.base.BaseActivity;
import com.zhenghaikj.shop.entity.PersonalInformation;
import com.zhenghaikj.shop.mvp.contract.PersonalInformationContract;
import com.zhenghaikj.shop.mvp.model.PersonalInformationModel;
import com.zhenghaikj.shop.mvp.presenter.PersonalInformationPresenter;
import com.zhenghaikj.shop.widget.CircleImageView;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import androidx.appcompat.widget.Toolbar;
import butterknife.BindView;
import butterknife.ButterKnife;

public class PersonalInformationActivity extends BaseActivity<PersonalInformationPresenter, PersonalInformationModel> implements View.OnClickListener, PersonalInformationContract.View {
    private static final String TAG = "PersonalInformationActivity";
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
    @BindView(R.id.ll_avatar)
    LinearLayout mLlAvatar;
    @BindView(R.id.tv_username)
    TextView mTvUsername;
    @BindView(R.id.ll_username)
    LinearLayout mLlUsername;
    @BindView(R.id.tv_nickname)
    TextView mTvNickname;
    @BindView(R.id.ll_nickname)
    LinearLayout mLlNickname;
    @BindView(R.id.ll_code)
    LinearLayout mLlCode;
    @BindView(R.id.ll_gender)
    LinearLayout mLlGender;
    @BindView(R.id.tv_gender)
    TextView mTvGender;
    private String userKey;
    private SPUtils spUtils;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_personal_information;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        mTvTitle.setText("个人信息");
        mTvTitle.setVisibility(View.VISIBLE);

        spUtils = SPUtils.getInstance("token");
        userKey = spUtils.getString("UserKey");
        mPresenter.PersonalInformation(userKey);
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
    public void PersonalInformation(PersonalInformation result) {
        if (result.isSuccess()) {
            mTvUsername.setText(result.getUserName());
            mTvNickname.setText(result.getUserName());
            /*设置头像*/
            if (result.getPhoto()==null){//显示默认头像
                return;
            }else {
                byte[] decode;
                decode = Base64.decode(result.getPhoto(), Base64.DEFAULT);
                Glide.with(mActivity).asBitmap().load(decode).into(mIvAvatar);
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(String name) {
        if (!"RegiaterActivity".equals(name)) {
            return;
        }
        mPresenter.PersonalInformation(userKey);
    }
}
