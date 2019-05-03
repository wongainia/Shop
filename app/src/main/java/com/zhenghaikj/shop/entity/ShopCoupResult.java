package com.zhenghaikj.shop.entity;

import java.io.Serializable;
import java.util.List;

public class ShopCoupResult implements Serializable {


    /**
     * Success : true
     * Coupon : [{"ShopId":210,"CouponId":107,"Price":10,"PerMax":0,"OrderAmount":100,"Num":10000,"StartTime":"2015-09-28T00:00:00","EndTime":"2015-10-28T00:00:00","CreateTime":"2015-09-28T11:37:25","CouponName":"APP测试","VShopLogo":"http://192.168.10.47/Storage/Shop/210/VShop/Logo.jpg","Receive":"1"},{"ShopId":210,"CouponId":108,"Price":5,"PerMax":0,"OrderAmount":0,"Num":1000,"StartTime":"2015-09-28T00:00:00","EndTime":"2015-10-28T00:00:00","CreateTime":"2015-09-28T11:39:49","CouponName":"APP测试2","VShopLogo":"http://192.168.10.47/Storage/Shop/210/VShop/Logo.jpg","Receive":"1"}]
     */

    private String Success;
    private String ErrorMsg;
    private List<CouponBean> Coupon;

    public String getSuccess() {
        return Success;
    }

    public void setSuccess(String Success) {
        this.Success = Success;
    }

    public List<CouponBean> getCoupon() {
        return Coupon;
    }

    public void setCoupon(List<CouponBean> Coupon) {
        this.Coupon = Coupon;
    }

    public String getErrorMsg() {
        return ErrorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        ErrorMsg = errorMsg;
    }

    public static class CouponBean {


        /**
         * ShopId : 1
         * CouponId : 67
         * Price : 50.0
         * PerMax : 1
         * OrderAmount : 200.0
         * Num : 999
         * StartTime : 2019/5/3 0:00:00
         * EndTime : 2019/6/3 0:00:00
         * CreateTime : 2019/5/3 13:58:04
         * CouponName : 满减100
         * VShopLogo : http://47.96.126.145:8830//Storage/Shop/1/VShop/201904271426408530240.jpg
         * VShopId : 10
         * ShopName : 官方自营店
         * Receive : 0
         */

        private String ShopId;
        private String CouponId;
        private double Price;
        private int PerMax;
        private double OrderAmount;
        private int Num;
        private String StartTime;
        private String EndTime;
        private String CreateTime;
        private String CouponName;
        private String VShopLogo;
        private String VShopId;
        private String ShopName;
        private int Receive;

        public String getShopId() {
            return ShopId;
        }

        public void setShopId(String shopId) {
            ShopId = shopId;
        }

        public String getCouponId() {
            return CouponId;
        }

        public void setCouponId(String couponId) {
            CouponId = couponId;
        }

        public String getVShopId() {
            return VShopId;
        }

        public void setVShopId(String VShopId) {
            this.VShopId = VShopId;
        }

        public double getPrice() {
            return Price;
        }

        public void setPrice(double Price) {
            this.Price = Price;
        }

        public int getPerMax() {
            return PerMax;
        }

        public void setPerMax(int PerMax) {
            this.PerMax = PerMax;
        }

        public double getOrderAmount() {
            return OrderAmount;
        }

        public void setOrderAmount(double OrderAmount) {
            this.OrderAmount = OrderAmount;
        }

        public int getNum() {
            return Num;
        }

        public void setNum(int Num) {
            this.Num = Num;
        }

        public String getStartTime() {
            return StartTime;
        }

        public void setStartTime(String StartTime) {
            this.StartTime = StartTime;
        }

        public String getEndTime() {
            return EndTime;
        }

        public void setEndTime(String EndTime) {
            this.EndTime = EndTime;
        }

        public String getCreateTime() {
            return CreateTime;
        }

        public void setCreateTime(String CreateTime) {
            this.CreateTime = CreateTime;
        }

        public String getCouponName() {
            return CouponName;
        }

        public void setCouponName(String CouponName) {
            this.CouponName = CouponName;
        }

        public String getVShopLogo() {
            return VShopLogo;
        }

        public void setVShopLogo(String VShopLogo) {
            this.VShopLogo = VShopLogo;
        }


        public String getShopName() {
            return ShopName;
        }

        public void setShopName(String ShopName) {
            this.ShopName = ShopName;
        }

        public int getReceive() {
            return Receive;
        }

        public void setReceive(int Receive) {
            this.Receive = Receive;
        }
    }
}
