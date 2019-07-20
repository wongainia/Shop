package com.zhenghaikj.shop.adapter;

import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.entity.WorkOrder;

import java.util.List;

public class WorkOrderAdapter extends BaseQuickAdapter<WorkOrder.DataBean,BaseViewHolder> {
    String name;
    public WorkOrderAdapter(int layoutResId, List<WorkOrder.DataBean> data, String name) {
        super(layoutResId, data);
        this.name=name;
    }
    @Override
    protected void convert(BaseViewHolder helper, WorkOrder.DataBean item) {
        helper.setText(R.id.tv_order_num,"工单号："+item.getOrderID())
                .setText(R.id.tv_name,item.getMemo())
                .setText(R.id.tv_warranty,item.getTypeName()+"/"+item.getGuarantee())
                .setText(R.id.tv_status,item.getState())
                .setText(R.id.tv_info,item.getUserName()+item.getPhone())
                .setText(R.id.tv_address,item.getAddress())
                .addOnClickListener(R.id.tv_complaint)
                .addOnClickListener(R.id.tv_leave_message)
                .addOnClickListener(R.id.tv_see_detail)
                .addOnClickListener(R.id.iv_copy)
                .addOnClickListener(R.id.tv_obsolete);
        if (item.getAccessoryMoney()!=null&&!"0.00".equals(item.getAccessoryMoney())){
            helper.setText(R.id.tv_cost,"¥" + (Double.parseDouble(item.getAccessoryMoney())+Double.parseDouble(item.getBeyondMoney())+Double.parseDouble(item.getPostMoney())) + "");
        }else{
            helper.setText(R.id.tv_cost,"¥" + item.getOrderMoney() + "");
        }
        TextView tv=helper.getView(R.id.tv_status);
        String status=tv.getText().toString();
        if ("待接单".equals(status)){
//            helper.setGone(R.id.tv_obsolete,false);
            helper.setVisible(R.id.tv_obsolete,true);
        }else {
            helper.setVisible(R.id.tv_obsolete,false);
        }
//        if ("0".equals(item.getBeyondState())){
//            helper.setText(R.id.tv_remind,"远程费待审核");
//        }else {
//            helper.setText(R.id.tv_remind,"");
//        }
        if (item.getBeyondState()==null){
            helper.setText(R.id.tv_remind,"");
        }else if ("0".equals(item.getBeyondState())) {
            helper.setText(R.id.tv_remind,"远程费待审核");
        } else if ("1".equals(item.getBeyondState())) {
            helper.setText(R.id.tv_remind,"远程费审核通过");

        } else {
            helper.setText(R.id.tv_remind,"远程费已拒");
        }

//        if ("0".equals(item.getAccessoryApplyState())){
//            helper.setText(R.id.tv_remind_two,"配件待审核");
//        }else {
//            helper.setText(R.id.tv_remind_two,"");
//        }
        if(item.getAccessoryApplyState()==null){
            helper.setText(R.id.tv_remind_two,"");
        } else if ("0".equals(item.getAccessoryApplyState())) {
            helper.setText(R.id.tv_remind_two,"配件待审核");
        } else if ("1".equals(item.getAccessoryApplyState())) {
            helper.setText(R.id.tv_remind_two,"配件审核通过");
        } else {
            helper.setText(R.id.tv_remind_two,"配件已拒");
        }

//        if ("质保单".equals(name)){
//            if (!"".equals(item.getAppointmentMessage())){
//                helper.setVisible(R.id.tv_remind,true);
//                helper.setText(R.id.tv_remind,item.getAppointmentMessage());
//            }
//        }

    }

}
