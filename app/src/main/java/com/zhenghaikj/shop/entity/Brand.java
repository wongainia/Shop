package com.zhenghaikj.shop.entity;

import java.io.Serializable;

public class Brand implements Serializable {
    /**
     * Id : 1
     * NBrandID : 1
     * ScategoryID : 250
     * FBrandName : 美的
     * IsUse : Y
     * Categorys : null
     * Version : 0
     */

    private int Id;
    private String NBrandID;
    private int ScategoryID;
    private String FBrandName;
    private String IsUse;
    private Object Categorys;
    private int Version;

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getNBrandID() {
        return NBrandID;
    }

    public void setNBrandID(String NBrandID) {
        this.NBrandID = NBrandID;
    }

    public int getScategoryID() {
        return ScategoryID;
    }

    public void setScategoryID(int ScategoryID) {
        this.ScategoryID = ScategoryID;
    }

    public String getFBrandName() {
        return FBrandName;
    }

    public void setFBrandName(String FBrandName) {
        this.FBrandName = FBrandName;
    }

    public String getIsUse() {
        return IsUse;
    }

    public void setIsUse(String IsUse) {
        this.IsUse = IsUse;
    }

    public Object getCategorys() {
        return Categorys;
    }

    public void setCategorys(Object Categorys) {
        this.Categorys = Categorys;
    }

    public int getVersion() {
        return Version;
    }

    public void setVersion(int Version) {
        this.Version = Version;
    }


    /**
     * Id : 6
     * FBrandID : 6
     * UserID : admin
     * FBrandName : 格力
     * IsUse : Y
     * Version : 0
     */

    /*private String Id;
    private String FBrandID;
    private String UserID;
    private String FBrandName;
    private String IsUse;
    private String Version;

    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public String getFBrandID() {
        return FBrandID;
    }

    public void setFBrandID(String FBrandID) {
        this.FBrandID = FBrandID;
    }

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String UserID) {
        this.UserID = UserID;
    }

    public String getFBrandName() {
        return FBrandName;
    }

    public void setFBrandName(String FBrandName) {
        this.FBrandName = FBrandName;
    }

    public String getIsUse() {
        return IsUse;
    }

    public void setIsUse(String IsUse) {
        this.IsUse = IsUse;
    }

    public String getVersion() {
        return Version;
    }

    public void setVersion(String Version) {
        this.Version = Version;
    }*/
}

