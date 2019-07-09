package com.zhenghaikj.shop.entity;

public class JsonStrOrderPay {

    /**
     * OrderId : 1111
     * BisId : 111
     * TotalMoney : 123
     */

    private long OrderId;
    private String BisId;
    private double TotalMoney;
    private double ActualMoney;

    public JsonStrOrderPay(long orderId, String bisId, double totalMoney, double actualMoney) {
        OrderId = orderId;
        BisId = bisId;
        TotalMoney = totalMoney;
        ActualMoney = actualMoney;
    }

    public long getOrderId() {
        return OrderId;
    }

    public void setOrderId(long orderId) {
        OrderId = orderId;
    }

    public String getBisId() {
        return BisId;
    }

    public void setBisId(String bisId) {
        BisId = bisId;
    }

    public double getTotalMoney() {
        return TotalMoney;
    }

    public void setTotalMoney(double totalMoney) {
        TotalMoney = totalMoney;
    }

    public double getActualMoney() {
        return ActualMoney;
    }

    public void setActualMoney(double actualMoney) {
        ActualMoney = actualMoney;
    }
}
