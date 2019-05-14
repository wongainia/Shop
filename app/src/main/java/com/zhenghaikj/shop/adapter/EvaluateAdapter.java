package com.zhenghaikj.shop.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.entity.CommentsInfo;
import com.zhenghaikj.shop.adapter.photoadapter.TagAdapter;
import com.zhenghaikj.shop.entity.EvaluateResult;
import com.zhenghaikj.shop.widget.StarBarView;
import com.zhenghaikj.shop.widget.flowtaglayout.FlowTagLayout;
import com.zhenghaikj.shop.widget.flowtaglayout.OnTagSelectListener;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;

public class EvaluateAdapter extends BaseQuickAdapter<EvaluateResult.ProductBean, BaseViewHolder> {
   private Context mContext;
    private ArrayList<CommentsInfo> list;
    private OnStatusListener onStatusListener;
    private FlowTagLayout flowTagLayout;

    public void setOnStatusListener(OnStatusListener onStatusListener) {
        this.onStatusListener = onStatusListener;
    }

    public EvaluateAdapter(int layoutResId, @Nullable List<EvaluateResult.ProductBean> data, Context context,ArrayList<CommentsInfo> list) {
        super(layoutResId, data);
        this.mContext=context;
        this.list=list;
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

        flowTagLayout=helper.getView(R.id.fl_comment);
        flowTagLayout.setTagCheckedMode(FlowTagLayout.FLOW_TAG_CHECKED_SINGLE);
        TagAdapter<String> TagAdapter = new TagAdapter<>(mContext);

        TagAdapter.onlyAddAll(list.get(helper.getLayoutPosition()).getCommentImgs());

        flowTagLayout.setOnTagSelectListener(new OnTagSelectListener() {
            @Override
            public void onItemSelect(FlowTagLayout parent, List<Integer> selectedList) {
                if(list.get(helper.getLayoutPosition()).getCommentImgs().get(selectedList.get(0)).equals("")){
                    onStatusListener.onSetStatusListener(helper.getLayoutPosition());
                }else{
                    onStatusListener.onDeleteListener(helper.getLayoutPosition(),selectedList.get(0));
                }

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

    public interface OnStatusListener {
        void onSetStatusListener(int pos);
        void onDeleteListener(int pos, int tagPos);
    }
}
