package com.zhenghaikj.shop.entity;

import java.io.Serializable;
import java.util.List;

public class CollectionProduct implements Serializable {
    /*
    * "Success":true,
    * "Data":[{"Id":699,"Image":"http://mall.xigyu.com//Storage/Shop/1/Products/699/1_220.png","ProductName":"三只松鼠_开口松子218gx2袋零食坚果特产炒货东北红松子原味","SalePrice":"49.90","Evaluation":0}],
    * "Total":1
    * */
    private boolean Success;
    private List<DataBean> Data;
    private String Total;

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

    public String getTotal() {
        return Total;
    }

    public void setTotal(String total) {
        Total = total;
    }

    /*
     * "Id":699,
     * "Image":"http://mall.xigyu.com//Storage/Shop/1/Products/699/1_220.png",
     * "ProductName":"三只松鼠_开口松子218gx2袋零食坚果特产炒货东北红松子原味",
     * "SalePrice":"49.90",
     * "Evaluation":0
     * */

    public static class DataBean{
        private String Id;
        private String categoryId;
        private String Image;
        private String ProductName;
        private String SalePrice;
        private String Evaluation;

        public String getId() {
            return Id;
        }

        public String getCategoryId() {
            return categoryId;
        }

        public void setCategoryId(String categoryId) {
            this.categoryId = categoryId;
        }

        public void setId(String id) {
            Id = id;
        }

        public String getImage() {
            return Image;
        }

        public void setImage(String image) {
            Image = image;
        }

        public String getProductName() {
            return ProductName;
        }

        public void setProductName(String productName) {
            ProductName = productName;
        }

        public String getSalePrice() {
            return SalePrice;
        }

        public void setSalePrice(String salePrice) {
            SalePrice = salePrice;
        }

        public String getEvaluation() {
            return Evaluation;
        }

        public void setEvaluation(String evaluation) {
            Evaluation = evaluation;
        }
    }
}
