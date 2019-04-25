package com.zhenghaikj.shop.entity;

import java.io.Serializable;
import java.util.List;

public class ShippingAddressList implements Serializable {

    /*
    * "Success": "true",
    * "ShippingAddress":
    * [{
    * "RegionFullName": "浙江省 杭州市 上城区",  收货地址
    * "RegionIdPath": null,
    * "Id": 366,  主键ID
    * "UserId": 0,
    * "RegionId": 0,
     * "ShipTo": "方法",  发送至
     * "Address": "vv",  详细地址
    * "Phone": "18711052244",  电话
    * "IsDefault": false,  是否为默认收货地址
    * "IsQuick": false,
    * "MemberInfo": null,
    * "Latitude":"28.888",  纬度
    * "Longitude":"128.888"   经度}]
    * */

    private boolean Success;
    private List<ShippingAddressBean> ShippingAddress;

    public boolean isSuccess() {
        return Success;
    }

    public void setSuccess(boolean success) {
        Success = success;
    }

    public List<ShippingAddressBean> getShippingAddress() {
        return ShippingAddress;
    }

    public void setShippingAddress(List<ShippingAddressBean> shippingAddress) {
        ShippingAddress = shippingAddress;
    }

    public static class ShippingAddressBean implements Serializable{
        private String RegionFullName;
        private String RegionIdPath;
        private String Id;
        private String UserId;
        private String RegionId;
        private String ShipTo;
        private String Address;
        private String Phone;
        private boolean IsDefault;
        private boolean IsQuick;
        private String MemberInfo;
        private String Latitude;
        private String Longitude;

        public String getRegionFullName() {
            return RegionFullName;
        }

        public void setRegionFullName(String regionFullName) {
            RegionFullName = regionFullName;
        }

        public String getRegionIdPath() {
            return RegionIdPath;
        }

        public void setRegionIdPath(String regionIdPath) {
            RegionIdPath = regionIdPath;
        }

        public String getId() {
            return Id;
        }

        public void setId(String id) {
            Id = id;
        }

        public String getUserId() {
            return UserId;
        }

        public void setUserId(String userId) {
            UserId = userId;
        }

        public String getRegionId() {
            return RegionId;
        }

        public void setRegionId(String regionId) {
            RegionId = regionId;
        }

        public String getShipTo() {
            return ShipTo;
        }

        public void setShipTo(String shipTo) {
            ShipTo = shipTo;
        }

        public String getAddress() {
            return Address;
        }

        public void setAddress(String address) {
            Address = address;
        }

        public String getPhone() {
            return Phone;
        }

        public void setPhone(String phone) {
            Phone = phone;
        }

        public boolean isDefault() {
            return IsDefault;
        }

        public void setDefault(boolean aDefault) {
            IsDefault = aDefault;
        }

        public boolean isQuick() {
            return IsQuick;
        }

        public void setQuick(boolean quick) {
            IsQuick = quick;
        }

        public String getMemberInfo() {
            return MemberInfo;
        }

        public void setMemberInfo(String memberInfo) {
            MemberInfo = memberInfo;
        }

        public String getLatitude() {
            return Latitude;
        }

        public void setLatitude(String latitude) {
            Latitude = latitude;
        }

        public String getLongitude() {
            return Longitude;
        }

        public void setLongitude(String longitude) {
            Longitude = longitude;
        }
    }
}
