package com.zhenghaikj.shop.entity;

import java.io.Serializable;
import java.util.List;

public class FilterResult implements Serializable {


    /**
     * Success : true
     * Attrs : [{"AttrId":207,"Name":"标签","AttrValues":[{"Id":860,"Name":"属性1"},{"Id":861,"Name":"属性2"},{"Id":862,"Name":"属性3"}]},{"AttrId":208,"Name":"折扣和服务","AttrValues":[{"Id":863,"Name":"包邮"},{"Id":864,"Name":"天猫"},{"Id":865,"Name":"赠送运费险"},{"Id":866,"Name":"天猫直达"},{"Id":867,"Name":"天猫无忧购"},{"Id":868,"Name":"花呗分期"}]}]
     * Brand : [{"Id":100,"Name":"佰益莱","Logo":"/Storage/Plat/Brand/logo_100.png"},{"Id":326,"Name":"海尔","Logo":"/Storage/Plat/Brand/logo_326.jpg"},{"Id":325,"Name":"美的","Logo":"/Storage/Plat/Brand/logo_325.jpg"},{"Id":356,"Name":"aux","Logo":"/Storage/Plat/Brand/logo_356.jpg"}]
     * Category : [{"Name":"家用电器","Id":24,"SubCategory":[{"Name":"冰箱","Id":555,"SubCategory":[{"Name":"三门双门","Id":557,"SubCategory":[]},{"Name":"多门对开门","Id":556,"SubCategory":[]}]}]}]
     */

    private String Success;
    private List<AttrsBean> Attrs;
    private List<BrandBean> Brand;
    private List<CategoryBean> Category;

    public String getSuccess() {
        return Success;
    }

    public void setSuccess(String Success) {
        this.Success = Success;
    }

    public List<AttrsBean> getAttrs() {
        return Attrs;
    }

    public void setAttrs(List<AttrsBean> Attrs) {
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

    public static class AttrsBean {
        /**
         * AttrId : 207
         * Name : 标签
         * AttrValues : [{"Id":860,"Name":"属性1"},{"Id":861,"Name":"属性2"},{"Id":862,"Name":"属性3"}]
         */

        private int AttrId;
        private String Name;
        private List<AttrValuesBean> AttrValues;

        public int getAttrId() {
            return AttrId;
        }

        public void setAttrId(int AttrId) {
            this.AttrId = AttrId;
        }

        public String getName() {
            return Name;
        }

        public void setName(String Name) {
            this.Name = Name;
        }

        public List<AttrValuesBean> getAttrValues() {
            return AttrValues;
        }

        public void setAttrValues(List<AttrValuesBean> AttrValues) {
            this.AttrValues = AttrValues;
        }

        public static class AttrValuesBean {
            /**
             * Id : 860
             * Name : 属性1
             */

            private int Id;
            private String Name;

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
        }
    }

    public static class BrandBean {
        /**
         * Id : 100
         * Name : 佰益莱
         * Logo : /Storage/Plat/Brand/logo_100.png
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
         * SubCategory : [{"Name":"冰箱","Id":555,"SubCategory":[{"Name":"三门双门","Id":557,"SubCategory":[]},{"Name":"多门对开门","Id":556,"SubCategory":[]}]}]
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
             * Name : 冰箱
             * Id : 555
             * SubCategory : [{"Name":"三门双门","Id":557,"SubCategory":[]},{"Name":"多门对开门","Id":556,"SubCategory":[]}]
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
                 * Name : 三门双门
                 * Id : 557
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
