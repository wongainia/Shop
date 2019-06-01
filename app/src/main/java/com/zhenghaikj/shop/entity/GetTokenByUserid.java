package com.zhenghaikj.shop.entity;

import java.io.Serializable;

public class GetTokenByUserid implements Serializable {


    /**
     * Id : 6
     * AccountAdmintokenID : 6
     * UserID : 18892621501
     * Nowtime : 2019-06-01T10:03:26
     * Token : 633fed7d-6a61-46a1-8ce0-008eab11fa4d
     * IsUse : Y
     * page : 1
     * limit : 999
     * Version : 0
     */

    private int Id;
    private int AccountAdmintokenID;
    private String UserID;
    private String Nowtime;
    private String Token;
    private String IsUse;
    private int page;
    private int limit;
    private int Version;

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public int getAccountAdmintokenID() {
        return AccountAdmintokenID;
    }

    public void setAccountAdmintokenID(int AccountAdmintokenID) {
        this.AccountAdmintokenID = AccountAdmintokenID;
    }

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String UserID) {
        this.UserID = UserID;
    }

    public String getNowtime() {
        return Nowtime;
    }

    public void setNowtime(String Nowtime) {
        this.Nowtime = Nowtime;
    }

    public String getToken() {
        return Token;
    }

    public void setToken(String Token) {
        this.Token = Token;
    }

    public String getIsUse() {
        return IsUse;
    }

    public void setIsUse(String IsUse) {
        this.IsUse = IsUse;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getVersion() {
        return Version;
    }

    public void setVersion(int Version) {
        this.Version = Version;
    }
}
