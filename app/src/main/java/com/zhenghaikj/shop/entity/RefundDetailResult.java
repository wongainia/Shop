package com.zhenghaikj.shop.entity;

import java.io.Serializable;
import java.util.List;

public class RefundDetailResult implements Serializable {

    /**
     * ManagerConfirmStatus : 待平台确认
     * ManagerConfirmStatusValue : 6
     * ManagerConfirmDate : 2019-08-18T14:57:10
     * SellerAuditStatus : 商家通过审核
     * SellerAuditStatusValue : 5
     * SellerRemark :
     * SellerAuditDate : 2019-08-18T14:57:10
     * RefundStatus : 待平台确认
     * Amount : 739
     * Id : 269
     * OrderId : 2019072458933410
     * OrderItemId : 1316
     * ShopName : 官方自营店
     * RefundMode : 货品退款
     * RefundModeValue : 2
     * ReturnQuantity : 0
     * RefundPayType : 原路返回
     * RefundPayTypeValue : 1
     * Reason : 不想买了
     * ApplyDate : 2019-08-16T09:05:27
     * IsOrderRefundTimeOut : false
     * LastConfirmDate : 2019-08-18T14:57:10
     * ResetActive : true
     * Reciver : uki-
     * RecivePhone :
     * RefundAddressList : [{"ShipTo":"肖敏","Address":"江北111","Phone":"18977777777","AddressDetail":"90号","Type":"2","UserId":573,"RegionFullName":"浙江省 宁波市 江北区 望山路703号靠近宁波企协大厦"}]
     */

    private String ManagerConfirmStatus;
    private int ManagerConfirmStatusValue;
    private String ManagerConfirmDate;
    private String SellerAuditStatus;
    private int SellerAuditStatusValue;
    private String SellerRemark;
    private String SellerAuditDate;
    private String RefundStatus;
    private int Amount;
    private int Id;
    private long OrderId;
    private int OrderItemId;
    private String ShopName;
    private String RefundMode;
    private int RefundModeValue;
    private int ReturnQuantity;
    private String RefundPayType;
    private int RefundPayTypeValue;
    private String Reason;
    private String ApplyDate;
    private boolean IsOrderRefundTimeOut;
    private String LastConfirmDate;
    private boolean ResetActive;
    private String Reciver;
    private String RecivePhone;
    private List<RefundAddressListBean> RefundAddressList;

    public String getManagerConfirmStatus() {
        return ManagerConfirmStatus;
    }

    public void setManagerConfirmStatus(String ManagerConfirmStatus) {
        this.ManagerConfirmStatus = ManagerConfirmStatus;
    }

    public int getManagerConfirmStatusValue() {
        return ManagerConfirmStatusValue;
    }

    public void setManagerConfirmStatusValue(int ManagerConfirmStatusValue) {
        this.ManagerConfirmStatusValue = ManagerConfirmStatusValue;
    }

    public String getManagerConfirmDate() {
        return ManagerConfirmDate;
    }

    public void setManagerConfirmDate(String ManagerConfirmDate) {
        this.ManagerConfirmDate = ManagerConfirmDate;
    }

    public String getSellerAuditStatus() {
        return SellerAuditStatus;
    }

    public void setSellerAuditStatus(String SellerAuditStatus) {
        this.SellerAuditStatus = SellerAuditStatus;
    }

    public int getSellerAuditStatusValue() {
        return SellerAuditStatusValue;
    }

    public void setSellerAuditStatusValue(int SellerAuditStatusValue) {
        this.SellerAuditStatusValue = SellerAuditStatusValue;
    }

    public String getSellerRemark() {
        return SellerRemark;
    }

    public void setSellerRemark(String SellerRemark) {
        this.SellerRemark = SellerRemark;
    }

    public String getSellerAuditDate() {
        return SellerAuditDate;
    }

    public void setSellerAuditDate(String SellerAuditDate) {
        this.SellerAuditDate = SellerAuditDate;
    }

    public String getRefundStatus() {
        return RefundStatus;
    }

    public void setRefundStatus(String RefundStatus) {
        this.RefundStatus = RefundStatus;
    }

    public int getAmount() {
        return Amount;
    }

    public void setAmount(int Amount) {
        this.Amount = Amount;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public long getOrderId() {
        return OrderId;
    }

    public void setOrderId(long OrderId) {
        this.OrderId = OrderId;
    }

    public int getOrderItemId() {
        return OrderItemId;
    }

    public void setOrderItemId(int OrderItemId) {
        this.OrderItemId = OrderItemId;
    }

    public String getShopName() {
        return ShopName;
    }

    public void setShopName(String ShopName) {
        this.ShopName = ShopName;
    }

    public String getRefundMode() {
        return RefundMode;
    }

    public void setRefundMode(String RefundMode) {
        this.RefundMode = RefundMode;
    }

    public int getRefundModeValue() {
        return RefundModeValue;
    }

    public void setRefundModeValue(int RefundModeValue) {
        this.RefundModeValue = RefundModeValue;
    }

    public int getReturnQuantity() {
        return ReturnQuantity;
    }

    public void setReturnQuantity(int ReturnQuantity) {
        this.ReturnQuantity = ReturnQuantity;
    }

    public String getRefundPayType() {
        return RefundPayType;
    }

    public void setRefundPayType(String RefundPayType) {
        this.RefundPayType = RefundPayType;
    }

    public int getRefundPayTypeValue() {
        return RefundPayTypeValue;
    }

    public void setRefundPayTypeValue(int RefundPayTypeValue) {
        this.RefundPayTypeValue = RefundPayTypeValue;
    }

    public String getReason() {
        return Reason;
    }

    public void setReason(String Reason) {
        this.Reason = Reason;
    }

    public String getApplyDate() {
        return ApplyDate;
    }

    public void setApplyDate(String ApplyDate) {
        this.ApplyDate = ApplyDate;
    }

    public boolean isIsOrderRefundTimeOut() {
        return IsOrderRefundTimeOut;
    }

    public void setIsOrderRefundTimeOut(boolean IsOrderRefundTimeOut) {
        this.IsOrderRefundTimeOut = IsOrderRefundTimeOut;
    }

    public String getLastConfirmDate() {
        return LastConfirmDate;
    }

    public void setLastConfirmDate(String LastConfirmDate) {
        this.LastConfirmDate = LastConfirmDate;
    }

    public boolean isResetActive() {
        return ResetActive;
    }

    public void setResetActive(boolean ResetActive) {
        this.ResetActive = ResetActive;
    }

    public String getReciver() {
        return Reciver;
    }

    public void setReciver(String Reciver) {
        this.Reciver = Reciver;
    }

    public String getRecivePhone() {
        return RecivePhone;
    }

    public void setRecivePhone(String RecivePhone) {
        this.RecivePhone = RecivePhone;
    }

    public List<RefundAddressListBean> getRefundAddressList() {
        return RefundAddressList;
    }

    public void setRefundAddressList(List<RefundAddressListBean> RefundAddressList) {
        this.RefundAddressList = RefundAddressList;
    }

    public static class RefundAddressListBean {
        /**
         * ShipTo : 肖敏
         * Address : 江北111
         * Phone : 18977777777
         * AddressDetail : 90号
         * Type : 2
         * UserId : 573
         * RegionFullName : 浙江省 宁波市 江北区 望山路703号靠近宁波企协大厦
         */

        private String ShipTo;
        private String Address;
        private String Phone;
        private String AddressDetail;
        private String Type;
        private int UserId;
        private String RegionFullName;

        public String getShipTo() {
            return ShipTo;
        }

        public void setShipTo(String ShipTo) {
            this.ShipTo = ShipTo;
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

        public String getAddressDetail() {
            return AddressDetail;
        }

        public void setAddressDetail(String AddressDetail) {
            this.AddressDetail = AddressDetail;
        }

        public String getType() {
            return Type;
        }

        public void setType(String Type) {
            this.Type = Type;
        }

        public int getUserId() {
            return UserId;
        }

        public void setUserId(int UserId) {
            this.UserId = UserId;
        }

        public String getRegionFullName() {
            return RegionFullName;
        }

        public void setRegionFullName(String RegionFullName) {
            this.RegionFullName = RegionFullName;
        }
    }
}
