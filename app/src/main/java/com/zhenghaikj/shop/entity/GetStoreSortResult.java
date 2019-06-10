package com.zhenghaikj.shop.entity;

import java.io.Serializable;
import java.util.List;

public class GetStoreSortResult implements Serializable {


    /**
     * Success : True
     * VShopId : 10
     * ShopCategories : [{"Id":365,"Name":"冰洗类","Image":null,"Depth":1,"DisplaySequence":0,"SubCategories":[{"Id":366,"Name":"冰箱","Image":null,"Depth":1,"DisplaySequence":0,"SubCategories":[]},{"Id":367,"Name":"冷柜","Image":null,"Depth":1,"DisplaySequence":0,"SubCategories":[]},{"Id":368,"Name":"家用制冰机","Image":null,"Depth":1,"DisplaySequence":0,"SubCategories":[]}]},{"Id":369,"Name":"热水器","Image":null,"Depth":1,"DisplaySequence":0,"SubCategories":[{"Id":370,"Name":"即热式热水器","Image":null,"Depth":1,"DisplaySequence":0,"SubCategories":[]}]}]
     * ShopId : 1
     */

    private String Success;
    private String VShopId;
    private String ShopId;
    private List<ShopCategoriesBean> ShopCategories;

    public String getSuccess() {
        return Success;
    }

    public void setSuccess(String Success) {
        this.Success = Success;
    }

    public String getVShopId() {
        return VShopId;
    }

    public void setVShopId(String VShopId) {
        this.VShopId = VShopId;
    }

    public String getShopId() {
        return ShopId;
    }

    public void setShopId(String ShopId) {
        this.ShopId = ShopId;
    }

    public List<ShopCategoriesBean> getShopCategories() {
        return ShopCategories;
    }

    public void setShopCategories(List<ShopCategoriesBean> ShopCategories) {
        this.ShopCategories = ShopCategories;
    }

    public static class ShopCategoriesBean {
        /**
         * Id : 365
         * Name : 冰洗类
         * Image : null
         * Depth : 1
         * DisplaySequence : 0
         * SubCategories : [{"Id":366,"Name":"冰箱","Image":null,"Depth":1,"DisplaySequence":0,"SubCategories":[]},{"Id":367,"Name":"冷柜","Image":null,"Depth":1,"DisplaySequence":0,"SubCategories":[]},{"Id":368,"Name":"家用制冰机","Image":null,"Depth":1,"DisplaySequence":0,"SubCategories":[]}]
         */

        private int Id;
        private String Name;
        private Object Image;
        private int Depth;
        private int DisplaySequence;
        private List<SubCategoriesBean> SubCategories;

        public int getId() {
            return Id;
        }

        public void setId(int Id) {
            this.Id = Id;
        }

        public String getName() {
            return Name;
        }

        public void setName(String Name) {
            this.Name = Name;
        }

        public Object getImage() {
            return Image;
        }

        public void setImage(Object Image) {
            this.Image = Image;
        }

        public int getDepth() {
            return Depth;
        }

        public void setDepth(int Depth) {
            this.Depth = Depth;
        }

        public int getDisplaySequence() {
            return DisplaySequence;
        }

        public void setDisplaySequence(int DisplaySequence) {
            this.DisplaySequence = DisplaySequence;
        }

        public List<SubCategoriesBean> getSubCategories() {
            return SubCategories;
        }

        public void setSubCategories(List<SubCategoriesBean> SubCategories) {
            this.SubCategories = SubCategories;
        }

        public static class SubCategoriesBean {
            /**
             * Id : 366
             * Name : 冰箱
             * Image : null
             * Depth : 1
             * DisplaySequence : 0
             * SubCategories : []
             */

            private int Id;
            private String Name;
            private Object Image;
            private int Depth;
            private int DisplaySequence;
            private List<?> SubCategories;

            public int getId() {
                return Id;
            }

            public void setId(int Id) {
                this.Id = Id;
            }

            public String getName() {
                return Name;
            }

            public void setName(String Name) {
                this.Name = Name;
            }

            public Object getImage() {
                return Image;
            }

            public void setImage(Object Image) {
                this.Image = Image;
            }

            public int getDepth() {
                return Depth;
            }

            public void setDepth(int Depth) {
                this.Depth = Depth;
            }

            public int getDisplaySequence() {
                return DisplaySequence;
            }

            public void setDisplaySequence(int DisplaySequence) {
                this.DisplaySequence = DisplaySequence;
            }

            public List<?> getSubCategories() {
                return SubCategories;
            }

            public void setSubCategories(List<?> SubCategories) {
                this.SubCategories = SubCategories;
            }
        }
    }
}
