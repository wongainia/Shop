package com.zhenghaikj.shop.entity;

import java.io.Serializable;
import java.util.List;

public class ShopResult implements Serializable {

    /*
    * "CouponList":[],
    * "CouponPageSize":6,
    * "CouponTotal":0,
    * "CouponMaxPage":1,
    * "GiftList":null,
    * "GiftListNew":[{"ShowSalesStatus":"可兑换","Id":77,"GiftName":"【松桂坊_五花腊肉500*2袋】 湖南湘西农家传统工艺 土猪特产腊肠腊肉烟熏腊肉","NeedIntegral":3000,"LimtQuantity":1,"StockQuantity":96,"EndDate":"\/Date(1559232000000)\/","NeedGrade":7,"VirtualSales":0,"RealSales":4,"SalesStatus":1,"ImagePath":"/Storage/Gift/77","Sequence":100,"GiftValue":88.00,"Description":null,"AddDate":"\/Date(1487216713000)\/","NeedGradeName":"黄金会员","GradeIntegral":5000,"DefaultShowImage":null,"SumSales":4,"ShowImagePath":"/Storage/Gift/77","GetSalesStatus":1,"ShowLimtQuantity":"1","Id":77}],
    * "GiftPageSize":12,
    * "GiftTotal":1,
    * "GiftMaxPage":1,
    * "MemberAvailableIntegrals":0,
    * "MemberGradeName":null
    *
    *
    * */

//    private List<CouponBean> CouponList;
    private String CouponPageSize;
    private String CouponTotal;
    private String CouponMaxPage;
    private String GiftPageSize;
    private String GiftTotal;
    private String GiftMaxPage;
    private String MemberAvailableIntegrals;
    private String MemberGradeName;
    private String GiftList;
    private List<GiftBean> GiftListNew;

//    public List<CouponBean> getCouponList() {
//        return CouponList;
//    }
//
//    public void setCouponList(List<CouponBean> couponList) {
//        CouponList = couponList;
//    }

    public String getCouponPageSize() {
        return CouponPageSize;
    }

    public void setCouponPageSize(String couponPageSize) {
        CouponPageSize = couponPageSize;
    }

    public String getCouponTotal() {
        return CouponTotal;
    }

    public void setCouponTotal(String couponTotal) {
        CouponTotal = couponTotal;
    }

    public String getCouponMaxPage() {
        return CouponMaxPage;
    }

    public void setCouponMaxPage(String couponMaxPage) {
        CouponMaxPage = couponMaxPage;
    }

    public String getGiftPageSize() {
        return GiftPageSize;
    }

    public void setGiftPageSize(String giftPageSize) {
        GiftPageSize = giftPageSize;
    }

    public String getGiftTotal() {
        return GiftTotal;
    }

    public void setGiftTotal(String giftTotal) {
        GiftTotal = giftTotal;
    }

    public String getGiftMaxPage() {
        return GiftMaxPage;
    }

    public void setGiftMaxPage(String giftMaxPage) {
        GiftMaxPage = giftMaxPage;
    }

    public String getMemberAvailableIntegrals() {
        return MemberAvailableIntegrals;
    }

    public void setMemberAvailableIntegrals(String memberAvailableIntegrals) {
        MemberAvailableIntegrals = memberAvailableIntegrals;
    }

    public String getMemberGradeName() {
        return MemberGradeName;
    }

    public void setMemberGradeName(String memberGradeName) {
        MemberGradeName = memberGradeName;
    }

    public String getGiftList() {
        return GiftList;
    }

    public void setGiftList(String giftList) {
        GiftList = giftList;
    }

    public List<GiftBean> getGiftListNew() {
        return GiftListNew;
    }

    public void setGiftListNew(List<GiftBean> giftListNew) {
        GiftListNew = giftListNew;
    }

    public static class CouponBean{

    }

    public static class GiftBean{
        /*
        * "ShowSalesStatus":"可兑换",
        * "Id":77,
        * "GiftName":"【松桂坊_五花腊肉500*2袋】 湖南湘西农家传统工艺 土猪特产腊肠腊肉烟熏腊肉",
        * "NeedIntegral":3000,
        * "LimtQuantity":1,
        * "StockQuantity":96,
        * "EndDate":"\/Date(1559232000000)\/",
        * "NeedGrade":7,
        * "VirtualSales":0,
        * "RealSales":4,
        * "SalesStatus":1,
        * "ImagePath":"/Storage/Gift/77",
        * "Sequence":100,
        * "GiftValue":88.00,
        * "Description":null,
        * "AddDate":"\/Date(1487216713000)\/",
        * "NeedGradeName":"黄金会员",
        * "GradeIntegral":5000,
        * "DefaultShowImage":null,
        * "SumSales":4,
        * "ShowImagePath":"/Storage/Gift/77",
        * "GetSalesStatus":1,
        * "ShowLimtQuantity":"1",
        *
         * */

        private String ShowSalesStatus;
        private String Id;
        private String GiftName;
        private String NeedIntegral;
        private String LimtQuantity;
        private String StockQuantity;
        private String EndDate;
        private String NeedGrade;
        private String VirtualSales;
        private String RealSales;
        private String SalesStatus;
        private String ImagePath;
        private String Sequence;
        private String GiftValue;
        private String Description;
        private String AddDate;
        private String NeedGradeName;
        private String GradeIntegral;
        private String DefaultShowImage;
        private String SumSales;
        private String ShowImagePath;
        private String GetSalesStatus;
        private String ShowLimtQuantity;

        public String getShowSalesStatus() {
            return ShowSalesStatus;
        }

        public void setShowSalesStatus(String showSalesStatus) {
            ShowSalesStatus = showSalesStatus;
        }

        public String getId() {
            return Id;
        }

        public void setId(String id) {
            Id = id;
        }

        public String getGiftName() {
            return GiftName;
        }

        public void setGiftName(String giftName) {
            GiftName = giftName;
        }

        public String getNeedIntegral() {
            return NeedIntegral;
        }

        public void setNeedIntegral(String needIntegral) {
            NeedIntegral = needIntegral;
        }

        public String getLimtQuantity() {
            return LimtQuantity;
        }

        public void setLimtQuantity(String limtQuantity) {
            LimtQuantity = limtQuantity;
        }

        public String getStockQuantity() {
            return StockQuantity;
        }

        public void setStockQuantity(String stockQuantity) {
            StockQuantity = stockQuantity;
        }

        public String getEndDate() {
            return EndDate;
        }

        public void setEndDate(String endDate) {
            EndDate = endDate;
        }

        public String getNeedGrade() {
            return NeedGrade;
        }

        public void setNeedGrade(String needGrade) {
            NeedGrade = needGrade;
        }

        public String getVirtualSales() {
            return VirtualSales;
        }

        public void setVirtualSales(String virtualSales) {
            VirtualSales = virtualSales;
        }

        public String getRealSales() {
            return RealSales;
        }

        public void setRealSales(String realSales) {
            RealSales = realSales;
        }

        public String getSalesStatus() {
            return SalesStatus;
        }

        public void setSalesStatus(String salesStatus) {
            SalesStatus = salesStatus;
        }

        public String getImagePath() {
            return ImagePath;
        }

        public void setImagePath(String imagePath) {
            ImagePath = imagePath;
        }

        public String getSequence() {
            return Sequence;
        }

        public void setSequence(String sequence) {
            Sequence = sequence;
        }

        public String getGiftValue() {
            return GiftValue;
        }

        public void setGiftValue(String giftValue) {
            GiftValue = giftValue;
        }

        public String getDescription() {
            return Description;
        }

        public void setDescription(String description) {
            Description = description;
        }

        public String getAddDate() {
            return AddDate;
        }

        public void setAddDate(String addDate) {
            AddDate = addDate;
        }

        public String getNeedGradeName() {
            return NeedGradeName;
        }

        public void setNeedGradeName(String needGradeName) {
            NeedGradeName = needGradeName;
        }

        public String getGradeIntegral() {
            return GradeIntegral;
        }

        public void setGradeIntegral(String gradeIntegral) {
            GradeIntegral = gradeIntegral;
        }

        public String getDefaultShowImage() {
            return DefaultShowImage;
        }

        public void setDefaultShowImage(String defaultShowImage) {
            DefaultShowImage = defaultShowImage;
        }

        public String getSumSales() {
            return SumSales;
        }

        public void setSumSales(String sumSales) {
            SumSales = sumSales;
        }

        public String getShowImagePath() {
            return ShowImagePath;
        }

        public void setShowImagePath(String showImagePath) {
            ShowImagePath = showImagePath;
        }

        public String getGetSalesStatus() {
            return GetSalesStatus;
        }

        public void setGetSalesStatus(String getSalesStatus) {
            GetSalesStatus = getSalesStatus;
        }

        public String getShowLimtQuantity() {
            return ShowLimtQuantity;
        }

        public void setShowLimtQuantity(String showLimtQuantity) {
            ShowLimtQuantity = showLimtQuantity;
        }
    }
}
