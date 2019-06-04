package com.zhenghaikj.shop.entity;

import java.io.Serializable;
import java.util.List;

public class ShopResult implements Serializable {


    /**
     * CouponList : []
     * CouponPageSize : 6
     * CouponTotal : 0
     * CouponMaxPage : 1
     * GiftList : null
     * GiftPageSize : 12
     * GiftTotal : 1
     * GiftMaxPage : 1
     * MemberAvailableIntegrals : 0
     * MemberGradeName : null
     * GiftListNew : [{"ShowSalesStatus":"可兑换","Id":76,"GiftName":"澳洲Healthy Care金装蜂胶软胶囊 200粒/瓶 提高免疫美白养颜 海外原装进口","NeedIntegral":2200,"LimtQuantity":1,"StockQuantity":100,"EndDateStr":"2019-12-7 1:5:0","NeedGrade":6,"VirtualSales":100,"RealSales":0,"SalesStatus":1,"ImagePath":"/Storage/Gift/76","Sequence":100,"GiftValue":399,"Description":null,"AddDateStr":"2017-2-16 11:42:58","NeedGradeName":"普通会员","GradeIntegral":0,"DefaultShowImage":0,"SumSales":100,"ShowImagePath":"/Storage/Gift/76","ShowLimtQuantity":"1","GetSalesStatus":1}]
     */

    private int CouponPageSize;
    private int CouponTotal;
    private int CouponMaxPage;
    private String GiftList;
    private int GiftPageSize;
    private int GiftTotal;
    private int GiftMaxPage;
    private int MemberAvailableIntegrals;
    private String MemberGradeName;
    private List<?> CouponList;
    private List<GiftListNewBean> GiftListNew;

    public int getCouponPageSize() {
        return CouponPageSize;
    }

    public void setCouponPageSize(int CouponPageSize) {
        this.CouponPageSize = CouponPageSize;
    }

    public int getCouponTotal() {
        return CouponTotal;
    }

    public void setCouponTotal(int CouponTotal) {
        this.CouponTotal = CouponTotal;
    }

    public int getCouponMaxPage() {
        return CouponMaxPage;
    }

    public void setCouponMaxPage(int CouponMaxPage) {
        this.CouponMaxPage = CouponMaxPage;
    }

    public String getGiftList() {
        return GiftList;
    }

    public void setGiftList(String giftList) {
        GiftList = giftList;
    }

    public void setMemberGradeName(String memberGradeName) {
        MemberGradeName = memberGradeName;
    }

    public int getGiftPageSize() {
        return GiftPageSize;
    }

    public void setGiftPageSize(int GiftPageSize) {
        this.GiftPageSize = GiftPageSize;
    }

    public int getGiftTotal() {
        return GiftTotal;
    }

    public void setGiftTotal(int GiftTotal) {
        this.GiftTotal = GiftTotal;
    }

    public int getGiftMaxPage() {
        return GiftMaxPage;
    }

    public void setGiftMaxPage(int GiftMaxPage) {
        this.GiftMaxPage = GiftMaxPage;
    }

    public int getMemberAvailableIntegrals() {
        return MemberAvailableIntegrals;
    }

    public void setMemberAvailableIntegrals(int MemberAvailableIntegrals) {
        this.MemberAvailableIntegrals = MemberAvailableIntegrals;
    }


    public List<?> getCouponList() {
        return CouponList;
    }

    public void setCouponList(List<?> CouponList) {
        this.CouponList = CouponList;
    }

    public List<GiftListNewBean> getGiftListNew() {
        return GiftListNew;
    }

    public void setGiftListNew(List<GiftListNewBean> GiftListNew) {
        this.GiftListNew = GiftListNew;
    }

    public static class GiftListNewBean {
        /**
         * ShowSalesStatus : 可兑换
         * Id : 76
         * GiftName : 澳洲Healthy Care金装蜂胶软胶囊 200粒/瓶 提高免疫美白养颜 海外原装进口
         * NeedIntegral : 2200
         * LimtQuantity : 1
         * StockQuantity : 100
         * EndDateStr : 2019-12-7 1:5:0
         * NeedGrade : 6
         * VirtualSales : 100
         * RealSales : 0
         * SalesStatus : 1
         * ImagePath : /Storage/Gift/76
         * Sequence : 100
         * GiftValue : 399
         * Description : null
         * AddDateStr : 2017-2-16 11:42:58
         * NeedGradeName : 普通会员
         * GradeIntegral : 0
         * DefaultShowImage : 0
         * SumSales : 100
         * ShowImagePath : /Storage/Gift/76
         * ShowLimtQuantity : 1
         * GetSalesStatus : 1
         */

        private String ShowSalesStatus;
        private String Id;
        private String GiftName;
        private int NeedIntegral;
        private int LimtQuantity;
        private int StockQuantity;
        private String EndDateStr;
        private int NeedGrade;
        private int VirtualSales;
        private int RealSales;
        private int SalesStatus;
        private String ImagePath;
        private int Sequence;
        private int GiftValue;
        private Object Description;
        private String AddDateStr;
        private String NeedGradeName;
        private int GradeIntegral;
        private int DefaultShowImage;
        private int SumSales;
        private String ShowImagePath;
        private String ShowLimtQuantity;
        private int GetSalesStatus;

        public String getShowSalesStatus() {
            return ShowSalesStatus;
        }

        public void setShowSalesStatus(String ShowSalesStatus) {
            this.ShowSalesStatus = ShowSalesStatus;
        }

        public String getId() {
            return Id;
        }

        public void setId(String Id) {
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

        public String getEndDateStr() {
            return EndDateStr;
        }

        public void setEndDateStr(String EndDateStr) {
            this.EndDateStr = EndDateStr;
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

        public int getGiftValue() {
            return GiftValue;
        }

        public void setGiftValue(int GiftValue) {
            this.GiftValue = GiftValue;
        }

        public Object getDescription() {
            return Description;
        }

        public void setDescription(Object Description) {
            this.Description = Description;
        }

        public String getAddDateStr() {
            return AddDateStr;
        }

        public void setAddDateStr(String AddDateStr) {
            this.AddDateStr = AddDateStr;
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

        public int getDefaultShowImage() {
            return DefaultShowImage;
        }

        public void setDefaultShowImage(int DefaultShowImage) {
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

        public String getShowLimtQuantity() {
            return ShowLimtQuantity;
        }

        public void setShowLimtQuantity(String ShowLimtQuantity) {
            this.ShowLimtQuantity = ShowLimtQuantity;
        }

        public int getGetSalesStatus() {
            return GetSalesStatus;
        }

        public void setGetSalesStatus(int GetSalesStatus) {
            this.GetSalesStatus = GetSalesStatus;
        }
    }
}
