package com.zhenghaikj.shop.entity;

import java.util.List;

public class CommentsInfo {
    private int ProductId;
    private String CommentContent;
    public List<String> CommentImgs;
    private double StarCount;

    public int getProductId() {
        return ProductId;
    }

    public void setProductId(int productId) {
        ProductId = productId;
    }

    public String getCommentContent() {
        return CommentContent;
    }

    public void setCommentContent(String commentContent) {
        CommentContent = commentContent;
    }

    public List<String> getCommentImgs() {
        return CommentImgs;
    }

    public void setCommentImgs(List<String> commentImgs) {
        CommentImgs = commentImgs;
    }

    public double getStarCount() {
        return StarCount;
    }

    public void setStarCount(double starCount) {
        StarCount = starCount;
    }

}
