package com.zhenghaikj.shop.entity;

import java.io.Serializable;

public class APPTheme implements Serializable {


    /**
     * home_top_color : #1f7f4b
     * home_bottom_menu_my : {"name":"tab_menu_mine_a","color":"","color_active":"","img":null,"img_active":null}
     * home_bottom_menu_cart : {"name":"购物车","color":"","color_active":"","img":null,"img_active":null}
     * home_bottom_menu_cate : {"name":"fenlei_one","color":"","color_active":"","img":null,"img_active":null}
     * home_bottom_menu_home : {"name":"首页","color":"","color_active":"","img":"http://qiniu.emjiayuan.com/upload_file/ems/2018071719723880357","img_active":null}
     * home_bottom_menu_sq : {"name":"社区","color":"#9e1111","color_active":"#6c7c08","img":"http://qiniu.emjiayuan.com/icon1516957778543","img_active":"http://qiniu.emjiayuan.com/upload_file/ems/2018052519844940932"}
     * user_top_img : http://qiniu.emjiayuan.com/icon1516957748771
     */

    private String home_top_color="#E82C00";
    private HomeBottomMenuMyBean home_bottom_menu_my;
    private HomeBottomMenuCartBean home_bottom_menu_cart;
    private HomeBottomMenuCateBean home_bottom_menu_cate;
    private HomeBottomMenuHomeBean home_bottom_menu_home;
    private HomeBottomMenuSqBean home_bottom_menu_sq;
    private String user_top_img;

    public String getHome_top_color() {
        return home_top_color;
    }

    public void setHome_top_color(String home_top_color) {
        this.home_top_color = home_top_color;
    }

    public HomeBottomMenuMyBean getHome_bottom_menu_my() {
        return home_bottom_menu_my;
    }

    public void setHome_bottom_menu_my(HomeBottomMenuMyBean home_bottom_menu_my) {
        this.home_bottom_menu_my = home_bottom_menu_my;
    }

    public HomeBottomMenuCartBean getHome_bottom_menu_cart() {
        return home_bottom_menu_cart;
    }

    public void setHome_bottom_menu_cart(HomeBottomMenuCartBean home_bottom_menu_cart) {
        this.home_bottom_menu_cart = home_bottom_menu_cart;
    }

    public HomeBottomMenuCateBean getHome_bottom_menu_cate() {
        return home_bottom_menu_cate;
    }

    public void setHome_bottom_menu_cate(HomeBottomMenuCateBean home_bottom_menu_cate) {
        this.home_bottom_menu_cate = home_bottom_menu_cate;
    }

    public HomeBottomMenuHomeBean getHome_bottom_menu_home() {
        return home_bottom_menu_home;
    }

    public void setHome_bottom_menu_home(HomeBottomMenuHomeBean home_bottom_menu_home) {
        this.home_bottom_menu_home = home_bottom_menu_home;
    }

    public HomeBottomMenuSqBean getHome_bottom_menu_sq() {
        return home_bottom_menu_sq;
    }

    public void setHome_bottom_menu_sq(HomeBottomMenuSqBean home_bottom_menu_sq) {
        this.home_bottom_menu_sq = home_bottom_menu_sq;
    }

    public String getUser_top_img() {
        return user_top_img;
    }

    public void setUser_top_img(String user_top_img) {
        this.user_top_img = user_top_img;
    }

    public static class HomeBottomMenuMyBean implements Serializable {
        /**
         * name : 我的
         * color :
         * color_active :
         * img : null
         * img_active : null
         */

        private String name;
        private String color;
        private String color_active;
        private String img;
        private String img_active;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public String getColor_active() {
            return color_active;
        }

        public void setColor_active(String color_active) {
            this.color_active = color_active;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getImg_active() {
            return img_active;
        }

        public void setImg_active(String img_active) {
            this.img_active = img_active;
        }
    }

    public static class HomeBottomMenuCartBean implements Serializable {
        /**
         * name : 购物车
         * color :
         * color_active :
         * img : null
         * img_active : null
         */

        private String name;
        private String color;
        private String color_active;
        private String img;
        private String img_active;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public String getColor_active() {
            return color_active;
        }

        public void setColor_active(String color_active) {
            this.color_active = color_active;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getImg_active() {
            return img_active;
        }

        public void setImg_active(String img_active) {
            this.img_active = img_active;
        }
    }

    public static class HomeBottomMenuCateBean implements Serializable {
        /**
         * name : fenlei_one
         * color :
         * color_active :
         * img : null
         * img_active : null
         */

        private String name;
        private String color;
        private String color_active;
        private String img;
        private String img_active;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public String getColor_active() {
            return color_active;
        }

        public void setColor_active(String color_active) {
            this.color_active = color_active;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getImg_active() {
            return img_active;
        }

        public void setImg_active(String img_active) {
            this.img_active = img_active;
        }
    }

    public static class HomeBottomMenuHomeBean implements Serializable {
        /**
         * name : 首页
         * color :
         * color_active :
         * img : http://qiniu.emjiayuan.com/upload_file/ems/2018071719723880357
         * img_active : null
         */

        private String name;
        private String color;
        private String color_active;
        private String img;
        private String img_active;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public String getColor_active() {
            return color_active;
        }

        public void setColor_active(String color_active) {
            this.color_active = color_active;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getImg_active() {
            return img_active;
        }

        public void setImg_active(String img_active) {
            this.img_active = img_active;
        }
    }

    public static class HomeBottomMenuSqBean implements Serializable {
        /**
         * name : 社区
         * color : #9e1111
         * color_active : #6c7c08
         * img : http://qiniu.emjiayuan.com/icon1516957778543
         * img_active : http://qiniu.emjiayuan.com/upload_file/ems/2018052519844940932
         */

        private String name;
        private String color;
        private String color_active;
        private String img;
        private String img_active;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public String getColor_active() {
            return color_active;
        }

        public void setColor_active(String color_active) {
            this.color_active = color_active;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getImg_active() {
            return img_active;
        }

        public void setImg_active(String img_active) {
            this.img_active = img_active;
        }
    }
}
