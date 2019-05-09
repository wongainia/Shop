package com.zhenghaikj.shop.entity;

import java.io.Serializable;
import java.util.List;

public class CollectionShop implements Serializable {

    /*
    * "Success":true,
    * "Data":[]
    * */

    private boolean Success;
    private List<DataBean> Data;

    public boolean isSuccess() {
        return Success;
    }

    public void setSuccess(boolean success) {
        Success = success;
    }

    public List<DataBean> getData() {
        return Data;
    }

    public void setData(List<DataBean> data) {
        Data = data;
    }

    public static class DataBean{
        /*
        * "Id":10,
        * "Logo":"http://mall.xigyu.com//Storage/Shop/1/VShop/201904271426408530240.jpg",
        * "Name":"官方自营店"
        * */

        private String Id;
        private String Logo;
        private String Name;

        public String getId() {
            return Id;
        }

        public void setId(String id) {
            Id = id;
        }

        public String getLogo() {
            return Logo;
        }

        public void setLogo(String logo) {
            Logo = logo;
        }

        public String getName() {
            return Name;
        }

        public void setName(String name) {
            Name = name;
        }
    }
}
