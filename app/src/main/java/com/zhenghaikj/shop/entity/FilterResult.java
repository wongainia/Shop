package com.zhenghaikj.shop.entity;

import java.io.Serializable;
import java.util.List;

public class FilterResult implements Serializable {


    /**
     * Success : true
     * Attrs : []
     * Brand : [{"Id":337,"Name":"Aimer","Logo":"/Storage/Plat/Brand/logo_337.jpg"},{"Id":100,"Name":"佰益莱","Logo":"/Storage/Plat/Brand/logo_100.png"},{"Id":326,"Name":"海尔","Logo":"/Storage/Plat/Brand/logo_326.jpg"},{"Id":325,"Name":"美的","Logo":"/Storage/Plat/Brand/logo_325.jpg"},{"Id":356,"Name":"aux","Logo":"/Storage/Plat/Brand/logo_356.jpg"}]
     * Category : [{"Name":"家用电器","Id":24,"SubCategory":[{"Name":"电视","Id":46,"SubCategory":[{"Name":"超薄电视","Id":51,"SubCategory":[]},{"Name":"55英寸","Id":548,"SubCategory":[]}]},{"Name":"冰箱","Id":555,"SubCategory":[{"Name":"三门双门","Id":557,"SubCategory":[]},{"Name":"冷柜冰吧","Id":558,"SubCategory":[]},{"Name":"多门对开门","Id":556,"SubCategory":[]},{"Name":"酒柜","Id":559,"SubCategory":[]}]},{"Name":"厨卫大电","Id":49,"SubCategory":[{"Name":"电热水器","Id":565,"SubCategory":[]}]},{"Name":"洗衣机","Id":48,"SubCategory":[{"Name":"波轮洗衣机","Id":60,"SubCategory":[]},{"Name":"滚筒洗衣机","Id":58,"SubCategory":[]}]},{"Name":"空调","Id":47,"SubCategory":[{"Name":"空调挂机","Id":54,"SubCategory":[]}]}]}]
     */

    private String Success;
    private List<?> Attrs;
    private List<BrandBean> Brand;
    private List<CategoryBean> Category;

    public String getSuccess() {
        return Success;
    }

    public void setSuccess(String Success) {
        this.Success = Success;
    }

    public List<?> getAttrs() {
        return Attrs;
    }

    public void setAttrs(List<?> Attrs) {
        this.Attrs = Attrs;
    }

    public List<BrandBean> getBrand() {
        return Brand;
    }

    public void setBrand(List<BrandBean> Brand) {
        this.Brand = Brand;
    }

    public List<CategoryBean> getCategory() {
        return Category;
    }

    public void setCategory(List<CategoryBean> Category) {
        this.Category = Category;
    }

    public static class BrandBean {
        /**
         * Id : 337
         * Name : Aimer
         * Logo : /Storage/Plat/Brand/logo_337.jpg
         */

        private int Id;
        private String Name;
        private String Logo;

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

        public String getLogo() {
            return Logo;
        }

        public void setLogo(String Logo) {
            this.Logo = Logo;
        }
    }

    public static class CategoryBean {
        /**
         * Name : 家用电器
         * Id : 24
         * SubCategory : [{"Name":"电视","Id":46,"SubCategory":[{"Name":"超薄电视","Id":51,"SubCategory":[]},{"Name":"55英寸","Id":548,"SubCategory":[]}]},{"Name":"冰箱","Id":555,"SubCategory":[{"Name":"三门双门","Id":557,"SubCategory":[]},{"Name":"冷柜冰吧","Id":558,"SubCategory":[]},{"Name":"多门对开门","Id":556,"SubCategory":[]},{"Name":"酒柜","Id":559,"SubCategory":[]}]},{"Name":"厨卫大电","Id":49,"SubCategory":[{"Name":"电热水器","Id":565,"SubCategory":[]}]},{"Name":"洗衣机","Id":48,"SubCategory":[{"Name":"波轮洗衣机","Id":60,"SubCategory":[]},{"Name":"滚筒洗衣机","Id":58,"SubCategory":[]}]},{"Name":"空调","Id":47,"SubCategory":[{"Name":"空调挂机","Id":54,"SubCategory":[]}]}]
         */

        private String Name;
        private int Id;
        private List<SubCategoryBeanX> SubCategory;

        public String getName() {
            return Name;
        }

        public void setName(String Name) {
            this.Name = Name;
        }

        public int getId() {
            return Id;
        }

        public void setId(int Id) {
            this.Id = Id;
        }

        public List<SubCategoryBeanX> getSubCategory() {
            return SubCategory;
        }

        public void setSubCategory(List<SubCategoryBeanX> SubCategory) {
            this.SubCategory = SubCategory;
        }

        public static class SubCategoryBeanX {
            /**
             * Name : 电视
             * Id : 46
             * SubCategory : [{"Name":"超薄电视","Id":51,"SubCategory":[]},{"Name":"55英寸","Id":548,"SubCategory":[]}]
             */

            private String Name;
            private int Id;
            private List<SubCategoryBean> SubCategory;

            public String getName() {
                return Name;
            }

            public void setName(String Name) {
                this.Name = Name;
            }

            public int getId() {
                return Id;
            }

            public void setId(int Id) {
                this.Id = Id;
            }

            public List<SubCategoryBean> getSubCategory() {
                return SubCategory;
            }

            public void setSubCategory(List<SubCategoryBean> SubCategory) {
                this.SubCategory = SubCategory;
            }

            public static class SubCategoryBean {
                /**
                 * Name : 超薄电视
                 * Id : 51
                 * SubCategory : []
                 */

                private String Name;
                private int Id;
                private List<?> SubCategory;

                public String getName() {
                    return Name;
                }

                public void setName(String Name) {
                    this.Name = Name;
                }

                public int getId() {
                    return Id;
                }

                public void setId(int Id) {
                    this.Id = Id;
                }

                public List<?> getSubCategory() {
                    return SubCategory;
                }

                public void setSubCategory(List<?> SubCategory) {
                    this.SubCategory = SubCategory;
                }
            }
        }
    }
}
