package com.zhenghaikj.shop.entity;

import java.io.Serializable;

public class CoinRecord implements Serializable {

    /**
     * Id : 13
     * ConID : 13
     * UserID : 18767772222
     * Invitee : null
     * ConCount : 3000
     * OrderID : 1201905300349025
     * State : 2
     * Memo : null
     * Nowtime : 2019-05-30T00:41:55
     * IsUse : Y
     * page : 0
     * limit : 0
     * Version : 0
     */

    private int Id;
    private int ConID;
    private String UserID;
    private String Invitee;
    private int ConCount;
    private long OrderID;
    private String State;
    private String Memo;
    private String Nowtime;
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

    public int getConID() {
        return ConID;
    }

    public void setConID(int ConID) {
        this.ConID = ConID;
    }

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String UserID) {
        this.UserID = UserID;
    }

    public String getInvitee() {
        return Invitee;
    }

    public void setInvitee(String Invitee) {
        this.Invitee = Invitee;
    }

    public int getConCount() {
        return ConCount;
    }

    public void setConCount(int ConCount) {
        this.ConCount = ConCount;
    }

    public long getOrderID() {
        return OrderID;
    }

    public void setOrderID(long OrderID) {
        this.OrderID = OrderID;
    }

    public String getState() {
        return State;
    }

    public void setState(String State) {
        this.State = State;
    }

    public String getMemo() {
        return Memo;
    }

    public void setMemo(String Memo) {
        this.Memo = Memo;
    }

    public String getNowtime() {
        return Nowtime.replace("T"," ");
    }

    public void setNowtime(String Nowtime) {
        this.Nowtime = Nowtime;
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
