package com.zhenghaikj.shop.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/*用于购物车店铺实体类*/
public class StoreBean implements Serializable {
    private boolean ischeck; //判断是否勾选
    private String ShopName;//店铺名
    private String ShopLogo;//店铺标志
    private List<CommodityBean> list; //店铺中的商品


    public boolean isIscheck() {
        return ischeck;
    }

    public void setIscheck(boolean ischeck) {
        this.ischeck = ischeck;
    }

    public String getShopName() {
        return ShopName;
    }

    public void setShopName(String shopName) {
        ShopName = shopName;
    }

    public String getShopLogo() {
        return ShopLogo;
    }

    public void setShopLogo(String shopLogo) {
        ShopLogo = shopLogo;
    }


    public List<CommodityBean> getList() {
        return list;
    }

    public void setList(List<CommodityBean> list) {
        this.list = list;
    }
}
