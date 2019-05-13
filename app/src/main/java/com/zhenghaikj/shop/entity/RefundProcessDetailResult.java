package com.zhenghaikj.shop.entity;

import java.io.Serializable;

public class RefundProcessDetailResult implements Serializable {


    /**
     * IsDiscard : false
     * IsUnAudit : false
     * IsReturnGoods : false
     * ManagerConfirmStatus : 待平台确认
     * ManagerConfirmStatusValue : 6
     * ManagerConfirmDate : 2016-02-19T11:12:58
     * ManagerRemark : null
     * SellerAuditStatus : 待商家审核
     * SellerAuditStatusValue : 1
     * SellerConfirmArrivalDate : null
     * SellerRemark : null
     * SellerAuditDate : 2016-02-19T11:12:58
     * BuyerDeliverDate : null
     * ExpressCompanyName : null
     * ShipOrderNumber : null
     * ApplyDate : 2016-02-19T11:12:58
     */

    private boolean IsDiscard;
    private boolean IsUnAudit;
    private boolean IsReturnGoods;
    private String ManagerConfirmStatus;
    private int ManagerConfirmStatusValue;
    private String ManagerConfirmDate;
    private String ManagerRemark;
    private String SellerAuditStatus;
    private int SellerAuditStatusValue;
    private String SellerConfirmArrivalDate;
    private String SellerRemark;
    private String SellerAuditDate;
    private String BuyerDeliverDate;
    private String ExpressCompanyName;
    private String ShipOrderNumber;
    private String ApplyDate;

    public boolean isIsDiscard() {
        return IsDiscard;
    }

    public void setIsDiscard(boolean IsDiscard) {
        this.IsDiscard = IsDiscard;
    }

    public boolean isIsUnAudit() {
        return IsUnAudit;
    }

    public void setIsUnAudit(boolean IsUnAudit) {
        this.IsUnAudit = IsUnAudit;
    }

    public boolean isIsReturnGoods() {
        return IsReturnGoods;
    }

    public void setIsReturnGoods(boolean IsReturnGoods) {
        this.IsReturnGoods = IsReturnGoods;
    }

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

    public String getManagerRemark() {
        return ManagerRemark;
    }

    public void setManagerRemark(String ManagerRemark) {
        this.ManagerRemark = ManagerRemark;
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

    public String getSellerConfirmArrivalDate() {
        return SellerConfirmArrivalDate;
    }

    public void setSellerConfirmArrivalDate(String SellerConfirmArrivalDate) {
        this.SellerConfirmArrivalDate = SellerConfirmArrivalDate;
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

    public String getBuyerDeliverDate() {
        return BuyerDeliverDate;
    }

    public void setBuyerDeliverDate(String BuyerDeliverDate) {
        this.BuyerDeliverDate = BuyerDeliverDate;
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

    public String getApplyDate() {
        return ApplyDate;
    }

    public void setApplyDate(String ApplyDate) {
        this.ApplyDate = ApplyDate;
    }
}
