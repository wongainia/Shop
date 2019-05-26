package com.zhenghaikj.shop.entity;

import java.io.Serializable;
import java.util.List;

public class Express implements Serializable {

    /*
    * "Success":"true",
    * "ExpressNum":"348710306900",
    * "ExpressCompanyName":"顺丰快递",
    * "Comment":{"Success":false,"Msg":"暂无物流信息","Data":[]}
    * */

    private boolean Success;
    private String ExpressNum;
    private String ExpressCompanyName;
//    private List<CommentBean> Comment;

    public boolean isSuccess() {
        return Success;
    }

    public void setSuccess(boolean success) {
        Success = success;
    }

    public String getExpressNum() {
        return ExpressNum;
    }

    public void setExpressNum(String expressNum) {
        ExpressNum = expressNum;
    }

    public String getExpressCompanyName() {
        return ExpressCompanyName;
    }

    public void setExpressCompanyName(String expressCompanyName) {
        ExpressCompanyName = expressCompanyName;
    }

//    public List<CommentBean> getComment() {
//        return Comment;
//    }
//
//    public void setComment(List<CommentBean> comment) {
//        Comment = comment;
//    }

    public static class CommentBean{
        /*
        * Success":false,"Msg":"暂无物流信息","Data":[]
        * */


    }
}
