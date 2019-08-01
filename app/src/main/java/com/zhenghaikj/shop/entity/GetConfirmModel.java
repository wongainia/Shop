package com.zhenghaikj.shop.entity;

import java.io.Serializable;
import java.util.List;

public class GetConfirmModel implements Serializable {


    /**
     * Address : {"Id":180,"ShipTo":"拜仁","Phone":"18892621501","Address":"天津 天津市 河东区 大直沽街道 12375","RegionId":3991}
     * Success : true
     * InvoiceContext : [{"Id":36,"Name":"个人"},{"Id":47,"Name":"个人"},{"Id":48,"Name":"公司"}]
     * products : [{"shopId":1,"ShopName":"官方自营店","Freight":0,"FreeFreight":11,"CartItemModels":[{"skuId":"756_0_0_0","size":null,"color":null,"version":null,"id":756,"imgUrl":"http://mall.xigyu.com//Storage/Shop/1/Products/756/1_100.png","shopName":null,"name":"Haier/海尔 BC-50ES 50升家用节能小型单门租房宿舍电冰箱","price":18,"count":1,"shopId":1,"vshopId":0,"IsSelf":true,"productCode":"sf","unit":"台","UserCoupons":null,"isDis":false,"ColorAlias":"颜色","SizeAlias":"尺码","VersionAlias":"规格","collpid":0,"IsLimit":false,"ShopBranchId":null,"ShopBranchName":null}],"UserCoupons":[],"FullDiscount":0,"ShopTotal":18,"ShopTotalWithoutFreight":18,"UserBonus":null,"BaseCoupons":null,"OneCoupons":{"BaseId":890,"BasePrice":18,"BaseName":"新用户优惠券","BaseType":0,"BaseShopName":"官方自营店","BaseEndTime":"2019-06-03T00:00:00","BaseShopId":1,"OrderAmount":0},"freightProductGroup":null,"IsFreeFreight":false,"VshopId":10,"IsSelf":true,"ExistShopBranch":false,"ShopBranchId":null,"ShopBranchName":null}]
     * integralPerMoney : 0.0
     * userIntegrals : 0.0
     * TotalAmount : 18.0
     * Freight : 0.0
     * orderAmount : 0.0
     * IsCashOnDelivery : false
     * IsOpenStore : true
     */

    private AddressBean Address;
    private String Success;
    private double integralPerMoney;
    private double userIntegrals;
    private double TotalAmount;
    private double Freight;
    private double orderAmount;
    private boolean IsCashOnDelivery;
    private boolean IsOpenStore;
    private String BisId;
    private List<InvoiceContextBean> InvoiceContext;
    private List<ProductsBean> products;

    public String getBisId() {
        return BisId;
    }

    public void setBisId(String bisId) {
        BisId = bisId;
    }

    public AddressBean getAddress() {
        return Address;
    }

    public void setAddress(AddressBean Address) {
        this.Address = Address;
    }

    public String getSuccess() {
        return Success;
    }

    public void setSuccess(String Success) {
        this.Success = Success;
    }

    public double getIntegralPerMoney() {
        return integralPerMoney;
    }

    public void setIntegralPerMoney(double integralPerMoney) {
        this.integralPerMoney = integralPerMoney;
    }

    public double getUserIntegrals() {
        return userIntegrals;
    }

    public void setUserIntegrals(double userIntegrals) {
        this.userIntegrals = userIntegrals;
    }

    public double getTotalAmount() {
        return TotalAmount;
    }

    public void setTotalAmount(double TotalAmount) {
        this.TotalAmount = TotalAmount;
    }

    public double getFreight() {
        return Freight;
    }

    public void setFreight(double Freight) {
        this.Freight = Freight;
    }

    public double getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(double orderAmount) {
        this.orderAmount = orderAmount;
    }

    public boolean isIsCashOnDelivery() {
        return IsCashOnDelivery;
    }

    public void setIsCashOnDelivery(boolean IsCashOnDelivery) {
        this.IsCashOnDelivery = IsCashOnDelivery;
    }

    public boolean isIsOpenStore() {
        return IsOpenStore;
    }

    public void setIsOpenStore(boolean IsOpenStore) {
        this.IsOpenStore = IsOpenStore;
    }

    public List<InvoiceContextBean> getInvoiceContext() {
        return InvoiceContext;
    }

    public void setInvoiceContext(List<InvoiceContextBean> InvoiceContext) {
        this.InvoiceContext = InvoiceContext;
    }

    public List<ProductsBean> getProducts() {
        return products;
    }

    public void setProducts(List<ProductsBean> products) {
        this.products = products;
    }

    public static class AddressBean {
        /**
         * Id : 180
         * ShipTo : 拜仁
         * Phone : 18892621501
         * Address : 天津 天津市 河东区 大直沽街道 12375
         * RegionId : 3991
         */

        private int Id;
        private String ShipTo;
        private String Phone;
        private String Address;
        private int RegionId;

        public int getId() {
            return Id;
        }

        public void setId(int Id) {
            this.Id = Id;
        }

        public String getShipTo() {
            return ShipTo;
        }

        public void setShipTo(String ShipTo) {
            this.ShipTo = ShipTo;
        }

        public String getPhone() {
            return Phone;
        }

        public void setPhone(String Phone) {
            this.Phone = Phone;
        }

        public String getAddress() {
            return Address;
        }

        public void setAddress(String Address) {
            this.Address = Address;
        }

        public int getRegionId() {
            return RegionId;
        }

        public void setRegionId(int RegionId) {
            this.RegionId = RegionId;
        }
    }

    public static class InvoiceContextBean {
        /**
         * Id : 36
         * Name : 个人
         */

        private int Id;
        private String Name;

        public int getId() {
            return Id;
        }

        public void setId(int Id) {
            this.Id = Id;
        }

        public String getName() {
            return Name;
        }

        public void setName(String Name) {
            this.Name = Name;
        }
    }

    public static class ProductsBean {
        /**
         * shopId : 1
         * ShopName : 官方自营店
         * Freight : 0.0
         * FreeFreight : 11.0
         * CartItemModels : [{"skuId":"756_0_0_0","size":null,"color":null,"version":null,"id":756,"imgUrl":"http://mall.xigyu.com//Storage/Shop/1/Products/756/1_100.png","shopName":null,"name":"Haier/海尔 BC-50ES 50升家用节能小型单门租房宿舍电冰箱","price":18,"count":1,"shopId":1,"vshopId":0,"IsSelf":true,"productCode":"sf","unit":"台","UserCoupons":null,"isDis":false,"ColorAlias":"颜色","SizeAlias":"尺码","VersionAlias":"规格","collpid":0,"IsLimit":false,"ShopBranchId":null,"ShopBranchName":null}]
         * UserCoupons : []
         * FullDiscount : 0.0
         * ShopTotal : 18.0
         * ShopTotalWithoutFreight : 18.0
         * UserBonus : null
         * BaseCoupons : null
         * OneCoupons : {"BaseId":890,"BasePrice":18,"BaseName":"新用户优惠券","BaseType":0,"BaseShopName":"官方自营店","BaseEndTime":"2019-06-03T00:00:00","BaseShopId":1,"OrderAmount":0}
         * freightProductGroup : null
         * IsFreeFreight : false
         * VshopId : 10
         * IsSelf : true
         * ExistShopBranch : false
         * ShopBranchId : null
         * ShopBranchName : null
         */

        private int shopId;
        private String ShopName;
        private double Freight;
        private double FreeFreight;
        private double FullDiscount;
        private double ShopTotal;
        private double ShopTotalWithoutFreight;
        private Object UserBonus;
        private Object BaseCoupons;
        private OneCouponsBean OneCoupons;
        private Object freightProductGroup;
        private boolean IsFreeFreight;
        private int VshopId;
        private boolean IsSelf;
        private boolean ExistShopBranch;
        private Object ShopBranchId;
        private Object ShopBranchName;
        private List<CartItemModelsBean> CartItemModels;
        private List<?> UserCoupons;
        private String ProvideInvoice;

        public String getProvideInvoice() {
            return ProvideInvoice;
        }

        public void setProvideInvoice(String provideInvoice) {
            ProvideInvoice = provideInvoice;
        }

        public int getShopId() {
            return shopId;
        }

        public void setShopId(int shopId) {
            this.shopId = shopId;
        }

        public String getShopName() {
            return ShopName;
        }

        public void setShopName(String ShopName) {
            this.ShopName = ShopName;
        }

        public double getFreight() {
            return Freight;
        }

        public void setFreight(double Freight) {
            this.Freight = Freight;
        }

        public double getFreeFreight() {
            return FreeFreight;
        }

        public void setFreeFreight(double FreeFreight) {
            this.FreeFreight = FreeFreight;
        }

        public double getFullDiscount() {
            return FullDiscount;
        }

        public void setFullDiscount(double FullDiscount) {
            this.FullDiscount = FullDiscount;
        }

        public double getShopTotal() {
            return ShopTotal;
        }

        public void setShopTotal(double ShopTotal) {
            this.ShopTotal = ShopTotal;
        }

        public double getShopTotalWithoutFreight() {
            return ShopTotalWithoutFreight;
        }

        public void setShopTotalWithoutFreight(double ShopTotalWithoutFreight) {
            this.ShopTotalWithoutFreight = ShopTotalWithoutFreight;
        }

        public Object getUserBonus() {
            return UserBonus;
        }

        public void setUserBonus(Object UserBonus) {
            this.UserBonus = UserBonus;
        }

        public Object getBaseCoupons() {
            return BaseCoupons;
        }

        public void setBaseCoupons(Object BaseCoupons) {
            this.BaseCoupons = BaseCoupons;
        }

        public OneCouponsBean getOneCoupons() {
            return OneCoupons;
        }

        public void setOneCoupons(OneCouponsBean OneCoupons) {
            this.OneCoupons = OneCoupons;
        }

        public Object getFreightProductGroup() {
            return freightProductGroup;
        }

        public void setFreightProductGroup(Object freightProductGroup) {
            this.freightProductGroup = freightProductGroup;
        }

        public boolean isIsFreeFreight() {
            return IsFreeFreight;
        }

        public void setIsFreeFreight(boolean IsFreeFreight) {
            this.IsFreeFreight = IsFreeFreight;
        }

        public int getVshopId() {
            return VshopId;
        }

        public void setVshopId(int VshopId) {
            this.VshopId = VshopId;
        }

        public boolean isIsSelf() {
            return IsSelf;
        }

        public void setIsSelf(boolean IsSelf) {
            this.IsSelf = IsSelf;
        }

        public boolean isExistShopBranch() {
            return ExistShopBranch;
        }

        public void setExistShopBranch(boolean ExistShopBranch) {
            this.ExistShopBranch = ExistShopBranch;
        }

        public Object getShopBranchId() {
            return ShopBranchId;
        }

        public void setShopBranchId(Object ShopBranchId) {
            this.ShopBranchId = ShopBranchId;
        }

        public Object getShopBranchName() {
            return ShopBranchName;
        }

        public void setShopBranchName(Object ShopBranchName) {
            this.ShopBranchName = ShopBranchName;
        }

        public List<CartItemModelsBean> getCartItemModels() {
            return CartItemModels;
        }

        public void setCartItemModels(List<CartItemModelsBean> CartItemModels) {
            this.CartItemModels = CartItemModels;
        }

        public List<?> getUserCoupons() {
            return UserCoupons;
        }

        public void setUserCoupons(List<?> UserCoupons) {
            this.UserCoupons = UserCoupons;
        }

        public static class OneCouponsBean {
            /**
             * BaseId : 890
             * BasePrice : 18.0
             * BaseName : 新用户优惠券
             * BaseType : 0
             * BaseShopName : 官方自营店
             * BaseEndTime : 2019-06-03T00:00:00
             * BaseShopId : 1
             * OrderAmount : 0.0
             */

            private int BaseId;
            private double BasePrice;
            private String BaseName;
            private int BaseType;
            private String BaseShopName;
            private String BaseEndTime;
            private int BaseShopId;
            private double OrderAmount;

            public int getBaseId() {
                return BaseId;
            }

            public void setBaseId(int BaseId) {
                this.BaseId = BaseId;
            }

            public double getBasePrice() {
                return BasePrice;
            }

            public void setBasePrice(double BasePrice) {
                this.BasePrice = BasePrice;
            }

            public String getBaseName() {
                return BaseName;
            }

            public void setBaseName(String BaseName) {
                this.BaseName = BaseName;
            }

            public int getBaseType() {
                return BaseType;
            }

            public void setBaseType(int BaseType) {
                this.BaseType = BaseType;
            }

            public String getBaseShopName() {
                return BaseShopName;
            }

            public void setBaseShopName(String BaseShopName) {
                this.BaseShopName = BaseShopName;
            }

            public String getBaseEndTime() {
                return BaseEndTime;
            }

            public void setBaseEndTime(String BaseEndTime) {
                this.BaseEndTime = BaseEndTime;
            }

            public int getBaseShopId() {
                return BaseShopId;
            }

            public void setBaseShopId(int BaseShopId) {
                this.BaseShopId = BaseShopId;
            }

            public double getOrderAmount() {
                return OrderAmount;
            }

            public void setOrderAmount(double OrderAmount) {
                this.OrderAmount = OrderAmount;
            }
        }

        public static class CartItemModelsBean {
            /**
             * skuId : 756_0_0_0
             * size : null
             * color : null
             * version : null
             * id : 756
             * imgUrl : http://mall.xigyu.com//Storage/Shop/1/Products/756/1_100.png
             * shopName : null
             * name : Haier/海尔 BC-50ES 50升家用节能小型单门租房宿舍电冰箱
             * price : 18.0
             * count : 1
             * shopId : 1
             * vshopId : 0
             * IsSelf : true
             * productCode : sf
             * unit : 台
             * UserCoupons : null
             * isDis : false
             * ColorAlias : 颜色
             * SizeAlias : 尺码
             * VersionAlias : 规格
             * collpid : 0
             * IsLimit : false
             * ShopBranchId : null
             * ShopBranchName : null
             */

            private String skuId;
            private String size;
            private String color;
            private String version;
            private String id;
            private String imgUrl;
            private String shopName;
            private String name;
            private String price;
            private String count;
            private String shopId;
            private String vshopId;
            private boolean IsSelf;
            private String productCode;
            private String unit;
            private String UserCoupons;
            private boolean isDis;
            private String ColorAlias;
            private String SizeAlias;
            private String VersionAlias;
            private String collpid;
            private boolean IsLimit;
            private String ShopBranchId;
            private String ShopBranchName;

            public String getSkuId() {
                return skuId;
            }

            public void setSkuId(String skuId) {
                this.skuId = skuId;
            }

            public String getSize() {
                return size;
            }

            public void setSize(String size) {
                this.size = size;
            }

            public String getColor() {
                return color;
            }

            public void setColor(String color) {
                this.color = color;
            }

            public String getVersion() {
                return version;
            }

            public void setVersion(String version) {
                this.version = version;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getImgUrl() {
                return imgUrl;
            }

            public void setImgUrl(String imgUrl) {
                this.imgUrl = imgUrl;
            }

            public Object getShopName() {
                return shopName;
            }

            public void setShopName(String shopName) {
                this.shopName = shopName;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getCount() {
                return count;
            }

            public void setCount(String count) {
                this.count = count;
            }

            public String getShopId() {
                return shopId;
            }

            public void setShopId(String shopId) {
                this.shopId = shopId;
            }

            public String getVshopId() {
                return vshopId;
            }

            public void setVshopId(String vshopId) {
                this.vshopId = vshopId;
            }

            public boolean isIsSelf() {
                return IsSelf;
            }

            public void setIsSelf(boolean IsSelf) {
                this.IsSelf = IsSelf;
            }

            public String getProductCode() {
                return productCode;
            }

            public void setProductCode(String productCode) {
                this.productCode = productCode;
            }

            public String getUnit() {
                return unit;
            }

            public void setUnit(String unit) {
                this.unit = unit;
            }

            public String getUserCoupons() {
                return UserCoupons;
            }

            public void setUserCoupons(String UserCoupons) {
                this.UserCoupons = UserCoupons;
            }

            public boolean isIsDis() {
                return isDis;
            }

            public void setIsDis(boolean isDis) {
                this.isDis = isDis;
            }

            public String getColorAlias() {
                return ColorAlias;
            }

            public void setColorAlias(String ColorAlias) {
                this.ColorAlias = ColorAlias;
            }

            public String getSizeAlias() {
                return SizeAlias;
            }

            public void setSizeAlias(String SizeAlias) {
                this.SizeAlias = SizeAlias;
            }

            public String getVersionAlias() {
                return VersionAlias;
            }

            public void setVersionAlias(String VersionAlias) {
                this.VersionAlias = VersionAlias;
            }

            public String getCollpid() {
                return collpid;
            }

            public void setCollpid(String collpid) {
                this.collpid = collpid;
            }

            public boolean isIsLimit() {
                return IsLimit;
            }

            public void setIsLimit(boolean IsLimit) {
                this.IsLimit = IsLimit;
            }

            public String getShopBranchId() {
                return ShopBranchId;
            }

            public void setShopBranchId(String ShopBranchId) {
                this.ShopBranchId = ShopBranchId;
            }

            public String getShopBranchName() {
                return ShopBranchName;
            }

            public void setShopBranchName(String ShopBranchName) {
                this.ShopBranchName = ShopBranchName;
            }
        }
    }
}
