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
        * "UserName":"18767772222",
        * "ReviewContent":"加咯咯哦哦lol",
        * "AppendContent":"好几口人咯了",
        * "AppendDate":"2019-05-10 14:34:09",
        * "ReplyAppendContent":"是的发送到费",
        * "ReplyAppendDate":"2019-05-10 14:44:34",
        * "FinshDate":"",
        * "Images":[{"CommentImage":"http://mall.xigyu.com//Storage/Plat/Comment/65420190510143152868683.png"},{"CommentImage":"http://mall.xigyu.com//Storage/Plat/Comment/65420190510143200759342.png"},{"CommentImage":"http://mall.xigyu.com//Storage/Plat/Comment/65420190510143208165533.png"},{"CommentImage":"http://mall.xigyu.com//Storage/Plat/Comment/65420190510143214165524.png"},{"CommentImage":"http://mall.xigyu.com//Storage/Plat/Comment/65420190510143221149937.png"}],
        * "AppendImages":[{"CommentImage":"http://mall.xigyu.com//Storage/Plat/Comment/65420190510143408196773.png"}],
        * "ReviewDate":"2019-05-10 14:32:38",
        * "ReplyContent":"东方闪电",
        * "ReplyDate":"2019-05-10 14:44:34",
        * "ReviewMark":5,
        * "BuyDate":"2019-05-10 11:49:19",
        * "AppendDays":0
        *
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
        /*
        * "CommentImage":"http://mall.xigyu.com//Storage/Plat/Comment/65420190510143408196773.png"
        * */

        private String CommentImage;

        public String getCommentImage() {
            return CommentImage;
        }

        public void setCommentImage(String commentImage) {
            CommentImage = commentImage;
        }
    }
}
