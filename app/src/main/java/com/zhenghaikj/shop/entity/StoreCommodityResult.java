package com.zhenghaikj.shop.entity;

import java.io.Serializable;
import java.util.List;

public class StoreCommodityResult implements Serializable {


    /**
     * Success : true
     * ProductList : [{"Id":822,"ProductName":"美的冰箱三门家用小型双门电冰箱三开门节能静音","MeasureUnit":"台","MinSalePrice":"1231.00","SaleCounts":0,"RelativePath":"http://mall.xigyu.com//Storage/Shop/1/Products/822/1_350.png"},{"Id":821,"ProductName":"美的 BCD-629WKPZM(E)双开门家用无霜风冷变频智能对开门电冰箱","MeasureUnit":"台","MinSalePrice":"3212.00","SaleCounts":0,"RelativePath":"http://mall.xigyu.com//Storage/Shop/1/Products/821/1_350.png"},{"Id":820,"ProductName":"Midea/美的 BCD-213TM(E) 小型宿舍出租房家用三门节能静音电冰箱","MeasureUnit":"台","MinSalePrice":"1388.00","SaleCounts":0,"RelativePath":"http://mall.xigyu.com//Storage/Shop/1/Products/820/1_350.png"},{"Id":819,"ProductName":"Haier/海尔 BCD-452WDPF 风冷无霜双门对开门冰箱家用电冰箱","MeasureUnit":"台","MinSalePrice":"2766.00","SaleCounts":0,"RelativePath":"http://mall.xigyu.com//Storage/Shop/1/Products/819/1_350.png"},{"Id":815,"ProductName":"佰益莱BCD-206STPA 206升三门小型家用节能宿舍租房小冰箱","MeasureUnit":"台","MinSalePrice":"1399.00","SaleCounts":0,"RelativePath":"http://mall.xigyu.com//Storage/Shop/1/Products/815/1_350.png"},{"Id":814,"ProductName":"55寸液晶电视机50高清4k智能wifi网络led平板42老人65家用彩电32","MeasureUnit":"台","MinSalePrice":"3000.00","SaleCounts":0,"RelativePath":"http://mall.xigyu.com//Storage/Shop/1/Products/814/1_350.png"}]
     * total : 6
     */

    private boolean Success;
    private int total;
    private List<ProductListBean> ProductList;

    public boolean isSuccess() {
        return Success;
    }

    public void setSuccess(boolean Success) {
        this.Success = Success;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<ProductListBean> getProductList() {
        return ProductList;
    }

    public void setProductList(List<ProductListBean> ProductList) {
        this.ProductList = ProductList;
    }

    public static class ProductListBean {
        /**
         * Id : 822
         * ProductName : 美的冰箱三门家用小型双门电冰箱三开门节能静音
         * MeasureUnit : 台
         * MinSalePrice : 1231.00
         * SaleCounts : 0
         * RelativePath : http://mall.xigyu.com//Storage/Shop/1/Products/822/1_350.png
         */

        private String Id;
        private String ProductName;
        private String MeasureUnit;
        private String MinSalePrice;
        private int SaleCounts;
        private String RelativePath;

        public String getId() {
            return Id;
        }

        public void setId(String Id) {
            this.Id = Id;
        }

        public String getProductName() {
            return ProductName;
        }

        public void setProductName(String ProductName) {
            this.ProductName = ProductName;
        }

        public String getMeasureUnit() {
            return MeasureUnit;
        }

        public void setMeasureUnit(String MeasureUnit) {
            this.MeasureUnit = MeasureUnit;
        }

        public String getMinSalePrice() {
            return MinSalePrice;
        }

        public void setMinSalePrice(String MinSalePrice) {
            this.MinSalePrice = MinSalePrice;
        }

        public int getSaleCounts() {
            return SaleCounts;
        }

        public void setSaleCounts(int SaleCounts) {
            this.SaleCounts = SaleCounts;
        }

        public String getRelativePath() {
            return RelativePath;
        }

        public void setRelativePath(String RelativePath) {
            this.RelativePath = RelativePath;
        }
    }
}
