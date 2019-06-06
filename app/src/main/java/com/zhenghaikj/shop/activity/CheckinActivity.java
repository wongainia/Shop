package com.zhenghaikj.shop.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.gyf.barlibrary.ImmersionBar;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.base.BaseActivity;
import com.zhenghaikj.shop.widget.fliplayout.FlipLayout;

import androidx.appcompat.widget.Toolbar;
import butterknife.BindView;
import butterknife.ButterKnife;

/*签到页面*/
public class CheckinActivity extends BaseActivity implements View.OnClickListener,FlipLayout.FlipOverListener {

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
    TextView mTvThree;
    @BindView(R.id.tv_day)
    TextView mTvDay;
    @BindView(R.id.tv_people)
    TextView mTvPeople;
    @BindView(R.id.btn_check_in)
    Button mBtnCheckIn;


    @BindView(R.id.flip_one)
    FlipLayout mflipone;

    @BindView(R.id.flip_two)
    FlipLayout mfliptwo;

    @BindView(R.id.flip_three)
    FlipLayout mflipthree;

    @BindView(R.id.pb_day)
    ProgressBar mPbday;

    private int getcurrentnum=132; //获取当前的坚持的天数
    @Override
    protected int setLayoutId() {
        return R.layout.activity_check_in;
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
         /*时间初始化*/
        FlipToday();
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void setListener() {
        mIconBack.setOnClickListener(this);
        mBtnCheckIn.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.icon_back:
                finish();
                break;
            case R.id.btn_check_in:
                Log.d("=====>getcurrentnum", String.valueOf(getcurrentnum));
                mflipthree.smoothFlip(1, false);
                getcurrentnum++;
                if (getcurrentnum % 10 == 0){  //个位为0 十位翻页
                    mfliptwo.smoothFlip(1, false);
                }
                if (getcurrentnum /10%10==0&&getcurrentnum %10==0){  //个位十位都为0 百位翻页
                    mflipone.smoothFlip(1, false);
                }
                if (getcurrentnum / 100 %10==0&&getcurrentnum /10%10==0&&getcurrentnum % 10 ==0){ //超过999自动回001
                    mflipone.flip(0);
                    mfliptwo.flip(0);
                    mflipthree.flip(1);
                    getcurrentnum=1;
                }
                mPbday.incrementProgressBy(1);
                mBtnCheckIn.setClickable(false);
                mBtnCheckIn.setText("今日已签到");
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
    public void onFLipOver(FlipLayout flipLayout) {

    }

    /*初始化已经连续签到的时间*/
    public void FlipToday(){
        int hundreds = getcurrentnum / 100;
        int decade = getcurrentnum /10% 10 ;
        int unit = getcurrentnum % 10;
        mflipone.flip(hundreds);
        mfliptwo.flip(decade);
        mflipthree.flip(unit);
    }

}
