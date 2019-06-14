package com.zhenghaikj.shop.entity;

import java.io.Serializable;
import java.util.List;

public class Announcement implements Serializable {


    /**
     * rows : [{"id":1,"addDate":"2019-06-13","title":"端午节放假通知"}]
     * total : 1
     */

    private int total;
    private List<RowsBean> rows;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<RowsBean> getRows() {
        return rows;
    }

    public void setRows(List<RowsBean> rows) {
        this.rows = rows;
    }

    public static class RowsBean {
        /**
         * id : 1
         * addDate : 2019-06-13
         * title : 端午节放假通知
         */

        private int id;
        private String addDate;
        private String title;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getAddDate() {
            return addDate;
        }

        public void setAddDate(String addDate) {
            this.addDate = addDate;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
