package com.zhenghaikj.shop.adapter;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.entity.Cbean;

import java.util.List;

public class RecyclerAdapter1 extends RecyclerView.Adapter<RecyclerAdapter1.MyHolder> {
    private List<Cbean> cbeanList, cbeanList1;

    public RecyclerAdapter1(List<Cbean> cbeanList) {
        this.cbeanList = cbeanList;
        cbeanList1 = cbeanList;
    }

    public static class MyHolder extends RecyclerView.ViewHolder {

        private CheckBox cb_choose;
//        private final TextView down;
//        private final TextView up;
//        private final EditText et_count;


        public CheckBox getCheckBox() {
            return cb_choose;
        }


        public MyHolder(View itemView) {
            super(itemView);
            cb_choose = (CheckBox) itemView.findViewById(R.id.cb_choose);
//            down = (TextView) itemView.findViewById(R.id.down);
//            up = (TextView) itemView.findViewById(R.id.up);
//            et_count = (EditText) itemView.findViewById(R.id.et_count);
        }
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_shop_type, parent,false);//解决显示不全
        MyHolder holder = new MyHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyHolder holder, final int position) {
        holder.getCheckBox().setChecked(cbeanList.get(position).ischeck());
        holder.getCheckBox().setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //将商品的checkbox的点击变化事件进行回调给第一个Recyclerview
                if (mCallBack != null) {
                    mCallBack.OnCheckListener(isChecked, position);
                }
            }
        });
        holder.itemView.setId(position);
    }

    @Override
    public int getItemCount() {

        return cbeanList.size();
    }

    private allCheck mCallBack;

    public void setCallBack(allCheck callBack) {
        mCallBack = callBack;
    }

    public interface allCheck {
        //回调函数 将店铺商品的checkbox的点击变化事件进行回调
        public void OnCheckListener(boolean isChecked, int childpostion);
    }
}
