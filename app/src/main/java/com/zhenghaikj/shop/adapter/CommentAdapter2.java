package com.zhenghaikj.shop.adapter;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.entity.Comment;
import com.zhenghaikj.shop.utils.GlideUtil;

import java.util.List;

import androidx.annotation.Nullable;

public class CommentAdapter2 extends BaseQuickAdapter<Comment.ImagesData, BaseViewHolder> {
    public CommentAdapter2(int layoutResId, @Nullable List<Comment.ImagesData> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Comment.ImagesData item) {
        ImageView icon = helper.getView(R.id.icon);
        GlideUtil.loadImageViewLoding(mContext,item.getCommentImage(),icon,R.drawable.image_loading,R.drawable.image_loading);
    }
}
