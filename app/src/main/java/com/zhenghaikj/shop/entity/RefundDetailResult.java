package com.zhenghaikj.shop.entity;

import java.io.Serializable;

public class RefundDetailResult implements Serializable {
    /**
     * ManagerConfirmStatus : 待平台确认
     * ManagerConfirmStatusValue : 6
     * ManagerConfirmDate : 2016-02-19T11:12:58
     * SellerAuditStatus : 待商家审核
     * SellerAuditStatusValue : 1
     * SellerRemark : null
     * SellerAuditDate : 2016-02-19T11:12:58
     * RefundStatus : 待商家审核
     * Amount : 0.1
     * Id : 35
     * ShopName : 官方自营店
     * RefundMode : 货品退款
     * RefundModeValue : 2
     * ReturnQuantity : 0
     * RefundPayType : 退到预付款
     * RefundPayTypeValue : 3
     * Reason : 111111
     * ApplyDate : 2016-02-19T11:12:58
     */

    private String ManagerConfirmStatus;
    private int ManagerConfirmStatusValue;
    private String ManagerConfirmDate;
    private String SellerAuditStatus;
    private int SellerAuditStatusValue;
    private String SellerRemark;
    private String SellerAuditDate;
    private String RefundStatus;
    private double Amount;
    private int Id;
    private String ShopName;
    private String RefundMode;
    private int RefundModeValue;
    private int ReturnQuantity;
    private String RefundPayType;
    private int RefundPayTypeValue;
    private String Reason;
    private String ApplyDate;

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

    public double getAmount() {
        return Amount;
    }

    public void setAmount(double Amount) {
        this.Amount = Amount;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
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

}
