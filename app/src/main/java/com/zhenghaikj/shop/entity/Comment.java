package com.zhenghaikj.shop.entity;

import java.io.Serializable;
import java.util.List;

public class Comment implements Serializable {

    private String value;
    private boolean isSelect;

//    public Comment(String value, boolean isSelect) {
//        this.value = value;
//        this.isSelect = isSelect;
//    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    /*
    * AllCommentCount":1,所有评论条数
    * "GoodCount":1, 好评数
    * "MediumCount":0,中评数
    * "BadCount":0,差评数
    * "AppendCount":0,追加评论条数
    * "ImageCount":1,有图评论条数
    * "List":[]评论详情列表
    * */

    private String AllCommentCount;
    private String GoodCount;
    private String MediumCount;
    private String BadCount;
    private String AppendCount;
    private String ImageCount;
    private List<listData> result;

    public String getAllCommentCount() {
        return AllCommentCount;
    }

    public void setAllCommentCount(String allCommentCount) {
        AllCommentCount = allCommentCount;
    }

    public String getGoodCount() {
        return GoodCount;
    }

    public void setGoodCount(String goodCount) {
        GoodCount = goodCount;
    }

    public String getMediumCount() {
        return MediumCount;
    }

    public void setMediumCount(String mediumCount) {
        MediumCount = mediumCount;
    }

    public String getBadCount() {
        return BadCount;
    }

    public void setBadCount(String badCount) {
        BadCount = badCount;
    }

    public String getAppendCount() {
        return AppendCount;
    }

    public void setAppendCount(String appendCount) {
        AppendCount = appendCount;
    }

    public String getImageCount() {
        return ImageCount;
    }

    public void setImageCount(String imageCount) {
        ImageCount = imageCount;
    }

    public List<listData> getResult() {
        return result;
    }

    public void setResult(List<listData> result) {
        this.result = result;
    }

    public static class listData{
        /*
        * "Sku":"",
        * "UserName":"18892621501",
        * "ReviewContent":"呵呵红红火火恍恍惚惚",
        * "AppendContent":null,
        * "AppendDate":"",
        * "ReplyAppendContent":null,
        * "ReplyAppendDate":"",
        * "FinshDate":"2019-05-13 10:13:31",
        * "Images":[{"CommentImage":"http://mall.xigyu.com//Storage/Plat/Comment/64520190513104529602966.png"}],
        * "AppendImages":[],
        * "ReviewDate":"2019-05-13 10:46:10",
        * "ReplyContent":"暂无回复",
        * "ReplyDate":" ",
        * "ReviewMark":5,
        * "BuyDate":"2019-05-09 15:15:08",
        * "AppendDays":0
        * */

        private String Sku;
        private String UserName;
        private String ReviewContent;
        private String AppendContent;
        private String AppendDate;
        private String ReplyAppendContent;
        private String ReplyAppendDate;
        private String FinshDate;
        private String ReviewDate;
        private String ReplyContent;
        private String ReplyDate;
        private String BuyDate;
        private String AppendDays;
        private List<ImagesData> Images;
        private List<AppendImagesData> AppendImages;

        public String getSku() {
            return Sku;
        }

        public void setSku(String sku) {
            Sku = sku;
        }

        public String getUserName() {
            return UserName;
        }

        public void setUserName(String userName) {
            UserName = userName;
        }

        public String getReviewContent() {
            return ReviewContent;
        }

        public void setReviewContent(String reviewContent) {
            ReviewContent = reviewContent;
        }

        public String getAppendContent() {
            return AppendContent;
        }

        public void setAppendContent(String appendContent) {
            AppendContent = appendContent;
        }

        public String getAppendDate() {
            return AppendDate;
        }

        public void setAppendDate(String appendDate) {
            AppendDate = appendDate;
        }

        public String getReplyAppendContent() {
            return ReplyAppendContent;
        }

        public void setReplyAppendContent(String replyAppendContent) {
            ReplyAppendContent = replyAppendContent;
        }

        public String getReplyAppendDate() {
            return ReplyAppendDate;
        }

        public void setReplyAppendDate(String replyAppendDate) {
            ReplyAppendDate = replyAppendDate;
        }

        public String getFinshDate() {
            return FinshDate;
        }

        public void setFinshDate(String finshDate) {
            FinshDate = finshDate;
        }

        public String getReviewDate() {
            return ReviewDate;
        }

        public void setReviewDate(String reviewDate) {
            ReviewDate = reviewDate;
        }

        public String getReplyContent() {
            return ReplyContent;
        }

        public void setReplyContent(String replyContent) {
            ReplyContent = replyContent;
        }

        public String getReplyDate() {
            return ReplyDate;
        }

        public void setReplyDate(String replyDate) {
            ReplyDate = replyDate;
        }

        public String getBuyDate() {
            return BuyDate;
        }

        public void setBuyDate(String buyDate) {
            BuyDate = buyDate;
        }

        public String getAppendDays() {
            return AppendDays;
        }

        public void setAppendDays(String appendDays) {
            AppendDays = appendDays;
        }

        public java.util.List<ImagesData> getImages() {
            return Images;
        }

        public void setImages(java.util.List<ImagesData> images) {
            Images = images;
        }

        public java.util.List<AppendImagesData> getAppendImages() {
            return AppendImages;
        }

        public void setAppendImages(java.util.List<AppendImagesData> appendImages) {
            AppendImages = appendImages;
        }
    }


    public static class ImagesData{
        /*
        * "CommentImage":"http://mall.xigyu.com//Storage/Plat/Comment/64520190513104529602966.png"
        * */

        private String CommentImage;

        public String getCommentImage() {
            return CommentImage;
        }

        public void setCommentImage(String commentImage) {
            CommentImage = commentImage;
        }
    }

    public static class AppendImagesData{

    }
}
