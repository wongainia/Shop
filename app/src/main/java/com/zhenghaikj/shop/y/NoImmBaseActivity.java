package com.zhenghaikj.shop.y;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.blankj.utilcode.util.SPUtils;
import com.gyf.barlibrary.ImmersionBar;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.base.BaseModel;
import com.zhenghaikj.shop.base.BasePresenter;
import com.zhenghaikj.shop.base.BaseView;
import com.zhenghaikj.shop.base.RxManager;
import com.zhenghaikj.shop.utils.HandleBackUtil;
import com.zhenghaikj.shop.utils.TUtil;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.imid.swipebacklayout.lib.SwipeBackLayout;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

/**
 *
 * 用于解决搜索界面无法自动弹出软键盘
 *
 */

public abstract class NoImmBaseActivity<P extends BasePresenter, M extends BaseModel> extends AppCompatActivity {

     private InputMethodManager imm;
    protected ImmersionBar mImmersionBar;
    private Unbinder unbinder;
    public Context mActivity;

    public P mPresenter;
    public M mModel;
    private RxManager mRxManage;
    // 右滑返回

    public SPUtils spUtils;
    public String userKey;
    public String UserID;
    public boolean isLogin;
    private String password;


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
      //  this.imm = null;
      if (mImmersionBar != null)
            mImmersionBar.destroy();  //在BaseActivity里销毁
        EventBus.getDefault().unregister(this);
    }

    protected abstract int setLayoutId();


    protected abstract void initData();

    protected abstract void initView();

    protected abstract void setListener();

    protected void initImmersionBar() {
        //在BaseActivity里初始化
        mImmersionBar = ImmersionBar.with(this);
        //mImmersionBar.statusBarDarkFont(true, 0.2f); //原理：如果当前设备支持状态栏字体变色，会设置状态栏字体为黑色，如果当前设备不支持状态栏字体变色，会使当前状态栏加上透明度，否则不执行透明度
        mImmersionBar.statusBarColor(R.color.red);
        mImmersionBar.fitsSystemWindows(true);
        mImmersionBar.keyboardEnable(true);
        mImmersionBar.init();
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


    protected boolean isImmersionBarEnabled() {
        return true;
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


    public void hideSoftKeyBoard() {
        View localView = getCurrentFocus();
        if (this.imm == null) {
            this.imm = ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE));
        }
        if ((localView != null) && (this.imm != null)) {
            this.imm.hideSoftInputFromWindow(localView.getWindowToken(), 2);
        }
    }


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



    @Override
    public void onBackPressed() {
        if (!HandleBackUtil.handleBackPress(this)) {
            super.onBackPressed();
        }
    }


}
