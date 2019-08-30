package com.zhenghaikj.shop.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.ToastUtils;
import com.gyf.barlibrary.ImmersionBar;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.adapter.ChooseBankAdapter;
import com.zhenghaikj.shop.base.BaseActivity;
import com.zhenghaikj.shop.base.BaseResult;
import com.zhenghaikj.shop.dialog.CommonDialog_Home;
import com.zhenghaikj.shop.dialog.CustomDialog_ChooseBank;
import com.zhenghaikj.shop.entity.BankCard;
import com.zhenghaikj.shop.entity.Data;
import com.zhenghaikj.shop.entity.UserInfo;
import com.zhenghaikj.shop.mvp.contract.CardContract;
import com.zhenghaikj.shop.mvp.model.CardModel;
import com.zhenghaikj.shop.mvp.presenter.CardPresenter;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddBrankCardActivity extends BaseActivity<CardPresenter, CardModel> implements View.OnClickListener, CardContract.View {


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
    @BindView(R.id.tv_add_card_name)
    EditText mTvAddCardName;
    @BindView(R.id.tv_add_card_bankname)
    TextView mTvAddCardBankname;
    @BindView(R.id.ll_choose_bank)
    LinearLayout mLlChooseBank;
    @BindView(R.id.et_banknumber)
    EditText mEtBanknumber;
    @BindView(R.id.et_add_card_phone)
    EditText mEtAddCardPhone;
    @BindView(R.id.tv_bind_card)
    TextView mTvBindCard;

    private CustomDialog_ChooseBank customDialog_chooseBank;//选择银行dialog
    private RecyclerView recyclerView_custom_bank;//显示银行的RecyclerView
    private ChooseBankAdapter chooseBankAdapter;
    private UserInfo.UserInfoDean userInfo=new UserInfo.UserInfoDean();

    @Override
    protected int setLayoutId() {
        return R.layout.activity_add_bank_card;
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
        mPresenter.GetUserInfoList(UserID,"1");
    }


    @Override
    protected void initView() {
        mTvTitle.setText("添加银行卡");
        mTvTitle.setVisibility(View.VISIBLE);
        customDialog_chooseBank = new CustomDialog_ChooseBank(mActivity);
    }

    @Override
    protected void setListener() {
        mIconBack.setOnClickListener(this);
        mTvBindCard.setOnClickListener(this);
        /*mimg_scan_card.setOnClickListener(this);*/
        mEtBanknumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


            }

            @Override
            public void afterTextChanged(Editable s) {

                if (s.toString().length()==6){

                    mPresenter.GetBankNameByCardNo(s.toString());
                }else if (s.toString().length()>=16){

                    String num=s.toString();
                    String substring = num.substring(0, 6);

                    mPresenter.GetBankNameByCardNo(substring);
                }

                else {
                    mTvAddCardBankname.setText("");
                }

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.icon_back:
                finish();
                break;
            case R.id.tv_bind_card:
                if ("".equals(mTvAddCardName.getText().toString())){
                    ToastUtils.showShort("请输入姓名");
                } else if (mTvAddCardBankname.getText().toString().length()==0||mEtBanknumber.getText().toString().length()==0||mEtAddCardPhone.getText()==null){
                    Toast.makeText(this,"请选择银行并输入卡号和手机号",Toast.LENGTH_SHORT).show();
                } else {
                    mPresenter.AddorUpdateAccountPayInfo(UserID,"Bank",mTvAddCardBankname.getText().toString(),mEtBanknumber.getText().toString(),mTvAddCardName.getText().toString());
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
    public void GetUserInfoList(BaseResult<UserInfo> baseResult) {
        switch (baseResult.getStatusCode()){
            case 200:
                if (baseResult.getData()==null){
                    return;
                }else {
                    userInfo=baseResult.getData().getData().get(0);
                    if (userInfo.getTrueName()==null){
                        return;
                    }else {
//                        mTvAddCardName.setText(userInfo.getTrueName());
                        if (userInfo.getPhone()==null){
                            return;
                        }else {
                            mEtAddCardPhone.setText(userInfo.getPhone());
                        }

                    }




                }
                break;

            default:
                break;
        }
    }

    @Override
    public void AddorUpdateAccountPayInfo(BaseResult<Data<String>> baseResult) {
        switch (baseResult.getStatusCode()){
            case 200:
                if (baseResult.getData().isItem1()){
                    setResult(2000);
                    EventBus.getDefault().post("GetAccountPayInfoList");
                    AddBrankCardActivity.this.finish();

                }else {
                    Toast.makeText(this,"添加失败",Toast.LENGTH_SHORT).show();

                }
                break;
            default:
                break;
        }

    }

    @Override
    public void GetAccountPayInfoList(BaseResult<List<BankCard>> baseResult) {

    }

    @Override
    public void GetBankNameByCardNo(BaseResult<Data<String>> baseResult) {
        switch (baseResult.getStatusCode()){
            case 200:
                if (baseResult.getData().isItem1()&&!baseResult.getData().getItem2().equals("")){

                    mTvAddCardBankname.setText(baseResult.getData().getItem2()); //绑定银行名


                }else {//不支持的银行
                    mEtBanknumber.setText("");
                    final CommonDialog_Home dialog = new CommonDialog_Home(mActivity);
                    dialog.setMessage("暂时不支持绑定该银行")
                            //.setImageResId(R.mipmap.ic_launcher)
                            .setTitle("提示")
                            .setSingle(false).setOnClickBottomListener(new CommonDialog_Home.OnClickBottomListener() {
                        @Override
                        public void onPositiveClick() {//拨打电话
                            dialog.dismiss();
                        }

                        @Override
                        public void onNegtiveClick() {//取消
                            dialog.dismiss();
                            // Toast.makeText(MainActivity.this,"ssss",Toast.LENGTH_SHORT).show();
                        }
                    }).show();

                }
                break;
            default:
                break;
        }
    }
}
