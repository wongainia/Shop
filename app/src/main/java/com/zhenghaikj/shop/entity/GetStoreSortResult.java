package com.zhenghaikj.shop.entity;

import java.io.Serializable;
import java.util.List;

public class GetStoreSortResult implements Serializable {


    /**
     * Success : True
     * VShopId : 4
     * ShopCategories : [{"Id":290,"Name":"电脑","Image":null,"Depth":1,"DisplaySequence":0,"SubCategories":[{"Id":291,"Name":"笔记本","Image":null,"Depth":1,"DisplaySequence":0,"SubCategories":[]},{"Id":292,"Name":"超极本","Image":null,"Depth":1,"DisplaySequence":0,"SubCategories":[]},{"Id":293,"Name":"台式机","Image":null,"Depth":1,"DisplaySequence":0,"SubCategories":[]},{"Id":294,"Name":"内存条","Image":null,"Depth":1,"DisplaySequence":0,"SubCategories":[]}]},{"Id":304,"Name":"IT","Image":null,"Depth":1,"DisplaySequence":0,"SubCategories":[{"Id":309,"Name":"电饭","Image":null,"Depth":1,"DisplaySequence":0,"SubCategories":[]}]}]
     */

    private String Success;
    private int VShopId;
    private List<ShopCategoriesBean> ShopCategories;

    public String getSuccess() {
        return Success;
    }

    public void setSuccess(String Success) {
        this.Success = Success;
    }

    public int getVShopId() {
        return VShopId;
    }

    public void setVShopId(int VShopId) {
        this.VShopId = VShopId;
    }

    public List<ShopCategoriesBean> getShopCategories() {
        return ShopCategories;
    }

    public void setShopCategories(List<ShopCategoriesBean> ShopCategories) {
        this.ShopCategories = ShopCategories;
    }

    public static class ShopCategoriesBean {
        /**
         * Id : 290
         * Name : 电脑
         * Image : null
         * Depth : 1
         * DisplaySequence : 0
         * SubCategories : [{"Id":291,"Name":"笔记本","Image":null,"Depth":1,"DisplaySequence":0,"SubCategories":[]},{"Id":292,"Name":"超极本","Image":null,"Depth":1,"DisplaySequence":0,"SubCategories":[]},{"Id":293,"Name":"台式机","Image":null,"Depth":1,"DisplaySequence":0,"SubCategories":[]},{"Id":294,"Name":"内存条","Image":null,"Depth":1,"DisplaySequence":0,"SubCategories":[]}]
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
             * Id : 291
             * Name : 笔记本
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
