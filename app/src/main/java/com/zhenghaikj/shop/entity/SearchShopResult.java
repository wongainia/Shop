package com.zhenghaikj.shop.entity;

import java.io.Serializable;
import java.util.List;

public class SearchShopResult implements Serializable {

    /**
     * Success : true
     * Total : 8
     * ShopCategory : [{"Id":365,"Name":"冰洗类","Image":null,"Depth":1,"DisplaySequence":0,"SubCategories":[{"Id":366,"Name":"冰箱","Image":null,"Depth":1,"DisplaySequence":0,"SubCategories":[]},{"Id":367,"Name":"冷柜","Image":null,"Depth":1,"DisplaySequence":0,"SubCategories":[]},{"Id":368,"Name":"家用制冰机","Image":null,"Depth":1,"DisplaySequence":0,"SubCategories":[]}]},{"Id":369,"Name":"热水器","Image":null,"Depth":1,"DisplaySequence":0,"SubCategories":[{"Id":370,"Name":"即热式热水器","Image":null,"Depth":1,"DisplaySequence":0,"SubCategories":[]}]}]
     * Products : [{"Id":829,"Name":"BCD-456WMSD电冰箱双开门对开门智能风冷无霜家用小型","MarketPrice":0,"SalePrice":1233,"ImageUrl":"http://mall.xigyu.com//Storage/Shop/1/Products/829/1_350.png","CommentsCount":0},{"Id":828,"Name":"BCD-456WMSD电冰箱双开门对开门智能风冷无霜家用小型","MarketPrice":0,"SalePrice":1235,"ImageUrl":"http://mall.xigyu.com//Storage/Shop/1/Products/828/1_350.png","CommentsCount":0},{"Id":827,"Name":"BCD-400升双开门冰箱电脑温控家用节能双门冰箱对开门电冰箱","MarketPrice":0,"SalePrice":1312,"ImageUrl":"http://mall.xigyu.com//Storage/Shop/1/Products/827/1_350.png","CommentsCount":0},{"Id":822,"Name":"美的冰箱三门家用小型双门电冰箱三开门节能静音","MarketPrice":0,"SalePrice":1231,"ImageUrl":"http://mall.xigyu.com//Storage/Shop/1/Products/822/1_350.png","CommentsCount":0},{"Id":821,"Name":"美的 BCD-629WKPZM(E)双开门家用无霜风冷变频智能对开门电冰箱","MarketPrice":0,"SalePrice":3212,"ImageUrl":"http://mall.xigyu.com//Storage/Shop/1/Products/821/1_350.png","CommentsCount":0},{"Id":820,"Name":"Midea/美的 BCD-213TM(E) 小型宿舍出租房家用三门节能静音电冰箱","MarketPrice":0,"SalePrice":1388,"ImageUrl":"http://mall.xigyu.com//Storage/Shop/1/Products/820/1_350.png","CommentsCount":0},{"Id":819,"Name":"Haier/海尔 BCD-452WDPF 风冷无霜双门对开门冰箱家用电冰箱","MarketPrice":0,"SalePrice":2766,"ImageUrl":"http://mall.xigyu.com//Storage/Shop/1/Products/819/1_350.png","CommentsCount":0},{"Id":815,"Name":"佰益莱BCD-206STPA 206升三门小型家用节能宿舍租房小冰箱","MarketPrice":0,"SalePrice":1399,"ImageUrl":"http://mall.xigyu.com//Storage/Shop/1/Products/815/1_350.png","CommentsCount":0}]
     * VShopId : 10
     * Keywords : null
     */

    private Boolean Success;
    private int Total;
    private int VShopId;
    private Object Keywords;
    private List<ShopCategoryBean> ShopCategory;
    private List<ProductsBean> Products;

    public Boolean getSuccess() {
        return Success;
    }

    public void setSuccess(Boolean success) {
        Success = success;
    }

    public int getTotal() {
        return Total;
    }

    public void setTotal(int Total) {
        this.Total = Total;
    }

    public int getVShopId() {
        return VShopId;
    }

    public void setVShopId(int VShopId) {
        this.VShopId = VShopId;
    }

    public Object getKeywords() {
        return Keywords;
    }

    public void setKeywords(Object Keywords) {
        this.Keywords = Keywords;
    }

    public List<ShopCategoryBean> getShopCategory() {
        return ShopCategory;
    }

    public void setShopCategory(List<ShopCategoryBean> ShopCategory) {
        this.ShopCategory = ShopCategory;
    }

    public List<ProductsBean> getProducts() {
        return Products;
    }

    public void setProducts(List<ProductsBean> Products) {
        this.Products = Products;
    }

    public static class ShopCategoryBean {
        /**
         * Id : 365
         * Name : 冰洗类
         * Image : null
         * Depth : 1
         * DisplaySequence : 0
         * SubCategories : [{"Id":366,"Name":"冰箱","Image":null,"Depth":1,"DisplaySequence":0,"SubCategories":[]},{"Id":367,"Name":"冷柜","Image":null,"Depth":1,"DisplaySequence":0,"SubCategories":[]},{"Id":368,"Name":"家用制冰机","Image":null,"Depth":1,"DisplaySequence":0,"SubCategories":[]}]
         */

        private int Id;
        private String Name;
        private Object Image;
        private int Depth;
        private int DisplaySequence;
        private List<SubCategoriesBean> SubCategories;

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

        public Object getImage() {
            return Image;
        }

        public void setImage(Object Image) {
            this.Image = Image;
        }

        public int getDepth() {
            return Depth;
        }

        public void setDepth(int Depth) {
            this.Depth = Depth;
        }

        public int getDisplaySequence() {
            return DisplaySequence;
        }

        public void setDisplaySequence(int DisplaySequence) {
            this.DisplaySequence = DisplaySequence;
        }

        public List<SubCategoriesBean> getSubCategories() {
            return SubCategories;
        }

        public void setSubCategories(List<SubCategoriesBean> SubCategories) {
            this.SubCategories = SubCategories;
        }

        public static class SubCategoriesBean {
            /**
             * Id : 366
             * Name : 冰箱
             * Image : null
             * Depth : 1
             * DisplaySequence : 0
             * SubCategories : []
             */

            private int Id;
            private String Name;
            private Object Image;
            private int Depth;
            private int DisplaySequence;
            private List<?> SubCategories;

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

            public Object getImage() {
                return Image;
            }

            public void setImage(Object Image) {
                this.Image = Image;
            }

            public int getDepth() {
                return Depth;
            }

            public void setDepth(int Depth) {
                this.Depth = Depth;
            }

            public int getDisplaySequence() {
                return DisplaySequence;
            }

            public void setDisplaySequence(int DisplaySequence) {
                this.DisplaySequence = DisplaySequence;
            }

            public List<?> getSubCategories() {
                return SubCategories;
            }

            public void setSubCategories(List<?> SubCategories) {
                this.SubCategories = SubCategories;
            }
        }
    }

    public static class ProductsBean {
        /**
         * Id : 829
         * Name : BCD-456WMSD电冰箱双开门对开门智能风冷无霜家用小型
         * MarketPrice : 0
         * SalePrice : 1233
         * ImageUrl : http://mall.xigyu.com//Storage/Shop/1/Products/829/1_350.png
         * CommentsCount : 0
         */

        private String Id;
        private String Name;
        private int MarketPrice;
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

        public int getMarketPrice() {
            return MarketPrice;
        }

        public void setMarketPrice(int MarketPrice) {
            this.MarketPrice = MarketPrice;
        }

        public double getSalePrice() {
            return SalePrice;
        }

        public void setSalePrice(double salePrice) {
            SalePrice = salePrice;
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
}
