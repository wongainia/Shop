package com.zhenghaikj.shop.entity;

import java.io.Serializable;

public class PersonalInformation implements Serializable {

    /**
     * Success : true
     * UserName : 18767776789
     * UserId : 669
     * Photo : http://mall.xigyu.com//avator/20190516144009743673c0b85a17df3e47619b5375e95429a0d7.png
     * AllOrders : 2
     * WaitingForPay : 2
     * WaitingForRecieve : 0
     * WaitingForDelivery : 0
     * WaitingForComments : 0
     * RefundOrders : 0
     * CellPhone : 18767776789
     * FavoriteShop : 0
     * FavoriteProduct : 0
     * Counpon : 0
     * Stringegral : 0
     * GroupTotal : 0
     * MyGroup : 0
     */

    private boolean Success;
    private String UserName;
    private String Nick;
    private String UserId;
    private String Photo;
    private String AllOrders;
    private String WaitingForPay;
    private String WaitingForRecieve;
    private String WaitingForDelivery;
    private String WaitingForComments;
    private String RefundOrders;
    private String CellPhone;
    private String FavoriteShop;
    private String FavoriteProduct;
    private String Counpon;
    private String Stringegral;
    private String GroupTotal;
    private String MyGroup;

    public boolean isSuccess() {
        return Success;
    }

    public void setSuccess(boolean success) {
        Success = success;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String UserName) {
        this.UserName = UserName;
    }

    public String getNick() {
        return Nick;
    }

    public void setNick(String nick) {
        Nick = nick;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String UserId) {
        this.UserId = UserId;
    }

    public String getPhoto() {
        return Photo;
    }

    public void setPhoto(String Photo) {
        this.Photo = Photo;
    }

    public String getAllOrders() {
        return AllOrders;
    }

    public void setAllOrders(String AllOrders) {
        this.AllOrders = AllOrders;
    }

    public String getWaitingForPay() {
        return WaitingForPay;
    }

    public void setWaitingForPay(String WaitingForPay) {
        this.WaitingForPay = WaitingForPay;
    }

    public String getWaitingForRecieve() {
        return WaitingForRecieve;
    }

    public void setWaitingForRecieve(String WaitingForRecieve) {
        this.WaitingForRecieve = WaitingForRecieve;
    }

    public String getWaitingForDelivery() {
        return WaitingForDelivery;
    }

    public void setWaitingForDelivery(String WaitingForDelivery) {
        this.WaitingForDelivery = WaitingForDelivery;
    }

    public String getWaitingForComments() {
        return WaitingForComments;
    }

    public void setWaitingForComments(String WaitingForComments) {
        this.WaitingForComments = WaitingForComments;
    }

    public String getRefundOrders() {
        return RefundOrders;
    }

    public void setRefundOrders(String RefundOrders) {
        this.RefundOrders = RefundOrders;
    }

    public String getCellPhone() {
        return CellPhone;
    }

    public void setCellPhone(String CellPhone) {
        this.CellPhone = CellPhone;
    }

    public String getFavoriteShop() {
        return FavoriteShop;
    }

    public void setFavoriteShop(String FavoriteShop) {
        this.FavoriteShop = FavoriteShop;
    }

    public String getFavoriteProduct() {
        return FavoriteProduct;
    }

    public void setFavoriteProduct(String FavoriteProduct) {
        this.FavoriteProduct = FavoriteProduct;
    }

    public String getCounpon() {
        return Counpon;
    }

    public void setCounpon(String Counpon) {
        this.Counpon = Counpon;
    }

    public String getStringegral() {
        return Stringegral;
    }

    public void setStringegral(String Stringegral) {
        this.Stringegral = Stringegral;
    }

    public String getGroupTotal() {
        return GroupTotal;
    }

    public void setGroupTotal(String GroupTotal) {
        this.GroupTotal = GroupTotal;
    }

    public String getMyGroup() {
        return MyGroup;
    }

    public void setMyGroup(String MyGroup) {
        this.MyGroup = MyGroup;
    }
}
