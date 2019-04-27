package com.zhenghaikj.shop.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.entity.CommodityBean;
import com.zhenghaikj.shop.entity.Data;
import com.zhenghaikj.shop.widget.AdderView;

import java.util.List;
import java.util.zip.Inflater;

import androidx.recyclerview.widget.RecyclerView;

public class RecyclerCommodityAdapter extends RecyclerView.Adapter<RecyclerCommodityAdapter.MyHolder> {
    private List<CommodityBean> list;

    public RecyclerCommodityAdapter(List<CommodityBean> list) {
        this.list = list;
    }

    public static class MyHolder extends RecyclerView.ViewHolder {

        private CheckBox cb_choose;
       // private final TextView down;
       // private final TextView up;
       // private final EditText et_count;
        private TextView tv_goods_name;
        private TextView tv_goods_type;
        private TextView tv_price;
        private AdderView adderView;


        public CheckBox getCheckBox() {
            return cb_choose;
        }


        public MyHolder(View itemView) {
            super(itemView);
            cb_choose = (CheckBox) itemView.findViewById(R.id.cb_choose);
            tv_goods_name=itemView.findViewById(R.id.tv_goods_name);
            tv_goods_type=itemView.findViewById(R.id.tv_goods_type);
            tv_price=itemView.findViewById(R.id.tv_price);
            adderView=itemView.findViewById(R.id.adderview);
        }
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
      /*  View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_shop_type, parent,false);//解决显示不全
        MyHolder holder = new MyHolder(view);
        return holder;*/
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item_shop_type,parent,false);
        return new MyHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyHolder holder, final int position) {
        holder.tv_goods_name.setText(list.get(position).getName());
        holder.adderView.setValue(Integer.parseInt(list.get(position).getCount()));
        holder.tv_price.setText(list.get(position).getPrice());
        holder.getCheckBox().setChecked(list.get(position).isIscheck());
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

        return list.size();
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
