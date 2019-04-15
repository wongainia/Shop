package com.zhenghaikj.shop.adapter;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.entity.Bean;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.MyHolder> {
    private List<Bean> list;

    public OrderAdapter(List<Bean> list){
        this.list = list;
    }


    public static class MyHolder extends RecyclerView.ViewHolder{
        private RecyclerView recyclerView;
        private TextView textView;
        private CheckBox checkBox;
        private OrderListAdapter1 adapter;
        private RecyclerView.LayoutManager manager;

        public CheckBox getCheckBox() {
            return checkBox;
        }

        public RecyclerView getRecyclerView() {
            return recyclerView;
        }

        public TextView getTextView() {
            return textView;
        }

        public MyHolder(View itemView) {
            super(itemView);
            recyclerView = (RecyclerView) itemView.findViewById(R.id.rv_order_list);
//            textView = (TextView) itemView.findViewById(R.id.tv_name);
//            checkBox = (CheckBox) itemView.findViewById(R.id.cb_choose_all);
            manager = new LinearLayoutManager(itemView.getContext());
            recyclerView.setLayoutManager(manager);
        }
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order,null);//解决嵌套显示条目不全
        MyHolder holder = new MyHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyHolder holder, final int position) {

        holder.adapter = new OrderListAdapter1(list.get(position).getList());
        holder.recyclerView.setAdapter(holder.adapter);
//        holder.getTextView().setText(list.get(position).getText());
//        holder.getCheckBox().setChecked(list.get(position).ischeck());
//        holder.getCheckBox().setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                //将店铺的checkbox的点击变化事件进行回调
//                if (mCallBack!=null){
//                    mCallBack.OnCheckListener(isChecked,position);
//                }
//            }
//        });

        //实现第二层RecyclerView的回调接口
        holder.adapter.setCallBack(new OrderListAdapter1.allCheck() {
            @Override
            public void OnCheckListener(boolean isChecked, int childpostion) {
                //将店铺商品的checkbox的点击变化事件进行回调
                if (mCallBack!=null){
                    mCallBack.OnItemCheckListener(isChecked,position,childpostion);
                }
            }

        });
        holder.itemView.setTag(list.get(position));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    private allCheck mCallBack;

    public void setCallBack(allCheck callBack) {
        mCallBack = callBack;
    }

    public interface allCheck{
        //回调函数 将店铺的checkbox的点击变化事件进行回调
        public void OnCheckListener(boolean isSelected, int position);
        //回调函数 将店铺商品的checkbox的点击变化事件进行回调
        public void OnItemCheckListener(boolean isSelected, int parentposition, int chaildposition);
    }


}
