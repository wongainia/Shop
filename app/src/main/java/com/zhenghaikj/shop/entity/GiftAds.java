package com.zhenghaikj.shop.entity;

import java.io.Serializable;

public class GiftAds implements Serializable {

    /**
     * ImageUrl : http://mall.xigyu.com//temp/201905221510544958300.jpg
     * Id : 125
     * ShopId : 0
     * Url : https://www.baidu.com/
     * DisplaySequence : 19
     * TypeId : 11
     * Description : 1
     */

    private String ImageUrl;
    private int Id;
    private int ShopId;
    private String Url;
    private int DisplaySequence;
    private int TypeId;
    private String Description;

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String ImageUrl) {
        this.ImageUrl = ImageUrl;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public int getShopId() {
        return ShopId;
    }

    public void setShopId(int ShopId) {
        this.ShopId = ShopId;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String Url) {
        this.Url = Url;
    }

    public int getDisplaySequence() {
        return DisplaySequence;
    }

    public void setDisplaySequence(int DisplaySequence) {
        this.DisplaySequence = DisplaySequence;
    }

    public int getTypeId() {
        return TypeId;
    }

    public void setTypeId(int TypeId) {
        this.TypeId = TypeId;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }
}
