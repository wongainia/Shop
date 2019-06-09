package com.zhenghaikj.shop.entity;

import java.io.Serializable;
import java.util.List;

public class GiftOrderDetail implements Serializable {

    /**
     * Id : 1201906064338561
     * OrderStatus : 5
     * UserId : 654
     * UserName : null
     * UserRemark : null
     * ShipTo : 中信信用卡
     * CellPhone : 186 5820 0762
     * TopRegionId : 130000
     * RegionId : 130723102
     * RegionFullName : 河北省 张家口市 康保县 土城子镇
     * Address : 好
     * ExpressCompanyName : 邮政EMS
     * ShipOrderNumber : 546545
     * ShippingDate : 2019-06-09T10:36:35
     * OrderDate : 2019-06-06T14:01:50
     * FinishDate : 2019-06-09T10:37:22
     * TotalIntegral : 10
     * CloseReason : null
     * ShowOrderStatus : 已完成
     * ShowExpressCompanyName : 邮政EMS
     * Items : [{"Id":8,"OrderId":1201906064338561,"GiftId":82,"Quantity":1,"SaleIntegral":10,"GiftName":"AUX奥克斯空调大15匹变频冷暖节能挂机KFR35GWBpNFW3","GiftValue":1999,"ImagePath":"/Storage/Gift/82","DefaultImage":"http://mall.xigyu.com//Storage/Gift/82/1_150.png"}]
     */

    private String Id;
    private int OrderStatus;
    private int UserId;
    private String UserName;
    private String UserRemark;
    private String ShipTo;
    private String CellPhone;
    private int TopRegionId;
    private int RegionId;
    private String RegionFullName;
    private String Address;
    private String ExpressCompanyName;
    private String ShipOrderNumber;
    private String ShippingDate;
    private String OrderDate;
    private String FinishDate;
    private int TotalIntegral;
    private String CloseReason;
    private String ShowOrderStatus;
    private String ShowExpressCompanyName;
    private List<ItemsBean> Items;

    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public int getOrderStatus() {
        return OrderStatus;
    }

    public void setOrderStatus(int OrderStatus) {
        this.OrderStatus = OrderStatus;
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int UserId) {
        this.UserId = UserId;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String UserName) {
        this.UserName = UserName;
    }

    public String getUserRemark() {
        return UserRemark;
    }

    public void setUserRemark(String UserRemark) {
        this.UserRemark = UserRemark;
    }

    public String getShipTo() {
        return ShipTo;
    }

    public void setShipTo(String ShipTo) {
        this.ShipTo = ShipTo;
    }

    public String getCellPhone() {
        return CellPhone;
    }

    public void setCellPhone(String CellPhone) {
        this.CellPhone = CellPhone;
    }

    public int getTopRegionId() {
        return TopRegionId;
    }

    public void setTopRegionId(int TopRegionId) {
        this.TopRegionId = TopRegionId;
    }

    public int getRegionId() {
        return RegionId;
    }

    public void setRegionId(int RegionId) {
        this.RegionId = RegionId;
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

    public String getExpressCompanyName() {
        return ExpressCompanyName;
    }

    public void setExpressCompanyName(String ExpressCompanyName) {
        this.ExpressCompanyName = ExpressCompanyName;
    }

    public String getShipOrderNumber() {
        return ShipOrderNumber;
    }

    public void setShipOrderNumber(String ShipOrderNumber) {
        this.ShipOrderNumber = ShipOrderNumber;
    }

    public String getShippingDate() {
        return ShippingDate;
    }

    public void setShippingDate(String ShippingDate) {
        this.ShippingDate = ShippingDate;
    }

    public String getOrderDate() {
        return OrderDate.replace("T"," ");
    }

    public void setOrderDate(String OrderDate) {
        this.OrderDate = OrderDate;
    }

    public String getFinishDate() {
        return FinishDate;
    }

    public void setFinishDate(String FinishDate) {
        this.FinishDate = FinishDate;
    }

    public int getTotalIntegral() {
        return TotalIntegral;
    }

    public void setTotalIntegral(int TotalIntegral) {
        this.TotalIntegral = TotalIntegral;
    }

    public String getCloseReason() {
        return CloseReason;
    }

    public void setCloseReason(String CloseReason) {
        this.CloseReason = CloseReason;
    }

    public String getShowOrderStatus() {
        return ShowOrderStatus;
    }

    public void setShowOrderStatus(String ShowOrderStatus) {
        this.ShowOrderStatus = ShowOrderStatus;
    }

    public String getShowExpressCompanyName() {
        return ShowExpressCompanyName;
    }

    public void setShowExpressCompanyName(String ShowExpressCompanyName) {
        this.ShowExpressCompanyName = ShowExpressCompanyName;
    }

    public List<ItemsBean> getItems() {
        return Items;
    }

    public void setItems(List<ItemsBean> Items) {
        this.Items = Items;
    }

    public static class ItemsBean {
        /**
         * Id : 8
         * OrderId : 1201906064338561
         * GiftId : 82
         * Quantity : 1
         * SaleIntegral : 10
         * GiftName : AUX奥克斯空调大15匹变频冷暖节能挂机KFR35GWBpNFW3
         * GiftValue : 1999.0
         * ImagePath : /Storage/Gift/82
         * DefaultImage : http://mall.xigyu.com//Storage/Gift/82/1_150.png
         */

        private int Id;
        private String OrderId;
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

        public String getOrderId() {
            return OrderId;
        }

        public void setOrderId(String OrderId) {
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
