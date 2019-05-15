package com.zhenghaikj.shop.entity;

import java.util.List;

public class CommentsInfo {
    private String orderItemId;
    private String CommentContent;
    public List<String> CommentImgs;
    private String mark;
    private List<String> Src; //发到服务端的地址

    public String getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(String orderItemId) {
        this.orderItemId = orderItemId;
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

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public List<String> getSrc() {
        return Src;
    }

    public void setSrc(List<String> src) {
        Src = src;
    }
}
