package com.zhenghaikj.shop.adapter;

import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.entity.FilterResult;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class FliterAttrsAdapter extends BaseQuickAdapter<FilterResult.AttrsBean, BaseViewHolder> {


    private RecyclerView recyclerView;
    private FliterItemAttrsAdapter fliterItemAttrsAdapter;
    private OnItemClickAttrsListner onItemClickAttrsListner;
    private Map<Integer,Boolean> map_attrscode= new HashMap<>();

    private List<FilterResult.AttrsBean> data;


    public OnItemClickAttrsListner getOnItemClickAttrsListner() {
        return onItemClickAttrsListner;
    }

    public void setOnItemClickAttrsListner(OnItemClickAttrsListner onItemClickAttrsListner) {
        this.onItemClickAttrsListner = onItemClickAttrsListner;
    }



    public FliterAttrsAdapter(int layoutResId, @Nullable List<FilterResult.AttrsBean> data) {
        super(layoutResId, data);
        this.data=data;
    }

    @Override
    protected void convert(BaseViewHolder helper, FilterResult.AttrsBean item) {
        helper.setText(R.id.tv_type,item.getName());
        recyclerView=helper.getView(R.id.rv_attrs);
        recyclerView.setLayoutManager(new GridLayoutManager(mContext,3));
        fliterItemAttrsAdapter=new FliterItemAttrsAdapter(R.layout.item_fliter_choose_attrs,item.getAttrValues());
        recyclerView.setAdapter(fliterItemAttrsAdapter);

            if (data.size()!=0){
                for (int i = 0; i < data.size(); i++) {
                    for (int j = 0; j < data.get(i).getAttrValues().size(); j++) {
                        map_attrscode.put(data.get(i).getAttrValues().get(j).getId(),false);
                    }
                }
            }

        fliterItemAttrsAdapter.setOnItemChildClickListener(new OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()){
                    case R.id.item_cb:
                        for (Map.Entry<Integer,Boolean> entry :map_attrscode.entrySet()){
                            if (entry.getKey().equals(((FilterResult.AttrsBean.AttrValuesBean)adapter.getItem(position)).getId())){
                                if (entry.getValue().equals(false)){
                                    map_attrscode.put(((FilterResult.AttrsBean.AttrValuesBean)adapter.getItem(position)).getId(),true);
                                }else {
                                    map_attrscode.put(((FilterResult.AttrsBean.AttrValuesBean)adapter.getItem(position)).getId(),false);
                                }
                            }
                        }
                        onItemClickAttrsListner.OnItemSaveattrs(getattrscode(map_attrscode));
                      /*  for (Map.Entry<Integer,Boolean> entry :map_attrscode.entrySet()){
                            System.out.println("key="+entry.getKey()+" value"+entry.getValue());
                        }*/
                        break;

                }

            }
        });





    }


    private String getattrscode(Map<Integer,Boolean> map){
        String attrscode="";
        for (Map.Entry<Integer,Boolean> entry :map.entrySet()){
                if (entry.getValue().equals(true)){
                    attrscode+=entry.getKey()+",";
                }
           }
              return attrscode;

    }


    public interface OnItemClickAttrsListner{
       void OnItemSaveattrs(String attrscode);
    }

}
