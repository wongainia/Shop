package com.zhenghaikj.shop.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.gyf.barlibrary.ImmersionBar;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.base.BaseActivity;
import com.zhenghaikj.shop.base.BaseResult;
import com.zhenghaikj.shop.entity.Data;
import com.zhenghaikj.shop.entity.UserInfo;
import com.zhenghaikj.shop.mvp.contract.ChangePayPasswordContract;
import com.zhenghaikj.shop.mvp.model.ChangePayPasswordModel;
import com.zhenghaikj.shop.mvp.presenter.ChangePayPasswordPresenter;
import com.zhenghaikj.shop.widget.paypassword.PasswordEditText;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SettingPayPasswordActivity extends BaseActivity<ChangePayPasswordPresenter, ChangePayPasswordModel> implements View.OnClickListener, PasswordEditText.PasswordFullListener, ChangePayPasswordContract.View {

    private LinearLayout mKeyBoardView;
    private PasswordEditText mPasswordEditText;

    private int Type;  //1设置 2修改

    private String paypassword; //支付密码

    private List<String> paylist=new ArrayList<>();



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
    @BindView(R.id.ll_toolbar)
    LinearLayout mLlToolbar;
    @BindView(R.id.passwordEdt)
    PasswordEditText mPasswordEdt;
    @BindView(R.id.img_clean)
    ImageView mImgClean;
    @BindView(R.id.img_delete)
    ImageView mImgDelete;
    @BindView(R.id.keyboard)
    LinearLayout mKeyboard;
    @BindView(R.id.tv_prompt)
    TextView mTvprompt;






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
        return R.layout.activity_settingpaypassword;
    }

    @Override
    protected void initData() {
        mPresenter.GetUserInfoList(UserID,"1");


    }

    @Override
    protected void initView() {
        mKeyBoardView= (LinearLayout) findViewById(R.id.keyboard);
        mPasswordEditText = (PasswordEditText)findViewById(R.id.passwordEdt);
        mPasswordEditText.setPasswordFullListener(this);
        setItemClickListener(mKeyBoardView);

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

    /**
     * 给每一个自定义数字键盘上的View 设置点击事件
     *
     * @param view
     */
    private void setItemClickListener(View view) {
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int childCount = viewGroup.getChildCount();
            for (int i = 0; i < childCount; i++) {
                //不断的给里面所有的View设置setOnClickListener
                View childView = ((ViewGroup) view).getChildAt(i);
                setItemClickListener(childView);
            }
        } else {
            view.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.icon_back:
                finish();
                break;
        }
        if (v instanceof TextView) {
            String number = ((TextView) v).getText().toString().trim();
            mPasswordEditText.addPassword(number);
        }
        if (v instanceof ImageView) {
            switch (v.getId()){
                case R.id.img_clean:
                    mPasswordEditText.cleanPassword();
                    break;
                case R.id.img_delete:
                    mPasswordEditText.deletePassword();
                    break;
            }
        }
    }

    @Override
    public void passwordFull(String password) {
        Log.d("=======>pass",password);
        if (Type==1){
         mPresenter.ChangePayPassword(UserID,"",password);
        }else if (Type==2){
            if (paylist.size()==0){ //输入旧支付密码
                if (!paypassword.equals(password)){
                    Toast.makeText(mActivity,"旧支付密码错误",Toast.LENGTH_SHORT).show();
                }else {
                    paylist.add(password);
                    mPasswordEditText.cleanPassword();//清除支付密码 输入新的支付密码
                    mTvprompt.setText("请输入新支付密码");
                }

            }else {
                mPresenter.ChangePayPassword(UserID,paypassword,password);
            }






        }


    }


    @Override
    public void GetUserInfoList(BaseResult<UserInfo> baseResult) {
          switch (baseResult.getStatusCode()){
              case 200:
                  if (baseResult.getData().getData()!=null){
                    /*判断是否有支付密码*/

                    if ("".equals(baseResult.getData().getData().get(0).getPayPassWord())){
                      mTvTitle.setText("设置支付密码");
                      mTvTitle.setVisibility(View.VISIBLE);
                      Type=1;
                      mTvprompt.setText("请输入六位密码");
                    }else {
                      paypassword=baseResult.getData().getData().get(0).getPayPassWord();
                      mTvTitle.setText("修改支付密码");
                      mTvTitle.setVisibility(View.VISIBLE);
                      Type=2;
                      mTvprompt.setText("请输入旧支付密码");
                    }

                  }

                  break;
          }


    }

    @Override
    public void ChangePayPassword(BaseResult<Data> baseResult) {
         switch (baseResult.getStatusCode()){
             case 200:
                 if (baseResult.getData().isItem1()){
                     if (Type==1){
                     Toast.makeText(mActivity,"设置支付密码成功",Toast.LENGTH_SHORT).show();
                     SettingPayPasswordActivity.this.finish();
                     }else if (Type==2){
                     Toast.makeText(mActivity,"修改支付密码成功",Toast.LENGTH_SHORT).show();
                     SettingPayPasswordActivity.this.finish();
                     }
                 }

                 break;

         }

    }
}
