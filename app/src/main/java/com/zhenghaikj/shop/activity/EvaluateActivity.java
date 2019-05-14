package com.zhenghaikj.shop.activity;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.adapter.EvaluateAdapter;
import com.zhenghaikj.shop.base.BaseActivity;
import com.zhenghaikj.shop.entity.EvaluateResult;
import com.zhenghaikj.shop.mvp.contract.EvaluateContract;
import com.zhenghaikj.shop.mvp.model.EvaluateModel;
import com.zhenghaikj.shop.mvp.presenter.EvaluatePresenter;
import com.zhenghaikj.shop.widget.StarBarView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

public class EvaluateActivity extends BaseActivity<EvaluatePresenter, EvaluateModel> implements EvaluateContract.View {
    @BindView(R.id.rv_rvaluate)
    RecyclerView mRvrvaluate;


    private StarBarView baozhuang_star,sudu_star,peisong_star;
    private TextView tv_baozhuang,tv_sudu,tv_peisong;
    private EvaluateAdapter evaluateAdapter;
    private View view;
    private String Userkey;
    private SPUtils spUtils = SPUtils.getInstance("token");

    @Override
    protected int setLayoutId() {
        return R.layout.activity_evaluate;
    }

    @Override
    protected void initData() {
        Userkey = spUtils.getString("UserKey");
        String orderID = getIntent().getStringExtra("OrderID");
        mPresenter.GetComment(orderID,Userkey);

    }

    @Override
    protected void initView() {
        view= LayoutInflater.from(mActivity).inflate(R.layout.item_evaluate_store,null);

    }

    @Override
    protected void setListener() {

    }

    /*获取 “评论” 详情*/
    @Override
    public void GetComment(EvaluateResult Result) {
        if (Result.isSuccess()){
            mRvrvaluate.setLayoutManager(new LinearLayoutManager(mActivity));
            evaluateAdapter=new EvaluateAdapter(R.layout.item_evaluate,Result.getProduct(),mActivity);
            evaluateAdapter.addFooterView(view);

            baozhuang_star=evaluateAdapter.getFooterLayout().findViewById(R.id.baozhuang_star);
            tv_baozhuang=evaluateAdapter.getFooterLayout().findViewById(R.id.tv_baozhuang);
            sudu_star=evaluateAdapter.getFooterLayout().findViewById(R.id.sudu_star);
            tv_sudu=evaluateAdapter.getFooterLayout().findViewById(R.id.tv_sudu);
            peisong_star=evaluateAdapter.getFooterLayout().findViewById(R.id.peisong_star);
            tv_peisong=evaluateAdapter.getFooterLayout().findViewById(R.id.tv_peisong);

            evaluateAdapter.getFooterLayout().findViewById(R.id.baozhuang_star).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setStarName(tv_baozhuang,baozhuang_star.getStarRating());
                }
            });

            evaluateAdapter.getFooterLayout().findViewById(R.id.sudu_star).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setStarName(tv_sudu,sudu_star.getStarRating());
                }
            });


            evaluateAdapter.getFooterLayout().findViewById(R.id.peisong_star).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setStarName(tv_peisong,peisong_star.getStarRating());
                }
            });

            mRvrvaluate.setAdapter(evaluateAdapter);
        }

    }

    @Override
    public void UploadPicEvaluate(String Result) {

    }


    /**
     * 设置星星文字
     * */
    private void setStarName(TextView textView, float star_num) {
        if (star_num==5.0f){
            textView.setText("非常好");
        }else if (star_num==4.0f){
            textView.setText("很好");
        }else if (star_num==3.0f){
            textView.setText("一般");
        }else if (star_num==2.0f){
            textView.setText("很差");
        }else if (star_num==1.0f){
            textView.setText("非常差");
        }

    }
}
