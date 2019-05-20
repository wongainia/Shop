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

    public JsonStrOrderPay(long orderId, String bisId, double totalMoney) {
        OrderId = orderId;
        BisId = bisId;
        TotalMoney = totalMoney;
    }

    public long getOrderId() {
        return OrderId;
    }

    public void setOrderId(long OrderId) {
        this.OrderId = OrderId;
    }

    public String getBisId() {
        return BisId;
    }

    public void setBisId(String BisId) {
        this.BisId = BisId;
    }

    public double getTotalMoney() {
        return TotalMoney;
    }

    public void setTotalMoney(double TotalMoney) {
        this.TotalMoney = TotalMoney;
    }
}
