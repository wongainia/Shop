package com.zhenghaikj.shop.entity;

import java.io.Serializable;
import java.util.List;

public class ConfirmModel implements Serializable {


    /**
     * Success : true
     * OrderIds : [2019052165244145]
     * PayInfo : [{"BisId":"selleradmin:seller","OrderId":2019052165244145,"TotalMoney":7457}]
     * RealTotalIsZero : false
     */

    private String Success;
    private String Msg;
    private boolean RealTotalIsZero;
    private List<Long> OrderIds;
    private List<PayInfoBean> PayInfo;

    public String getMsg() {
        return Msg;
    }

    public void setMsg(String msg) {
        Msg = msg;
    }

    public String getSuccess() {
        return Success;
    }

    public void setSuccess(String Success) {
        this.Success = Success;
    }

    public boolean isRealTotalIsZero() {
        return RealTotalIsZero;
    }

    public void setRealTotalIsZero(boolean RealTotalIsZero) {
        this.RealTotalIsZero = RealTotalIsZero;
    }

    public List<Long> getOrderIds() {
        return OrderIds;
    }

    public void setOrderIds(List<Long> OrderIds) {
        this.OrderIds = OrderIds;
    }

    public List<PayInfoBean> getPayInfo() {
        return PayInfo;
    }

    public void setPayInfo(List<PayInfoBean> PayInfo) {
        this.PayInfo = PayInfo;
    }

    public static class PayInfoBean {
        /**
         * BisId : selleradmin:seller
         * OrderId : 2019052165244145
         * TotalMoney : 7457.0
         */

        private String BisId;
        private long OrderId;
        private double TotalMoney;
        private double ActualMoney;

        public String getBisId() {
            return BisId;
        }

        public void setBisId(String BisId) {
            this.BisId = BisId;
        }

        public long getOrderId() {
            return OrderId;
        }

        public void setOrderId(long OrderId) {
            this.OrderId = OrderId;
        }

        public double getTotalMoney() {
            return TotalMoney;
        }

        public void setTotalMoney(double TotalMoney) {
            this.TotalMoney = TotalMoney;
        }

        public double getActualMoney() {
            return ActualMoney;
        }

        public void setActualMoney(double actualMoney) {
            ActualMoney = actualMoney;
        }
    }
}
