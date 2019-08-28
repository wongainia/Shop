package com.zhenghaikj.shop.adapter;

import android.view.View;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.entity.Message;

import java.util.List;

public class MessageAdapter2 extends BaseQuickAdapter<Message, BaseViewHolder> {
    public MessageAdapter2(int layoutResId, @Nullable List<Message> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Message item) {
            helper.setText(R.id.tv_order_message,"工单消息:"+item.getContent());
              StringBuilder stringBuilder = new StringBuilder(item.getNowtime());
              String time = "" + stringBuilder.replace(10, 11, " "); //替换"T"为" "
            helper.setText(R.id.tv_order_time,"时间:"+time);

            if (item.getIsLook().equals("2")){
                helper.getView(R.id.iv_new).setVisibility(View.INVISIBLE);
//                helper.getView(R.id.ll_all_message).setBackgroundResource(R.drawable.bg_messageread);
            }


             helper.addOnClickListener(R.id.ll_order_message);

    }
}
