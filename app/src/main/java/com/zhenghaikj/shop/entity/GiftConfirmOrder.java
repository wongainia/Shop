package com.zhenghaikj.shop.entity;

import java.io.Serializable;
import java.util.List;

public class GiftConfirmOrder implements Serializable {

    /**
     * GiftList : [{"Id":0,"OrderId":0,"GiftId":81,"Quantity":1,"SaleIntegral":10,"GiftName":"AUX奥克斯空调大2匹定频节能冷暖挂机柜机能量KFR50GWNFJ183","GiftValue":3199,"ImagePath":"/Storage/Gift/81","DefaultImage":"http://mall.xigyu.com//Storage/Gift/81/1_100.png"}]
     * TotalAmount : 10
     * GiftValueTotal : 3199.0
     * ShipAddress : {"Id":211,"UserId":654,"RegionId":130723102,"ShipTo":"中信信用卡","RegionFullName":"河北省 张家口市 康保县 土城子镇","Address":"好","Phone":"186 5820 0762","IsDefault":false,"IsQuick":false}
     */

    private int TotalAmount;
    private double GiftValueTotal;
    private ShipAddressBean ShipAddress;
    private List<GiftListBean> GiftList;

    public int getTotalAmount() {
        return TotalAmount;
    }

    public void setTotalAmount(int TotalAmount) {
        this.TotalAmount = TotalAmount;
    }

    public double getGiftValueTotal() {
        return GiftValueTotal;
    }

    public void setGiftValueTotal(double GiftValueTotal) {
        this.GiftValueTotal = GiftValueTotal;
    }

    public ShipAddressBean getShipAddress() {
        return ShipAddress;
    }

    public void setShipAddress(ShipAddressBean ShipAddress) {
        this.ShipAddress = ShipAddress;
    }

    public List<GiftListBean> getGiftList() {
        return GiftList;
    }

    public void setGiftList(List<GiftListBean> GiftList) {
        this.GiftList = GiftList;
    }

    public static class ShipAddressBean {
        /**
         * Id : 211
         * UserId : 654
         * RegionId : 130723102
         * ShipTo : 中信信用卡
         * RegionFullName : 河北省 张家口市 康保县 土城子镇
         * Address : 好
         * Phone : 186 5820 0762
         * IsDefault : false
         * IsQuick : false
         */

        private int Id;
        private int UserId;
        private int RegionId;
        private String ShipTo;
        private String RegionFullName;
        private String Address;
        private String Phone;
        private boolean IsDefault;
        private boolean IsQuick;

        public int getId() {
            return Id;
        }

        public void setId(int Id) {
            this.Id = Id;
        }

        public int getUserId() {
            return UserId;
        }

        public void setUserId(int UserId) {
            this.UserId = UserId;
        }

        public int getRegionId() {
            return RegionId;
        }

        public void setRegionId(int RegionId) {
            this.RegionId = RegionId;
        }

        public String getShipTo() {
            return ShipTo;
        }

        public void setShipTo(String ShipTo) {
            this.ShipTo = ShipTo;
        }

        public String getRegionFullName() {
            return RegionFullName;
        }

        public void setRegionFullName(String RegionFullName) {
            this.RegionFullName = RegionFullName;
        }

        public String getAddress() {
            return Address;
        }

        public void setAddress(String Address) {
            this.Address = Address;
        }

        public String getPhone() {
            return Phone;
        }

        public void setPhone(String Phone) {
            this.Phone = Phone;
        }

        public boolean isIsDefault() {
            return IsDefault;
        }

        public void setIsDefault(boolean IsDefault) {
            this.IsDefault = IsDefault;
        }

        public boolean isIsQuick() {
            return IsQuick;
        }

        public void setIsQuick(boolean IsQuick) {
            this.IsQuick = IsQuick;
        }
    }

    public static class GiftListBean {
        /**
         * Id : 0
         * OrderId : 0
         * GiftId : 81
         * Quantity : 1
         * SaleIntegral : 10
         * GiftName : AUX奥克斯空调大2匹定频节能冷暖挂机柜机能量KFR50GWNFJ183
         * GiftValue : 3199.0
         * ImagePath : /Storage/Gift/81
         * DefaultImage : http://mall.xigyu.com//Storage/Gift/81/1_100.png
         */

        private int Id;
        private int OrderId;
        private int GiftId;
        private int Quantity;
        private int SaleIntegral;
        private String GiftName;
        private double GiftValue;
        private String ImagePath;
        private String DefaultImage;

        public int getId() {
            return Id;
        }

        public void setId(int Id) {
            this.Id = Id;
        }

        public int getOrderId() {
            return OrderId;
        }

        public void setOrderId(int OrderId) {
            this.OrderId = OrderId;
        }

        public int getGiftId() {
            return GiftId;
        }

        public void setGiftId(int GiftId) {
            this.GiftId = GiftId;
        }

        public int getQuantity() {
            return Quantity;
        }

        public void setQuantity(int Quantity) {
            this.Quantity = Quantity;
        }

        public int getSaleIntegral() {
            return SaleIntegral;
        }

        public void setSaleIntegral(int SaleIntegral) {
            this.SaleIntegral = SaleIntegral;
        }

        public String getGiftName() {
            return GiftName;
        }

        public void setGiftName(String GiftName) {
            this.GiftName = GiftName;
        }

        public double getGiftValue() {
            return GiftValue;
        }

        public void setGiftValue(double GiftValue) {
            this.GiftValue = GiftValue;
        }

        public String getImagePath() {
            return ImagePath;
        }

        public void setImagePath(String ImagePath) {
            this.ImagePath = ImagePath;
        }

        public String getDefaultImage() {
            return DefaultImage;
        }

        public void setDefaultImage(String DefaultImage) {
            this.DefaultImage = DefaultImage;
        }
    }
}
