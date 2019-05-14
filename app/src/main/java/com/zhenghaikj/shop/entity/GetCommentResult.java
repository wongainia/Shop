package com.zhenghaikj.shop.entity;

import java.io.Serializable;
import java.util.List;

public class GetCommentResult implements Serializable {

    /**
     * success : true
     * data : [{"Sku":"","UserName":"18892621501","ReviewContent":"呵呵红红火火恍恍惚惚","AppendContent":null,"AppendDate":null,"ReplyAppendContent":null,"ReplyAppendDate":null,"FinshDate":"2019-05-13T10:13:31","Images":["http://mall.xigyu.com//Storage/Plat/Comment/64520190513104529602966.png"],"AppendImages":[],"ReviewDate":"2019-05-13T10:46:10","ReplyContent":"暂无回复","ReplyDate":null,"ReviewMark":5,"BuyDate":"2019-05-09T15:15:08"}]
     */

    private boolean success;
    private List<DataBean> data;
    private String ErrorMsg;
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public String getErrorMsg() {
        return ErrorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        ErrorMsg = errorMsg;
    }

    public static class DataBean {
        /**
         * Sku :
         * UserName : 18892621501
         * ReviewContent : 呵呵红红火火恍恍惚惚
         * AppendContent : null
         * AppendDate : null
         * ReplyAppendContent : null
         * ReplyAppendDate : null
         * FinshDate : 2019-05-13T10:13:31
         * Images : ["http://mall.xigyu.com//Storage/Plat/Comment/64520190513104529602966.png"]
         * AppendImages : []
         * ReviewDate : 2019-05-13T10:46:10
         * ReplyContent : 暂无回复
         * ReplyDate : null
         * ReviewMark : 5
         * BuyDate : 2019-05-09T15:15:08
         */

        private String Sku;
        private String UserName;
        private String ReviewContent;
        private Object AppendContent;
        private Object AppendDate;
        private Object ReplyAppendContent;
        private Object ReplyAppendDate;
        private String FinshDate;
        private String ReviewDate;
        private String ReplyContent;
        private Object ReplyDate;
        private int ReviewMark;
        private String BuyDate;
        private List<String> Images;
        private List<?> AppendImages;

        public String getSku() {
            return Sku;
        }

        public void setSku(String Sku) {
            this.Sku = Sku;
        }

        public String getUserName() {
            return UserName;
        }

        public void setUserName(String UserName) {
            this.UserName = UserName;
        }

        public String getReviewContent() {
            return ReviewContent;
        }

        public void setReviewContent(String ReviewContent) {
            this.ReviewContent = ReviewContent;
        }

        public Object getAppendContent() {
            return AppendContent;
        }

        public void setAppendContent(Object AppendContent) {
            this.AppendContent = AppendContent;
        }

        public Object getAppendDate() {
            return AppendDate;
        }

        public void setAppendDate(Object AppendDate) {
            this.AppendDate = AppendDate;
        }

        public Object getReplyAppendContent() {
            return ReplyAppendContent;
        }

        public void setReplyAppendContent(Object ReplyAppendContent) {
            this.ReplyAppendContent = ReplyAppendContent;
        }

        public Object getReplyAppendDate() {
            return ReplyAppendDate;
        }

        public void setReplyAppendDate(Object ReplyAppendDate) {
            this.ReplyAppendDate = ReplyAppendDate;
        }

        public String getFinshDate() {
            return FinshDate;
        }

        public void setFinshDate(String FinshDate) {
            this.FinshDate = FinshDate;
        }

        public String getReviewDate() {
            return ReviewDate;
        }

        public void setReviewDate(String ReviewDate) {
            this.ReviewDate = ReviewDate;
        }

        public String getReplyContent() {
            return ReplyContent;
        }

        public void setReplyContent(String ReplyContent) {
            this.ReplyContent = ReplyContent;
        }

        public Object getReplyDate() {
            return ReplyDate;
        }

        public void setReplyDate(Object ReplyDate) {
            this.ReplyDate = ReplyDate;
        }

        public int getReviewMark() {
            return ReviewMark;
        }

        public void setReviewMark(int ReviewMark) {
            this.ReviewMark = ReviewMark;
        }

        public String getBuyDate() {
            return BuyDate;
        }

        public void setBuyDate(String BuyDate) {
            this.BuyDate = BuyDate;
        }

        public List<String> getImages() {
            return Images;
        }

        public void setImages(List<String> Images) {
            this.Images = Images;
        }

        public List<?> getAppendImages() {
            return AppendImages;
        }

        public void setAppendImages(List<?> AppendImages) {
            this.AppendImages = AppendImages;
        }
    }
}
