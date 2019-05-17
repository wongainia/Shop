package com.zhenghaikj.shop.entity;

import java.io.Serializable;
import java.util.List;

public class UserCouponListResult implements Serializable {


    /**
     * Success : true
     * NoUseCount : 0
     * UserCount : 12
     * Coupon : [{"UserId":396,"ShopId":215,"CouponId":56,"Price":200,"PerMax":0,"OrderAmount":800,"Num":50,"StartTime":"2015-07-20T00:00:00","EndTime":"2015-08-17T00:00:00","CreateTime":"2015-07-20T10:57:27","CouponName":"满800减200","UseStatus":0,"UseTime":null,"VShop":{"VShopId":8,"VShopLogo":"/Storage/Shop/215/VShop/Logo.jpg"}}]
     */

    private String Success;
    private String NoUseCount;
    private String UserCount;
    private List<CouponBean> Coupon;

    public String getSuccess() {
        return Success;
    }

    public void setSuccess(String Success) {
        this.Success = Success;
    }

    public String getNoUseCount() {
        return NoUseCount;
    }

    public void setNoUseCount(String NoUseCount) {
        this.NoUseCount = NoUseCount;
    }

    public String getUserCount() {
        return UserCount;
    }

    public void setUserCount(String UserCount) {
        this.UserCount = UserCount;
    }

    public List<CouponBean> getCoupon() {
        return Coupon;
    }

    public void setCoupon(List<CouponBean> Coupon) {
        this.Coupon = Coupon;
    }

    public static class CouponBean {
        /**
         * UserId : 396
         * ShopId : 215
         * CouponId : 56
         * Price : 200
         * PerMax : 0
         * OrderAmount : 800
         * Num : 50
         * StartTime : 2015-07-20T00:00:00
         * EndTime : 2015-08-17T00:00:00
         * CreateTime : 2015-07-20T10:57:27
         * CouponName : 满800减200
         * UseStatus : 0
         * UseTime : null
         * VShop : {"VShopId":8,"VShopLogo":"/Storage/Shop/215/VShop/Logo.jpg"}
         */

        private String UserId;
        private String ShopId;
        private String CouponId;
        private String Price;
        private String PerMax;
        private String OrderAmount;
        private String Num;
        private String StartTime;
        private String EndTime;
        private String CreateTime;
        private String CouponName;
        private String UseStatus;
        private String UseTime;
        private VShopBean VShop;

        public String getUserId() {
            return UserId;
        }

        public void setUserId(String UserId) {
            this.UserId = UserId;
        }

        public String getShopId() {
            return ShopId;
        }

        public void setShopId(String ShopId) {
            this.ShopId = ShopId;
        }

        public String getCouponId() {
            return CouponId;
        }

        public void setCouponId(String CouponId) {
            this.CouponId = CouponId;
        }

        public String getPrice() {
            return Price;
        }

        public void setPrice(String Price) {
            this.Price = Price;
        }

        public String getPerMax() {
            return PerMax;
        }

        public void setPerMax(String PerMax) {
            this.PerMax = PerMax;
        }

        public String getOrderAmount() {
            return OrderAmount;
        }

        public void setOrderAmount(String OrderAmount) {
            this.OrderAmount = OrderAmount;
        }

        public String getNum() {
            return Num;
        }

        public void setNum(String Num) {
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

        public String getUseStatus() {
            return UseStatus;
        }

        public void setUseStatus(String UseStatus) {
            this.UseStatus = UseStatus;
        }

        public String getUseTime() {
            return UseTime;
        }

        public void setUseTime(String UseTime) {
            this.UseTime = UseTime;
        }

        public VShopBean getVShop() {
            return VShop;
        }

        public void setVShop(VShopBean VShop) {
            this.VShop = VShop;
        }

        public static class VShopBean {
            /**
             * VShopId : 8
             * VShopLogo : /Storage/Shop/215/VShop/Logo.jpg
             */

            private String VShopId;
            private String VShopLogo;

            public String getVShopId() {
                return VShopId;
            }

            public void setVShopId(String VShopId) {
                this.VShopId = VShopId;
            }

            public String getVShopLogo() {
                return VShopLogo;
            }

            public void setVShopLogo(String VShopLogo) {
                this.VShopLogo = VShopLogo;
            }
        }
    }
}
