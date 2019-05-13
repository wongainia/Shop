package com.zhenghaikj.shop.entity;

import java.io.Serializable;

public class RefundModelResult implements Serializable {
    //    {
//        "Success": true,
//            "RefundMode": {
//        "Id": 2016021994415777,   订单号
//        "MaxRGDNumber": 0,        最大退货数量
//        "MaxRefundAmount": 0.01,  最大退款金额
//        "OrderItemId": null,      子订单号
//        "RefundType": 1,          售后类型：1 仅退款 2：退款退货
//        "IsRefundOrder": true,    是否为整笔订单退款
//        "OrderStatus": "待发货",  订单状态描述
//        "OrderStatusValue": 2,    订单状态 1：待付款 2：待发货 3：待收货 4：已关闭 5：已完成 6：用户申请取消 7：未评价
//        "ContactPerson": "zltest",联系人
//        "ContactCellPhone": null, 联系电话
//        "BackOut": false          是否支持原路返回
//    }
//    }

    /**
     * Success : true
     * RefundMode : {"Id":2016021994415777,"MaxRGDNumber":0,"MaxRefundAmount":0.01,"OrderItemId":null,"RefundType":1,"IsRefundOrder":true,"OrderStatus":"待发货","OrderStatusValue":2,"ContactPerson":"zltest","ContactCellPhone":null,"BackOut":false}
     */

    private boolean Success;
    private RefundModeBean RefundMode;

    public boolean isSuccess() {
        return Success;
    }

    public void setSuccess(boolean Success) {
        this.Success = Success;
    }

    public RefundModeBean getRefundMode() {
        return RefundMode;
    }

    public void setRefundMode(RefundModeBean RefundMode) {
        this.RefundMode = RefundMode;
    }

    public static class RefundModeBean {
        /**
         * Id : 2016021994415777
         * MaxRGDNumber : 0
         * MaxRefundAmount : 0.01
         * OrderItemId : null
         * RefundType : 1
         * IsRefundOrder : true
         * OrderStatus : 待发货
         * OrderStatusValue : 2
         * ContactPerson : zltest
         * ContactCellPhone : null
         * BackOut : false
         */

        private long Id;
        private int MaxRGDNumber;
        private double MaxRefundAmount;
        private Object OrderItemId;
        private int RefundType;
        private boolean IsRefundOrder;
        private String OrderStatus;
        private int OrderStatusValue;
        private String ContactPerson;
        private Object ContactCellPhone;
        private boolean BackOut;

        public long getId() {
            return Id;
        }

        public void setId(long Id) {
            this.Id = Id;
        }

        public int getMaxRGDNumber() {
            return MaxRGDNumber;
        }

        public void setMaxRGDNumber(int MaxRGDNumber) {
            this.MaxRGDNumber = MaxRGDNumber;
        }

        public double getMaxRefundAmount() {
            return MaxRefundAmount;
        }

        public void setMaxRefundAmount(double MaxRefundAmount) {
            this.MaxRefundAmount = MaxRefundAmount;
        }

        public Object getOrderItemId() {
            return OrderItemId;
        }

        public void setOrderItemId(Object OrderItemId) {
            this.OrderItemId = OrderItemId;
        }

        public int getRefundType() {
            return RefundType;
        }

        public void setRefundType(int RefundType) {
            this.RefundType = RefundType;
        }

        public boolean isIsRefundOrder() {
            return IsRefundOrder;
        }

        public void setIsRefundOrder(boolean IsRefundOrder) {
            this.IsRefundOrder = IsRefundOrder;
        }

        public String getOrderStatus() {
            return OrderStatus;
        }

        public void setOrderStatus(String OrderStatus) {
            this.OrderStatus = OrderStatus;
        }

        public int getOrderStatusValue() {
            return OrderStatusValue;
        }

        public void setOrderStatusValue(int OrderStatusValue) {
            this.OrderStatusValue = OrderStatusValue;
        }

        public String getContactPerson() {
            return ContactPerson;
        }

        public void setContactPerson(String ContactPerson) {
            this.ContactPerson = ContactPerson;
        }

        public Object getContactCellPhone() {
            return ContactCellPhone;
        }

        public void setContactCellPhone(Object ContactCellPhone) {
            this.ContactCellPhone = ContactCellPhone;
        }

        public boolean isBackOut() {
            return BackOut;
        }

        public void setBackOut(boolean BackOut) {
            this.BackOut = BackOut;
        }
    }

}
