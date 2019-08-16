package com.zhenghaikj.shop.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.activity.GiftsDetailActivity;
import com.zhenghaikj.shop.entity.ShopResult;

import java.util.List;

import static com.blankj.utilcode.util.ActivityUtils.startActivity;

public class ShopAdapter extends RecyclerView.Adapter<ShopAdapter.MyViewHolder> {
    private Context context;
    private List<ShopResult.GiftListNewBean> list;

    public ShopAdapter(Context context, List<ShopResult.GiftListNewBean> list) {
        this.context=context;
        this.list=list;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_shop, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
//        ViewGroup.LayoutParams params = holder.iv_goods.getLayoutParams();
//        params.height = params.height + new Random().nextInt(300);
//        holder.iv_goods.setLayoutParams(params);
        Glide.with(context)
                .asBitmap()
                .load("http://mall.xigyu.com/" + list.get(position).getImagePath()+"/1.png")
                .into(holder.iv_goods);
//        holder.iv_goods.setImageResource(Integer.parseInt("http://mall.xigyu.com/" + list.get(position).getImagePath()+"/1.png"));
        holder.tv_goods_name.setText(list.get(position).getGiftName());
        holder.tv_goods_money.setText(list.get(position).getNeedIntegral()+"");
        holder.ll_goods.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, GiftsDetailActivity.class);
                intent.putExtra("giftId", list.get(position).getId());
                startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    /**
     * ViewHolder的类，用于缓存控件
     */
    class MyViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout ll_goods;
        private ImageView iv_goods;
        private TextView tv_goods_name;
        private TextView tv_goods_money;

        public MyViewHolder(View itemView){
            super(itemView);
            iv_goods= (ImageView) itemView.findViewById(R.id.iv_goods );
            tv_goods_name= (TextView) itemView.findViewById(R.id.tv_goods_name);
            tv_goods_money= (TextView) itemView.findViewById(R.id.tv_goods_money);
            ll_goods =(LinearLayout) itemView.findViewById(R.id.ll_goods);

        }


    }

}
