package com.zhenghaikj.shop.entity;

import java.io.Serializable;
import java.util.List;

public class HistoryVisite implements Serializable {
    /*
     * "Success":"true",
     * "Product":[{"ProductId":699,"ProductPrice":49.90,"ProductName":"三只松鼠_开口松子218gx2袋零食坚果特产炒货东北红松子原味","ImagePath":"http://mall.xigyu.com//Storage/Shop/1/Products/699/1_100.png","BrowseTime":"2019-04-25T10:47:58","UserId":0}]}
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

    /*
    * "ProductId":699,
    * "ProductPrice":49.90,
    * "ProductName":"三只松鼠_开口松子218gx2袋零食坚果特产炒货东北红松子原味",
    * "ImagePath":"http://mall.xigyu.com//Storage/Shop/1/Products/699/1_100.png",
    * "BrowseTime":"2019-04-25T10:47:58",
    * "UserId":0
    * */

    public static class ProductBean{
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
            return BrowseTime.replace("T"," ");
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
