package com.zhenghaikj.shop.entity;

import java.io.Serializable;

public class PersonalInformation implements Serializable {
    /*
    * "Success":"true",请求状态
    * "UserName":"xiaohe",用户名
    * "UserId":283,用户ID
    * "Photo":"",用户头像
    * "AllOrders":"131",订单总数
    * "WaitingForPay":"0",待付款订单数
    * "WaitingForRecieve":"0",待发货订单数
    * "WaitingForComments":"10",待评价订单数
    * "FavoriteShop":"3",收藏的店铺数
    * "FavoriteProduct": "4",收藏的商品数
    * "Counpon": "12"未使用的优惠券数
    * MyGroup: 我的拼团
    * */

    private boolean Success;
    private String UserName;
    private String Photo;
    private String AllOrders;
    private String WaitingForPay;
    private String WaitingForRecieve;
    private String WaitingForComments;
    private String FavoriteShop;
    private String FavoriteProduct;
    private String Counpon;
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

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getPhoto() {
        return Photo;
    }

    public void setPhoto(String photo) {
        Photo = photo;
    }

    public String getAllOrders() {
        return AllOrders;
    }

    public void setAllOrders(String allOrders) {
        AllOrders = allOrders;
    }

    public String getWaitingForPay() {
        return WaitingForPay;
    }

    public void setWaitingForPay(String waitingForPay) {
        WaitingForPay = waitingForPay;
    }

    public String getWaitingForRecieve() {
        return WaitingForRecieve;
    }

    public void setWaitingForRecieve(String waitingForRecieve) {
        WaitingForRecieve = waitingForRecieve;
    }

    public String getWaitingForComments() {
        return WaitingForComments;
    }

    public void setWaitingForComments(String waitingForComments) {
        WaitingForComments = waitingForComments;
    }

    public String getFavoriteShop() {
        return FavoriteShop;
    }

    public void setFavoriteShop(String favoriteShop) {
        FavoriteShop = favoriteShop;
    }

    public String getFavoriteProduct() {
        return FavoriteProduct;
    }

    public void setFavoriteProduct(String favoriteProduct) {
        FavoriteProduct = favoriteProduct;
    }

    public String getCounpon() {
        return Counpon;
    }

    public void setCounpon(String counpon) {
        Counpon = counpon;
    }

    public String getMyGroup() {
        return MyGroup;
    }

    public void setMyGroup(String myGroup) {
        MyGroup = myGroup;
    }
}
