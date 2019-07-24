package com.zhenghaikj.shop.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.entity.GAccessory;

import java.util.List;

public class AccessoryDetailAdapter extends BaseQuickAdapter<GAccessory,BaseViewHolder> {
    private String state;
    public AccessoryDetailAdapter(int layoutResId, List<GAccessory> data,String state) {
        super(layoutResId, data);
        this.state=state;
    }
    @Override
    protected void convert(BaseViewHolder helper, GAccessory item) {
        helper.setText(R.id.tv_accessories_name,item.getFAccessoryName());
        helper.setText(R.id.tv_accessories_number,"¥"+item.getDiscountPrice()+"/"+item.getQuantity()+"个");
        helper.addOnClickListener(R.id.tv_reject);
        helper.addOnClickListener(R.id.tv_pass);
        if ("保内".equals(state)){
            helper.setGone(R.id.tv_reject,false);
            helper.setGone(R.id.tv_pass,false);
            helper.setGone(R.id.tv_status_accessory,false);
        }else {
            if ("0".equals(item.getState())){
                helper.setVisible(R.id.tv_reject,true);
                helper.setVisible(R.id.tv_pass,true);
                helper.setGone(R.id.tv_status_accessory,false);
            }else if ("1".equals(item.getState())){
                helper.setGone(R.id.tv_reject,false);
                helper.setGone(R.id.tv_pass,false);
                helper.setVisible(R.id.tv_status_accessory,true);
                helper.setText(R.id.tv_status_accessory,"审核通过");
            }else {
                helper.setGone(R.id.tv_reject,false);
                helper.setGone(R.id.tv_pass,false);
                helper.setVisible(R.id.tv_status_accessory,true);
                helper.setText(R.id.tv_status_accessory,"审核拒绝");
            }
        }

    }
}
