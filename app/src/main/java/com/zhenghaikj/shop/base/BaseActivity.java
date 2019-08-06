package com.zhenghaikj.shop.base;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.blankj.utilcode.util.SPUtils;
import com.gyf.barlibrary.ImmersionBar;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.utils.HandleBackUtil;
import com.zhenghaikj.shop.utils.TUtil;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.imid.swipebacklayout.lib.SwipeBackLayout;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

/**
 * Activity基类
 * Created by geyifeng on 2017/5/9.
 */

public abstract class BaseActivity<P extends BasePresenter, M extends BaseModel> extends SwipeBackActivity implements BaseView {

    private InputMethodManager imm;
    protected ImmersionBar mImmersionBar;
    private Unbinder unbinder;
    public Context mActivity;

    public P mPresenter;
    public M mModel;
    private RxManager mRxManage;
    // 右滑返回
    private SwipeBackLayout mSwipeBackLayout;

    public SPUtils spUtils;
    public String userKey;
    public String UserID;
    public boolean isLogin;
    private String password;

    @Override
    public Resources getResources() {
        Resources res = super.getResources();
        Configuration config=new Configuration();
        config.setToDefaults();
        res.updateConfiguration(config,res.getDisplayMetrics() );
        return res;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        EnvUtils.setEnv(EnvUtils.EnvEnum.SANDBOX);
        super.onCreate(savedInstanceState);
//        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT); //竖屏
        setContentView(setLayoutId());

        spUtils = SPUtils.getInstance("token");
        getLoginMsg();

        this.mActivity=this;
        try {
            EventBus.getDefault().register(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        mSwipeBackLayout=getSwipeBackLayout();
        // 设置滑动方向，可设置EDGE_LEFT, EDGE_RIGHT, EDGE_ALL, EDGE_BOTTOM
        mSwipeBackLayout.setEdgeTrackingEnabled(SwipeBackLayout.EDGE_LEFT);

        mPresenter = obtainPresenter();
        mModel = obtainModel();
        if (mPresenter != null) {
            mPresenter.mContext = mActivity;
            if (this instanceof BaseView) {
                mPresenter.setVM(this, mModel);
            }
            mPresenter.onCreate(savedInstanceState);
        }

        mRxManage = new RxManager();
        //绑定控件
        unbinder = ButterKnife.bind(this);
        //初始化沉浸式
       if (isImmersionBarEnabled())
           initImmersionBar();
        //初始化数据
        initData();
        //view与数据绑定
        initView();
        //设置监听
        setListener();

    }
    protected P obtainPresenter() {
        return TUtil.getT(this, 0);
    }

    protected M obtainModel() {
        return TUtil.getT(this, 1);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        this.imm = null;
        if (mImmersionBar != null)
            mImmersionBar.destroy();  //在BaseActivity里销毁
        EventBus.getDefault().unregister(this);
    }

    protected abstract int setLayoutId();

    protected void initImmersionBar() {
        //在BaseActivity里初始化
        mImmersionBar = ImmersionBar.with(this);
        //mImmersionBar.statusBarDarkFont(true, 0.2f); //原理：如果当前设备支持状态栏字体变色，会设置状态栏字体为黑色，如果当前设备不支持状态栏字体变色，会使当前状态栏加上透明度，否则不执行透明度
        mImmersionBar.statusBarColor(R.color.red);
        mImmersionBar.fitsSystemWindows(true);
        mImmersionBar.keyboardEnable(true);
        mImmersionBar.init();
    }

    protected abstract void initData();

    protected abstract void initView();

    protected abstract void setListener();

    /**
     * 是否可以使用沉浸式
     * Is immersion bar enabled boolean.
     *
     * @return the boolean
     */

    protected boolean isImmersionBarEnabled() {
        return true;
    }

    public void finish() {
        super.finish();
        hideSoftKeyBoard();
    }
    public void getLoginMsg(){
        userKey = spUtils.getString("UserKey");
        UserID = spUtils.getString("userName");
        password = spUtils.getString("password");
        isLogin = spUtils.getBoolean("isLogin",false);
    }
//    @Subscribe(threadMode = ThreadMode.MAIN)
//    public void Event(String name) {
//        if ("更新登录信息".equals(name)){
//            getLoginMsg();
//            //初始化数据
////            initData();
//            //view与数据绑定
////            initView();
//        }
//    }

    public View getEmptyView() {
        return  LayoutInflater.from(mActivity).inflate(R.layout.layout_no_data_commodity,null);
    }
    public View getEmptyViewNoAddress() {
        return  LayoutInflater.from(mActivity).inflate(R.layout.layout_no_data_address,null);
    }

    public View getEmptyViewComment() {
        return  LayoutInflater.from(mActivity).inflate(R.layout.layout_no_data_comment,null);
    }
    public View getEmptyViewCommodity() {
        return  LayoutInflater.from(mActivity).inflate(R.layout.layout_no_data_commodity,null);
    }


    public void hideSoftKeyBoard() {
        View localView = getCurrentFocus();
        if (this.imm == null) {
            this.imm = ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE));
        }
        if ((localView != null) && (this.imm != null)) {
            this.imm.hideSoftInputFromWindow(localView.getWindowToken(), 2);
        }
    }


    @Override
    public void onBackPressed() {
        if (!HandleBackUtil.handleBackPress(this)) {
            super.onBackPressed();
        }
    }

    /*电话*/
    public static final int REQUEST_CALL_PERMISSION = 10111; //拨号请求码
    /**
     * 判断是否有某项权限
     * @param string_permission 权限
     * @param request_code 请求码
     * @return
     */
    public boolean checkReadPermission(String string_permission,int request_code) {
        boolean flag = false;
        if (ContextCompat.checkSelfPermission(this, string_permission) == PackageManager.PERMISSION_GRANTED) {//已有权限
            flag = true;
        } else {//申请权限
            ActivityCompat.requestPermissions(this, new String[]{string_permission}, request_code);
        }
        return flag;
    }

    /**
     * 拨打电话（直接拨打）
     * @param telPhone 电话
     */
    public void call(String telPhone){
        if(checkReadPermission(Manifest.permission.CALL_PHONE,REQUEST_CALL_PERMISSION)){
            Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse(telPhone));
            startActivity(intent);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void contentLoading() {

    }

    @Override
    public void contentLoadingComplete() {

    }

    @Override
    public void contentLoadingError() {

    }

    @Override
    public void contentLoadingEmpty() {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }
}
