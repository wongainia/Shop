package com.zhenghaikj.shop.entity;

import java.io.Serializable;
import java.util.List;

public class GiftDetailResult implements Serializable {


    /**
     * CanBuy : false
     * CanNotBuyDes : 积分不足
     * Images : ["http://mall.xigyu.com//Storage/Gift/77/1_500.png"]
     * ShowSalesStatus : 可兑换
     * Id : 77
     * GiftName : 【松桂坊_五花腊肉500*2袋】 湖南湘西农家传统工艺 土猪特产腊肠腊肉烟熏腊肉
     * NeedIntegral : 3000
     * LimtQuantity : 1
     * StockQuantity : 96
     * EndDate : 2019-06-13T01:05:00
     * NeedGrade : 7
     * VirtualSales : 0
     * RealSales : 4
     * SalesStatus : 1
     * ImagePath : /Storage/Gift/77
     * Sequence : 100
     * GiftValue : 88.0
     * Description : <p><img src="http://mall.xigyu.com//Storage/Gift/77/Details/c8b2af53b85349b5af4056ab85b73629.jpg" title="403519394375682081332700_x.jpg" alt="403519394375682081332700_x.jpg"/><img src="http://mall.xigyu.com//Storage/Gift/77/Details/2d9cef803baa43ad801463f28abb74d4.jpg" title="132029199783667016411770_x.jpg" alt="132029199783667016411770_x.jpg"/></p>
     * AddDate : 2017-02-16T11:45:13
     * NeedGradeName : 黄金会员
     * GradeIntegral : 5000
     * DefaultShowImage : http://mall.xigyu.com//Storage/Gift/77/1_100.png
     * SumSales : 4
     * ShowImagePath : /Storage/Gift/77
     * GetSalesStatus : 1
     * ShowLimtQuantity : 1
     */

    private boolean CanBuy;
    private String CanNotBuyDes;
    private String ShowSalesStatus;
    private int Id;
    private String GiftName;
    private int NeedIntegral;
    private int LimtQuantity;
    private int StockQuantity;
    private String EndDate;
    private int NeedGrade;
    private int VirtualSales;
    private int RealSales;
    private int SalesStatus;
    private String ImagePath;
    private int Sequence;
    private double GiftValue;
    private String Description;
    private String AddDate;
    private String NeedGradeName;
    private int GradeIntegral;
    private String DefaultShowImage;
    private int SumSales;
    private String ShowImagePath;
    private int GetSalesStatus;
    private String ShowLimtQuantity;
    private List<String> Images;

    public boolean isCanBuy() {
        return CanBuy;
    }

    public void setCanBuy(boolean CanBuy) {
        this.CanBuy = CanBuy;
    }

    public String getCanNotBuyDes() {
        return CanNotBuyDes;
    }

    public void setCanNotBuyDes(String CanNotBuyDes) {
        this.CanNotBuyDes = CanNotBuyDes;
    }

    public String getShowSalesStatus() {
        return ShowSalesStatus;
    }

    public void setShowSalesStatus(String ShowSalesStatus) {
        this.ShowSalesStatus = ShowSalesStatus;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getGiftName() {
        return GiftName;
    }

    public void setGiftName(String GiftName) {
        this.GiftName = GiftName;
    }

    public int getNeedIntegral() {
        return NeedIntegral;
    }

    public void setNeedIntegral(int NeedIntegral) {
        this.NeedIntegral = NeedIntegral;
    }

    public int getLimtQuantity() {
        return LimtQuantity;
    }

    public void setLimtQuantity(int LimtQuantity) {
        this.LimtQuantity = LimtQuantity;
    }

    public int getStockQuantity() {
        return StockQuantity;
    }

    public void setStockQuantity(int StockQuantity) {
        this.StockQuantity = StockQuantity;
    }

    public String getEndDate() {
        return EndDate.replace("T"," ");
    }

    public void setEndDate(String EndDate) {
        this.EndDate = EndDate;
    }

    public int getNeedGrade() {
        return NeedGrade;
    }

    public void setNeedGrade(int NeedGrade) {
        this.NeedGrade = NeedGrade;
    }

    public int getVirtualSales() {
        return VirtualSales;
    }

    public void setVirtualSales(int VirtualSales) {
        this.VirtualSales = VirtualSales;
    }

    public int getRealSales() {
        return RealSales;
    }

    public void setRealSales(int RealSales) {
        this.RealSales = RealSales;
    }

    public int getSalesStatus() {
        return SalesStatus;
    }

    public void setSalesStatus(int SalesStatus) {
        this.SalesStatus = SalesStatus;
    }

    public String getImagePath() {
        return ImagePath;
    }

    public void setImagePath(String ImagePath) {
        this.ImagePath = ImagePath;
    }

    public int getSequence() {
        return Sequence;
    }

    public void setSequence(int Sequence) {
        this.Sequence = Sequence;
    }

    public double getGiftValue() {
        return GiftValue;
    }

    public void setGiftValue(double GiftValue) {
        this.GiftValue = GiftValue;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public String getAddDate() {
        return AddDate;
    }

    public void setAddDate(String AddDate) {
        this.AddDate = AddDate;
    }

    public String getNeedGradeName() {
        return NeedGradeName;
    }

    public void setNeedGradeName(String NeedGradeName) {
        this.NeedGradeName = NeedGradeName;
    }

    public int getGradeIntegral() {
        return GradeIntegral;
    }

    public void setGradeIntegral(int GradeIntegral) {
        this.GradeIntegral = GradeIntegral;
    }

    public String getDefaultShowImage() {
        return DefaultShowImage;
    }

    public void setDefaultShowImage(String DefaultShowImage) {
        this.DefaultShowImage = DefaultShowImage;
    }

    public int getSumSales() {
        return SumSales;
    }

    public void setSumSales(int SumSales) {
        this.SumSales = SumSales;
    }

    public String getShowImagePath() {
        return ShowImagePath;
    }

    public void setShowImagePath(String ShowImagePath) {
        this.ShowImagePath = ShowImagePath;
    }

    public int getGetSalesStatus() {
        return GetSalesStatus;
    }

    public void setGetSalesStatus(int GetSalesStatus) {
        this.GetSalesStatus = GetSalesStatus;
    }

    public String getShowLimtQuantity() {
        return ShowLimtQuantity;
    }

    public void setShowLimtQuantity(String ShowLimtQuantity) {
        this.ShowLimtQuantity = ShowLimtQuantity;
    }

    public List<String> getImages() {
        return Images;
    }

    public void setImages(List<String> Images) {
        this.Images = Images;
    }
}
