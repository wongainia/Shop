package com.zhenghaikj.shop.entity;

import java.io.Serializable;
import java.util.List;

public class OrderDetail implements Serializable {
    /*
    * "Success":"true",
    * "Order":{"Id":2017021483156396,"OrderType":null,"OrderTypeName":"","Status":"待消费","JoinStatus":-2,"ShipTo":"小蘑菇","Phone":"18216367122","Address":"湖南省 长沙市 芙蓉区 文艺路街道 湖南大剧院19楼","HasExpressStatus":false,"ExpressCompanyName":null,"Freight":0.00,"IntegralDiscount":0.00,"RealTotalAmount":11.90,"RefundTotalAmount":0.00,"OrderDate":"2017-02-14 10:32:08","ShopName":"官方自营店","VShopId":10,"commentCount":0,"ShopId":1,"orderStatus":2,"Invoice":"不需要发票","InvoiceValue":0,"InvoiceContext":null,"InvoiceTitle":null,"PaymentType":"线上支付","PaymentTypeValue":1,"FullDiscount":0.00,"DiscountAmount":0.00,"OrderRemarks":"","HasBonus":false,"ShareHref":"","ShareTitle":"","ShareDetail":"","IsCanRefund":true,"EnabledRefundAmount":11.90,"HasAppendComment":false,"SelfTake":0},
    * "OrderItem":[{"ItemId":1086,"ProductId":702,"ProductName":"卫龙 休闲零食 辣条 小面筋 办公室休闲食品 22g*20包(新老包装随机发货)","Count":1,"Price":11.90,"ProductImage":"http://47.96.126.145:8830//Storage/Shop/1/Products/702/1_100.png","color":null,"size":null,"version":null,"IsCanRefund":true,"ColorAlias":"颜色","SizeAlias":"尺码","VersionAlias":"规格","EnabledRefundAmount":11.90}],
    * "StoreInfo":{"ShopName":null,"ShopBranchInTagNames":null,"ShopBranchTagId":null,"Id":26,"ShopId":1,"ShopBranchName":"辣条君","AddressId":27073,"AddressDetail":"湖南省长沙市韶山北路139号湖南文化大厦","AddressFullName":"湖南省长沙市芙蓉区文艺路街道韶山北路139号湖南文化大厦","ContactUser":"李广田","ContactPhone":"13306589754","Status":0,"CreateDate":"0001-01-01T00:00:00","UserName":"admin","PasswordOne":null,"PasswordTwo":null,"RegionIdPath":null,"ServeRadius":0,"Longitude":0.0,"Latitude":0.0,"ShopImages":null,"Distance":0.0,"DistanceUnit":null,"Enabled":false,"AddressPath":"1812,1813,1814,27073,","IsStoreDelive":false,"IsAboveSelf":false,"DeliveFee":0,"FreeMailFee":0,"DeliveTotalFee":0,"StoreOpenStartTime":"08:00:00","StoreOpenEndTime":"20:00:00","RecommendSequence":0,"IsRecommend":false}
    * "CustomerServices":[]}
    * */

    private boolean Success;
    private OrderBean Order;
    private StoreInfoBean StoreInfo;
    private List<CustomerServicesBean> CustomerServices;
    private List<OrderItemBean> OrderItem;

    public boolean isSuccess() {
        return Success;
    }

    public void setSuccess(boolean success) {
        Success = success;
    }

    public OrderBean getOrder() {
        return Order;
    }

    public void setOrder(OrderBean order) {
        Order = order;
    }

    public StoreInfoBean getStoreInfo() {
        return StoreInfo;
    }

    public void setStoreInfo(StoreInfoBean storeInfo) {
        StoreInfo = storeInfo;
    }

    public List<CustomerServicesBean> getCustomerServices() {
        return CustomerServices;
    }

    public void setCustomerServices(List<CustomerServicesBean> customerServices) {
        CustomerServices = customerServices;
    }

    public List<OrderItemBean> getOrderItem() {
        return OrderItem;
    }

    public void setOrderItem(List<OrderItemBean> orderItem) {
        OrderItem = orderItem;
    }

    /*
    * "Id":2017021483156396,
    * "OrderType":null,
    * "OrderTypeName":"",
    * "Status":"待消费",
    * "JoinStatus":-2,
    * "ShipTo":"小蘑菇",
    * "Phone":"18216367122",
    * "Address":"湖南省 长沙市 芙蓉区 文艺路街道 湖南大剧院19楼",
    * "HasExpressStatus":false,
    * "ExpressCompanyName":null,
    * "Freight":0.00,
    * "IntegralDiscount":0.00,  整体折扣
    * "RealTotalAmount":11.90,   实际金额
    * "RefundTotalAmount":0.00,   退款总金额
    * "OrderDate":"2017-02-14 10:32:08",
    * "ShopName":"官方自营店",
    * "VShopId":10,
    * "commentCount":0,
    * "ShopId":1,
    * "orderStatus":2,
    * "Invoice":"不需要发票",
    * "InvoiceValue":0,
    * "InvoiceContext":null,
    * "InvoiceTitle":null,
    * "PaymentType":"线上支付",
    * "PaymentTypeValue":1,
    * "FullDiscount":0.00,  全额折扣
    * "DiscountAmount":0.00,  折扣金额
    * "OrderRemarks":"",   订单备注
    * "HasBonus":false,
    * "ShareHref":"",
    * "ShareTitle":"",
    * "ShareDetail":"",
    * "IsCanRefund":true,
    * "EnabledRefundAmount":11.90,  退款金额
    * "HasAppendComment":false,
    * "SelfTake":0},
    * */
    public static class OrderBean{
        private String Id;
        private String OrderType;
        private String OrderTypeName;
        private String Status;
        private String JoinStatus;
        private String ShipTo;
        private String Phone;
        private String Address;
        private boolean HasExpressStatus;
        private String ExpressCompanyName;
        private String Freight;
        private String IntegralDiscount;
        private String RealTotalAmount;
        private String RefundTotalAmount;
        private String OrderDate;
        private String ShopName;
        private String VShopId;
        private String commentCount;
        private String ShopId;
        private String orderStatus;
        private String Invoice;
        private String InvoiceValue;
        private String InvoiceContext;
        private String InvoiceTitle;
        private String PaymentType;
        private String PaymentTypeValue;
        private String FullDiscount;
        private String DiscountAmount;
        private String OrderRemarks;
        private boolean HasBonus;
        private String ShareHref;
        private String ShareTitle;
        private String ShareDetail;
        private boolean IsCanRefund;
        private String EnabledRefundAmount;
        private boolean HasAppendComment;
        private String SelfTake;

        public String getId() {
            return Id;
        }

        public void setId(String id) {
            Id = id;
        }

        public String getOrderType() {
            return OrderType;
        }

        public void setOrderType(String orderType) {
            OrderType = orderType;
        }

        public String getOrderTypeName() {
            return OrderTypeName;
        }

        public void setOrderTypeName(String orderTypeName) {
            OrderTypeName = orderTypeName;
        }

        public String getStatus() {
            return Status;
        }

        public void setStatus(String status) {
            Status = status;
        }

        public String getJoinStatus() {
            return JoinStatus;
        }

        public void setJoinStatus(String joinStatus) {
            JoinStatus = joinStatus;
        }

        public String getShipTo() {
            return ShipTo;
        }

        public void setShipTo(String shipTo) {
            ShipTo = shipTo;
        }

        public String getPhone() {
            return Phone;
        }

        public void setPhone(String phone) {
            Phone = phone;
        }

        public String getAddress() {
            return Address;
        }

        public void setAddress(String address) {
            Address = address;
        }

        public boolean isHasExpressStatus() {
            return HasExpressStatus;
        }

        public void setHasExpressStatus(boolean hasExpressStatus) {
            HasExpressStatus = hasExpressStatus;
        }

        public boolean isHasBonus() {
            return HasBonus;
        }

        public void setHasBonus(boolean hasBonus) {
            HasBonus = hasBonus;
        }

        public boolean isCanRefund() {
            return IsCanRefund;
        }

        public void setCanRefund(boolean canRefund) {
            IsCanRefund = canRefund;
        }

        public boolean isHasAppendComment() {
            return HasAppendComment;
        }

        public void setHasAppendComment(boolean hasAppendComment) {
            HasAppendComment = hasAppendComment;
        }

        public String getExpressCompanyName() {
            return ExpressCompanyName;
        }

        public void setExpressCompanyName(String expressCompanyName) {
            ExpressCompanyName = expressCompanyName;
        }

        public String getFreight() {
            return Freight;
        }

        public void setFreight(String freight) {
            Freight = freight;
        }

        public String getIntegralDiscount() {
            return IntegralDiscount;
        }

        public void setIntegralDiscount(String integralDiscount) {
            IntegralDiscount = integralDiscount;
        }

        public String getRealTotalAmount() {
            return RealTotalAmount;
        }

        public void setRealTotalAmount(String realTotalAmount) {
            RealTotalAmount = realTotalAmount;
        }

        public String getRefundTotalAmount() {
            return RefundTotalAmount;
        }

        public void setRefundTotalAmount(String refundTotalAmount) {
            RefundTotalAmount = refundTotalAmount;
        }

        public String getOrderDate() {
            return OrderDate;
        }

        public void setOrderDate(String orderDate) {
            OrderDate = orderDate;
        }

        public String getShopName() {
            return ShopName;
        }

        public void setShopName(String shopName) {
            ShopName = shopName;
        }

        public String getVShopId() {
            return VShopId;
        }

        public void setVShopId(String VShopId) {
            this.VShopId = VShopId;
        }

        public String getCommentCount() {
            return commentCount;
        }

        public void setCommentCount(String commentCount) {
            this.commentCount = commentCount;
        }

        public String getShopId() {
            return ShopId;
        }

        public void setShopId(String shopId) {
            ShopId = shopId;
        }

        public String getOrderStatus() {
            return orderStatus;
        }

        public void setOrderStatus(String orderStatus) {
            this.orderStatus = orderStatus;
        }

        public String getInvoice() {
            return Invoice;
        }

        public void setInvoice(String invoice) {
            Invoice = invoice;
        }

        public String getInvoiceValue() {
            return InvoiceValue;
        }

        public void setInvoiceValue(String invoiceValue) {
            InvoiceValue = invoiceValue;
        }

        public String getInvoiceContext() {
            return InvoiceContext;
        }

        public void setInvoiceContext(String invoiceContext) {
            InvoiceContext = invoiceContext;
        }

        public String getInvoiceTitle() {
            return InvoiceTitle;
        }

        public void setInvoiceTitle(String invoiceTitle) {
            InvoiceTitle = invoiceTitle;
        }

        public String getPaymentType() {
            return PaymentType;
        }

        public void setPaymentType(String paymentType) {
            PaymentType = paymentType;
        }

        public String getPaymentTypeValue() {
            return PaymentTypeValue;
        }

        public void setPaymentTypeValue(String paymentTypeValue) {
            PaymentTypeValue = paymentTypeValue;
        }

        public String getFullDiscount() {
            return FullDiscount;
        }

        public void setFullDiscount(String fullDiscount) {
            FullDiscount = fullDiscount;
        }

        public String getDiscountAmount() {
            return DiscountAmount;
        }

        public void setDiscountAmount(String discountAmount) {
            DiscountAmount = discountAmount;
        }

        public String getOrderRemarks() {
            return OrderRemarks;
        }

        public void setOrderRemarks(String orderRemarks) {
            OrderRemarks = orderRemarks;
        }



        public String getShareHref() {
            return ShareHref;
        }

        public void setShareHref(String shareHref) {
            ShareHref = shareHref;
        }

        public String getShareTitle() {
            return ShareTitle;
        }

        public void setShareTitle(String shareTitle) {
            ShareTitle = shareTitle;
        }

        public String getShareDetail() {
            return ShareDetail;
        }

        public void setShareDetail(String shareDetail) {
            ShareDetail = shareDetail;
        }


        public String getEnabledRefundAmount() {
            return EnabledRefundAmount;
        }

        public void setEnabledRefundAmount(String enabledRefundAmount) {
            EnabledRefundAmount = enabledRefundAmount;
        }



        public String getSelfTake() {
            return SelfTake;
        }

        public void setSelfTake(String selfTake) {
            SelfTake = selfTake;
        }
    }


    /*
    * "ItemId":1086,
    * "ProductId":702,
    * "ProductName":"卫龙 休闲零食 辣条 小面筋 办公室休闲食品 22g*20包(新老包装随机发货)",
    * "Count":1,
    * "Price":11.90,
    * "ProductImage":"http://47.96.126.145:8830//Storage/Shop/1/Products/702/1_100.png",
    * "color":null,
    * "size":null,
    * "version":null,
    * "IsCanRefund":true,
    * "ColorAlias":"颜色",
    * "SizeAlias":"尺码",
    * "VersionAlias":"规格",
    * "EnabledRefundAmount":11.90
    * */

    public static class OrderItemBean{
        private String ItemId;
        private String ProductId;
        private String ProductName;
        private String Count;
        private String Price;
        private String ProductImage;
        private String color;
        private String size;
        private String version;
        private String IsCanRefund;
        private String ColorAlias;
        private String SizeAlias;
        private String VersionAlias;
        private String EnabledRefundAmount;

        public String getItemId() {
            return ItemId;
        }

        public void setItemId(String itemId) {
            ItemId = itemId;
        }

        public String getProductId() {
            return ProductId;
        }

        public void setProductId(String productId) {
            ProductId = productId;
        }

        public String getProductName() {
            return ProductName;
        }

        public void setProductName(String productName) {
            ProductName = productName;
        }

        public String getCount() {
            return Count;
        }

        public void setCount(String count) {
            Count = count;
        }

        public String getPrice() {
            return Price;
        }

        public void setPrice(String price) {
            Price = price;
        }

        public String getProductImage() {
            return ProductImage;
        }

        public void setProductImage(String productImage) {
            ProductImage = productImage;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public String getSize() {
            return size;
        }

        public void setSize(String size) {
            this.size = size;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public String getIsCanRefund() {
            return IsCanRefund;
        }

        public void setIsCanRefund(String isCanRefund) {
            IsCanRefund = isCanRefund;
        }

        public String getColorAlias() {
            return ColorAlias;
        }

        public void setColorAlias(String colorAlias) {
            ColorAlias = colorAlias;
        }

        public String getSizeAlias() {
            return SizeAlias;
        }

        public void setSizeAlias(String sizeAlias) {
            SizeAlias = sizeAlias;
        }

        public String getVersionAlias() {
            return VersionAlias;
        }

        public void setVersionAlias(String versionAlias) {
            VersionAlias = versionAlias;
        }

        public String getEnabledRefundAmount() {
            return EnabledRefundAmount;
        }

        public void setEnabledRefundAmount(String enabledRefundAmount) {
            EnabledRefundAmount = enabledRefundAmount;
        }
    }

    /*
    * "ShopName":null,
    * "ShopBranchInTagNames":null,
    * "ShopBranchTagId":null,
    * "Id":26,
    * "ShopId":1,
    * "ShopBranchName":"辣条君",
    * "AddressId":27073,
    * "AddressDetail":"湖南省长沙市韶山北路139号湖南文化大厦",
    * "AddressFullName":"湖南省长沙市芙蓉区文艺路街道韶山北路139号湖南文化大厦",
    * "ContactUser":"李广田",
    * "ContactPhone":"13306589754",
    * "Status":0,
    * "CreateDate":"0001-01-01T00:00:00",
    * "UserName":"admin",
    * "PasswordOne":null,
    * "PasswordTwo":null,
    * "RegionIdPath":null,
    * "ServeRadius":0,
    * "Longitude":0.0,
    * "Latitude":0.0,
    * "ShopImages":null,
    * "Distance":0.0,
    * "DistanceUnit":null,
    * "Enabled":false,
    * "AddressPath":"1812,1813,1814,27073,",
    * "IsStoreDelive":false,
    * "IsAboveSelf":false,
    * "DeliveFee":0,
    * "FreeMailFee":0,
    * "DeliveTotalFee":0,
    * "StoreOpenStartTime":"08:00:00",
    * "StoreOpenEndTime":"20:00:00",
    * "RecommendSequence":0,
    * "IsRecommend":false}
    * */

    public static class StoreInfoBean{
        private String ShopName;
        private String ShopBranchInTagNames;
        private String ShopBranchTagId;
        private String Id;
        private String ShopId;
        private String ShopBranchName;
        private String AddressId;
        private String AddressDetail;
        private String AddressFullName;
        private String ContactUser;
        private String ContactPhone;
        private String Status;
        private String CreateDate;
        private String UserName;
        private String PasswordOne;
        private String PasswordTwo;
        private String RegionIdPath;
        private String ServeRadius;
        private String Longitude;
        private String Latitude;
        private String ShopImages;
        private String Distance;
        private String DistanceUnit;
        private String Enabled;
        private String AddressPath;
        private String IsStoreDelive;
        private String IsAboveSelf;
        private String DeliveFee;
        private String FreeMailFee;
        private String DeliveTotalFee;
        private String StoreOpenStartTime;
        private String StoreOpenEndTime;
        private String RecommendSequence;
        private String IsRecommend;

        public String getShopName() {
            return ShopName;
        }

        public void setShopName(String shopName) {
            ShopName = shopName;
        }

        public String getShopBranchInTagNames() {
            return ShopBranchInTagNames;
        }

        public void setShopBranchInTagNames(String shopBranchInTagNames) {
            ShopBranchInTagNames = shopBranchInTagNames;
        }

        public String getShopBranchTagId() {
            return ShopBranchTagId;
        }

        public void setShopBranchTagId(String shopBranchTagId) {
            ShopBranchTagId = shopBranchTagId;
        }

        public String getId() {
            return Id;
        }

        public void setId(String id) {
            Id = id;
        }

        public String getShopId() {
            return ShopId;
        }

        public void setShopId(String shopId) {
            ShopId = shopId;
        }

        public String getShopBranchName() {
            return ShopBranchName;
        }

        public void setShopBranchName(String shopBranchName) {
            ShopBranchName = shopBranchName;
        }

        public String getAddressId() {
            return AddressId;
        }

        public void setAddressId(String addressId) {
            AddressId = addressId;
        }

        public String getAddressDetail() {
            return AddressDetail;
        }

        public void setAddressDetail(String addressDetail) {
            AddressDetail = addressDetail;
        }

        public String getAddressFullName() {
            return AddressFullName;
        }

        public void setAddressFullName(String addressFullName) {
            AddressFullName = addressFullName;
        }

        public String getContactUser() {
            return ContactUser;
        }

        public void setContactUser(String contactUser) {
            ContactUser = contactUser;
        }

        public String getContactPhone() {
            return ContactPhone;
        }

        public void setContactPhone(String contactPhone) {
            ContactPhone = contactPhone;
        }

        public String getStatus() {
            return Status;
        }

        public void setStatus(String status) {
            Status = status;
        }

        public String getCreateDate() {
            return CreateDate;
        }

        public void setCreateDate(String createDate) {
            CreateDate = createDate;
        }

        public String getUserName() {
            return UserName;
        }

        public void setUserName(String userName) {
            UserName = userName;
        }

        public String getPasswordOne() {
            return PasswordOne;
        }

        public void setPasswordOne(String passwordOne) {
            PasswordOne = passwordOne;
        }

        public String getPasswordTwo() {
            return PasswordTwo;
        }

        public void setPasswordTwo(String passwordTwo) {
            PasswordTwo = passwordTwo;
        }

        public String getRegionIdPath() {
            return RegionIdPath;
        }

        public void setRegionIdPath(String regionIdPath) {
            RegionIdPath = regionIdPath;
        }

        public String getServeRadius() {
            return ServeRadius;
        }

        public void setServeRadius(String serveRadius) {
            ServeRadius = serveRadius;
        }

        public String getLongitude() {
            return Longitude;
        }

        public void setLongitude(String longitude) {
            Longitude = longitude;
        }

        public String getLatitude() {
            return Latitude;
        }

        public void setLatitude(String latitude) {
            Latitude = latitude;
        }

        public String getShopImages() {
            return ShopImages;
        }

        public void setShopImages(String shopImages) {
            ShopImages = shopImages;
        }

        public String getDistance() {
            return Distance;
        }

        public void setDistance(String distance) {
            Distance = distance;
        }

        public String getDistanceUnit() {
            return DistanceUnit;
        }

        public void setDistanceUnit(String distanceUnit) {
            DistanceUnit = distanceUnit;
        }

        public String getEnabled() {
            return Enabled;
        }

        public void setEnabled(String enabled) {
            Enabled = enabled;
        }

        public String getAddressPath() {
            return AddressPath;
        }

        public void setAddressPath(String addressPath) {
            AddressPath = addressPath;
        }

        public String getIsStoreDelive() {
            return IsStoreDelive;
        }

        public void setIsStoreDelive(String isStoreDelive) {
            IsStoreDelive = isStoreDelive;
        }

        public String getIsAboveSelf() {
            return IsAboveSelf;
        }

        public void setIsAboveSelf(String isAboveSelf) {
            IsAboveSelf = isAboveSelf;
        }

        public String getDeliveFee() {
            return DeliveFee;
        }

        public void setDeliveFee(String deliveFee) {
            DeliveFee = deliveFee;
        }

        public String getFreeMailFee() {
            return FreeMailFee;
        }

        public void setFreeMailFee(String freeMailFee) {
            FreeMailFee = freeMailFee;
        }

        public String getDeliveTotalFee() {
            return DeliveTotalFee;
        }

        public void setDeliveTotalFee(String deliveTotalFee) {
            DeliveTotalFee = deliveTotalFee;
        }

        public String getStoreOpenStartTime() {
            return StoreOpenStartTime;
        }

        public void setStoreOpenStartTime(String storeOpenStartTime) {
            StoreOpenStartTime = storeOpenStartTime;
        }

        public String getStoreOpenEndTime() {
            return StoreOpenEndTime;
        }

        public void setStoreOpenEndTime(String storeOpenEndTime) {
            StoreOpenEndTime = storeOpenEndTime;
        }

        public String getRecommendSequence() {
            return RecommendSequence;
        }

        public void setRecommendSequence(String recommendSequence) {
            RecommendSequence = recommendSequence;
        }

        public String getIsRecommend() {
            return IsRecommend;
        }

        public void setIsRecommend(String isRecommend) {
            IsRecommend = isRecommend;
        }
    }


    public static class CustomerServicesBean{

    }
}
