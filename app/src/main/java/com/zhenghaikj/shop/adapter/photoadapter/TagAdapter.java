package com.zhenghaikj.shop.adapter.photoadapter;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.widget.flowtaglayout.OnInitSelectedPosition;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.RequiresApi;

/**
 * Created by HanHailong on 15/10/19.
 */
public class TagAdapter<T> extends BaseAdapter implements OnInitSelectedPosition {

    private final Context mContext;
    private final List<T> mDataList;

    public TagAdapter(Context context) {
        this.mContext = context;
        mDataList = new ArrayList<T>();
    }

    @Override
    public int getCount() {
        return mDataList.size();
    }

    @Override
    public Object getItem(int position) {
        return mDataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.tag_item2, null);

        ImageView ivImage = (ImageView) view.findViewById(R.id.iv_comment_image);
        T t = mDataList.get(position);

        if (t instanceof String) {
            if(t.equals("")){
                ivImage.setBackground(mContext.getResources().getDrawable(R.mipmap.iv_up_image));
            }else{
             //   ImageLoader.getInstance().displayImage(t.toString(), ivImage);

                Glide.with(mContext).load(t.toString()).into(ivImage);
            }
        }
        return view;
    }

    public void onlyAddAll(List<T> datas) {

        if (datas==null){
            Log.d("=====>","数据为空");
        }
        mDataList.clear();
        mDataList.addAll(datas);
        notifyDataSetChanged();
    }

    public void clearAndAddAll(List<T> datas) {
        mDataList.clear();
        onlyAddAll(datas);
    }

    @Override
    public boolean isSelectedPosition(int position) {
        if (position % 2 == 0) {
            return true;
        }
        return false;
    }
}
