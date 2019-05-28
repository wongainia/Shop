package com.zhenghaikj.shop.entity;

import java.io.Serializable;
import java.util.List;

public class StoreDetailResult implements Serializable {


    /**
     * Success : True
     * VShop : {"Id":4,"Logo":"http://192.168.10.47/Storage/Shop/210/VShop/Logo.jpg","Name":"扣扣电脑城","ShopId":210,"Favorite":true,"State":2,"FollowUrl":"http://becks1989.oicp.net/m-WeiXin/vshop/detail/4"}
     * SlideImgs : [{"ImageUrl":"http://192.168.10.47/Storage/Shop/210/VShop/201507201025240381540.jpg","Url":"www.baidu.com"}]
     * Products : [{"Id":252,"Name":"Apple MacBook 12英寸笔记本 金色 512GB闪存 MK4N2CH/A","MarketPrice":9666,"SalePrice":5999,"ImageUrl":"/Storage/Shop/210/Products/252/1_350.png","CommentsCount":0},{"Id":245,"Name":"未来人类（Terrans Force）P57 17.3英寸游戏本(六核i7-4960X 32G 1.","MarketPrice":8256,"SalePrice":5555,"ImageUrl":"/Storage/Shop/210/Products/245/1_350.png","CommentsCount":0},{"Id":243,"Name":"      酷爱（COOAV）一体机电脑1037双核台式机电脑 19.5英寸4G/500G     ","MarketPrice":5522,"SalePrice":5555,"ImageUrl":"/Storage/Shop/210/Products/243/1_350.png","CommentsCount":0},{"Id":241,"Name":"2015新款Apple/苹果 MacBook Air MJVE2CH/A 13.3/128GB电脑","MarketPrice":10999,"SalePrice":6888,"ImageUrl":"/Storage/Shop/210/Products/241/1_350.png","CommentsCount":0},{"Id":238,"Name":"    整机厚度15.8mm，重量仅1.1kg，360°自由翻转， 四种使用模式，满足更多使用环境！","MarketPrice":2269,"SalePrice":5592,"ImageUrl":"/Storage/Shop/210/Products/238/1_350.png","CommentsCount":0},{"Id":236,"Name":"      联想（Lenovo）YOGA3 11 11.6英寸触控超薄本 （5Y10 4G 256G","MarketPrice":59222,"SalePrice":55500,"ImageUrl":"/Storage/Shop/210/Products/236/1_350.png","CommentsCount":0},{"Id":235,"Name":"      清华同方U850台式电脑23英寸屏 i5-4460 4G 1T 2G独显 带wifi  ","MarketPrice":54,"SalePrice":5000,"ImageUrl":"/Storage/Shop/210/Products/235/1_350.png","CommentsCount":0},{"Id":234,"Name":"      清华同方（TongFang）精锐X200H-BI02 18.5英寸台式电脑(G1840 ","MarketPrice":19,"SalePrice":0.01,"ImageUrl":"/Storage/Shop/210/Products/234/1_350.png","CommentsCount":0}]
     * Banner : [{"Id":46,"ShopId":210,"Name":"全部商品","Position":0,"DisplaySequence":1,"Url":"/m-WeiXin/vshop/Search?vshopid=4","Platform":1,"UrlType":1},{"Id":47,"ShopId":210,"Name":"店铺简介","Position":0,"DisplaySequence":2,"Url":"/m-WeiXin/vshop/introduce/4","Platform":1,"UrlType":3},{"Id":48,"ShopId":210,"Name":"商品分类","Position":0,"DisplaySequence":3,"Url":"/m-WeiXin/vshop/Category?vshopid=4","Platform":1,"UrlType":2},{"Id":49,"ShopId":210,"Name":"限时购","Position":0,"DisplaySequence":4,"Url":"","Platform":1,"UrlType":0},{"Id":54,"ShopId":210,"Name":"白富美","Position":0,"DisplaySequence":7,"Url":"/m-WeiXin/vshop/introduce/4","Platform":1,"UrlType":3}]
     * Coupon : null
     */

    private String Success;
    private VShopBean VShop;
    private Object Coupon;
    private List<SlideImgsBean> SlideImgs;
    private List<ProductsBean> Products;
    private List<BannerBean> Banner;

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

    public Object getCoupon() {
        return Coupon;
    }

    public void setCoupon(Object Coupon) {
        this.Coupon = Coupon;
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

    public List<BannerBean> getBanner() {
        return Banner;
    }

    public void setBanner(List<BannerBean> Banner) {
        this.Banner = Banner;
    }

    public static class VShopBean {
        /**
         * Id : 4
         * Logo : http://192.168.10.47/Storage/Shop/210/VShop/Logo.jpg
         * Name : 扣扣电脑城
         * ShopId : 210
         * Favorite : true
         * State : 2
         * FollowUrl : http://becks1989.oicp.net/m-WeiXin/vshop/detail/4
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
         * ImageUrl : http://192.168.10.47/Storage/Shop/210/VShop/201507201025240381540.jpg
         * Url : www.baidu.com
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
         * Id : 252
         * Name : Apple MacBook 12英寸笔记本 金色 512GB闪存 MK4N2CH/A
         * MarketPrice : 9666
         * SalePrice : 5999
         * ImageUrl : /Storage/Shop/210/Products/252/1_350.png
         * CommentsCount : 0
         */

        private int Id;
        private String Name;
        private int MarketPrice;
        private int SalePrice;
        private String ImageUrl;
        private int CommentsCount;

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

        public int getMarketPrice() {
            return MarketPrice;
        }

        public void setMarketPrice(int MarketPrice) {
            this.MarketPrice = MarketPrice;
        }

        public int getSalePrice() {
            return SalePrice;
        }

        public void setSalePrice(int SalePrice) {
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

    public static class BannerBean {
        /**
         * Id : 46
         * ShopId : 210
         * Name : 全部商品
         * Position : 0
         * DisplaySequence : 1
         * Url : /m-WeiXin/vshop/Search?vshopid=4
         * Platform : 1
         * UrlType : 1
         */

        private int Id;
        private int ShopId;
        private String Name;
        private int Position;
        private int DisplaySequence;
        private String Url;
        private int Platform;
        private int UrlType;

        public int getId() {
            return Id;
        }

        public void setId(int Id) {
            this.Id = Id;
        }

        public int getShopId() {
            return ShopId;
        }

        public void setShopId(int ShopId) {
            this.ShopId = ShopId;
        }

        public String getName() {
            return Name;
        }

        public void setName(String Name) {
            this.Name = Name;
        }

        public int getPosition() {
            return Position;
        }

        public void setPosition(int Position) {
            this.Position = Position;
        }

        public int getDisplaySequence() {
            return DisplaySequence;
        }

        public void setDisplaySequence(int DisplaySequence) {
            this.DisplaySequence = DisplaySequence;
        }

        public String getUrl() {
            return Url;
        }

        public void setUrl(String Url) {
            this.Url = Url;
        }

        public int getPlatform() {
            return Platform;
        }

        public void setPlatform(int Platform) {
            this.Platform = Platform;
        }

        public int getUrlType() {
            return UrlType;
        }

        public void setUrlType(int UrlType) {
            this.UrlType = UrlType;
        }
    }
}
