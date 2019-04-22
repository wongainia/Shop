package com.zhenghaikj.shop.entity;

import java.io.Serializable;
import java.util.List;

public class SearchResult implements Serializable {

    /**
     * Success : true
     * Product : [{"ProductId":702,"ShopId":1,"ImagePath":"http://47.96.126.145:8830//Storage/Shop/1/Products/702/1_350.png","SalePrice":11.9,"ProductName":"卫龙 休闲零食 辣条 小面筋 办公室休闲食品 22g*20包(新老包装随机发货)","ShopName":"官方自营店","ThirdCateId":5,"SaleCount":0,"Comments":0,"HasSKU":false,"SkuId":null,"MinSalePrice":0,"cartquantity":0},{"ProductId":704,"ShopId":1,"ImagePath":"http://47.96.126.145:8830//Storage/Shop/1/Products/704/1_350.png","SalePrice":499,"ProductName":"ONLY2017早春新品宽松猫咪图案假两件针织衫女L|117124507 G43花灰 170/88A/L","ShopName":"官方自营店","ThirdCateId":13,"SaleCount":0,"Comments":0,"HasSKU":false,"SkuId":null,"MinSalePrice":0,"cartquantity":0},{"ProductId":705,"ShopId":1,"ImagePath":"http://47.96.126.145:8830//Storage/Shop/1/Products/705/1_350.png","SalePrice":399,"ProductName":"ONLY2017早春新品大V领宽松下摆开衩长袖针织衫女ES|117124502 F17庆典红 165/84A/M","ShopName":"官方自营店","ThirdCateId":10,"SaleCount":0,"Comments":0,"HasSKU":false,"SkuId":null,"MinSalePrice":0,"cartquantity":0},{"ProductId":706,"ShopId":1,"ImagePath":"http://47.96.126.145:8830//Storage/Shop/1/Products/706/1_350.png","SalePrice":234,"ProductName":"ONLY2016早春新品拼色宽松太空棉卫衣女E|11639R511 07B酒红色 165/84A/M","ShopName":"官方自营店","ThirdCateId":10,"SaleCount":0,"Comments":0,"HasSKU":false,"SkuId":null,"MinSalePrice":0,"cartquantity":0},{"ProductId":707,"ShopId":1,"ImagePath":"http://47.96.126.145:8830//Storage/Shop/1/Products/707/1_350.png","SalePrice":175,"ProductName":"ONLY春季新品含莱卡面料五分袖修身露肩一字领针织连衣裙女E|116361504 010黑 175/92A/XL","ShopName":"官方自营店","ThirdCateId":11,"SaleCount":0,"Comments":0,"HasSKU":false,"SkuId":null,"MinSalePrice":0,"cartquantity":0},{"ProductId":708,"ShopId":1,"ImagePath":"http://47.96.126.145:8830//Storage/Shop/1/Products/708/1_350.png","SalePrice":450,"ProductName":"ONLY春季新品纯棉修身高腰镂空蕾丝连衣裙女L|116307501 021奶白 155/76A/XS","ShopName":"官方自营店","ThirdCateId":11,"SaleCount":0,"Comments":0,"HasSKU":false,"SkuId":null,"MinSalePrice":0,"cartquantity":0},{"ProductId":737,"ShopId":1,"ImagePath":"ht tp://47.96.126.145:8830//Storage/Shop/1/Products/737/1_350.png","SalePrice":83,"ProductName":"怡鲜来 法国新鲜冷冻银鳕鱼中段 200g 进口海鲜水产 深海野生鳕鱼","ShopName":"官方自营店","ThirdCateId":7,"SaleCount":0,"Comments":0,"HasSKU":false,"SkuId":null,"MinSalePrice":0,"cartquantity":0},{"ProductId":740,"ShopId":1,"ImagePath":"http://47.96.126.145:8830//Storage/Shop/1/Products/740/1_350.png","SalePrice":25,"ProductName":"红霞草莓 3斤 单果20-30克 新鲜水果 SG","ShopName":"官方自营店","ThirdCateId":154,"SaleCount":0,"Comments":0,"HasSKU":false,"SkuId":null,"MinSalePrice":0,"cartquantity":0},{"ProductId":745,"ShopId":1,"ImagePath":"http://47.96.126.145:8830//Storage/Shop/1/Products/745/1_350.png","SalePrice":219,"ProductName":"新西兰蔓越莓蜂蜜480g 进口蜂蜜选新西兰蜂蜜品牌 NatureBeing","ShopName":"官方自营店","ThirdCateId":130,"SaleCount":0,"Comments":0,"HasSKU":false,"SkuId":null,"MinSalePrice":0,"cartquantity":0}]
     * keywords : 新
     * Total : 9
     * cid : 0
     * b_id : 0
     * a_id :
     * orderKey : 1
     * orderType : 1
     */

    private Boolean Success;
    private String keywords;
    private int Total;
    private int cid;
    private int b_id;
    private String a_id;
    private int orderKey;
    private int orderType;
    private List<ProductBean> Product;

    public Boolean getSuccess() {
        return Success;
    }

    public void setSuccess(Boolean success) {
        Success = success;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public int getTotal() {
        return Total;
    }

    public void setTotal(int Total) {
        this.Total = Total;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public int getB_id() {
        return b_id;
    }

    public void setB_id(int b_id) {
        this.b_id = b_id;
    }

    public String getA_id() {
        return a_id;
    }

    public void setA_id(String a_id) {
        this.a_id = a_id;
    }

    public int getOrderKey() {
        return orderKey;
    }

    public void setOrderKey(int orderKey) {
        this.orderKey = orderKey;
    }

    public int getOrderType() {
        return orderType;
    }

    public void setOrderType(int orderType) {
        this.orderType = orderType;
    }

    public List<ProductBean> getProduct() {
        return Product;
    }

    public void setProduct(List<ProductBean> Product) {
        this.Product = Product;
    }

    public static class ProductBean {
        /**
         * ProductId : 702
         * ShopId : 1
         * ImagePath : http://47.96.126.145:8830//Storage/Shop/1/Products/702/1_350.png
         * SalePrice : 11.9
         * ProductName : 卫龙 休闲零食 辣条 小面筋 办公室休闲食品 22g*20包(新老包装随机发货)
         * ShopName : 官方自营店
         * ThirdCateId : 5
         * SaleCount : 0
         * Comments : 0
         * HasSKU : false
         * SkuId : null
         * MinSalePrice : 0.0
         * cartquantity : 0
         */

        private int ProductId;
        private int ShopId;
        private String ImagePath;
        private double SalePrice;
        private String ProductName;
        private String ShopName;
        private int ThirdCateId;
        private int SaleCount;
        private int Comments;
        private boolean HasSKU;
        private String SkuId;
        private double MinSalePrice;
        private int cartquantity;

        public int getProductId() {
            return ProductId;
        }

        public void setProductId(int ProductId) {
            this.ProductId = ProductId;
        }

        public int getShopId() {
            return ShopId;
        }

        public void setShopId(int ShopId) {
            this.ShopId = ShopId;
        }

        public String getImagePath() {
            return ImagePath;
        }

        public void setImagePath(String ImagePath) {
            this.ImagePath = ImagePath;
        }

        public double getSalePrice() {
            return SalePrice;
        }

        public void setSalePrice(double SalePrice) {
            this.SalePrice = SalePrice;
        }

        public String getProductName() {
            return ProductName;
        }

        public void setProductName(String ProductName) {
            this.ProductName = ProductName;
        }

        public String getShopName() {
            return ShopName;
        }

        public void setShopName(String ShopName) {
            this.ShopName = ShopName;
        }

        public int getThirdCateId() {
            return ThirdCateId;
        }

        public void setThirdCateId(int ThirdCateId) {
            this.ThirdCateId = ThirdCateId;
        }

        public int getSaleCount() {
            return SaleCount;
        }

        public void setSaleCount(int SaleCount) {
            this.SaleCount = SaleCount;
        }

        public int getComments() {
            return Comments;
        }

        public void setComments(int Comments) {
            this.Comments = Comments;
        }

        public boolean isHasSKU() {
            return HasSKU;
        }

        public void setHasSKU(boolean HasSKU) {
            this.HasSKU = HasSKU;
        }

        public String getSkuId() {
            return SkuId;
        }

        public void setSkuId(String SkuId) {
            this.SkuId = SkuId;
        }

        public double getMinSalePrice() {
            return MinSalePrice;
        }

        public void setMinSalePrice(double MinSalePrice) {
            this.MinSalePrice = MinSalePrice;
        }

        public int getCartquantity() {
            return cartquantity;
        }

        public void setCartquantity(int cartquantity) {
            this.cartquantity = cartquantity;
        }
    }
}
