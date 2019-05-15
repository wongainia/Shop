package com.zhenghaikj.shop.adapter;

import android.content.Intent;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.activity.PhotosViewActivity;
import com.zhenghaikj.shop.entity.Comment;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static com.blankj.utilcode.util.ActivityUtils.startActivity;


public class CommentAdapter extends BaseQuickAdapter<Comment.listData,BaseViewHolder> {

    private RecyclerView recyclerView;
    private CommentAdapter2 commentAdapter2;

    public CommentAdapter(int layoutResId, @Nullable List<Comment.listData> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Comment.listData item) {
        commentAdapter2 = new CommentAdapter2(R.layout.item_picture,item.getImages());
        helper.setText(R.id.tv_username,item.getUserName())
                .setText(R.id.tv_content,item.getReviewContent())
                .setText(R.id.tv_time,item.getReviewDate());

        recyclerView = helper.getView(R.id.rv_picture);
        recyclerView.setLayoutManager(new GridLayoutManager(mContext,3));
        recyclerView.setAdapter(commentAdapter2);
        commentAdapter2.setOnItemChildClickListener(new OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()){
                    case R.id.icon:
                        ArrayList<String> images = new ArrayList<>();
                        for (int i = 0; i < item.getImages().size(); i++) {
                            images.add(item.getImages().get(i).getCommentImage());
                        }
                        Intent intent=new Intent(mContext, PhotosViewActivity.class);
                        intent.putExtra("photo_list",(Serializable)images);
                        intent.putExtra("photo_position",position);
                        startActivity(intent);
                        break;
                }
            }
        });

        if (item.getAppendContent()!=null){
            helper.setVisible(R.id.ll_append,true);
            helper.setText(R.id.tv_append,"用户"+item.getAppendDays()+"天后追评");
            helper.setText(R.id.tv_append_count,item.getAppendContent());
            RecyclerView rv=helper.getView(R.id.rv_append_picture);
            CommentAdapter3 commentAdapter3=new CommentAdapter3(R.layout.item_picture,item.getAppendImages());
            rv.setLayoutManager(new GridLayoutManager(mContext,3));
            rv.setAdapter(commentAdapter3);
        }

        if (!"暂无回复".equals(item.getReplyContent())){
            helper.setVisible(R.id.ll_reply,true);
            helper.setText(R.id.tv_reply_count,item.getReplyContent());
        }
        if (item.getReplyAppendContent()!=null){
            helper.setVisible(R.id.ll_reply_again,true);
            helper.setText(R.id.tv_reply_count_again,item.getReplyAppendContent());
        }
    }
}
