package com.zhenghaikj.shop.adapter;

import androidx.annotation.Nullable;

import com.blankj.utilcode.util.TimeUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.qiyukf.unicorn.api.pop.POPManager;
import com.qiyukf.unicorn.api.pop.Session;
import com.qiyukf.unicorn.api.pop.ShopInfo;
import com.zhenghaikj.shop.R;
import com.zhenghaikj.shop.utils.GlideUtil;

import java.util.List;

public class SessionAdapter extends BaseQuickAdapter<Session, BaseViewHolder> {
    public SessionAdapter(int layoutResId, @Nullable List<Session> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Session item) {
        /**
         * 根据商家ID获取商家信息，如名称，logo
         *
         * @param shopId 商家ID
         * @return 如果用户联系过该商家，返回商家信息，否则返回 null
         */
        ShopInfo shopInfo = POPManager.getShopInfo(item.getContactId());
        helper.setText(R.id.tv_userName,shopInfo.getName())
                .setText(R.id.tv_content, item.getContent())
                .setText(R.id.tv_time, TimeUtils.millis2String(item.getTime()));
        GlideUtil.loadImageViewLoding(mContext,shopInfo.getAvatar(),helper.getView(R.id.iv_avatar),R.drawable.image_loading,R.drawable.image_loading);

//        helper.addOnClickListener(R.id.ll_message);
    }
}
