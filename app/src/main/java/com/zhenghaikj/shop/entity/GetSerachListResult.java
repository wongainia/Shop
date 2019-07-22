package com.zhenghaikj.shop.entity;

import java.io.Serializable;
import java.util.List;

public class GetSerachListResult implements Serializable {


    /**
     * DataList : [{"ShowSalesStatus":"可兑换","Id":203,"GiftName":"小米九号平衡车成人体感智能骑行遥控漂移代步电动九号平衡车","NeedIntegral":5997,"LimtQuantity":1,"StockQuantity":99,"EndDate":"2020-07-22T10:05:12","NeedGrade":0,"VirtualSales":0,"RealSales":0,"SalesStatus":1,"ImagePath":"/Storage/Gift/203","Sequence":100,"GiftValue":11994,"Description":null,"AddDate":"2019-07-22T10:13:17","NeedGradeName":"不限等级","GradeIntegral":0,"DefaultShowImage":"http://mall.xigyu.com//Storage/Gift/203/1_100.png","SumSales":0,"ShowImagePath":"/Storage/Gift/203","GetSalesStatus":1,"ShowLimtQuantity":"1"}]
     * PageSize : 10
     * Total : 1
     * MaxPage : 1
     */

    private int PageSize;
    private int Total;
    private int MaxPage;
    private List<DataListBean> DataList;

    public int getPageSize() {
        return PageSize;
    }

    public void setPageSize(int PageSize) {
        this.PageSize = PageSize;
    }

    public int getTotal() {
        return Total;
    }

    public void setTotal(int Total) {
        this.Total = Total;
    }

    public int getMaxPage() {
        return MaxPage;
    }

    public void setMaxPage(int MaxPage) {
        this.MaxPage = MaxPage;
    }

    public List<DataListBean> getDataList() {
        return DataList;
    }

    public void setDataList(List<DataListBean> DataList) {
        this.DataList = DataList;
    }

    public static class DataListBean {
        /**
         * ShowSalesStatus : 可兑换
         * Id : 203
         * GiftName : 小米九号平衡车成人体感智能骑行遥控漂移代步电动九号平衡车
         * NeedIntegral : 5997
         * LimtQuantity : 1
         * StockQuantity : 99
         * EndDate : 2020-07-22T10:05:12
         * NeedGrade : 0
         * VirtualSales : 0
         * RealSales : 0
         * SalesStatus : 1
         * ImagePath : /Storage/Gift/203
         * Sequence : 100
         * GiftValue : 11994.0
         * Description : null
         * AddDate : 2019-07-22T10:13:17
         * NeedGradeName : 不限等级
         * GradeIntegral : 0
         * DefaultShowImage : http://mall.xigyu.com//Storage/Gift/203/1_100.png
         * SumSales : 0
         * ShowImagePath : /Storage/Gift/203
         * GetSalesStatus : 1
         * ShowLimtQuantity : 1
         */

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
        private Object Description;
        private String AddDate;
        private String NeedGradeName;
        private int GradeIntegral;
        private String DefaultShowImage;
        private int SumSales;
        private String ShowImagePath;
        private int GetSalesStatus;
        private String ShowLimtQuantity;

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
            return EndDate;
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

        public Object getDescription() {
            return Description;
        }

        public void setDescription(Object Description) {
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
    }
}
