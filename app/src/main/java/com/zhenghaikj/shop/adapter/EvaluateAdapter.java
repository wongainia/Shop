package com.zhenghaikj.shop.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.entity.EvaluateResult;
import com.zhenghaikj.shop.widget.StarBarView;

import java.util.List;

import androidx.annotation.Nullable;

public class EvaluateAdapter extends BaseQuickAdapter<EvaluateResult.ProductBean, BaseViewHolder> {
   private Context mContext;
    public EvaluateAdapter(int layoutResId, @Nullable List<EvaluateResult.ProductBean> data,Context context) {
        super(layoutResId, data);
        this.mContext=context;
    }
    @Override
    protected void convert(BaseViewHolder helper, EvaluateResult.ProductBean item) {
    Glide.with(mContext).load(item.getImage()).into((ImageView) helper.getView(R.id.img_goods));
    helper.setText(R.id.tv_goods_name,item.getProductName());

        helper.getView(R.id.good_star).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float starRating = ((StarBarView) helper.getView(R.id.good_star)).getStarRating();

                setStarName(helper.getView(R.id.tv_evaluate),starRating);

            }
        });







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
