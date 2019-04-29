package com.zhenghaikj.shop.entity;

import java.io.Serializable;
import java.util.List;

public class Refund implements Serializable {

    /*
    *"Total":0,
    * "Data":[],
    * "Success":true
    * */

    private String Total;
    private List<DataBean> Data;
    private boolean Success;

    public String getTotal() {
        return Total;
    }

    public void setTotal(String total) {
        Total = total;
    }

    public List<DataBean> getData() {
        return Data;
    }

    public void setData(List<DataBean> data) {
        Data = data;
    }

    public boolean isSuccess() {
        return Success;
    }

    public void setSuccess(boolean success) {
        Success = success;
    }

    public static class DataBean{
        /*
        * "ShopName": "官方自营店",     店铺名称
        * "Vshopid": 12,                微店ID
        * "RefundStatus": "待商家审核", 状态描述
        * "Id": 35,
         *"ProductName": "名称",        商品名称
        *"EnabledRefundAmount": 0.1,   可退款金额
        *"Amount": 0.1,                实际退款金额
        *"Img": "url",
        *"ShopId": 1,                  店铺ID
        * "RefundMode": "货品退款",     1：订单退款2：货品退款3：退货退款
        * "RefundModeValue": 2,
        * "OrderId": 2016021988828332,
        *"OrderItems": null,
        *"SellerAuditStatus": "待商家审核",1：待商家审核2：待买家寄货3：待商家收货4：商家拒绝5：商家通过审核
        *"SellerAuditStatusValue": 1
         * */

        private String ShopName;
        private String Vshopid;
        private String RefundStatus;
        private String Id;
        private String ProductName;
        private String EnabledRefundAmount;
        private String Amount;
        private String Img;
        private String ShopId;
        private String RefundMode;
        private String RefundModeValue;
        private String OrderId;
        private String OrderItems;
        private String SellerAuditStatus;
        private String SellerAuditStatusValue;

        public String getShopName() {
            return ShopName;
        }

        public void setShopName(String shopName) {
            ShopName = shopName;
        }

        public String getVshopid() {
            return Vshopid;
        }

        public void setVshopid(String vshopid) {
            Vshopid = vshopid;
        }

        public String getRefundStatus() {
            return RefundStatus;
        }

        public void setRefundStatus(String refundStatus) {
            RefundStatus = refundStatus;
        }

        public String getId() {
            return Id;
        }

        public void setId(String id) {
            Id = id;
        }

        public String getProductName() {
            return ProductName;
        }

        public void setProductName(String productName) {
            ProductName = productName;
        }

        public String getEnabledRefundAmount() {
            return EnabledRefundAmount;
        }

        public void setEnabledRefundAmount(String enabledRefundAmount) {
            EnabledRefundAmount = enabledRefundAmount;
        }

        public String getAmount() {
            return Amount;
        }

        public void setAmount(String amount) {
            Amount = amount;
        }

        public String getImg() {
            return Img;
        }

        public void setImg(String img) {
            Img = img;
        }

        public String getShopId() {
            return ShopId;
        }

        public void setShopId(String shopId) {
            ShopId = shopId;
        }

        public String getRefundMode() {
            return RefundMode;
        }

        public void setRefundMode(String refundMode) {
            RefundMode = refundMode;
        }

        public String getRefundModeValue() {
            return RefundModeValue;
        }

        public void setRefundModeValue(String refundModeValue) {
            RefundModeValue = refundModeValue;
        }

        public String getOrderId() {
            return OrderId;
        }

        public void setOrderId(String orderId) {
            OrderId = orderId;
        }

        public String getOrderItems() {
            return OrderItems;
        }

        public void setOrderItems(String orderItems) {
            OrderItems = orderItems;
        }

        public String getSellerAuditStatus() {
            return SellerAuditStatus;
        }

        public void setSellerAuditStatus(String sellerAuditStatus) {
            SellerAuditStatus = sellerAuditStatus;
        }

        public String getSellerAuditStatusValue() {
            return SellerAuditStatusValue;
        }

        public void setSellerAuditStatusValue(String sellerAuditStatusValue) {
            SellerAuditStatusValue = sellerAuditStatusValue;
        }
    }
}
