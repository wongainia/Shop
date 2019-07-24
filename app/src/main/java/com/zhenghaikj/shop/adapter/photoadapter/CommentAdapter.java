package com.zhenghaikj.shop.adapter.photoadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.entity.CommentsInfo;
import com.zhenghaikj.shop.entity.EvaluateResult;
import com.zhenghaikj.shop.widget.StarBarView;
import com.zhenghaikj.shop.widget.flowtaglayout.FlowTagLayout;
import com.zhenghaikj.shop.widget.flowtaglayout.OnTagSelectListener;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder> {
    private List<EvaluateResult.ProductBean> list;

    private List<CommentsInfo> commentslist;
    private Context mContext;
    private OnStatusListener onStatusListener;
    public CommentAdapter(List<EvaluateResult.ProductBean> list, Context context,List<CommentsInfo> commentslist) {
        this.list = list;
        this.mContext = context;
        this.commentslist=commentslist;
    }

    public void setOnStatusListener(OnStatusListener onStatusListener) {
        this.onStatusListener = onStatusListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        ViewHolder viewHolder = new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_evaluate, viewGroup, false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int i) {

        Glide.with(mContext).load(list.get(i).getImage()).into(viewHolder.img_goods);
        viewHolder.tv_goods_name.setText(list.get(i).getProductName());
        viewHolder.good_star.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float starRating = viewHolder.good_star.getStarRating();
                setStarName(viewHolder.tv_evaluate,starRating);
            }
        });

        viewHolder.flComment.setTagCheckedMode(FlowTagLayout.FLOW_TAG_CHECKED_SINGLE);
        TagAdapter<String> TagAdapter = new TagAdapter<>(mContext);
        viewHolder.flComment.setAdapter(TagAdapter);
        TagAdapter.onlyAddAll(commentslist.get(i).getCommentImgs());

        viewHolder.flComment.setOnTagSelectListener(new OnTagSelectListener() {
            @Override
            public void onItemSelect(FlowTagLayout parent, List<Integer> selectedList) {
                if(commentslist.get(i).getCommentImgs().get(selectedList.get(0)).equals("")){
                    onStatusListener.onSetStatusListener(i);
                }else{
                    onStatusListener.onDeleteListener(i,selectedList.get(0));
                }
            }
        });
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        FlowTagLayout flComment;
        ImageView img_goods;
        StarBarView good_star;
        TextView tv_evaluate;
        TextView tv_goods_name;
        EditText et_content;
        public ViewHolder(View itemView) {
            super(itemView);
            flComment = itemView.findViewById(R.id.fl_comment);
            img_goods=itemView.findViewById(R.id.img_goods);
            good_star=itemView.findViewById(R.id.good_star);
            tv_evaluate=itemView.findViewById(R.id.tv_evaluate);
            tv_goods_name=itemView.findViewById(R.id.tv_goods_name);
            et_content=itemView.findViewById(R.id.et_content);



        }
    }

    public interface OnStatusListener {
        void onSetStatusListener(int pos);
        void onDeleteListener(int pos, int tagPos);
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
