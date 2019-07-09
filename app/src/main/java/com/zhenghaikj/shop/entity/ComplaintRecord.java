package com.zhenghaikj.shop.entity;

import java.io.Serializable;
import java.util.List;

public class ComplaintRecord implements Serializable {

    private List<ResultBean> result;

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * OrderInfo : {"Id":0,"OrderStatus":0,"OrderDate":"0001-01-01T00:00:00","CloseReason":null,"ShopId":0,"ShopName":null,"SellerPhone":null,"SellerAddress":null,"SellerRemark":null,"UserId":0,"UserName":null,"UserRemark":null,"ShipTo":null,"CellPhone":null,"TopRegionId":0,"RegionId":0,"RegionFullName":null,"Address":null,"ExpressCompanyName":null,"Freight":0,"ShipOrderNumber":null,"ShippingDate":null,"IsPrinted":false,"PaymentTypeName":null,"PaymentTypeGateway":null,"GatewayOrderId":null,"PayRemark":null,"PayDate":null,"InvoiceTitle":null,"InvoiceCode":null,"Tax":0,"FinishDate":null,"ProductTotalAmount":0,"RefundTotalAmount":0,"CommisTotalAmount":0,"RefundCommisAmount":0,"ActiveType":0,"Platform":0,"DiscountAmount":0,"InvoiceType":0,"IntegralDiscount":0,"InvoiceContext":null,"OrderType":null,"PaymentType":0,"ShareUserId":null,"OrderRemarks":null,"LastModifyTime":null,"DeliveryType":0,"ShopBranchId":null,"PickupCode":null,"SellerRemarkFlag":null,"TotalAmount":0,"ActualPayAmount":0,"FullDiscount":0,"CapitalAmount":0,"ReceiveLongitude":0,"ReceiveLatitude":0,"OrderComplaintInfo":[],"OrderItemInfo":[],"OrderOperationLogInfo":[],"OrderCommentInfo":[],"OrderProductQuantity":0,"OrderReturnQuantity":0,"OrderTotalAmount":0,"OrderAmount":0,"ProductTotal":0,"OrderEnabledRefundAmount":0,"CommisAmount":0,"ShopAccountAmount":0,"RefundStats":null,"IsRefundTimeOut":null,"FightGroupOrderJoinStatus":null,"FightGroupCanRefund":null,"ShowExpressCompanyName":null,"ReceiveAddressId":0,"GroupId":0}
         * Id : 32
         * OrderId : 2019070698011415
         * Status : 2
         * ComplaintReason : 就是想退货
         * SellerReply : 就是不给退
         * ComplaintDate : 2019-07-09T11:18:12
         * ShopId : 335
         * ShopName : 蛋炒饭哟
         * ShopPhone :
         * UserId : 716
         * UserName : 18067138219
         * UserPhone : 18067138219
         */

        private OrderInfoBean OrderInfo;
        private int Id;
        private String OrderId;
        private int Status;
        private String ComplaintReason;
        private String SellerReply;
        private String ComplaintDate;
        private int ShopId;
        private String ShopName;
        private String ShopPhone;
        private int UserId;
        private String UserName;
        private String UserPhone;

        public OrderInfoBean getOrderInfo() {
            return OrderInfo;
        }

        public void setOrderInfo(OrderInfoBean OrderInfo) {
            this.OrderInfo = OrderInfo;
        }

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

        public int getStatus() {
            return Status;
        }

        public void setStatus(int Status) {
            this.Status = Status;
        }

        public String getComplaintReason() {
            return ComplaintReason;
        }

        public void setComplaintReason(String ComplaintReason) {
            this.ComplaintReason = ComplaintReason;
        }

        public String getSellerReply() {
            return SellerReply;
        }

        public void setSellerReply(String SellerReply) {
            this.SellerReply = SellerReply;
        }

        public String getComplaintDate() {
            return ComplaintDate;
        }

        public void setComplaintDate(String ComplaintDate) {
            this.ComplaintDate = ComplaintDate;
        }

        public int getShopId() {
            return ShopId;
        }

        public void setShopId(int ShopId) {
            this.ShopId = ShopId;
        }

        public String getShopName() {
            return ShopName;
        }

        public void setShopName(String ShopName) {
            this.ShopName = ShopName;
        }

        public String getShopPhone() {
            return ShopPhone;
        }

        public void setShopPhone(String ShopPhone) {
            this.ShopPhone = ShopPhone;
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

        public String getUserPhone() {
            return UserPhone;
        }

        public void setUserPhone(String UserPhone) {
            this.UserPhone = UserPhone;
        }

        public static class OrderInfoBean {
            /**
             * Id : 0
             * OrderStatus : 0
             * OrderDate : 0001-01-01T00:00:00
             * CloseReason : null
             * ShopId : 0
             * ShopName : null
             * SellerPhone : null
             * SellerAddress : null
             * SellerRemark : null
             * UserId : 0
             * UserName : null
             * UserRemark : null
             * ShipTo : null
             * CellPhone : null
             * TopRegionId : 0
             * RegionId : 0
             * RegionFullName : null
             * Address : null
             * ExpressCompanyName : null
             * Freight : 0
             * ShipOrderNumber : null
             * ShippingDate : null
             * IsPrinted : false
             * PaymentTypeName : null
             * PaymentTypeGateway : null
             * GatewayOrderId : null
             * PayRemark : null
             * PayDate : null
             * InvoiceTitle : null
             * InvoiceCode : null
             * Tax : 0
             * FinishDate : null
             * ProductTotalAmount : 0
             * RefundTotalAmount : 0
             * CommisTotalAmount : 0
             * RefundCommisAmount : 0
             * ActiveType : 0
             * Platform : 0
             * DiscountAmount : 0
             * InvoiceType : 0
             * IntegralDiscount : 0
             * InvoiceContext : null
             * OrderType : null
             * PaymentType : 0
             * ShareUserId : null
             * OrderRemarks : null
             * LastModifyTime : null
             * DeliveryType : 0
             * ShopBranchId : null
             * PickupCode : null
             * SellerRemarkFlag : null
             * TotalAmount : 0
             * ActualPayAmount : 0
             * FullDiscount : 0
             * CapitalAmount : 0
             * ReceiveLongitude : 0
             * ReceiveLatitude : 0
             * OrderComplaintInfo : []
             * OrderItemInfo : []
             * OrderOperationLogInfo : []
             * OrderCommentInfo : []
             * OrderProductQuantity : 0
             * OrderReturnQuantity : 0
             * OrderTotalAmount : 0
             * OrderAmount : 0
             * ProductTotal : 0
             * OrderEnabledRefundAmount : 0
             * CommisAmount : 0
             * ShopAccountAmount : 0
             * RefundStats : null
             * IsRefundTimeOut : null
             * FightGroupOrderJoinStatus : null
             * FightGroupCanRefund : null
             * ShowExpressCompanyName : null
             * ReceiveAddressId : 0
             * GroupId : 0
             */

            private int Id;
            private int OrderStatus;
            private String OrderDate;
            private Object CloseReason;
            private int ShopId;
            private Object ShopName;
            private Object SellerPhone;
            private Object SellerAddress;
            private Object SellerRemark;
            private int UserId;
            private Object UserName;
            private Object UserRemark;
            private Object ShipTo;
            private Object CellPhone;
            private int TopRegionId;
            private int RegionId;
            private Object RegionFullName;
            private Object Address;
            private Object ExpressCompanyName;
            private int Freight;
            private Object ShipOrderNumber;
            private Object ShippingDate;
            private boolean IsPrinted;
            private Object PaymentTypeName;
            private Object PaymentTypeGateway;
            private Object GatewayOrderId;
            private Object PayRemark;
            private Object PayDate;
            private Object InvoiceTitle;
            private Object InvoiceCode;
            private int Tax;
            private Object FinishDate;
            private int ProductTotalAmount;
            private int RefundTotalAmount;
            private int CommisTotalAmount;
            private int RefundCommisAmount;
            private int ActiveType;
            private int Platform;
            private int DiscountAmount;
            private int InvoiceType;
            private int IntegralDiscount;
            private Object InvoiceContext;
            private Object OrderType;
            private int PaymentType;
            private Object ShareUserId;
            private Object OrderRemarks;
            private Object LastModifyTime;
            private int DeliveryType;
            private Object ShopBranchId;
            private Object PickupCode;
            private Object SellerRemarkFlag;
            private int TotalAmount;
            private int ActualPayAmount;
            private int FullDiscount;
            private int CapitalAmount;
            private int ReceiveLongitude;
            private int ReceiveLatitude;
            private int OrderProductQuantity;
            private int OrderReturnQuantity;
            private int OrderTotalAmount;
            private int OrderAmount;
            private int ProductTotal;
            private int OrderEnabledRefundAmount;
            private int CommisAmount;
            private int ShopAccountAmount;
            private Object RefundStats;
            private Object IsRefundTimeOut;
            private Object FightGroupOrderJoinStatus;
            private Object FightGroupCanRefund;
            private Object ShowExpressCompanyName;
            private int ReceiveAddressId;
            private int GroupId;
            private List<?> OrderComplaintInfo;
            private List<?> OrderItemInfo;
            private List<?> OrderOperationLogInfo;
            private List<?> OrderCommentInfo;

            public int getId() {
                return Id;
            }

            public void setId(int Id) {
                this.Id = Id;
            }

            public int getOrderStatus() {
                return OrderStatus;
            }

            public void setOrderStatus(int OrderStatus) {
                this.OrderStatus = OrderStatus;
            }

            public String getOrderDate() {
                return OrderDate;
            }

            public void setOrderDate(String OrderDate) {
                this.OrderDate = OrderDate;
            }

            public Object getCloseReason() {
                return CloseReason;
            }

            public void setCloseReason(Object CloseReason) {
                this.CloseReason = CloseReason;
            }

            public int getShopId() {
                return ShopId;
            }

            public void setShopId(int ShopId) {
                this.ShopId = ShopId;
            }

            public Object getShopName() {
                return ShopName;
            }

            public void setShopName(Object ShopName) {
                this.ShopName = ShopName;
            }

            public Object getSellerPhone() {
                return SellerPhone;
            }

            public void setSellerPhone(Object SellerPhone) {
                this.SellerPhone = SellerPhone;
            }

            public Object getSellerAddress() {
                return SellerAddress;
            }

            public void setSellerAddress(Object SellerAddress) {
                this.SellerAddress = SellerAddress;
            }

            public Object getSellerRemark() {
                return SellerRemark;
            }

            public void setSellerRemark(Object SellerRemark) {
                this.SellerRemark = SellerRemark;
            }

            public int getUserId() {
                return UserId;
            }

            public void setUserId(int UserId) {
                this.UserId = UserId;
            }

            public Object getUserName() {
                return UserName;
            }

            public void setUserName(Object UserName) {
                this.UserName = UserName;
            }

            public Object getUserRemark() {
                return UserRemark;
            }

            public void setUserRemark(Object UserRemark) {
                this.UserRemark = UserRemark;
            }

            public Object getShipTo() {
                return ShipTo;
            }

            public void setShipTo(Object ShipTo) {
                this.ShipTo = ShipTo;
            }

            public Object getCellPhone() {
                return CellPhone;
            }

            public void setCellPhone(Object CellPhone) {
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

            public Object getRegionFullName() {
                return RegionFullName;
            }

            public void setRegionFullName(Object RegionFullName) {
                this.RegionFullName = RegionFullName;
            }

            public Object getAddress() {
                return Address;
            }

            public void setAddress(Object Address) {
                this.Address = Address;
            }

            public Object getExpressCompanyName() {
                return ExpressCompanyName;
            }

            public void setExpressCompanyName(Object ExpressCompanyName) {
                this.ExpressCompanyName = ExpressCompanyName;
            }

            public int getFreight() {
                return Freight;
            }

            public void setFreight(int Freight) {
                this.Freight = Freight;
            }

            public Object getShipOrderNumber() {
                return ShipOrderNumber;
            }

            public void setShipOrderNumber(Object ShipOrderNumber) {
                this.ShipOrderNumber = ShipOrderNumber;
            }

            public Object getShippingDate() {
                return ShippingDate;
            }

            public void setShippingDate(Object ShippingDate) {
                this.ShippingDate = ShippingDate;
            }

            public boolean isIsPrinted() {
                return IsPrinted;
            }

            public void setIsPrinted(boolean IsPrinted) {
                this.IsPrinted = IsPrinted;
            }

            public Object getPaymentTypeName() {
                return PaymentTypeName;
            }

            public void setPaymentTypeName(Object PaymentTypeName) {
                this.PaymentTypeName = PaymentTypeName;
            }

            public Object getPaymentTypeGateway() {
                return PaymentTypeGateway;
            }

            public void setPaymentTypeGateway(Object PaymentTypeGateway) {
                this.PaymentTypeGateway = PaymentTypeGateway;
            }

            public Object getGatewayOrderId() {
                return GatewayOrderId;
            }

            public void setGatewayOrderId(Object GatewayOrderId) {
                this.GatewayOrderId = GatewayOrderId;
            }

            public Object getPayRemark() {
                return PayRemark;
            }

            public void setPayRemark(Object PayRemark) {
                this.PayRemark = PayRemark;
            }

            public Object getPayDate() {
                return PayDate;
            }

            public void setPayDate(Object PayDate) {
                this.PayDate = PayDate;
            }

            public Object getInvoiceTitle() {
                return InvoiceTitle;
            }

            public void setInvoiceTitle(Object InvoiceTitle) {
                this.InvoiceTitle = InvoiceTitle;
            }

            public Object getInvoiceCode() {
                return InvoiceCode;
            }

            public void setInvoiceCode(Object InvoiceCode) {
                this.InvoiceCode = InvoiceCode;
            }

            public int getTax() {
                return Tax;
            }

            public void setTax(int Tax) {
                this.Tax = Tax;
            }

            public Object getFinishDate() {
                return FinishDate;
            }

            public void setFinishDate(Object FinishDate) {
                this.FinishDate = FinishDate;
            }

            public int getProductTotalAmount() {
                return ProductTotalAmount;
            }

            public void setProductTotalAmount(int ProductTotalAmount) {
                this.ProductTotalAmount = ProductTotalAmount;
            }

            public int getRefundTotalAmount() {
                return RefundTotalAmount;
            }

            public void setRefundTotalAmount(int RefundTotalAmount) {
                this.RefundTotalAmount = RefundTotalAmount;
            }

            public int getCommisTotalAmount() {
                return CommisTotalAmount;
            }

            public void setCommisTotalAmount(int CommisTotalAmount) {
                this.CommisTotalAmount = CommisTotalAmount;
            }

            public int getRefundCommisAmount() {
                return RefundCommisAmount;
            }

            public void setRefundCommisAmount(int RefundCommisAmount) {
                this.RefundCommisAmount = RefundCommisAmount;
            }

            public int getActiveType() {
                return ActiveType;
            }

            public void setActiveType(int ActiveType) {
                this.ActiveType = ActiveType;
            }

            public int getPlatform() {
                return Platform;
            }

            public void setPlatform(int Platform) {
                this.Platform = Platform;
            }

            public int getDiscountAmount() {
                return DiscountAmount;
            }

            public void setDiscountAmount(int DiscountAmount) {
                this.DiscountAmount = DiscountAmount;
            }

            public int getInvoiceType() {
                return InvoiceType;
            }

            public void setInvoiceType(int InvoiceType) {
                this.InvoiceType = InvoiceType;
            }

            public int getIntegralDiscount() {
                return IntegralDiscount;
            }

            public void setIntegralDiscount(int IntegralDiscount) {
                this.IntegralDiscount = IntegralDiscount;
            }

            public Object getInvoiceContext() {
                return InvoiceContext;
            }

            public void setInvoiceContext(Object InvoiceContext) {
                this.InvoiceContext = InvoiceContext;
            }

            public Object getOrderType() {
                return OrderType;
            }

            public void setOrderType(Object OrderType) {
                this.OrderType = OrderType;
            }

            public int getPaymentType() {
                return PaymentType;
            }

            public void setPaymentType(int PaymentType) {
                this.PaymentType = PaymentType;
            }

            public Object getShareUserId() {
                return ShareUserId;
            }

            public void setShareUserId(Object ShareUserId) {
                this.ShareUserId = ShareUserId;
            }

            public Object getOrderRemarks() {
                return OrderRemarks;
            }

            public void setOrderRemarks(Object OrderRemarks) {
                this.OrderRemarks = OrderRemarks;
            }

            public Object getLastModifyTime() {
                return LastModifyTime;
            }

            public void setLastModifyTime(Object LastModifyTime) {
                this.LastModifyTime = LastModifyTime;
            }

            public int getDeliveryType() {
                return DeliveryType;
            }

            public void setDeliveryType(int DeliveryType) {
                this.DeliveryType = DeliveryType;
            }

            public Object getShopBranchId() {
                return ShopBranchId;
            }

            public void setShopBranchId(Object ShopBranchId) {
                this.ShopBranchId = ShopBranchId;
            }

            public Object getPickupCode() {
                return PickupCode;
            }

            public void setPickupCode(Object PickupCode) {
                this.PickupCode = PickupCode;
            }

            public Object getSellerRemarkFlag() {
                return SellerRemarkFlag;
            }

            public void setSellerRemarkFlag(Object SellerRemarkFlag) {
                this.SellerRemarkFlag = SellerRemarkFlag;
            }

            public int getTotalAmount() {
                return TotalAmount;
            }

            public void setTotalAmount(int TotalAmount) {
                this.TotalAmount = TotalAmount;
            }

            public int getActualPayAmount() {
                return ActualPayAmount;
            }

            public void setActualPayAmount(int ActualPayAmount) {
                this.ActualPayAmount = ActualPayAmount;
            }

            public int getFullDiscount() {
                return FullDiscount;
            }

            public void setFullDiscount(int FullDiscount) {
                this.FullDiscount = FullDiscount;
            }

            public int getCapitalAmount() {
                return CapitalAmount;
            }

            public void setCapitalAmount(int CapitalAmount) {
                this.CapitalAmount = CapitalAmount;
            }

            public int getReceiveLongitude() {
                return ReceiveLongitude;
            }

            public void setReceiveLongitude(int ReceiveLongitude) {
                this.ReceiveLongitude = ReceiveLongitude;
            }

            public int getReceiveLatitude() {
                return ReceiveLatitude;
            }

            public void setReceiveLatitude(int ReceiveLatitude) {
                this.ReceiveLatitude = ReceiveLatitude;
            }

            public int getOrderProductQuantity() {
                return OrderProductQuantity;
            }

            public void setOrderProductQuantity(int OrderProductQuantity) {
                this.OrderProductQuantity = OrderProductQuantity;
            }

            public int getOrderReturnQuantity() {
                return OrderReturnQuantity;
            }

            public void setOrderReturnQuantity(int OrderReturnQuantity) {
                this.OrderReturnQuantity = OrderReturnQuantity;
            }

            public int getOrderTotalAmount() {
                return OrderTotalAmount;
            }

            public void setOrderTotalAmount(int OrderTotalAmount) {
                this.OrderTotalAmount = OrderTotalAmount;
            }

            public int getOrderAmount() {
                return OrderAmount;
            }

            public void setOrderAmount(int OrderAmount) {
                this.OrderAmount = OrderAmount;
            }

            public int getProductTotal() {
                return ProductTotal;
            }

            public void setProductTotal(int ProductTotal) {
                this.ProductTotal = ProductTotal;
            }

            public int getOrderEnabledRefundAmount() {
                return OrderEnabledRefundAmount;
            }

            public void setOrderEnabledRefundAmount(int OrderEnabledRefundAmount) {
                this.OrderEnabledRefundAmount = OrderEnabledRefundAmount;
            }

            public int getCommisAmount() {
                return CommisAmount;
            }

            public void setCommisAmount(int CommisAmount) {
                this.CommisAmount = CommisAmount;
            }

            public int getShopAccountAmount() {
                return ShopAccountAmount;
            }

            public void setShopAccountAmount(int ShopAccountAmount) {
                this.ShopAccountAmount = ShopAccountAmount;
            }

            public Object getRefundStats() {
                return RefundStats;
            }

            public void setRefundStats(Object RefundStats) {
                this.RefundStats = RefundStats;
            }

            public Object getIsRefundTimeOut() {
                return IsRefundTimeOut;
            }

            public void setIsRefundTimeOut(Object IsRefundTimeOut) {
                this.IsRefundTimeOut = IsRefundTimeOut;
            }

            public Object getFightGroupOrderJoinStatus() {
                return FightGroupOrderJoinStatus;
            }

            public void setFightGroupOrderJoinStatus(Object FightGroupOrderJoinStatus) {
                this.FightGroupOrderJoinStatus = FightGroupOrderJoinStatus;
            }

            public Object getFightGroupCanRefund() {
                return FightGroupCanRefund;
            }

            public void setFightGroupCanRefund(Object FightGroupCanRefund) {
                this.FightGroupCanRefund = FightGroupCanRefund;
            }

            public Object getShowExpressCompanyName() {
                return ShowExpressCompanyName;
            }

            public void setShowExpressCompanyName(Object ShowExpressCompanyName) {
                this.ShowExpressCompanyName = ShowExpressCompanyName;
            }

            public int getReceiveAddressId() {
                return ReceiveAddressId;
            }

            public void setReceiveAddressId(int ReceiveAddressId) {
                this.ReceiveAddressId = ReceiveAddressId;
            }

            public int getGroupId() {
                return GroupId;
            }

            public void setGroupId(int GroupId) {
                this.GroupId = GroupId;
            }

            public List<?> getOrderComplaintInfo() {
                return OrderComplaintInfo;
            }

            public void setOrderComplaintInfo(List<?> OrderComplaintInfo) {
                this.OrderComplaintInfo = OrderComplaintInfo;
            }

            public List<?> getOrderItemInfo() {
                return OrderItemInfo;
            }

            public void setOrderItemInfo(List<?> OrderItemInfo) {
                this.OrderItemInfo = OrderItemInfo;
            }

            public List<?> getOrderOperationLogInfo() {
                return OrderOperationLogInfo;
            }

            public void setOrderOperationLogInfo(List<?> OrderOperationLogInfo) {
                this.OrderOperationLogInfo = OrderOperationLogInfo;
            }

            public List<?> getOrderCommentInfo() {
                return OrderCommentInfo;
            }

            public void setOrderCommentInfo(List<?> OrderCommentInfo) {
                this.OrderCommentInfo = OrderCommentInfo;
            }
        }
    }
}
