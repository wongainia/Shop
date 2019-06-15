package com.zhenghaikj.shop.widget;

import android.content.Context;

import com.lxj.xpopup.core.DrawerPopupView;
import com.zhenghaikj.shop.R;

import androidx.annotation.NonNull;

public class CustomFilterDrawerPopupView extends DrawerPopupView {

    public CustomFilterDrawerPopupView(@NonNull Context context) {
        super(context);
    }
    @Override
    protected int getImplLayoutId() {
        return R.layout.popupview_customfilterdrawer;
    }


}
