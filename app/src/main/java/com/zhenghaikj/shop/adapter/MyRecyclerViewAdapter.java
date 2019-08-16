package com.zhenghaikj.shop.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StrikethroughSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.TimeUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.activity.StoreDetailActivity;
import com.zhenghaikj.shop.entity.HomeResult;
import com.zhenghaikj.shop.utils.GlideUtil;
import com.zhenghaikj.shop.widget.GlideRoundCropTransform;
import com.zhenghaikj.shop.widget.RoundBackGroundColorSpan;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import androidx.recyclerview.widget.RecyclerView;

import static com.blankj.utilcode.util.ActivityUtils.startActivity;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder> {
    private Context context;
    private List<HomeResult.ProductBean> list;//数据
    private List<Integer> heightList;//装产出的随机数

    private OnRecyclerItemClickListener mOnItemClickListener;//单击事件
    private onRecyclerItemLongClickListener mOnItemLongClickListener;//长按事件

    public void setList(List<HomeResult.ProductBean> list) {
        this.list = list;
        heightList.clear();
        for (int i = 0; i < list.size(); i++) {
            int height = new Random().nextInt(200) + 100;//[100,300)的随机数
            heightList.add(height);
        }
        notifyDataSetChanged();
    }

    public MyRecyclerViewAdapter(Context context, List<HomeResult.ProductBean> list) {
        this.context = context;
        this.list = list;
        //记录为每个控件产生的随机高度,避免滑回到顶部出现空白
        heightList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            int height = new Random().nextInt(200) + 100;//[100,300)的随机数
            heightList.add(height);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //找到item的布局
        View view= LayoutInflater.from(context).inflate(R.layout.item_home_two,parent,false);
        return new MyViewHolder(view);//将布局设置给holder
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    /**
     * 绑定视图到holder,就如同ListView的getView(),但是这里已经把复用实现了,我们只需要填充数据就行.
     * 由于在复用的时候都是调用该方法填充数据,但是上滑的时候,又会随机产生高度设置到控件上,这样当滑
     * 到顶部可能就会看到一片空白,因为后面随机产生的高度和之前的高度不一样,就不能填充屏幕了,所以
     * 需要记录每个控件产生的随机高度,然后在复用的时候再设置上去
     */
    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        //填充数据
        HomeResult.ProductBean bean=list.get(position);
        if ("官方自营店".equals(bean.getShopName())){
            SpannableString spannableString = new SpannableString("官方"+" "+bean.getName());
            ForegroundColorSpan colorSpan = new ForegroundColorSpan(Color.WHITE);
            spannableString.setSpan(colorSpan, 0,2, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
            RoundBackGroundColorSpan span = new RoundBackGroundColorSpan(Color.parseColor("#ff0000"),Color.parseColor("#FFFFFF"), 10);
            spannableString.setSpan(span, 0, 2, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
            spannableString.setSpan(new AbsoluteSizeSpan(35), 0, 2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            holder.tv_goods_name.setText(spannableString);
        }else {
            holder.tv_goods_name.setText(bean.getName());
        }

        holder.ll_gotoshop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bean.getVshopId()==null){
                    ToastUtils.showShort("该商家未申请微店");
                }else {
                    Intent intent1 = new Intent(context, StoreDetailActivity.class);
                    intent1.putExtra("VShopId", bean.getVshopId());
                    startActivity(intent1);
                }
            }
        });

        SpannableString spannableString = new SpannableString(bean.getSalePrice());
        if (bean.getSalePrice().contains(".")) {
            spannableString.setSpan(new RelativeSizeSpan(0.6f), bean.getSalePrice().indexOf("."), bean.getSalePrice().length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        holder.tv_goods_money.setText(spannableString);
        String string = "¥"+bean.getMarketPrice();
        SpannableString sp = new SpannableString(string);
        sp.setSpan(new StrikethroughSpan(), 0, string.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        holder.tv_payment.setText(sp);
        holder.tv_shop_name.setText(bean.getShopName());
        if (list.get(position).getProductAttributeInfos()!=null){
            if (list.get(position).getProductAttributeInfos().size()>=3){
                holder.tv_comment.setText(list.get(position).getProductAttributeInfos().get(0)+" | "+list.get(position).getProductAttributeInfos().get(1)+" | "+list.get(position).getProductAttributeInfos().get(2));
            }else if (list.get(position).getProductAttributeInfos().size()==1){
                holder.tv_comment.setText(list.get(position).getProductAttributeInfos().get(0));
            }else if (list.get(position).getProductAttributeInfos().size()==2){
                holder.tv_comment.setText(list.get(position).getProductAttributeInfos().get(0)+" | "+list.get(position).getProductAttributeInfos().get(1));
            }else if (list.get(position).getProductAttributeInfos().size()==0||list.get(position).getProductAttributeInfos()==null){
                holder.tv_comment.setVisibility(View.INVISIBLE);
            }
        }else {
            holder.tv_comment.setVisibility(View.INVISIBLE);
        }


        if (list.get(position).getCashDepositsServer().isIsSevenDayNoReasonReturn()){
            holder.tv_service.setText("七天无理由退换");
        }else {
            holder.tv_service.setVisibility(View.GONE);
        }


        if (list.get(position).getCashDepositsServer().isIsTimelyShip()){
            holder.tv_service_two.setText("急速发货");
        }else {
            holder.tv_service_two.setVisibility(View.GONE);
        }

        if (list.get(position).getCashDepositsServer().isIsCustomerSecurity()){
            holder.tv_service_three.setText("消费者保证");
        }else {
            holder.tv_service_three.setVisibility(View.GONE);
        }


        String timestamp= TimeUtils.getNowString(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        String str=bean.getImageUrl();
        str=str.replace("http","https");
        GlideUtil.loadImageViewLodingRadius(context,str+"?"+timestamp,holder.iv_goods,R.drawable.image_loading,R.drawable.image_loading,10);

       // GlideUtil.loadImageViewLodingRadius(context,bean.getImageUrl()+"?"+timestamp,holder.iv_goods,R.drawable.image_loading,R.drawable.image_loading,10);

      //  Log.d("=====>",bean.getImageUrl()+"?"+timestamp);

        Glide.with(context).load(bean.getImageUrl()+"?"+timestamp)
                .apply(RequestOptions.bitmapTransform(new GlideRoundCropTransform(context, 5)))
                .into(holder.iv_goods);

        //由于需要实现瀑布流的效果,所以就需要动态的改变控件的高度了
//        ViewGroup.LayoutParams params = holder.tv_goods_money.getLayoutParams();
//        params.height=heightList.get(position);
//        holder.tv_goods_money.setLayoutParams(params);

        //设置单击事件
        if(mOnItemClickListener !=null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //这里是为textView设置了单击事件,回调出去
                    //mOnItemClickListener.onItemClick(v,position);这里需要获取布局中的position,不然乱序
                    mOnItemClickListener.onItemClick(v,holder.getLayoutPosition());
                }
            });
        }
        //长按事件
//        if(mOnItemLongClickListener != null){
//            holder.textView.setOnLongClickListener(new View.OnLongClickListener() {
//                @Override
//                public boolean onLongClick(View v) {
//                    //回调出去
//                    mOnItemLongClickListener.onItemLongClick(v,holder.getLayoutPosition());
//                    return true;//不返回true,松手还会去执行单击事件
//                }
//            });
//        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView iv_goods;
        TextView tv_goods_name;
        TextView tv_goods_money;
        TextView tv_look_similar;
        TextView tv_payment;
        TextView tv_comment;
        TextView tv_service;
        TextView tv_service_two;
        TextView tv_service_three;
        TextView tv_shop_name;
        LinearLayout ll_gotoshop;

        public MyViewHolder(View itemView) {
            super(itemView);
            iv_goods = itemView.findViewById(R.id.iv_goods);
            tv_goods_name = itemView.findViewById(R.id.tv_goods_name);
            tv_goods_money = itemView.findViewById(R.id.tv_goods_money);
            tv_look_similar = itemView.findViewById(R.id.tv_look_similar);
            tv_payment = itemView.findViewById(R.id.tv_payment);
            tv_comment = itemView.findViewById(R.id.tv_comment);
            tv_service = itemView.findViewById(R.id.tv_service);
            tv_service_two = itemView.findViewById(R.id.tv_service_two);
            tv_service_three = itemView.findViewById(R.id.tv_service_three);
            tv_shop_name = itemView.findViewById(R.id.tv_shop_name);
            ll_gotoshop = itemView.findViewById(R.id.ll_gotoshop);
        }
    }

    /**
     * 处理item的点击事件,因为recycler没有提供单击事件,所以只能自己写了
     */
    public interface OnRecyclerItemClickListener {
        public void onItemClick(View view, int position);
    }

    /**
     * 长按事件
     */
    public interface  onRecyclerItemLongClickListener{
        public void onItemLongClick(View view, int position);
    }

    /**
     * 暴露给外面的设置单击事件
     */
    public void setOnItemClickListener(OnRecyclerItemClickListener onItemClickListener){
        mOnItemClickListener = onItemClickListener;
    }

    /**
     * 暴露给外面的长按事件
     */
    public void setOnItemLongClickListener(onRecyclerItemLongClickListener onItemLongClickListener){
        mOnItemLongClickListener = onItemLongClickListener;
    }

    /**
     * 向指定位置添加元素
     */
    public void addItem(int position, HomeResult.ProductBean value) {
        if(position > list.size()) {
            position = list.size();
        }
        if(position < 0) {
            position = 0;
        }
        /**
         * 使用notifyItemInserted/notifyItemRemoved会有动画效果
         * 而使用notifyDataSetChanged()则没有
         */
        list.add(position, value);//在集合中添加这条数据
        heightList.add(position,new Random().nextInt(200) + 100);//添加一个随机高度,会在onBindViewHolder方法中得到设置
        notifyItemInserted(position);//通知插入了数据
    }

    /**
     * 移除指定位置元素
     */
    public HomeResult.ProductBean removeItem(int position) {
        if(position > list.size()-1) {
            return null;
        }
        heightList.remove(position);//删除添加的高度
        HomeResult.ProductBean value = list.remove(position);//所以还需要手动在集合中删除一次
        notifyItemRemoved(position);//通知删除了数据,但是没有删除list集合中的数据
        return value;
    }
}
