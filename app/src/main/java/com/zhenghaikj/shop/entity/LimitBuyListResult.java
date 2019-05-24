package com.zhenghaikj.shop.entity;

import java.io.Serializable;
import java.util.List;

public class LimitBuyListResult implements Serializable {


    /**
     * Total : 3
     * List : [{"Id":52,"ProductId":756,"ProductImg":"http://mall.xigyu.com//Storage/Shop/1/Products/756/1_350.png","ProductName":"Haier/海尔 BC-50ES 50升家用节能小型单门租房宿舍电冰箱","MinPrice":20,"EndDate":"2019-05-10T17:30:16"},{"Id":53,"ProductId":749,"ProductImg":"http://mall.xigyu.com//Storage/Shop/1/Products/749/1_350.png","ProductName":"积家（Jaeger）Master Control大师系列机械男表Q1552520 银色","MinPrice":20000,"EndDate":"2019-05-18T17:30:39"},{"Id":54,"ProductId":745,"ProductImg":"http://mall.xigyu.com//Storage/Shop/1/Products/745/1_350.png","ProductName":"新西兰蔓越莓蜂蜜480g 进口蜂蜜选新西兰蜂蜜品牌 NatureBeing","MinPrice":219,"EndDate":"2019-05-26T02:30:55"}]
     */

    private int Total;
    private List<ListBean> List;

    public int getTotal() {
        return Total;
    }

    public void setTotal(int Total) {
        this.Total = Total;
    }

    public List<ListBean> getList() {
        return List;
    }

    public void setList(List<ListBean> List) {
        this.List = List;
    }

    public static class ListBean {
        /**
         * Id : 52
         * ProductId : 756
         * ProductImg : http://mall.xigyu.com//Storage/Shop/1/Products/756/1_350.png
         * ProductName : Haier/海尔 BC-50ES 50升家用节能小型单门租房宿舍电冰箱
         * MinPrice : 20.0
         * EndDate : 2019-05-10T17:30:16
         */

        private int Id;
        private int ProductId;
        private String ProductImg;
        private String ProductName;
        private double MinPrice;
        private String EndDate;
        private String MarketPrice;
        private int SaleCount;
        private int Quantity;

        public String getMarketPrice() {
            return MarketPrice;
        }

        public void setMarketPrice(String marketPrice) {
            MarketPrice = marketPrice;
        }

        public int getSaleCount() {
            return SaleCount;
        }

        public void setSaleCount(int saleCount) {
            SaleCount = saleCount;
        }

        public int getQuantity() {
            return Quantity;
        }

        public void setQuantity(int quantity) {
            Quantity = quantity;
        }

        public int getId() {
            return Id;
        }

        public void setId(int Id) {
            this.Id = Id;
        }

        public int getProductId() {
            return ProductId;
        }

        public void setProductId(int ProductId) {
            this.ProductId = ProductId;
        }

        public String getProductImg() {
            return ProductImg;
        }

        public void setProductImg(String ProductImg) {
            this.ProductImg = ProductImg;
        }

        public String getProductName() {
            return ProductName;
        }

        public void setProductName(String ProductName) {
            this.ProductName = ProductName;
        }

        public double getMinPrice() {
            return MinPrice;
        }

        public void setMinPrice(double MinPrice) {
            this.MinPrice = MinPrice;
        }

        public String getEndDate() {
            return EndDate;
        }

        public void setEndDate(String EndDate) {
            this.EndDate = EndDate;
        }
    }
}
