package com.zhenghaikj.shop.entity;


import java.util.List;

public class Bean {
    private boolean ischeck;
    private String text;
    private List<Cbean> list;

    public boolean ischeck() {
        return ischeck;
    }

    public void setIscheck(boolean ischeck) {
        this.ischeck = ischeck;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<Cbean> getList() {
        return list;
    }

    public void setList(List<Cbean> list) {
        this.list = list;
    }

}
