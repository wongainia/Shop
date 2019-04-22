package com.zhenghaikj.shop.entity;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.io.Serializable;
import java.util.List;

public class Category implements Serializable {

    /**
     * Success : True
     * Category : [{"Id":1,"Name":"食品、酒类、特产","Image":null,"Depth":0,"DisplaySequence":1,"SubCategories":[{"Id":2,"Name":"坚果","Image":"","Depth":1,"DisplaySequence":1,"SubCategories":[{"Id":3,"Name":"松子","Image":"","Depth":1,"DisplaySequence":1,"SubCategories":[]},{"Id":6,"Name":"核桃","Image":"","Depth":1,"DisplaySequence":2,"SubCategories":[]},{"Id":7,"Name":"碧根果","Image":"","Depth":1,"DisplaySequence":3,"SubCategories":[]}]},{"Id":4,"Name":"辣条","Image":"","Depth":1,"DisplaySequence":2,"SubCategories":[{"Id":5,"Name":"卫龙","Image":"","Depth":1,"DisplaySequence":1,"SubCategories":[]}]},{"Id":153,"Name":"水果","Image":"","Depth":1,"DisplaySequence":3,"SubCategories":[{"Id":154,"Name":"水果","Image":"","Depth":1,"DisplaySequence":1,"SubCategories":[]}]}]},{"Id":8,"Name":"男装、女装、童装","Image":null,"Depth":0,"DisplaySequence":2,"SubCategories":[{"Id":9,"Name":"女装","Image":"","Depth":1,"DisplaySequence":1,"SubCategories":[{"Id":10,"Name":"外套","Image":"","Depth":1,"DisplaySequence":1,"SubCategories":[]},{"Id":11,"Name":"裙子","Image":"","Depth":1,"DisplaySequence":2,"SubCategories":[]},{"Id":12,"Name":"衬衣","Image":"","Depth":1,"DisplaySequence":3,"SubCategories":[]},{"Id":13,"Name":"针织衫","Image":"","Depth":1,"DisplaySequence":4,"SubCategories":[]}]}]},{"Id":14,"Name":"电脑办公","Image":null,"Depth":0,"DisplaySequence":3,"SubCategories":[{"Id":15,"Name":"电脑整机","Image":"","Depth":1,"DisplaySequence":1,"SubCategories":[{"Id":16,"Name":"笔记本","Image":"","Depth":1,"DisplaySequence":1,"SubCategories":[]}]}]},{"Id":17,"Name":"生鲜","Image":null,"Depth":0,"DisplaySequence":4,"SubCategories":[{"Id":18,"Name":"海鲜水产","Image":"","Depth":1,"DisplaySequence":1,"SubCategories":[{"Id":19,"Name":"虾类","Image":"","Depth":1,"DisplaySequence":1,"SubCategories":[]}]}]},{"Id":20,"Name":"个护化妆、清洁用品","Image":null,"Depth":0,"DisplaySequence":5,"SubCategories":[{"Id":21,"Name":"面部护肤","Image":"","Depth":1,"DisplaySequence":1,"SubCategories":[{"Id":22,"Name":"洁面","Image":"","Depth":1,"DisplaySequence":1,"SubC ategories":[]},{"Id":23,"Name":"卸妆","Image":"","Depth":1,"DisplaySequence":2,"SubCategories":[]},{"Id":157,"Name":"面部护理","Image":"","Depth":1,"DisplaySequence":3,"SubCategories":[]}]}]},{"Id":24,"Name":"家用电器","Image":null,"Depth":0,"DisplaySequence":6,"SubCategories":[{"Id":46,"Name":"电视","Image":"","Depth":1,"DisplaySequence":1,"SubCategories":[{"Id":51,"Name":"合资品牌","Image":"","Depth":1,"DisplaySequence":1,"SubCategories":[]},{"Id":52,"Name":"国产品牌","Image":"","Depth":1,"DisplaySequence":2,"SubCategories":[]},{"Id":53,"Name":"互联网品牌","Image":"","Depth":1,"DisplaySequence":3,"SubCategories":[]}]},{"Id":47,"Name":"空调","Image":"","Depth":1,"DisplaySequence":2,"SubCategories":[{"Id":54,"Name":"壁挂式空调","Image":"","Depth":1,"DisplaySequence":1,"SubCategories":[]},{"Id":55,"Name":"柜式空调","Image":"","Depth":1,"DisplaySequence":2,"SubCategories":[]},{"Id":56,"Name":"中央空调","Image":"","Depth":1,"DisplaySequence":3,"SubCategories":[]},{"Id":57,"Name":"空调配件","Image":"","Depth":1,"DisplaySequence":4,"SubCategories":[]}]},{"Id":48,"Name":"洗衣机","Image":"","Depth":1,"DisplaySequence":3,"SubCategories":[{"Id":58,"Name":"滚筒洗衣机","Image":"","Depth":1,"DisplaySequence":1,"SubCategories":[]},{"Id":59,"Name":"机烘一体机","Image":"","Depth":1,"DisplaySequence":2,"SubCategories":[]},{"Id":60,"Name":"波轮洗衣机","Image":"","Depth":1,"DisplaySequence":3,"SubCategories":[]},{"Id":61,"Name":"迷你洗衣机","Image":"","Depth":1,"DisplaySequence":4,"SubCategories":[]},{"Id":62,"Name":"洗衣机配件","Image":"","Depth":1,"DisplaySequence":5,"SubCategories":[]}]},{"Id":49,"Name":"厨卫大电","Image":"","Depth":1,"DisplaySequence":4,"SubCategories":[{"Id":63,"Name":"油烟机","Image":"","Depth":1,"DisplaySequence":1,"SubCategories":[]},{"Id":64,"Name":"燃气灶","Image":"","Depth":1,"DisplaySequence":2,"SubCategories":[]}]},{"Id":50,"Name":"生活电器","Image":"","Depth":1,"DisplaySequence":5,"SubCategories":[{"Id":65,"Name":"吸尘器","Image":"","Depth":1,"DisplaySequence":1,"SubCategories":[]},{"Id":66,"Name":"进化器","Image":"","Depth":1,"DisplaySequence ":2,"SubCategories":[]},{"Id":67,"Name":"加湿器","Image":"","Depth":1,"DisplaySequence":3,"SubCategories":[]}]}]},{"Id":25,"Name":"家居、家具、家装","Image":null,"Depth":0,"DisplaySequence":7,"SubCategories":[{"Id":26,"Name":"厨具","Image":"","Depth":1,"DisplaySequence":1,"SubCategories":[{"Id":28,"Name":"烹饪锅具","Image":"","Depth":1,"DisplaySequence":1,"SubCategories":[]},{"Id":29,"Name":"厨房配件","Image":"","Depth":1,"DisplaySequence":2,"SubCategories":[]},{"Id":30,"Name":"餐具","Image":"","Depth":1,"DisplaySequence":3,"SubCategories":[]}]},{"Id":31,"Name":"家纺","Image":"","Depth":1,"DisplaySequence":2,"SubCategories":[{"Id":32,"Name":"床品套件","Image":"","Depth":1,"DisplaySequence":1,"SubCategories":[]},{"Id":33,"Name":"被子","Image":"","Depth":1,"DisplaySequence":2,"SubCategories":[]},{"Id":34,"Name":"枕头","Image":"","Depth":1,"DisplaySequence":3,"SubCategories":[]}]},{"Id":35,"Name":"家装软式","Image":"","Depth":1,"DisplaySequence":3,"SubCategories":[{"Id":36,"Name":"装饰字画","Image":"","Depth":1,"DisplaySequence":1,"SubCategories":[]},{"Id":37,"Name":"装饰摆件","Image":"","Depth":1,"DisplaySequence":2,"SubCategories":[]},{"Id":38,"Name":"窗帘隔断","Image":"","Depth":1,"DisplaySequence":3,"SubCategories":[]}]}]},{"Id":39,"Name":"鞋靴、箱包","Image":null,"Depth":0,"DisplaySequence":8,"SubCategories":[{"Id":68,"Name":"时尚女鞋","Image":"","Depth":1,"DisplaySequence":1,"SubCategories":[{"Id":72,"Name":"女靴","Image":"","Depth":1,"DisplaySequence":1,"SubCategories":[]},{"Id":73,"Name":"单鞋","Image":"","Depth":1,"DisplaySequence":2,"SubCategories":[]},{"Id":74,"Name":"布鞋","Image":"","Depth":1,"DisplaySequence":3,"SubCategories":[]},{"Id":80,"Name":"凉鞋","Image":"","Depth":1,"DisplaySequence":4,"SubCategories":[]}]},{"Id":69,"Name":"流行男鞋","Image":"","Depth":1,"DisplaySequence":2,"SubCategories":[{"Id":75,"Name":"休闲鞋","Image":"","Depth":1,"DisplaySequence":1,"SubCategories":[]},{"Id":76,"Name":"正装鞋","Image":"","Depth":1,"DisplaySequence":2,"SubCategories":[]},{"Id":77,"Name":"运动鞋","Image":"","Depth":1,"DisplaySequence":3,"SubCategories":[]},{"Id":78,"Name":"男靴","Image":"","Depth":1,"DisplaySequence":4,"SubCategories":[]},{"Id":79,"Name":"凉鞋","Image":"","Depth":1,"DisplaySequence":5,"SubCategories":[]}]},{"Id":70,"Name":"潮流女包","Image":"","Depth":1,"DisplaySequence":3,"SubCategories":[{"Id":81,"Name":"真皮包","Image":"","Depth":1,"DisplaySequence":1,"SubCategories":[]},{"Id":82,"Name":"水桶包","Image":"","Depth":1,"DisplaySequence":2,"SubCategories":[]},{"Id":83,"Name":"斜挎包","Image":"","Depth":1,"DisplaySequence":3,"SubCategories":[]},{"Id":84,"Name":"手提包","Image":"","Depth":1,"DisplaySequence":4,"SubCategories":[]},{"Id":85,"Name":"帆布包","Image":"","Depth":1,"DisplaySequence":5,"SubCategories":[]}]},{"Id":71,"Name":"精品男包","Image":"","Depth":1,"DisplaySequence":4,"SubCategories":[{"Id":86,"Name":"商务包","Image":"","Depth":1,"DisplaySequence":1,"SubCategories":[]},{"Id":87,"Name":"双肩背包","Image":"","Depth":1,"DisplaySequence":2,"SubCategories":[]},{"Id":88,"Name":"男士手包","Image":"","Depth":1,"DisplaySequence":3,"SubCategories":[]}]}]},{"Id":40,"Name":"运动、户外","Image":null,"Depth":0,"DisplaySequence":9,"SubCategories":[{"Id":89,"Name":"运动鞋包","Image":"","Depth":1,"DisplaySequence":1,"SubCategories":[{"Id":93,"Name":"跑步鞋","Image":"","Depth":1,"DisplaySequence":1,"SubCategories":[]},{"Id":94,"Name":"篮球鞋","Image":"","Depth":1,"DisplaySequence":2,"SubCategories":[]},{"Id":95,"Name":"帆布鞋","Image":"","Depth":1,"DisplaySequence":3,"SubCategories":[]},{"Id":96,"Name":"登山鞋","Image":"","Depth":1,"DisplaySequence":4,"SubCategories":[]},{"Id":97,"Name":"足球鞋","Image":"","Depth":1,"DisplaySequence":5,"SubCategories":[]}]},{"Id":90,"Name":"运动服饰","Image":"","Depth":1,"DisplaySequence":2,"SubCategories":[{"Id":98,"Name":"T恤","Image":"","Depth":1,"DisplaySequence":1,"SubCategories":[]},{"Id":99,"Name":"运动裤","Image":"","Depth":1,"DisplaySequence":2,"SubCategories":[]},{"Id":100,"Name":"汗巾","Image":"","Depth":1,"DisplaySequence":3,"SubCategories":[]},{"Id":101,"Name":"护腕","Image":"","Depth":1,"DisplaySequence":4,"SubCa tegories":[]}]},{"Id":91,"Name":"骑行运动","Image":"","Depth":1,"DisplaySequence":3,"SubCategories":[{"Id":102,"Name":"山地车","Image":"","Depth":1,"DisplaySequence":1,"SubCategories":[]},{"Id":103,"Name":"自行车","Image":"","Depth":1,"DisplaySequence":2,"SubCategories":[]},{"Id":104,"Name":"折叠车","Image":"","Depth":1,"DisplaySequence":3,"SubCategories":[]},{"Id":105,"Name":"骑行装备","Image":"","Depth":1,"DisplaySequence":4,"SubCategories":[]}]},{"Id":92,"Name":"体育用品","Image":"","Depth":1,"DisplaySequence":4,"SubCategories":[{"Id":106,"Name":"羽毛球","Image":"","Depth":1,"DisplaySequence":1,"SubCategories":[]},{"Id":107,"Name":"乒乓球","Image":"","Depth":1,"DisplaySequence":2,"SubCategories":[]},{"Id":108,"Name":"篮球","Image":"","Depth":1,"DisplaySequence":3,"SubCategories":[]},{"Id":109,"Name":"足球","Image":"","Depth":1,"DisplaySequence":4,"SubCategories":[]},{"Id":110,"Name":"棒球","Image":"","Depth":1,"DisplaySequence":5,"SubCategories":[]},{"Id":111,"Name":"网球","Image":"","Depth":1,"DisplaySequence":6,"SubCategories":[]},{"Id":112,"Name":"高尔夫","Image":"","Depth":1,"DisplaySequence":7,"SubCategories":[]},{"Id":113,"Name":"台球","Image":"","Depth":1,"DisplaySequence":8,"SubCategories":[]}]}]},{"Id":41,"Name":"母婴、玩具乐器","Image":null,"Depth":0,"DisplaySequence":10,"SubCategories":[{"Id":114,"Name":"奶粉","Image":"","Depth":1,"DisplaySequence":1,"SubCategories":[{"Id":117,"Name":"1段","Image":"","Depth":1,"DisplaySequence":1,"SubCategories":[]},{"Id":118,"Name":"2段","Image":"","Depth":1,"DisplaySequence":2,"SubCategories":[]},{"Id":119,"Name":"3段","Image":"","Depth":1,"DisplaySequence":3,"SubCategories":[]},{"Id":120,"Name":"4段","Image":"","Depth":1,"DisplaySequence":4,"SubCategories":[]},{"Id":121,"Name":"孕妈奶粉","Image":"","Depth":1,"DisplaySequence":5,"SubCategories":[]}]},{"Id":115,"Name":"营养辅食","Image":"","Depth":1,"DisplaySequence":2,"SubCategories":[{"Id":122,"Name":"米粉、菜粉","Image":"","Depth":1,"DisplaySequence":1,"SubCategories":[]},{"Id":123,"Name":"DHA","Image":"","Depth":1,"DisplaySequen ce":2,"SubCategories":[]}]},{"Id":116,"Name":"尿裤湿巾","Image":"","Depth":1,"DisplaySequence":3,"SubCategories":[{"Id":124,"Name":"拉拉裤","Image":"","Depth":1,"DisplaySequence":1,"SubCategories":[]},{"Id":125,"Name":"婴儿湿巾","Image":"","Depth":1,"DisplaySequence":2,"SubCategories":[]}]}]},{"Id":42,"Name":"医药保健","Image":null,"Depth":0,"DisplaySequence":11,"SubCategories":[{"Id":126,"Name":"中西药品","Image":"","Depth":1,"DisplaySequence":1,"SubCategories":[{"Id":128,"Name":"感冒咳嗽","Image":"","Depth":1,"DisplaySequence":1,"SubCategories":[]},{"Id":129,"Name":"补气养血","Image":"","Depth":1,"DisplaySequence":2,"SubCategories":[]}]},{"Id":127,"Name":"营养健康","Image":"","Depth":1,"DisplaySequence":2,"SubCategories":[{"Id":130,"Name":"调节免疫","Image":"","Depth":1,"DisplaySequence":1,"SubCategories":[]},{"Id":131,"Name":"调节三高","Image":"","Depth":1,"DisplaySequence":2,"SubCategories":[]}]}]},{"Id":43,"Name":"图书、影像、电子书","Image":null,"Depth":0,"DisplaySequence":12,"SubCategories":[]},{"Id":44,"Name":"手机、 数码","Image":null,"Depth":0,"DisplaySequence":13,"SubCategories":[{"Id":132,"Name":"手机通讯","Image":"","Depth":1,"DisplaySequence":1,"SubCategories":[{"Id":134,"Name":"手机","Image":"","Depth":1,"DisplaySequence":1,"SubCategories":[]},{"Id":135,"Name":"对讲机","Image":"","Depth":1,"DisplaySequence":2,"SubCategories":[]}]},{"Id":133,"Name":"手机配件","Image":"","Depth":1,"DisplaySequence":2,"SubCategories":[{"Id":136,"Name":"手机壳","Image":"","Depth":1,"DisplaySequence":1,"SubCategories":[]},{"Id":137,"Name":"贴膜","Image":"","Depth":1,"DisplaySequence":2,"SubCategories":[]},{"Id":138,"Name":"数据线","Image":"","Depth":1,"DisplaySequence":3,"SubCategories":[]},{"Id":139,"Name":"充电器","Image":"","Depth":1,"DisplaySequence":4,"SubCategories":[]},{"Id":140,"Name":"耳机","Image":"","Depth":1,"DisplaySequence":5,"SubCategories":[]}]},{"Id":155,"Name":"数码产品","Image":"","Depth":1,"DisplaySequence":3,"SubCategories":[{"Id":156,"Name":"视频类用品","Image":"","Depth":1,"DisplaySequence":1,"SubCategories":[]}]}]},{"Id":45,"Name":"汽车、 汽车用品","Image":null,"Depth":0,"DisplaySequence":14,"SubCategories":[{"Id":141,"Name":"汽车装饰","Image":"","Depth":1,"DisplaySequence":1,"SubCategories":[{"Id":143,"Name":"脚垫","Image":"","Depth":1,"DisplaySequence":1,"SubCategories":[]},{"Id":144,"Name":"座套","Image":"","Depth":1,"DisplaySequence":2,"SubCategories":[]},{"Id":145,"Name":"方向盘套","Image":"","Depth":1,"DisplaySequence":3,"SubCategories":[]},{"Id":146,"Name":"后备箱垫","Image":"","Depth":1,"DisplaySequence":4,"SubCategories":[]}]},{"Id":142,"Name":"美容清洗","Image":"","Depth":1,"DisplaySequence":2,"SubCategories":[{"Id":147,"Name":"车蜡","Image":"","Depth":1,"DisplaySequence":1,"SubCategories":[]},{"Id":148,"Name":"补漆笔","Image":"","Depth":1,"DisplaySequence":2,"SubCategories":[]},{"Id":149,"Name":"玻璃水","Image":"","Depth":1,"DisplaySequence":3,"SubCategories":[]},{"Id":150,"Name":"清洁剂","Image":"","Depth":1,"DisplaySequence":4,"SubCategories":[]}]}]},{"Id":151,"Name":"钟表","Image":null,"Depth":0,"DisplaySequence":15,"SubCategories":[{"Id":158,"Name":"手表","Image":"","Depth":1,"DisplaySequence":1,"SubCategories":[{"Id":159,"Name":"男表","Image":"","Depth":1,"DisplaySequence":1,"SubCategories":[]}]}]},{"Id":152,"Name":"珠宝","Image":null,"Depth":0,"DisplaySequence":16,"SubCategories":[]}]
     */

    private Boolean Success;
    private List<CategoryBean> Category;

    public Boolean getSuccess() {
        return Success;
    }

    public void setSuccess(Boolean success) {
        Success = success;
    }

    public List<CategoryBean> getCategory() {
        return Category;
    }

    public void setCategory(List<CategoryBean> Category) {
        this.Category = Category;
    }

    public static class CategoryBean implements MultiItemEntity,Serializable {
        /**
         * Id : 1
         * Name : 食品、酒类、特产
         * Image : null
         * Depth : 0
         * DisplaySequence : 1
         * SubCategories : [{"Id":2,"Name":"坚果","Image":"","Depth":1,"DisplaySequence":1,"SubCategories":[{"Id":3,"Name":"松子","Image":"","Depth":1,"DisplaySequence":1,"SubCategories":[]},{"Id":6,"Name":"核桃","Image":"","Depth":1,"DisplaySequence":2,"SubCategories":[]},{"Id":7,"Name":"碧根果","Image":"","Depth":1,"DisplaySequence":3,"SubCategories":[]}]},{"Id":4,"Name":"辣条","Image":"","Depth":1,"DisplaySequence":2,"SubCategories":[{"Id":5,"Name":"卫龙","Image":"","Depth":1,"DisplaySequence":1,"SubCategories":[]}]},{"Id":153,"Name":"水果","Image":"","Depth":1,"DisplaySequence":3,"SubCategories":[{"Id":154,"Name":"水果","Image":"","Depth":1,"DisplaySequence":1,"SubCategories":[]}]}]
         */
        private int itemType;
        private boolean selected;
        private String Id;
        private String Name;
        private String Image;
        private String Depth;
        private String DisplaySequence;
        private List<CategoryBean> SubCategories;

        public boolean isSelected() {
            return selected;
        }

        public void setSelected(boolean selected) {
            this.selected = selected;
        }

        public void setItemType(int itemType) {
            this.itemType = itemType;
        }

        @Override
        public int getItemType() {
            return itemType;
        }
        public String getId() {
            return Id;
        }

        public void setId(String Id) {
            this.Id = Id;
        }

        public String getName() {
            return Name;
        }

        public void setName(String Name) {
            this.Name = Name;
        }

        public String getImage() {
            return Image;
        }

        public void setImage(String Image) {
            this.Image = Image;
        }

        public String getDepth() {
            return Depth;
        }

        public void setDepth(String Depth) {
            this.Depth = Depth;
        }

        public String getDisplaySequence() {
            return DisplaySequence;
        }

        public void setDisplaySequence(String DisplaySequence) {
            this.DisplaySequence = DisplaySequence;
        }

        public List<CategoryBean> getSubCategories() {
            return SubCategories;
        }

        public void setSubCategories(List<CategoryBean> SubCategories) {
            this.SubCategories = SubCategories;
        }
    }
}
