package com.zhenghaikj.shop.entity;

import java.io.Serializable;
import java.util.List;

public class Cart implements Serializable {


    /**
     * Success : true
     * Shop : [[{"CartItemId":"209","SkuId":"117_0_0_0","Id":"117","ImgUrl":"http://moyue214.oicp.net/Storage/Shop/1/Products/117/1_50.png","Name":"土土土寺","Price":"120.00","Count":"1","ShopId":"1","Size":"","Color":"","Version":"","VshopId":"1","ShopName":"官方自营店","ShopLogo":"http://moyue214.oicp.net/Storage/Shop/1/VShop/Logo.jpg","Status":1}],[{"CartItemId":"186","SkuId":"93_411_550_0","Id":"93","ImgUrl":"http://moyue214.oicp.net/Storage/Shop/160/Products/93/1_50.png","Name":"华硕（ASUS）轻薄便携系列 思聪本X205 11.6英寸超薄本（四核 2G 128G固态硬盘 蓝牙","Price":"1088.00","Count":"1","ShopId":"160","Size":"21","Color":"黑色","Version":"","VshopId":"2","ShopName":"小何店铺","ShopLogo":"http://moyue214.oicp.net/Storage/Shop/160/VShop/Logo.jpg","Status":1},{"CartItemId":"189","SkuId":"112_0_0_0","Id":"112","ImgUrl":"http://moyue214.oicp.net/Storage/Shop/160/Products/112/1_50.png","Name":"ANNE KLEIN 淑女百搭无袖连衣裙","Price":"100.00","Count":"3","ShopId":"160","Size":"","Color":"","Version":"","VshopId":"2","ShopName":"小何店铺","ShopLogo":"http://moyue214.oicp.net/Storage/Shop/160/VShop/Logo.jpg","Status":1},{"CartItemId":"194","SkuId":"103_0_0_0","Id":"103","ImgUrl":"http://moyue214.oicp.net/Storage/Shop/160/Products/103/1_50.png","Name":"sssss","Price":"1.00","Count":"1","ShopId":"160","Size":"","Color":"","Version":"","VshopId":"2","ShopName":"小何店铺","ShopLogo":"http://moyue214.oicp.net/Storage/Shop/160/VShop/Logo.jpg","Status":1},{"CartItemId":"196","SkuId":"100_0_0_0","Id":"100","ImgUrl":"http://moyue214.oicp.net/Storage/Shop/160/Products/100/1_50.png","Name":"asas","Price":"111.00","Count":"1222222","ShopId":"160","Size":"","Color":"","Version":"","VshopId":"2","ShopName":"小何店铺","ShopLogo":"http://moyue214.oicp.net/Storage/Shop/160/VShop/Logo.jpg","Status":0}],[{"CartItemId":"183","SkuId":"96_0_0_0","Id":"96","ImgUrl":"http://moyue214.oicp.net/Storage/Shop/169/Products/96/1_50.png","Name":"151名称名称","Price":"100.00","Count":"1","ShopId":"169","Size":"","Color":"","Version":"","VshopId":"3","ShopName":"151公司店铺","ShopLogo":"http://moyue214.oicp.net/Storage/Shop/169/VShop/Logo.png","Status":1}]]
     */

    private String Success;
    private List<List<ShopBean>> Shop;

    public String getSuccess() {
        return Success;
    }

    public void setSuccess(String Success) {
        this.Success = Success;
    }

    public List<List<ShopBean>> getShop() {
        return Shop;
    }

    public void setShop(List<List<ShopBean>> Shop) {
        this.Shop = Shop;
    }

    public static class ShopBean {
        /**
         * CartItemId : 209
         * SkuId : 117_0_0_0
         * Id : 117
         * ImgUrl : http://moyue214.oicp.net/Storage/Shop/1/Products/117/1_50.png
         * Name : 土土土寺
         * Price : 120.00
         * Count : 1
         * ShopId : 1
         * Size :
         * Color :
         * Version :
         * VshopId : 1
         * ShopName : 官方自营店
         * ShopLogo : http://moyue214.oicp.net/Storage/Shop/1/VShop/Logo.jpg
         * Status : 1
         */

        private String CartItemId;
        private String SkuId;
        private String Id;
        private String ImgUrl;
        private String Name;
        private String Price;
        private String Count;
        private String ShopId;
        private String Size;
        private String Color;
        private String Version;
        private String VshopId;
        private String ShopName;
        private String ShopLogo;
        private int Status;

        public String getCartItemId() {
            return CartItemId;
        }

        public void setCartItemId(String CartItemId) {
            this.CartItemId = CartItemId;
        }

        public String getSkuId() {
            return SkuId;
        }

        public void setSkuId(String SkuId) {
            this.SkuId = SkuId;
        }

        public String getId() {
            return Id;
        }

        public void setId(String Id) {
            this.Id = Id;
        }

        public String getImgUrl() {
            return ImgUrl;
        }

        public void setImgUrl(String ImgUrl) {
            this.ImgUrl = ImgUrl;
        }

        public String getName() {
            return Name;
        }

        public void setName(String Name) {
            this.Name = Name;
        }

        public String getPrice() {
            return Price;
        }

        public void setPrice(String Price) {
            this.Price = Price;
        }

        public String getCount() {
            return Count;
        }

        public void setCount(String Count) {
            this.Count = Count;
        }

        public String getShopId() {
            return ShopId;
        }

        public void setShopId(String ShopId) {
            this.ShopId = ShopId;
        }

        public String getSize() {
            return Size;
        }

        public void setSize(String Size) {
            this.Size = Size;
        }

        public String getColor() {
            return Color;
        }

        public void setColor(String Color) {
            this.Color = Color;
        }

        public String getVersion() {
            return Version;
        }

        public void setVersion(String Version) {
            this.Version = Version;
        }

        public String getVshopId() {
            return VshopId;
        }

        public void setVshopId(String VshopId) {
            this.VshopId = VshopId;
        }

        public String getShopName() {
            return ShopName;
        }

        public void setShopName(String ShopName) {
            this.ShopName = ShopName;
        }

        public String getShopLogo() {
            return ShopLogo;
        }

        public void setShopLogo(String ShopLogo) {
            this.ShopLogo = ShopLogo;
        }

        public int getStatus() {
            return Status;
        }

        public void setStatus(int Status) {
            this.Status = Status;
        }
    }
}
