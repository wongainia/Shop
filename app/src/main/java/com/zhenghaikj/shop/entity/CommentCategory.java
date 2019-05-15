package com.zhenghaikj.shop.entity;

import java.io.Serializable;

public class CommentCategory implements Serializable {
    private String value;
    private boolean isSelect;

    public CommentCategory(String value, boolean isSelect) {
        this.value = value;
        this.isSelect = isSelect;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }
}
