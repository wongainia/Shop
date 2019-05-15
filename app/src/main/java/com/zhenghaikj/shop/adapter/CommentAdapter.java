package com.zhenghaikj.shop.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.entity.Comment;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
                .setText(R.id.tv_time,item.getFinshDate());

        recyclerView = helper.getView(R.id.rv_picture);
        recyclerView.setLayoutManager(new GridLayoutManager(mContext,3));
        recyclerView.setAdapter(commentAdapter2);
    }
}
