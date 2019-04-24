package com.zhenghaikj.shop.entity;

import java.io.Serializable;
import java.util.List;

public class HistoryVisite implements Serializable {
    /*
    * "Success": "true",
    * "Product":
     * [{
     * "ProductId": 253,
     * "ProductPrice": 3799,
     * "ProductName": "      宏碁（acer）V3-572G 15.6英寸超薄本（i5-5200U 4G 500G G",
     * "ImagePath": "http://moyue214.oicp.net/Storage/Shop/210/Products/253",
     * "BrowseTime": "2015-09-25T10:57:09",
     * "UserId": 0}]
    * */

    private boolean Success;
    private List<ProductBean> Product;

    public boolean isSuccess() {
        return Success;
    }

    public void setSuccess(boolean success) {
        Success = success;
    }

    public List<ProductBean> getProduct() {
        return Product;
    }

    public void setProduct(List<ProductBean> product) {
        Product = product;
    }

    public abstract class ProductBean{
        private String ProductId;
        private String ProductPrice;
        private String ProductName;
        private String ImagePath;
        private String BrowseTime;
        private String UserId;

        public String getProductId() {
            return ProductId;
        }

        public void setProductId(String productId) {
            ProductId = productId;
        }

        public String getProductPrice() {
            return ProductPrice;
        }

        public void setProductPrice(String productPrice) {
            ProductPrice = productPrice;
        }

        public String getProductName() {
            return ProductName;
        }

        public void setProductName(String productName) {
            ProductName = productName;
        }

        public String getImagePath() {
            return ImagePath;
        }

        public void setImagePath(String imagePath) {
            ImagePath = imagePath;
        }

        public String getBrowseTime() {
            return BrowseTime;
        }

        public void setBrowseTime(String browseTime) {
            BrowseTime = browseTime;
        }

        public String getUserId() {
            return UserId;
        }

        public void setUserId(String userId) {
            UserId = userId;
        }
    }
}
