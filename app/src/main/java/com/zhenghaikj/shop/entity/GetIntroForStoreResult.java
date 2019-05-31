package com.zhenghaikj.shop.entity;

import java.io.Serializable;

public class GetIntroForStoreResult implements Serializable {

    /**
     * Success : True
     * VShop : {"QRCode":"http://192.168.10.47/temp/150925155445911191.jpg","Name":"扣扣电脑城","IsFavorite":true,"ProductAndDescription":5,"SellerDeliverySpeed":5,"SellerServiceAttitude":5,"Description":"数码","ShopId":210,"Id":4}
     */

    private String Success;
    private VShopBean VShop;

    public String getSuccess() {
        return Success;
    }

    public void setSuccess(String Success) {
        this.Success = Success;
    }

    public VShopBean getVShop() {
        return VShop;
    }

    public void setVShop(VShopBean VShop) {
        this.VShop = VShop;
    }

    public static class VShopBean {
        /**
         * QRCode : http://192.168.10.47/temp/150925155445911191.jpg
         * Name : 扣扣电脑城
         * IsFavorite : true
         * ProductAndDescription : 5
         * SellerDeliverySpeed : 5
         * SellerServiceAttitude : 5
         * Description : 数码
         * ShopId : 210
         * Id : 4
         */

        private String QRCode;
        private String Name;
        private boolean IsFavorite;
        private int ProductAndDescription;
        private int SellerDeliverySpeed;
        private int SellerServiceAttitude;
        private String Description;
        private int ShopId;
        private int Id;

        public String getQRCode() {
            return QRCode;
        }

        public void setQRCode(String QRCode) {
            this.QRCode = QRCode;
        }

        public String getName() {
            return Name;
        }

        public void setName(String Name) {
            this.Name = Name;
        }

        public boolean isIsFavorite() {
            return IsFavorite;
        }

        public void setIsFavorite(boolean IsFavorite) {
            this.IsFavorite = IsFavorite;
        }

        public int getProductAndDescription() {
            return ProductAndDescription;
        }

        public void setProductAndDescription(int ProductAndDescription) {
            this.ProductAndDescription = ProductAndDescription;
        }

        public int getSellerDeliverySpeed() {
            return SellerDeliverySpeed;
        }

        public void setSellerDeliverySpeed(int SellerDeliverySpeed) {
            this.SellerDeliverySpeed = SellerDeliverySpeed;
        }

        public int getSellerServiceAttitude() {
            return SellerServiceAttitude;
        }

        public void setSellerServiceAttitude(int SellerServiceAttitude) {
            this.SellerServiceAttitude = SellerServiceAttitude;
        }

        public String getDescription() {
            return Description;
        }

        public void setDescription(String Description) {
            this.Description = Description;
        }

        public int getShopId() {
            return ShopId;
        }

        public void setShopId(int ShopId) {
            this.ShopId = ShopId;
        }

        public int getId() {
            return Id;
        }

        public void setId(int Id) {
            this.Id = Id;
        }
    }
}
