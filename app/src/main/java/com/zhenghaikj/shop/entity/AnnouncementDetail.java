package com.zhenghaikj.shop.entity;

import java.io.Serializable;

public class AnnouncementDetail implements Serializable {


    /**
     * AddDate : 2019-06-13T23:10:10
     * Title : 端午节放假通知
     * Content : <p>端午节商城放假通知---tzj</p>
     */

    private String AddDate;
    private String Title;
    private String Content;

    public String getAddDate() {
        return AddDate;
    }

    public void setAddDate(String AddDate) {
        this.AddDate = AddDate;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String Content) {
        this.Content = Content;
    }
}
