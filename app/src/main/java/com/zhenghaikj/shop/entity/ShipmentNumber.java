package com.zhenghaikj.shop.entity;

import java.io.Serializable;

public class ShipmentNumber implements Serializable {

    /**
     * Success : true
     * ExpressNum : 91050944202
     */

    private String Success;
    private String ExpressNum;
    private String ExpressCompany;

    public String getExpressCompany() {
        return ExpressCompany;
    }

    public void setExpressCompany(String expressCompany) {
        ExpressCompany = expressCompany;
    }

    public String getSuccess() {
        return Success;
    }

    public void setSuccess(String Success) {
        this.Success = Success;
    }

    public String getExpressNum() {
        return ExpressNum;
    }

    public void setExpressNum(String ExpressNum) {
        this.ExpressNum = ExpressNum;
    }
}
