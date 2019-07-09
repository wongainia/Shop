package com.zhenghaikj.shop.entity;

import java.io.Serializable;
import java.util.List;

public class HomeResult implements Serializable {


    /**
     * Success : true
     * TotalProduct : 16
     * Slide : [{"ImageUrl":"http://mall.xigyu.com//Storage/Plat/APP/SlidAd/201702141656064131340.png","Url":"./product/detail/735","Desc":null},{"ImageUrl":"http://mall.xigyu.com//Storage/Plat/APP/SlidAd/201702141656402812130.jpg","Url":"./product/detail/745","Desc":null},{"ImageUrl":"http://mall.xigyu.com//Storage/Plat/APP/SlidAd/201702141729484199440.jpg","Url":"./product/detail/713","Desc":null}]
     * Icon : [{"ImageUrl":"http://mall.xigyu.com//temp/201702151053059693630.png","Url":"5","Desc":"分类"},{"ImageUrl":"http://mall.xigyu.com//temp/201702151053171172520.png","Url":"3","Desc":"限时购"},{"ImageUrl":"http://mall.xigyu.com//temp/201702151053291655660.png","Url":"2","Desc":"拼团"},{"ImageUrl":"http://mall.xigyu.com//temp/201702151053500286720.png","Url":"4","Desc":"积分商城"},{"ImageUrl":"http://mall.xigyu.com//temp/201702151053418652720.png","Url":"1","Desc":"专题"}]
     * Topic : [{"ImageUrl":"http://mall.xigyu.com//Storage/Plat/ImageAd/201702141703459833250.jpg","Url":"./m-wap/topic/detail/54"},{"ImageUrl":"http://mall.xigyu.com//Storage/Plat/ImageAd/201702141700011816110.jpg","Url":"./m-wap/topic/detail/54"},{"ImageUrl":"http://mall.xigyu.com//Storage/Plat/ImageAd/201702141700147587450.jpg","Url":"./m-wap/topic/detail/54"},{"ImageUrl":"http://mall.xigyu.com//Storage/Plat/ImageAd/201702141700462909120.jpg","Url":"./m-wap/topic/detail/54"},{"ImageUrl":"http://mall.xigyu.com//Storage/Plat/ImageAd/201702141701212821850.jpg","Url":"./m-wap/topic/detail/54"}]
     * Product : [{"Id":"733","Name":"丹麦依诺维绅 功能沙发床 时尚沙发 书房必用 小鸟","MarketPrice":"5000.00","SalePrice":"4425.00","ImageUrl":"http://mall.xigyu.com//Storage/Shop/1/Products/733/1_220.png","CommentsCount":null,"Discount":"0.9","Url":"http://mall.xigyu.com//m-ios/product/detail/733"},{"Id":"732","Name":"全实木电脑桌书桌办公桌","MarketPrice":"5000.00","SalePrice":"200.00","ImageUrl":"http://mall.xigyu.com//Storage/Shop/1/Products/732/1_220.png","CommentsCount  ":null,"Discount":"0.0","Url":"http://mall.xigyu.com//m-ios/product/detail/732"},{"Id":"722","Name":"Clinique倩碧液体洁面皂温和型200ml","MarketPrice":"150.00","SalePrice":"110.00","ImageUrl":"http://mall.xigyu.com//Storage/Shop/1/Products/722/1_220.png","CommentsCount":null,"Discount":"0.7","Url":"http://mall.xigyu.com//m-ios/product/detail/722"},{"Id":"721","Name":"丝塔芙(Cetaphil)洁面乳118ml","MarketPrice":"60.00","SalePrice":"55.00","ImageUrl":"http://mall.xigyu.com//Storage/Shop/1/Products/721/1_220.png","CommentsCount":null,"Discount":"0.9","Url":"http://mall.xigyu.com//m-ios/product/detail/721"},{"Id":"720","Name":"欧莱雅（LOREAL）男士火山岩控油清痘洁面膏100ml","MarketPrice":"40.00","SalePrice":"32.90","ImageUrl":"http://mall.xigyu.com//Storage/Shop/1/Products/720/1_220.png","CommentsCount":null,"Discount":"0.8","Url":"http://mall.xigyu.com//m-ios/product/detail/720"},{"Id":"708","Name":"ONLY春季新品纯棉修身高腰镂空蕾丝连衣裙女L|116307501 021奶白 155/76A/XS","MarketPrice":"500.00","SalePrice":"450.00","ImageUrl":"http://mall.xigyu.com//Storage/Shop/1/Products/708/1_220.png","CommentsCount":null,"Discount":"0.9","Url":"http://mall.xigyu.com//m-ios/product/detail/708"},{"Id":"719","Name":"资生堂洗颜专科柔澈泡沫洁面乳１２０ｇ","MarketPrice":"40.00","SalePrice":"37.00","ImageUrl":"http://mall.xigyu.com//Storage/Shop/1/Products/719/1_220.png","CommentsCount":null,"Discount":"0.9","Url":"http://mall.xigyu.com//m-ios/product/detail/719"},{"Id":"743","Name":"勇艺达小勇机器人Y50B 太空银 家庭陪伴 启智教育 声控智能家居 视频监控","MarketPrice":"3680.00","SalePrice":"2999.00","ImageUrl":"http://mall.xigyu.com//Storage/Shop/1/Products/743/1_220.png","CommentsCount":null,"Discount":"0.8","Url":"http://mall.xigyu.com//m-ios/product/detail/743"},{"Id":"742","Name":"怡鲜来 阿根廷红虾2000g 4斤/盒 大号L2级 40-60尾","MarketPrice":"338.00","SalePrice":"168.00","ImageUrl":"http://mall.xigyu.com//Storage/Shop/1/Products/742/1_220.png","CommentsCount":null,"Discount":"0.5","Url":"http://mall.xigyu.com//m-ios/product/detail/742"},{"Id":"7  41","Name":"华硕（ASUS）FX50VX 15.6英寸游戏本（I7-6700 8G 1T GTX950M 2GB Win10 黑红）","MarketPrice":"5899.00","SalePrice":"5399.00","ImageUrl":"http://mall.xigyu.com//Storage/Shop/1/Products/741/1_220.png","CommentsCount":null,"Discount":"0.9","Url":"http://mall.xigyu.com//m-ios/product/detail/741"}]
     * CustomerServices : []
     */

    private Boolean Success;
    private String TotalProduct;
    private List<SlideBean> Slide;
    private List<IconBean> Icon;
    private List<TopicBean> Topic;
    private List<ProductBean> Product;
    private List<?> CustomerServices;

    public Boolean getSuccess() {
        return Success;
    }

    public void setSuccess(Boolean success) {
        Success = success;
    }

    public String getTotalProduct() {
        return TotalProduct;
    }

    public void setTotalProduct(String TotalProduct) {
        this.TotalProduct = TotalProduct;
    }

    public List<SlideBean> getSlide() {
        return Slide;
    }

    public void setSlide(List<SlideBean> Slide) {
        this.Slide = Slide;
    }

    public List<IconBean> getIcon() {
        return Icon;
    }

    public void setIcon(List<IconBean> Icon) {
        this.Icon = Icon;
    }

    public List<TopicBean> getTopic() {
        return Topic;
    }

    public void setTopic(List<TopicBean> Topic) {
        this.Topic = Topic;
    }

    public List<ProductBean> getProduct() {
        return Product;
    }

    public void setProduct(List<ProductBean> Product) {
        this.Product = Product;
    }

    public List<?> getCustomerServices() {
        return CustomerServices;
    }

    public void setCustomerServices(List<?> CustomerServices) {
        this.CustomerServices = CustomerServices;
    }

    public static class SlideBean {
        /**
         * ImageUrl : http://mall.xigyu.com//Storage/Plat/APP/SlidAd/201702141656064131340.png
         * Url : ./product/detail/735
         * Desc : null
         */

        private String ImageUrl;
        private String Url;
        private String Desc;

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

        public String getDesc() {
            return Desc;
        }

        public void setDesc(String Desc) {
            this.Desc = Desc;
        }
    }

    public static class IconBean {
        /**
         * ImageUrl : http://mall.xigyu.com//temp/201702151053059693630.png
         * Url : 5
         * Desc : 分类
         */

        private String ImageUrl;
        private String Url;
        private String Desc;

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

        public String getDesc() {
            return Desc;
        }

        public void setDesc(String Desc) {
            this.Desc = Desc;
        }
    }

    public static class TopicBean {
        /**
         * ImageUrl : http://mall.xigyu.com//Storage/Plat/ImageAd/201702141703459833250.jpg
         * Url : ./m-wap/topic/detail/54
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

    public static class ProductBean {
        /**
         * Id : 733
         * Name : 丹麦依诺维绅 功能沙发床 时尚沙发 书房必用 小鸟
         * MarketPrice : 5000.00
         * SalePrice : 4425.00
         * ImageUrl : http://mall.xigyu.com//Storage/Shop/1/Products/733/1_220.png
         * CommentsCount : null
         * Discount : 0.9
         * Url : http://mall.xigyu.com//m-ios/product/detail/733
         */

        private String Id;
        private String Name;
        private String MarketPrice;
        private String SalePrice;
        private String ImageUrl;
        private String CommentsCount;
        private String Discount;
        private String Url;
        private List<String> ProductAttributeInfos;
        private CashDepositsServerBean CashDepositsServer;

        public CashDepositsServerBean getCashDepositsServer() {
            return CashDepositsServer;
        }

        public void setCashDepositsServer(CashDepositsServerBean cashDepositsServer) {
            CashDepositsServer = cashDepositsServer;
        }

        public List<String> getProductAttributeInfos() {
            return ProductAttributeInfos;
        }

        public void setProductAttributeInfos(List<String> productAttributeInfos) {
            ProductAttributeInfos = productAttributeInfos;
        }

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

        public String getMarketPrice() {
            return MarketPrice;
        }

        public void setMarketPrice(String MarketPrice) {
            this.MarketPrice = MarketPrice;
        }

        public String getSalePrice() {
            return SalePrice;
        }

        public void setSalePrice(String SalePrice) {
            this.SalePrice = SalePrice;
        }

        public String getImageUrl() {
            return ImageUrl;
        }

        public void setImageUrl(String ImageUrl) {
            this.ImageUrl = ImageUrl;
        }

        public String getCommentsCount() {
            return CommentsCount;
        }

        public void setCommentsCount(String CommentsCount) {
            this.CommentsCount = CommentsCount;
        }

        public String getDiscount() {
            return Discount;
        }

        public void setDiscount(String Discount) {
            this.Discount = Discount;
        }

        public String getUrl() {
            return Url;
        }

        public void setUrl(String Url) {
            this.Url = Url;
        }
    }

    public static class CashDepositsServerBean {
        /**
         * IsSevenDayNoReasonReturn : true
         * IsTimelyShip : false
         * IsCustomerSecurity : true
         * CanSelfTake : false
         */

        private boolean IsSevenDayNoReasonReturn;
        private boolean IsTimelyShip;
        private boolean IsCustomerSecurity;
        private boolean CanSelfTake;

        public boolean isIsSevenDayNoReasonReturn() {
            return IsSevenDayNoReasonReturn;
        }

        public void setIsSevenDayNoReasonReturn(boolean IsSevenDayNoReasonReturn) {
            this.IsSevenDayNoReasonReturn = IsSevenDayNoReasonReturn;
        }

        public boolean isIsTimelyShip() {
            return IsTimelyShip;
        }

        public void setIsTimelyShip(boolean IsTimelyShip) {
            this.IsTimelyShip = IsTimelyShip;
        }

        public boolean isIsCustomerSecurity() {
            return IsCustomerSecurity;
        }

        public void setIsCustomerSecurity(boolean IsCustomerSecurity) {
            this.IsCustomerSecurity = IsCustomerSecurity;
        }

        public boolean isCanSelfTake() {
            return CanSelfTake;
        }

        public void setCanSelfTake(boolean CanSelfTake) {
            this.CanSelfTake = CanSelfTake;
        }
    }
}
