package com.zhenghaikj.shop.entity;

import java.io.Serializable;
import java.util.List;

public class CommentModel implements Serializable {


    /**
     * orderId : 2016022392950672
     * serviceMark : 1
     * deliveryMark : 2
     * packMark : 1
     * productComments : [{"mark":"3","content":"11111111","orderItemId":"23503","Images":["/temp/201602231625532011430.jpg","/temp/201602231625550162470.jpg","/temp/201602231625567793470.jpg"],"WXmediaId":[]},{"mark":"4","content":"22222222222","orderItemId":"23504","Images":["/temp/201602231626042447740.jpg"],"WXmediaId":[]},{"mark":"5","content":"333333333","orderItemId":"23505","Images":["/temp/201602231626083560100.jpg"],"WXmediaId":[]}]
     */

    private String orderId;
    private String serviceMark;
    private String deliveryMark;
    private String packMark;
    private List<ProductCommentsBean> productComments;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getServiceMark() {
        return serviceMark;
    }

    public void setServiceMark(String serviceMark) {
        this.serviceMark = serviceMark;
    }

    public String getDeliveryMark() {
        return deliveryMark;
    }

    public void setDeliveryMark(String deliveryMark) {
        this.deliveryMark = deliveryMark;
    }

    public String getPackMark() {
        return packMark;
    }

    public void setPackMark(String packMark) {
        this.packMark = packMark;
    }

    public List<ProductCommentsBean> getProductComments() {
        return productComments;
    }

    public void setProductComments(List<ProductCommentsBean> productComments) {
        this.productComments = productComments;
    }

    public static class ProductCommentsBean {
        /**
         * mark : 3
         * content : 11111111
         * orderItemId : 23503
         * Images : ["/temp/201602231625532011430.jpg","/temp/201602231625550162470.jpg","/temp/201602231625567793470.jpg"]
         * WXmediaId : []
         */

        private String mark;
        private String content;
        private String orderItemId;
        private List<String> Images;
        private List<?> WXmediaId;

        public String getMark() {
            return mark;
        }

        public void setMark(String mark) {
            this.mark = mark;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getOrderItemId() {
            return orderItemId;
        }

        public void setOrderItemId(String orderItemId) {
            this.orderItemId = orderItemId;
        }

        public List<String> getImages() {
            return Images;
        }

        public void setImages(List<String> Images) {
            this.Images = Images;
        }

        public List<?> getWXmediaId() {
            return WXmediaId;
        }

        public void setWXmediaId(List<?> WXmediaId) {
            this.WXmediaId = WXmediaId;
        }
    }
}
