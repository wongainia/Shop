package com.zhenghaikj.shop.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.entity.Product;
import com.zhenghaikj.shop.entity.ShippingAddressList;

import java.util.List;

public class AddressAdapter extends BaseQuickAdapter<ShippingAddressList.ShippingAddressBean, BaseViewHolder> {


    public AddressAdapter(int layoutResId, @Nullable List<ShippingAddressList.ShippingAddressBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ShippingAddressList.ShippingAddressBean item) {
        helper.setText(R.id.tv_name,item.getShipTo())
                .setText(R.id.tv_phone,item.getPhone())
                .setText(R.id.tv_address,item.getRegionFullName()+item.getAddress())
                .setText(R.id.tv_name_first,item.getShipTo().substring(0,1));
        if (item.isDefault()){
            helper.setVisible(R.id.tv_default,true);
        }
    }
}
