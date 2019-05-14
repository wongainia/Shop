package com.zhenghaikj.shop.entity;

import java.io.Serializable;
import java.util.List;

public class EvaluateResult implements Serializable {

    /**
     * Success : true
     * Product : [{"ProductId":779,"ProductName":"AUX奥克斯KFR35GWNFI193大15匹冷暖定频壁挂式家用空调挂机","Image":"http://mall.xigyu.com//Storage/Shop/1/Products/779/1_220.png"},{"ProductId":778,"ProductName":"AUX奥克斯KFR35GWBpR3QYQ22大15匹冷暖家用2级变频壁挂空调","Image":"http://mall.xigyu.com//Storage/Shop/1/Products/778/1_220.png"}]
     * orderItemIds : [1205,1206]
     */

    private boolean Success;
    private List<ProductBean> Product;
    private List<Integer> orderItemIds;

    public boolean isSuccess() {
        return Success;
    }

    public void setSuccess(boolean Success) {
        this.Success = Success;
    }

    public List<ProductBean> getProduct() {
        return Product;
    }

    public void setProduct(List<ProductBean> Product) {
        this.Product = Product;
    }

    public List<Integer> getOrderItemIds() {
        return orderItemIds;
    }

    public void setOrderItemIds(List<Integer> orderItemIds) {
        this.orderItemIds = orderItemIds;
    }

    public static class ProductBean {
        /**
         * ProductId : 779
         * ProductName : AUX奥克斯KFR35GWNFI193大15匹冷暖定频壁挂式家用空调挂机
         * Image : http://mall.xigyu.com//Storage/Shop/1/Products/779/1_220.png
         */
        private int ProductId;
        private String ProductName;
        private String Image;

        public int getProductId() {
            return ProductId;
        }

        public void setProductId(int ProductId) {
            this.ProductId = ProductId;
        }

        public String getProductName() {
            return ProductName;
        }

        public void setProductName(String ProductName) {
            this.ProductName = ProductName;
        }

        public String getImage() {
            return Image;
        }

        public void setImage(String Image) {
            this.Image = Image;
        }
    }
}
