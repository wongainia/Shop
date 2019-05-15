package com.zhenghaikj.shop.adapter;

import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.entity.Comment;
import com.zhenghaikj.shop.entity.CommentCategory;

import java.util.List;

public class CommentCategoryAdapter extends BaseQuickAdapter<CommentCategory,BaseViewHolder> {
    public CommentCategoryAdapter(int layoutResId, List<CommentCategory> data) {
        super(layoutResId, data);
    }
    public  void setSelect(List<Comment> data,int position){
        for (int i = 0; i <data.size() ; i++) {
            if (i==position){
                data.get(position).setSelect(true);
            }else{
                data.get(position).setSelect(false);
            }
        }
        notifyDataSetChanged();
    }
    @Override
    protected void convert(BaseViewHolder helper, CommentCategory item) {
        TextView tv_initials=helper.getView(R.id.tv_initials);
        tv_initials.setSelected(item.isSelect());
        helper.setText(R.id.tv_initials,item.getValue());
        // 加载网络图片
        /*helper.setText(R.id.name,item.getUsername());
        helper.setText(R.id.phone,item.getTelphone());
        helper.setText(R.id.address,item.getString());
        helper.addOnClickListener(R.id.edit_ll);
        helper.addOnClickListener(R.id.delete_ll);
        helper.addOnClickListener(R.id.default_ll);
        if ("1".equals(item.getIs_default())){
            helper.setChecked(R.id.check,true);
        }else{
            helper.setChecked(R.id.check,false);
        }*/
    }
}
