package com.zhenghaikj.shop.entity;

import java.io.Serializable;

public class EvaluatePhotoEntity implements Serializable {


    /**
     * Success : true
     * Src : /temp/20190515105334243405.png
     * RomoteImage : http://mall.xigyu.com//temp/20190515105334243405.png
     */

    private boolean Success;
    private String Src;
    private String RomoteImage;
    private String Msg;

    public String getMsg() {
        return Msg;
    }

    public void setMsg(String msg) {
        Msg = msg;
    }

    public boolean isSuccess() {
        return Success;
    }

    public void setSuccess(boolean Success) {
        this.Success = Success;
    }

    public String getSrc() {
        return Src;
    }

    public void setSrc(String Src) {
        this.Src = Src;
    }

    public String getRomoteImage() {
        return RomoteImage;
    }

    public void setRomoteImage(String RomoteImage) {
        this.RomoteImage = RomoteImage;
    }
}
