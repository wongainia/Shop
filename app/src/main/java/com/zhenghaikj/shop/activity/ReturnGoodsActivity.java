package com.zhenghaikj.shop.activity;

import android.app.AlertDialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.blankj.utilcode.util.SPUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.gyf.barlibrary.ImmersionBar;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.base.BaseActivity;
import com.zhenghaikj.shop.entity.OrderDetail;
import com.zhenghaikj.shop.entity.RefundApplyResult;
import com.zhenghaikj.shop.mvp.contract.ReturnGoodsContract;
import com.zhenghaikj.shop.mvp.model.ReturnGoodsModel;
import com.zhenghaikj.shop.mvp.presenter.ReturnGoodsPresenter;
import com.zhenghaikj.shop.widget.GlideRoundCropTransform;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/*退款退货activity*/
public class ReturnGoodsActivity extends BaseActivity<ReturnGoodsPresenter, ReturnGoodsModel> implements ReturnGoodsContract.View, View.OnClickListener {
    @BindView(R.id.ll_toolbar)
    LinearLayout mLlToolbar;
    @BindView(R.id.tv_returnmoney)
    TextView mTvReturnmoney;
    @BindView(R.id.tv_return_reason)
    TextView mTvReturnReason;
    @BindView(R.id.tv_name)
    TextView mTvName;
    @BindView(R.id.tv_phone)
    TextView mTvPhone;
    private SPUtils spUtils = SPUtils.getInstance("token");
    OrderDetail.OrderItemBean bean = new OrderDetail.OrderItemBean();
    OrderDetail.OrderBean order = new OrderDetail.OrderBean();

    List<OrderDetail.OrderItemBean> list = new ArrayList<>();
    private String userKey;
    private String orderID;
    private String RefundType;
    private String title;
    private String count;
    private String itemid;
    private double price; //获取商品单价

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
    @BindView(R.id.img_shop)
    ImageView mImgShop;
    @BindView(R.id.tv_shop)
    TextView mTvShop;
    @BindView(R.id.tv_count)
    TextView mTvCount;
    @BindView(R.id.return_type)
    TextView mReturnType;
    @BindView(R.id.tv_reason)
    TextView mTvReason;
    @BindView(R.id.img_arrow)
    ImageView mImgArrow;
    @BindView(R.id.rl_reason)
    RelativeLayout mRlReason;

    @BindView(R.id.et_name)
    EditText mEtname;
    @BindView(R.id.et_phone)
    EditText mEtphone;

    @BindView(R.id.tv_money)
    TextView mTvmoney;
    private AlertDialog dialog;

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
        return R.layout.activity_returngoods;
    }

    @Override
    protected void initData() {
        mTvTitle.setVisibility(View.VISIBLE);

        userKey = spUtils.getString("UserKey");

        Bundle extras = getIntent().getExtras();
        title = extras.getString("title");
        count = extras.getString("num");
        RefundType = extras.getString("RefundType");
        mTvCount.setText("数量:" + count);
        mTvTitle.setText(title);
        mReturnType.setText(title);
        orderID = extras.getString("OrderId");
        itemid = extras.getString("itemid");
        price = extras.getDouble("price");


        mPresenter.GetOrderDetail(orderID, userKey);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void setListener() {
        mIconBack.setOnClickListener(this);
        mTvSave.setOnClickListener(this);
        mRlReason.setOnClickListener(this);


      /*  mEtmoney.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //删除“.”后面超过2位后的数据
                if (s.toString().contains(".")) {
                    if (s.length() - 1 - s.toString().indexOf(".") > 2) {
                        s = s.toString().subSequence(0,
                                s.toString().indexOf(".") + 3);
                        mEtmoney.setText(s);
                        mEtmoney.setSelection(s.length()); //光标移到最后
                    }
                }
                //如果"."在起始位置,则起始位置自动补0
                if (s.toString().trim().substring(0).equals(".")) {
                    s = "0" + s;
                    mEtmoney.setText(s);
                    mEtmoney.setSelection(2);
                }

                //如果起始位置为0,且第二位跟的不是".",则无法后续输入
                if (s.toString().startsWith("0")
                        && s.toString().trim().length() > 1) {
                    if (!s.toString().substring(1, 2).equals(".")) {
                        mEtmoney.setText(s.subSequence(0, 1));
                        mEtmoney.setSelection(1);
                        return;
                    }
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });*/

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void GetOrderDetail(OrderDetail result) {
        if (result.isSuccess()) {
            bean = result.getOrderItem().get(0);
            order = result.getOrder();
            list.addAll(result.getOrderItem());

            Glide.with(mActivity).load(result.getOrderItem().get(0).getProductImage())
                    .apply(RequestOptions.bitmapTransform(new GlideRoundCropTransform(mActivity, 5)))
                    .into(mImgShop);
            mTvShop.setText(result.getOrderItem().get(0).getProductName());
            mTvSave.setText("提交");
            mTvSave.setVisibility(View.VISIBLE);

            mEtname.setText(result.getOrder().getShipTo());
            mEtphone.setText(result.getOrder().getPhone());

            /*显示退款金额*/
            double num = Double.parseDouble(count);
            double totalmoney = num * price;
            mTvmoney.setText(String.valueOf(totalmoney));


        }
    }

    @Override
    public void PostRefundApply(RefundApplyResult result) {
        if (result.isSuccess()) {
            Toast.makeText(mActivity, result.getMsg(), Toast.LENGTH_SHORT).show();
            ReturnGoodsActivity.this.finish();

        } else {
            Toast.makeText(mActivity, result.getMsg(), Toast.LENGTH_SHORT).show();

        }


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.icon_back:
                ReturnGoodsActivity.this.finish();
                break;
            case R.id.rl_reason:
                showReason();
                break;
            case R.id.tv_save://提交    退款金额暂时为全部‘


                String name = mEtname.getText().toString();
                String phone = mEtphone.getText().toString();

                double num = Double.parseDouble(count);
                double totalmoney = num * price;
                if ("".equals(name)) {
                    Toast.makeText(mActivity, "请输入姓名", Toast.LENGTH_SHORT).show();
                    return;
                }
                if ("".equals(phone)) {
                    Toast.makeText(mActivity, "请输入手机号", Toast.LENGTH_SHORT).show();
                    return;
                }
                String reason=mTvReturnReason.getText().toString();
                mPresenter.PostRefundApply(orderID, bean.getItemId(), RefundType, count, String.valueOf(totalmoney), reason, name, phone, "1", userKey);
                break;

        }
    }

    private void showReason() {
        View view = LayoutInflater.from(mActivity).inflate(R.layout.dialog_reason_for_refund, null);
        ImageView iv_close = view.findViewById(R.id.iv_close);
        TextView tv_one = view.findViewById(R.id.tv_one);
        TextView tv_two = view.findViewById(R.id.tv_two);
        TextView tv_three = view.findViewById(R.id.tv_three);
        TextView tv_four = view.findViewById(R.id.tv_four);
        iv_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        tv_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTvReturnReason.setText("不想要了");
                dialog.dismiss();
            }
        });

        tv_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTvReturnReason.setText("图片与实物不符");
                dialog.dismiss();
            }
        });

        tv_three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTvReturnReason.setText("卖家发错货");
                dialog.dismiss();
            }
        });
        tv_four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTvReturnReason.setText("其他");
                dialog.dismiss();
            }
        });

        dialog = new AlertDialog.Builder(mActivity).setView(view).create();
        dialog.show();
        Window window = dialog.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        window.setGravity(Gravity.BOTTOM);
        window.setAttributes(lp);
//                window.setDimAmount(0.1f);
        window.setBackgroundDrawable(new ColorDrawable());
    }


}
