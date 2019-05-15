package com.zhenghaikj.shop.adapter;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.entity.Comment;
import com.zhenghaikj.shop.utils.GlideUtil;

import java.util.List;

import androidx.annotation.Nullable;

public class CommentAdapter3 extends BaseQuickAdapter<Comment.AppendImagesData, BaseViewHolder> {
    public CommentAdapter3(int layoutResId, @Nullable List<Comment.AppendImagesData> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Comment.AppendImagesData item) {
        ImageView icon = helper.getView(R.id.icon);
        GlideUtil.loadImageViewLoding(mContext,item.getCommentImage(),icon,R.drawable.image_loading,R.drawable.image_loading);
        helper.addOnClickListener(R.id.icon);
    }
}
