package com.zhenghaikj.shop.entity;

import java.io.Serializable;
import java.util.List;

public class UserCouponListResult implements Serializable {


    /**
     * Success : true
     * NoUseCount : 26
     * UserCount : 6
     * Coupon : [{"UserId":654,"ShopId":1,"CouponId":67,"Price":50,"PerMax":1,"OrderAmount":200,"Num":999,"StartTime":"2019/5/3 0:00:00","EndTime":"2019/5/20 0:00:00","CreateTime":"2019/5/3 13:58:04","CouponName":"满减100","UseStatus":0,"UseTime":null,"VShop":{"VShopId":10,"VShopLogo":"http://mall.xigyu.com//Storage/Shop/1/VShop/201904271426408530240.jpg"},"ShopName":"官方自营店"}]
     * Bonus : []
     */

    private boolean Success;
    private int NoUseCount;
    private int UserCount;
    private List<CouponBean> Coupon;
    private List<?> Bonus;

    public boolean isSuccess() {
        return Success;
    }

    public void setSuccess(boolean Success) {
        this.Success = Success;
    }

    public int getNoUseCount() {
        return NoUseCount;
    }

    public void setNoUseCount(int NoUseCount) {
        this.NoUseCount = NoUseCount;
    }

    public int getUserCount() {
        return UserCount;
    }

    public void setUserCount(int UserCount) {
        this.UserCount = UserCount;
    }

    public List<CouponBean> getCoupon() {
        return Coupon;
    }

    public void setCoupon(List<CouponBean> Coupon) {
        this.Coupon = Coupon;
    }

    public List<?> getBonus() {
        return Bonus;
    }

    public void setBonus(List<?> Bonus) {
        this.Bonus = Bonus;
    }

    public static class CouponBean {
        /**
         * UserId : 654
         * ShopId : 1
         * CouponId : 67
         * Price : 50.0
         * PerMax : 1
         * OrderAmount : 200.0
         * Num : 999
         * StartTime : 2019/5/3 0:00:00
         * EndTime : 2019/5/20 0:00:00
         * CreateTime : 2019/5/3 13:58:04
         * CouponName : 满减100
         * UseStatus : 0
         * UseTime : null
         * VShop : {"VShopId":10,"VShopLogo":"http://mall.xigyu.com//Storage/Shop/1/VShop/201904271426408530240.jpg"}
         * ShopName : 官方自营店
         */

        private int UserId;
        private int ShopId;
        private int CouponId;
        private double Price;
        private int PerMax;
        private double OrderAmount;
        private int Num;
        private String StartTime;
        private String EndTime;
        private String CreateTime;
        private String CouponName;
        private int UseStatus;
        private Object UseTime;
        private VShopBean VShop;
        private String ShopName;

        public int getUserId() {
            return UserId;
        }

        public void setUserId(int UserId) {
            this.UserId = UserId;
        }

        public int getShopId() {
            return ShopId;
        }

        public void setShopId(int ShopId) {
            this.ShopId = ShopId;
        }

        public int getCouponId() {
            return CouponId;
        }

        public void setCouponId(int CouponId) {
            this.CouponId = CouponId;
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

        public int getUseStatus() {
            return UseStatus;
        }

        public void setUseStatus(int UseStatus) {
            this.UseStatus = UseStatus;
        }

        public Object getUseTime() {
            return UseTime;
        }

        public void setUseTime(Object UseTime) {
            this.UseTime = UseTime;
        }

        public VShopBean getVShop() {
            return VShop;
        }

        public void setVShop(VShopBean VShop) {
            this.VShop = VShop;
        }

        public String getShopName() {
            return ShopName;
        }

        public void setShopName(String ShopName) {
            this.ShopName = ShopName;
        }

        public static class VShopBean {
            /**
             * VShopId : 10
             * VShopLogo : http://mall.xigyu.com//Storage/Shop/1/VShop/201904271426408530240.jpg
             */

            private int VShopId;
            private String VShopLogo;

            public int getVShopId() {
                return VShopId;
            }

            public void setVShopId(int VShopId) {
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
