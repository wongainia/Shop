package com.zhenghaikj.shop.entity;

import java.io.Serializable;
import java.util.List;

public class ConfirmModel implements Serializable {

    /**
     * Success : true
     * OrderIds : [2019050944667548]
     * RealTotalIsZero : false
     */

    private String Success;
    private String Msg;
    private boolean RealTotalIsZero;
    private List<Long> OrderIds;

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
}
