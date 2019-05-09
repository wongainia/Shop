package com.zhenghaikj.shop.entity;

import java.io.Serializable;
import java.util.List;

public class Cart implements Serializable {


    /**
     * Success : true
     * Shop : [[{"CartItemId":"182","SkuId":"707_675_661_0","Id":"707","ImgUrl":"http://mall.xigyu.com//Storage/Shop/1/Products/707/1_150.png","Name":"ONLY春季新品含莱卡面料五分袖修身露肩一字领针织连衣裙女E|116361504 010黑 175/92A/XL","Price":"90.00","Count":"2","Size":"165/84(S)","Color":"蓝色","Version":null,"ShopId":"1","VShopId":"10","ShopName":"官方自营店","ShopLogo":"http://mall.xigyu.com//Storage/Shop/1/VShop/201702141737310419930.png","Url":"http://mall.xigyu.com//m-IOS/product/detail/707","Status":1,"ColorAlias":"颜色","SizeAlias":"尺码","VersionAlias":"规格","AddTime":"2019-04-27T10:24:49","ShopBranchId":0,"ShopBranchName":null,"ShopBranchLogo":null},{"CartItemId":"173","SkuId":"733_0_0_0","Id":"733","ImgUrl":"http://mall.xigyu.com//Storage/Shop/1/Products/733/1_150.png","Name":"丹麦依诺维绅 功能沙发床 时尚沙发 书房必用 小鸟","Price":"3982.50","Count":"18","Size":null,"Color":null,"Version":null,"ShopId":"1","VShopId":"10","ShopName":"官方自营店","ShopLogo":"http://mall.xigyu.com//Storage/Shop/1/VShop/201702141737310419930.png","Url":"http://mall.xigyu.com//m-IOS/product/detail/733","Status":1,"ColorAlias":"颜色","SizeAlias":"尺码","VersionAlias":"规格","AddTime":"2019-04-26T14:54:10","ShopBranchId":0,"ShopBranchName":null,"ShopBranchLogo":null},{"CartItemId":"172","SkuId":"699_0_0_0","Id":"699","ImgUrl":"http://mall.xigyu.com//Storage/Shop/1/Products/699/1_150.png","Name":"三只松鼠_开口松子218gx2袋零食坚果特产炒货东北红松子原味","Price":"44.91","Count":"10","Size":null,"Color":null,"Version":null,"ShopId":"1","VShopId":"10","ShopName":"官方自营店","ShopLogo":"http://mall.xigyu.com//Storage/Shop/1/VShop/201702141737310419930.png","Url":"http://mall.xigyu.com//m-IOS/product/detail/699","Status":1,"ColorAlias":"颜色","SizeAlias":"尺码","VersionAlias":"规格","AddTime":"2019-04-23T16:14:32","ShopBranchId":0,"ShopBranchName":null,"ShopBranchLogo":null}],[{"CartItemId":"181","SkuId":"702_0_0_0","Id":"702","ImgUrl":"http://mall.xigyu.com//Storage/Shop/1/Products/702/1_150.png","Name":"卫龙 休闲零食 辣条 小面筋 办公室休闲食品 22g*20包(新老包装随机发货)","Price":"11.90","Count":"1","Size":null,"Color":null,"Version":null,"ShopId":"314","VShopId":"0","ShopName":"卫龙零食店","ShopLogo":"","Url":"http://mall.xigyu.com//m-IOS/product/detail/702","Status":1,"ColorAlias":"颜色","SizeAlias":"尺码","VersionAlias":"规格","AddTime":"2019-04-27T09:24:29","ShopBranchId":0,"ShopBranchName":null,"ShopBranchLogo":null}]]
     */

    private String Success;

    private List<List<ShopBean>> Shop;

    public String getSuccess() {
        return Success;
    }

    public void setSuccess(String Success) {
        this.Success = Success;
    }

    public List<List<ShopBean>> getShop() {
        return Shop;
    }

    public void setShop(List<List<ShopBean>> Shop) {
        this.Shop = Shop;
    }

    public static class ShopBean {
        /**
         * CartItemId : 182
         * SkuId : 707_675_661_0
         * Id : 707
         * ImgUrl : http://mall.xigyu.com//Storage/Shop/1/Products/707/1_150.png
         * Name : ONLY春季新品含莱卡面料五分袖修身露肩一字领针织连衣裙女E|116361504 010黑 175/92A/XL
         * Price : 90.00
         * Count : 2
         * Size : 165/84(S)
         * Color : 蓝色
         * Version : null
         * ShopId : 1
         * VShopId : 10
         * ShopName : 官方自营店
         * ShopLogo : http://mall.xigyu.com//Storage/Shop/1/VShop/201702141737310419930.png
         * Url : http://mall.xigyu.com//m-IOS/product/detail/707
         * Status : 1
         * ColorAlias : 颜色
         * SizeAlias : 尺码
         * VersionAlias : 规格
         * AddTime : 2019-04-27T10:24:49
         * ShopBranchId : 0
         * ShopBranchName : null
         * ShopBranchLogo : null
         */

        private String CartItemId;
        private String SkuId;
        private String Id;
        private String ImgUrl;
        private String Name;
        private String Price;
        private String Count;
        private String Size;
        private String Color;
        private Object Version;
        private String ShopId;
        private String VShopId;
        private String ShopName;
        private String ShopLogo;
        private String Url;
        private int Status;
        private String ColorAlias;
        private String SizeAlias;
        private String VersionAlias;
        private String AddTime;
        private int ShopBranchId;
        private String ShopBranchName;
        private String ShopBranchLogo;

        public String getCartItemId() {
            return CartItemId;
        }

        public void setCartItemId(String CartItemId) {
            this.CartItemId = CartItemId;
        }

        public String getSkuId() {
            return SkuId;
        }

        public void setSkuId(String SkuId) {
            this.SkuId = SkuId;
        }

        public String getId() {
            return Id;
        }

        public void setId(String Id) {
            this.Id = Id;
        }

        public String getImgUrl() {
            return ImgUrl;
        }

        public void setImgUrl(String ImgUrl) {
            this.ImgUrl = ImgUrl;
        }

        public String getName() {
            return Name;
        }

        public void setName(String Name) {
            this.Name = Name;
        }

        public String getPrice() {
            return Price;
        }

        public void setPrice(String Price) {
            this.Price = Price;
        }

        public String getCount() {
            return Count;
        }

        public void setCount(String Count) {
            this.Count = Count;
        }

        public String getSize() {
            return Size;
        }

        public void setSize(String Size) {
            this.Size = Size;
        }

        public String getColor() {
            return Color;
        }

        public void setColor(String Color) {
            this.Color = Color;
        }

        public Object getVersion() {
            return Version;
        }

        public void setVersion(Object Version) {
            this.Version = Version;
        }

        public String getShopId() {
            return ShopId;
        }

        public void setShopId(String ShopId) {
            this.ShopId = ShopId;
        }

        public String getVShopId() {
            return VShopId;
        }

        public void setVShopId(String VShopId) {
            this.VShopId = VShopId;
        }

        public String getShopName() {
            return ShopName;
        }

        public void setShopName(String ShopName) {
            this.ShopName = ShopName;
        }

        public String getShopLogo() {
            return ShopLogo;
        }

        public void setShopLogo(String ShopLogo) {
            this.ShopLogo = ShopLogo;
        }

        public String getUrl() {
            return Url;
        }

        public void setUrl(String Url) {
            this.Url = Url;
        }

        public int getStatus() {
            return Status;
        }

        public void setStatus(int Status) {
            this.Status = Status;
        }

        public String getColorAlias() {
            return ColorAlias;
        }

        public void setColorAlias(String ColorAlias) {
            this.ColorAlias = ColorAlias;
        }

        public String getSizeAlias() {
            return SizeAlias;
        }

        public void setSizeAlias(String SizeAlias) {
            this.SizeAlias = SizeAlias;
        }

        public String getVersionAlias() {
            return VersionAlias;
        }

        public void setVersionAlias(String VersionAlias) {
            this.VersionAlias = VersionAlias;
        }

        public String getAddTime() {
            return AddTime;
        }

        public void setAddTime(String AddTime) {
            this.AddTime = AddTime;
        }

        public int getShopBranchId() {
            return ShopBranchId;
        }

        public void setShopBranchId(int ShopBranchId) {
            this.ShopBranchId = ShopBranchId;
        }

        public String getShopBranchName() {
            return ShopBranchName;
        }

        public void setShopBranchName(String ShopBranchName) {
            this.ShopBranchName = ShopBranchName;
        }

        public String getShopBranchLogo() {
            return ShopBranchLogo;
        }

        public void setShopBranchLogo(String ShopBranchLogo) {
            this.ShopBranchLogo = ShopBranchLogo;
        }
    }
}
