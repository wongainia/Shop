package com.zhenghaikj.shop.entity;

import java.io.Serializable;

public class Distribution implements Serializable {

    /**
     * BrokerageId : 0
     * ProductId : 972
     * ProductName : 先科小型电冰箱118L家用双门三门式租房宿舍家用冷藏冷冻二人世界
     * DistributorRate : 3
     * CategoryId : 557
     * CategoryName : 三门双门
     * SellPrice : 638
     * ProductSaleState : 1
     * ShowProductSaleState : 出售中
     * ProductBrokerageState : 0
     * ShowProductBrokerageState : 推广中
     * Image : /Storage/Shop/1/Products/972/1_350.png
     * ShopId : 1
     * ShortDescription : null
     * ShareImageUrl : http://mall.xigyu.com//Storage/Shop/1/Products/972/1_350.png
     * SaleNum : 3
     * AgentNum : 4
     * ForwardNum : 0
     * isHasAgent : true
     */

    private int BrokerageId;
    private int ProductId;
    private String ProductName;
    private int DistributorRate;
    private int CategoryId;
    private String CategoryName;
    private Double SellPrice;
    private int ProductSaleState;
    private String ShowProductSaleState;
    private int ProductBrokerageState;
    private String ShowProductBrokerageState;
    private String Image;
    private int ShopId;
    private Object ShortDescription;
    private String ShareImageUrl;
    private int SaleNum;
    private int AgentNum;
    private int ForwardNum;
    private boolean isHasAgent;

    public int getBrokerageId() {
        return BrokerageId;
    }

    public void setBrokerageId(int BrokerageId) {
        this.BrokerageId = BrokerageId;
    }

    public int getProductId() {
        return ProductId;
    }

    public void setProductId(int ProductId) {
        this.ProductId = ProductId;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String ProductName) {
        this.ProductName = ProductName;
    }

    public int getDistributorRate() {
        return DistributorRate;
    }

    public void setDistributorRate(int DistributorRate) {
        this.DistributorRate = DistributorRate;
    }

    public int getCategoryId() {
        return CategoryId;
    }

    public void setCategoryId(int CategoryId) {
        this.CategoryId = CategoryId;
    }

    public String getCategoryName() {
        return CategoryName;
    }

    public void setCategoryName(String CategoryName) {
        this.CategoryName = CategoryName;
    }

    public Double getSellPrice() {
        return SellPrice;
    }

    public void setSellPrice(Double SellPrice) {
        this.SellPrice = SellPrice;
    }

    public int getProductSaleState() {
        return ProductSaleState;
    }

    public void setProductSaleState(int ProductSaleState) {
        this.ProductSaleState = ProductSaleState;
    }

    public String getShowProductSaleState() {
        return ShowProductSaleState;
    }

    public void setShowProductSaleState(String ShowProductSaleState) {
        this.ShowProductSaleState = ShowProductSaleState;
    }

    public int getProductBrokerageState() {
        return ProductBrokerageState;
    }

    public void setProductBrokerageState(int ProductBrokerageState) {
        this.ProductBrokerageState = ProductBrokerageState;
    }

    public String getShowProductBrokerageState() {
        return ShowProductBrokerageState;
    }

    public void setShowProductBrokerageState(String ShowProductBrokerageState) {
        this.ShowProductBrokerageState = ShowProductBrokerageState;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String Image) {
        this.Image = Image;
    }

    public int getShopId() {
        return ShopId;
    }

    public void setShopId(int ShopId) {
        this.ShopId = ShopId;
    }

    public Object getShortDescription() {
        return ShortDescription;
    }

    public void setShortDescription(Object ShortDescription) {
        this.ShortDescription = ShortDescription;
    }

    public String getShareImageUrl() {
        return ShareImageUrl;
    }

    public void setShareImageUrl(String ShareImageUrl) {
        this.ShareImageUrl = ShareImageUrl;
    }

    public int getSaleNum() {
        return SaleNum;
    }

    public void setSaleNum(int SaleNum) {
        this.SaleNum = SaleNum;
    }

    public int getAgentNum() {
        return AgentNum;
    }

    public void setAgentNum(int AgentNum) {
        this.AgentNum = AgentNum;
    }

    public int getForwardNum() {
        return ForwardNum;
    }

    public void setForwardNum(int ForwardNum) {
        this.ForwardNum = ForwardNum;
    }

    public boolean isIsHasAgent() {
        return isHasAgent;
    }

    public void setIsHasAgent(boolean isHasAgent) {
        this.isHasAgent = isHasAgent;
    }
}
