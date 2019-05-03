package com.zhenghaikj.shop.entity;

import java.io.Serializable;

public class GetShopCoupResult implements Serializable {


    /*
    *
    * 1：成功
2：优惠券已经过期
3：达到个领取最大张数
4：优惠券已领完
5：积分不足
*/
    /**
     * Status : 1
     * Success : true
     */

    private int Status;
    private String Success;
    private  String ErrorMsg;
    public int getStatus() {
        return Status;
    }

    public void setStatus(int Status) {
        this.Status = Status;
    }

    public String getSuccess() {
        return Success;
    }

    public void setSuccess(String Success) {
        this.Success = Success;
    }

    public String getErrorMsg() {
        return ErrorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        ErrorMsg = errorMsg;
    }
}
