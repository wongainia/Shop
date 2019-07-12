package com.zhenghaikj.shop.entity;

import java.io.Serializable;
import java.util.List;

public class LimitBuyListResult implements Serializable {


    /**
     * Total : 1
     * List : [{"Id":70,"ProductId":845,"ProductImg":"http://mall.xigyu.com//Storage/Shop/1/Products/845/1_350.png","ProductName":"航天SQD60-611 嵌入式单烤箱家用 60L","MinPrice":2199,"EndDate":"2020-05-05T09:45:14","MarketPrice":5499,"SaleCount":0,"ProductAttributeInfos":["嵌入式","51L（含）-80L（含）","解冻","内置照明"],"Stock":4520}]
     */

    private int Total;
    private java.util.List<ListBean> List;

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
         * Id : 70
         * ProductId : 845
         * ProductImg : http://mall.xigyu.com//Storage/Shop/1/Products/845/1_350.png
         * ProductName : 航天SQD60-611 嵌入式单烤箱家用 60L
         * MinPrice : 2199
         * EndDate : 2020-05-05T09:45:14
         * MarketPrice : 5499
         * SaleCount : 0
         * ProductAttributeInfos : ["嵌入式","51L（含）-80L（含）","解冻","内置照明"]
         * Stock : 4520
         */

        private int Id;
        private int ProductId;
        private String ProductImg;
        private String ProductName;
        private int MinPrice;
        private String EndDate;
        private int MarketPrice;
        private int SaleCount;
        private int Stock;
        private List<String> ProductAttributeInfos;

        private CashDepositsServerBean CashDepositsServer;
        private String Address;
        private String ShopName;

        public String getAddress() {
            return Address;
        }

        public void setAddress(String address) {
            Address = address;
        }

        public String getShopName() {
            return ShopName;
        }

        public void setShopName(String shopName) {
            ShopName = shopName;
        }

        public CashDepositsServerBean getCashDepositsServer() {
            return CashDepositsServer;
        }

        public void setCashDepositsServer(CashDepositsServerBean cashDepositsServer) {
            CashDepositsServer = cashDepositsServer;
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

        public int getMinPrice() {
            return MinPrice;
        }

        public void setMinPrice(int MinPrice) {
            this.MinPrice = MinPrice;
        }

        public String getEndDate() {
            return EndDate;
        }

        public void setEndDate(String EndDate) {
            this.EndDate = EndDate;
        }

        public int getMarketPrice() {
            return MarketPrice;
        }

        public void setMarketPrice(int MarketPrice) {
            this.MarketPrice = MarketPrice;
        }

        public int getSaleCount() {
            return SaleCount;
        }

        public void setSaleCount(int SaleCount) {
            this.SaleCount = SaleCount;
        }

        public int getStock() {
            return Stock;
        }

        public void setStock(int Stock) {
            this.Stock = Stock;
        }

        public List<String> getProductAttributeInfos() {
            return ProductAttributeInfos;
        }

        public void setProductAttributeInfos(List<String> ProductAttributeInfos) {
            this.ProductAttributeInfos = ProductAttributeInfos;
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
