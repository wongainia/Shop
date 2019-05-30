package com.zhenghaikj.shop.entity;

import java.io.Serializable;
import java.util.List;

public class StoreDetailResult implements Serializable {


    /**
     * Success : True
     * VShop : {"Id":10,"Logo":"http://mall.xigyu.com//Storage/Shop/1/VShop/201904271426408530240.jpg","Name":"官方自营店","ShopId":1,"Favorite":false,"State":2,"FollowUrl":""}
     * SlideImgs : [{"ImageUrl":"http://mall.xigyu.com//Storage/Shop/1/VShop/201905301356453237790.jpg","Url":"暂无"},{"ImageUrl":"http://mall.xigyu.com//Storage/Shop/1/VShop/201905301357173862700.jpg","Url":"暂无"},{"ImageUrl":"http://mall.xigyu.com//Storage/Shop/1/VShop/201905301357331675110.jpg","Url":"无"},{"ImageUrl":"http://mall.xigyu.com//Storage/Shop/1/VShop/201905301357431987570.jpg","Url":"无"},{"ImageUrl":"http://mall.xigyu.com//Storage/Shop/1/VShop/201905301357509175110.jpg","Url":"无"}]
     * Products : [{"Id":802,"Name":"电热水龙头即热式厨房快速加热速热电热水器淋浴洗澡","MarketPrice":129,"SalePrice":500,"ImageUrl":"http://mall.xigyu.com//Storage/Shop/1/Products/802/1_350.png","CommentsCount":0},{"Id":803,"Name":"除湿机家用卧室小型空气吸湿器地下室工业抽湿大功率干燥","MarketPrice":666.66,"SalePrice":0.01,"ImageUrl":"http://mall.xigyu.com//Storage/Shop/1/Products/803/1_350.png","CommentsCount":0},{"Id":804,"Name":"LQ55H31 55英寸4K超高清全面屏曲面智能LED电视50 60","MarketPrice":2799,"SalePrice":2799,"ImageUrl":"http://mall.xigyu.com//Storage/Shop/1/Products/804/1_350.png","CommentsCount":0}]
     * Banner : []
     * Coupon : [{"Id":71,"Price":"5.00","OrderAmount":"10.00"},{"Id":72,"Price":"10.00","OrderAmount":"100.00"},{"Id":73,"Price":"300.00","OrderAmount":"1000.00"}]
     * CustomerServices : []
     */

    private String Success;
    private VShopBean VShop;
    private List<SlideImgsBean> SlideImgs;
    private List<ProductsBean> Products;
    private List<?> Banner;
    private List<CouponBean> Coupon;
    private List<?> CustomerServices;

    public String getSuccess() {
        return Success;
    }

    public void setSuccess(String Success) {
        this.Success = Success;
    }

    public VShopBean getVShop() {
        return VShop;
    }

    public void setVShop(VShopBean VShop) {
        this.VShop = VShop;
    }

    public List<SlideImgsBean> getSlideImgs() {
        return SlideImgs;
    }

    public void setSlideImgs(List<SlideImgsBean> SlideImgs) {
        this.SlideImgs = SlideImgs;
    }

    public List<ProductsBean> getProducts() {
        return Products;
    }

    public void setProducts(List<ProductsBean> Products) {
        this.Products = Products;
    }

    public List<?> getBanner() {
        return Banner;
    }

    public void setBanner(List<?> Banner) {
        this.Banner = Banner;
    }

    public List<CouponBean> getCoupon() {
        return Coupon;
    }

    public void setCoupon(List<CouponBean> Coupon) {
        this.Coupon = Coupon;
    }

    public List<?> getCustomerServices() {
        return CustomerServices;
    }

    public void setCustomerServices(List<?> CustomerServices) {
        this.CustomerServices = CustomerServices;
    }

    public static class VShopBean {
        /**
         * Id : 10
         * Logo : http://mall.xigyu.com//Storage/Shop/1/VShop/201904271426408530240.jpg
         * Name : 官方自营店
         * ShopId : 1
         * Favorite : false
         * State : 2
         * FollowUrl :
         */

        private int Id;
        private String Logo;
        private String Name;
        private int ShopId;
        private boolean Favorite;
        private int State;
        private String FollowUrl;

        public int getId() {
            return Id;
        }

        public void setId(int Id) {
            this.Id = Id;
        }

        public String getLogo() {
            return Logo;
        }

        public void setLogo(String Logo) {
            this.Logo = Logo;
        }

        public String getName() {
            return Name;
        }

        public void setName(String Name) {
            this.Name = Name;
        }

        public int getShopId() {
            return ShopId;
        }

        public void setShopId(int ShopId) {
            this.ShopId = ShopId;
        }

        public boolean isFavorite() {
            return Favorite;
        }

        public void setFavorite(boolean Favorite) {
            this.Favorite = Favorite;
        }

        public int getState() {
            return State;
        }

        public void setState(int State) {
            this.State = State;
        }

        public String getFollowUrl() {
            return FollowUrl;
        }

        public void setFollowUrl(String FollowUrl) {
            this.FollowUrl = FollowUrl;
        }
    }

    public static class SlideImgsBean {
        /**
         * ImageUrl : http://mall.xigyu.com//Storage/Shop/1/VShop/201905301356453237790.jpg
         * Url : 暂无
         */

        private String ImageUrl;
        private String Url;

        public String getImageUrl() {
            return ImageUrl;
        }

        public void setImageUrl(String ImageUrl) {
            this.ImageUrl = ImageUrl;
        }

        public String getUrl() {
            return Url;
        }

        public void setUrl(String Url) {
            this.Url = Url;
        }
    }

    public static class ProductsBean {
        /**
         * Id : 802
         * Name : 电热水龙头即热式厨房快速加热速热电热水器淋浴洗澡
         * MarketPrice : 129.0
         * SalePrice : 500.0
         * ImageUrl : http://mall.xigyu.com//Storage/Shop/1/Products/802/1_350.png
         * CommentsCount : 0
         */

        private String Id;
        private String Name;
        private double MarketPrice;
        private double SalePrice;
        private String ImageUrl;
        private int CommentsCount;

        public String getId() {
            return Id;
        }

        public void setId(String Id) {
            this.Id = Id;
        }

        public String getName() {
            return Name;
        }

        public void setName(String Name) {
            this.Name = Name;
        }

        public double getMarketPrice() {
            return MarketPrice;
        }

        public void setMarketPrice(double MarketPrice) {
            this.MarketPrice = MarketPrice;
        }

        public double getSalePrice() {
            return SalePrice;
        }

        public void setSalePrice(double SalePrice) {
            this.SalePrice = SalePrice;
        }

        public String getImageUrl() {
            return ImageUrl;
        }

        public void setImageUrl(String ImageUrl) {
            this.ImageUrl = ImageUrl;
        }

        public int getCommentsCount() {
            return CommentsCount;
        }

        public void setCommentsCount(int CommentsCount) {
            this.CommentsCount = CommentsCount;
        }
    }

    public static class CouponBean {
        /**
         * Id : 71
         * Price : 5.00
         * OrderAmount : 10.00
         */

        private int Id;
        private String Price;
        private String OrderAmount;

        public int getId() {
            return Id;
        }

        public void setId(int Id) {
            this.Id = Id;
        }

        public String getPrice() {
            return Price;
        }

        public void setPrice(String Price) {
            this.Price = Price;
        }

        public String getOrderAmount() {
            return OrderAmount;
        }

        public void setOrderAmount(String OrderAmount) {
            this.OrderAmount = OrderAmount;
        }
    }
}
