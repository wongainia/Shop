package com.zhenghaikj.shop.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.utils.MyUtils;
import com.zhenghaikj.shop.entity.Products;
import com.zhenghaikj.shop.widget.MyGridView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cyl on 2018年5月10日 09:52:49.
 */

public class CategoryContentAdapter extends BaseAdapter {
    private Context mContext;

    private List<Products> grouplists = new ArrayList<>();
    private LayoutInflater mInflater;

    public CategoryContentAdapter(Context mContext, List<Products> grouplists) {
        super();
        this.mContext = mContext;
        this.grouplists = grouplists;
        this.mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return grouplists.size();
    }

    @Override
    public Products getItem(int position) {
        return grouplists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {// 如果是第一次显示该页面(要记得保存到viewholder中供下次直接从缓存中调用)
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.fragment_categorys_item, null);
            holder.icon = (ImageView) convertView.findViewById(R.id.icon);
            holder.content = (TextView) convertView.findViewById(R.id.txt_content);
            holder.gv_content = (MyGridView) convertView.findViewById(R.id.gv_content);
            convertView.setTag(holder);
        } else {// 如果之前已经显示过该页面，则用viewholder中的缓存直接刷屏
            holder = (ViewHolder) convertView.getTag();
        }

        final Products item = grouplists.get(position);
        holder.content.setText(item.getName());
//        if (position==0){
//        GlideUtil.loadImageViewLoding(mContext,item.getBanner(),holder.icon,R.drawable.banner_cate,R.drawable.banner_cate);
//        Glide.with(mContext).load(item.getBanner()).into(holder.icon);
//            holder.icon.setImageResource(R.drawable.bg);
//        }else{
//            holder.icon.setVisibility(View.GONE);
//        }
//        holder.icon.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (!MyUtils.isFastClick()){
//                    return;
//                }
////                Intent intent =new Intent(mContext, TypeActivity.class);
////                intent.putExtra("Products",item);
////                mContext.startActivity(intent);
//            }
//        });
        holder.gv_content.setAdapter(new CategeryGoodsAdapter(mContext, item.getProduct_list()));
        holder.gv_content.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (!MyUtils.isFastClick()){
                    return;
                }
//                Intent intent=new Intent(mContext, GoodsDetailActivity.class);
//                intent.putExtra("productid",item.getProduct_list().get(i).getId());
//                mContext.startActivity(intent);
            }
        });
        return convertView;
    }

    public class ViewHolder {
        public ImageView icon;
        public TextView content;
        public MyGridView gv_content;
    }
}
