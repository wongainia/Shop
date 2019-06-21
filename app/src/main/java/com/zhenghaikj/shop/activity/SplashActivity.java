package com.zhenghaikj.shop.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.gyf.barlibrary.ImmersionBar;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.base.BaseActivity;
import com.zhenghaikj.shop.base.BaseResult;
import com.zhenghaikj.shop.entity.Data;
import com.zhenghaikj.shop.entity.LoginResult;
import com.zhenghaikj.shop.mvp.contract.LoginContract;
import com.zhenghaikj.shop.mvp.model.LoginModel;
import com.zhenghaikj.shop.mvp.presenter.LoginPresenter;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;

public class SplashActivity extends BaseActivity<LoginPresenter, LoginModel> implements LoginContract.View, View.OnClickListener {

    @BindView(R.id.view)
    View mView;
    private int recLen = 4; //倒计时3秒
    private TextView tv_splash_skin;
    Timer timer = new Timer();
    private Handler handler;
    private Runnable runnable;

    @Override
    protected void initImmersionBar() {
        mImmersionBar = ImmersionBar.with(this);
        //mImmersionBar.statusBarDarkFont(true, 0.2f); //原理：如果当前设备支持状态栏字体变色，会设置状态栏字体为黑色，如果当前设备不支持状态栏字体变色，会使当前状态栏加上透明度，否则不执行透明度
        mImmersionBar.statusBarView(mView);
        mImmersionBar.keyboardEnable(true);
        mImmersionBar.init();
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initData() {
        if (!isLogin) {
            return;
        } else {
            mPresenter.GettokenbyUserid(UserID);
        }

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    @Override
    protected void initView() {
        if ((getIntent().getFlags() & Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT) != 0) {
            finish();
            return;
        }
        tv_splash_skin = (TextView) findViewById(R.id.tv_splash_skin);
    }

    @Override
    protected void setListener() {
        tv_splash_skin.setOnClickListener(this);
        timer.schedule(task, 0, 1000);

        /**
         * 正常情况下不点击跳过
         */
        handler = new Handler();
        handler.postDelayed(runnable = new Runnable() {
            @Override
            public void run() {
                /*调转到主界面界面*/
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                SplashActivity.this.finish();
            }
        }, 3000); //延时3秒后发送信息

    }


    TimerTask task = new TimerTask() {
        @Override
        public void run() {
            runOnUiThread(new Runnable() { //主线程
                @Override
                public void run() {
                    recLen--;
                    tv_splash_skin.setText("跳过 " + recLen);
                    if (recLen < 0) {
                        timer.cancel();
                        tv_splash_skin.setVisibility(View.GONE); //倒计时到0隐藏字体
                    }
                }
            });

        }
    };

    @Override
    public void GetUser(LoginResult Result) {

    }

    @Override
    public void LoginOn(BaseResult<Data<String>> Result) {

    }

    @Override
    public void AddAndUpdatePushAccount(BaseResult<Data<String>> Result) {

    }

    @Override
    public void GettokenbyUserid(BaseResult<Data<String>> Result) {
        switch (Result.getStatusCode()) {
            case 200:
                if (Result.getData().isItem1()) {
                    spUtils.put("adminToken", Result.getData().getItem2());
                    spUtils.put("userName", UserID);
                }
                break;


        }


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_splash_skin:
                /*调转到主界面界面*/
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                SplashActivity.this.finish();
                if (runnable != null) {
                    handler.removeCallbacks(runnable);
                }

                break;
            default:
                break;
        }


    }

  /*  boolean isFirstIn = false;
    private static final int GO_HOME = 1000;
    private static final int GO_GUIDE = 1001;
    private static final long SPLASH_DELAY_MILLIS = 2000;


    private static final String SHAREDPREFERENCES_NAME = "first_pref";
    private SPUtils spUtils;
    private String UserID;
    private String passWord;
    private boolean isLogin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if((getIntent().getFlags() & Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT) != 0){
            finish();
            return;
        }
        init();
    }
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case GO_HOME:
                    goHome();
//                    goGuide();
                    break;
                case GO_GUIDE:
//                    goGuide();
                    goHome();
                    break;
            }
            super.handleMessage(msg);
        }
    };
    private void init() {
        SharedPreferences preferences = getSharedPreferences(
                SHAREDPREFERENCES_NAME, MODE_PRIVATE);


        isFirstIn = preferences.getBoolean("isFirstIn", true);
//        isFirstIn = true;
        if (!isFirstIn) {
            mHandler.sendEmptyMessageDelayed(GO_HOME, SPLASH_DELAY_MILLIS);
        } else {
            mHandler.sendEmptyMessageDelayed(GO_GUIDE, SPLASH_DELAY_MILLIS);
        }

    }


    private void goHome() {
        startActivity(new Intent(SplashActivity.this, MainActivity.class));
        *//*spUtils = SPUtils.getInstance("token");
        UserID = spUtils.getString("UserID");
        passWord = spUtils.getString("password");
        isLogin = spUtils.getBoolean("isLogin");
        if (UserID!=null&&isLogin){
            startActivity(new Intent(SplashActivity.this, MainActivity.class));
        }else{
            startActivity(new Intent(SplashActivity.this, LoginActivity.class));
        }*//*
        finish();
    }


    private void goGuide() {
//        Intent intent = new Intent(SplashActivity.this, GuideActivity.class);
//        SplashActivity.this.startActivity(intent);
//        SplashActivity.this.finish();
    }*/


}


