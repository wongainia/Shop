package com.zhenghaikj.shop.entity;


import java.io.Serializable;
import java.util.List;

public class WorkOrder implements Serializable {


    /**
     * code : 0
     * msg : success
     * count : 1
     * data : [{"Id":1,"OrderID":1,"TypeID":2,"TypeName":"上门安装","SubTypeID":11,"SubTypeName":"电视机","CategoryID":1,"CategoryName":"大家电","SubCategoryID":1,"SubCategoryName":"电视机","Memo":"123","BrandID":1,"BrandName":"松下","ProductType":"TV02","ProvinceCode":"330000","CityCode":"330200","AreaCode":"330205","Address":"奔腾科技园","UserID":"admin","Guarantee":"N","UserName":"邰振江","Phone":"17681886869","CreateDate":"2019-01-11T16:21:37","AudDate":"2019-01-11T16:21:37","RepairCompleteDate":"2019-01-11T16:21:37","AppraiseDate":"2019-01-11T16:21:37","State":"0","Extra":"Y","ExtraTime":null,"ExtraFee":0,"IsUse":"Y","SendUser":"admin","LoginUser":"system","IsPay":"Y","OrderMoney":0,"BeyondMoney":0,"BeyondID":0,"BeyondState":"0","BeyondDistance":null,"Accessory":null,"AccessorySequency":null,"AccessoryState":null,"AccessorySendState":null,"AccessoryMoney":0,"Service":null,"ServiceMoney":0,"ReturnAccessory":null,"ReturnAccessoryMsg":null,"ApplyCancel":null,"UpdateTime":"2019-01-11T16:21:37","SendOrder":[{"Id":2,"SendID":2,"CreateDate":"2018-12-27T16:19:33","UserID":"admin","OrderID":1,"State":"1","UpdateDate":null,"LoginUser":"system","IsUse":"Y","CategoryID":0,"CategoryName":null,"SubTypeID":0,"SubTypeName":null,"Memo":null,"BrandID":0,"BrandName":null,"ProductType":null,"ProvinceCode":null,"CityCode":null,"AreaCode":null,"Address":null,"Guarantee":null,"UserName":null,"Phone":null,"page":1,"limit":10,"Version":0}],"OrderPayStr":null,"ThirdPartyNo":null,"ExpressNo":null,"RecycleOrderHour":0,"IsRecevieGoods":null,"page":0,"limit":0,"Version":0}]
     */

    private String code;
    private String msg;
    private String count;
    private List<DataBean> data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {

        /**
         * Id : 0
         * OrderID : 0
         * TypeID : 0
         * TypeName : string
         * SubTypeID : 0
         * SubTypeName : string
         * CategoryID : 0
         * CategoryName : string
         * SubCategoryID : 0
         * SubCategoryName : string
         * Memo : string
         * BrandID : 0
         * BrandName : string
         * ProductType : string
         * Num : string
         * ProvinceCode : string
         * CityCode : string
         * AreaCode : string
         * DistrictCode : string
         * Address : string
         * Longitude : string
         * Dimension : string
         * UserID : string
         * Guarantee : string
         * UserName : string
         * Phone : string
         * CreateDate : 2019-02-27T01:45:22.583Z
         * AudDate : 2019-02-27T01:45:22.583Z
         * RepairCompleteDate : 2019-02-27T01:45:22.583Z
         * AppraiseDate : 2019-02-27T01:45:22.583Z
         * State : string
         * StateHtml : string
         * Extra : string
         * ExtraTime : string
         * ExtraFee : 0
         * IsUse : string
         * SendUser : string
         * OrgSendUser : string
         * LoginUser : string
         * IsPay : string
         * OrderMoney : 0
         * InitMoney : 0
         * BeyondMoney : 0
         * BeyondID : 0
         * BeyondState : string
         * BeyondDistance : string
         * Accessory : string
         * AccessorySequency : string
         * AccessoryApplyState : string
         * AccessoryState : string
         * AccessorySendState : string
         * AccessoryMoney : 0
         * Service : string
         * ServiceMoney : 0
         * ReturnAccessory : string
         * ReturnAccessoryMsg : string   返件单号
         * ApplyCancel : string
         * UpdateTime : 2019-02-27T01:45:22.583Z
         * OrderPayStr : string
         * ThirdPartyNo : string
         * ExpressNo : string
         * RecycleOrderHour : 0
         * IsRecevieGoods : string
         * AppointmentMessage : string
         * AppointmentState : string
         * page : 0
         * limit : 0
         * Version : 0
         */

        private String Id;
        private String OrderID;
        private String TypeID;
        private String TypeName;
        private String SubTypeID;
        private String SubTypeName;
        private String CategoryID;
        private String CategoryName;
        private String SubCategoryID;
        private String SubCategoryName;
        private String Memo;
        private String BrandID;
        private String BrandName;
        private String ProductType;
        private String Num;
        private String ProvinceCode;
        private String CityCode;
        private String AreaCode;
        private String DistrictCode;
        private String Address;
        private String Longitude;
        private String Dimension;
        private String UserID;
        private String Guarantee;
        private String UserName;
        private String Phone;
        private String CreateDate;
        private String AudDate;
        private String RepairCompleteDate;
        private String AppraiseDate;
        private String State;
        private String StateHtml;
        private String Extra;
        private String ExtraTime;
        private String ExtraFee;
        private String IsUse;
        private String SendUser;
        private String OrgSendUser;
        private String LoginUser;
        private String IsPay;
        private String OrderMoney;
        private String InitMoney;
        private String BeyondMoney;
        private String BeyondID;
        private String BeyondState;
        private String BeyondDistance;
        private String Accessory;
        private String AccessorySequency;
        private String AccessoryMemo;;
        private String AccessoryApplyState;
        private String AccessoryState="0";
        private String AccessorySendState;
        private String AccessoryMoney;
        private String Service;
        private String ServiceApplyState;
        private String ServiceMoney;
        private String ReturnAccessory;
        private String ReturnAccessoryMsg;
        private String ApplyCancel;
        private String UpdateTime;
        private String OrderPayStr;
        private String ThirdPartyNo;
        private String ExpressNo;
        private String RecycleOrderHour;
        private String IsRecevieGoods;
        private String AppointmentMessage;
        private String AppointmentState;
        private String  IsPressFactory ;

        private String  IsReturn ;
        private String  AddressBack ;
        private String  PostPayType ;

        private List<GAccessory> OrderAccessroyDetail; //所选配件详情
        private List<GService> OrderServiceDetail;  //所选配件详情
        private List<ReturnaccessoryImg> ReturnaccessoryImg;//返件图片
        private List<OrderImg> OrderImg;//返回服务图片
        private List<OrderBeyondImg> OrderBeyondImg;//远程费图片
        private List<SendOrderBean> SendOrderList;
        private String Distance;//返回距离
        private String page;
        private String limit;
        private String Version;
        private String PostMoney;
        private String NewMoney;
        private String QuaMoney;

        public String getQuaMoney() {
            return QuaMoney;
        }

        public void setQuaMoney(String quaMoney) {
            QuaMoney = quaMoney;
        }

        public String getNewMoney() {
            return NewMoney;
        }

        public void setNewMoney(String newMoney) {
            NewMoney = newMoney;
        }

        public String getPostMoney() {
            return PostMoney;
        }

        public void setPostMoney(String postMoney) {
            PostMoney = postMoney;
        }

        public List<SendOrderBean> getSendOrderList() {
            return SendOrderList;
        }

        public void setSendOrderList(List<SendOrderBean> sendOrderList) {
            SendOrderList = sendOrderList;
        }

        public String getIsReturn() {
            return IsReturn;
        }

        public void setIsReturn(String isReturn) {
            IsReturn = isReturn;
        }

        public String getAddressBack() {
            return AddressBack;
        }

        public void setAddressBack(String addressBack) {
            AddressBack = addressBack;
        }

        public String getPostPayType() {
            return PostPayType;
        }

        public void setPostPayType(String postPayType) {
            PostPayType = postPayType;
        }

        public String getAccessoryMemo() {
            return AccessoryMemo==null?"":AccessoryMemo;
        }

        public void setAccessoryMemo(String accessoryMemo) {
            AccessoryMemo = accessoryMemo;
        }
        public String getServiceApplyState() {
            return ServiceApplyState;
        }

        public void setServiceApplyState(String serviceApplyState) {
            ServiceApplyState = serviceApplyState;
        }

        public List<OrderBeyondImg> getOrderBeyondImg() {
            return OrderBeyondImg;
        }

        public void setOrderBeyondImg(List<OrderBeyondImg> orderBeyondImg) {
            OrderBeyondImg = orderBeyondImg;
        }

        public String getIsPressFactory() {
            return IsPressFactory;
        }

        public void setIsPressFactory(String isPressFactory) {
            IsPressFactory = isPressFactory;
        }
        public List<OrderImg> getOrderImg() {
            return OrderImg;
        }

        public void setOrderImg(List<OrderImg> orderImg) {
            OrderImg = orderImg;
        }

        public List<ReturnaccessoryImg> getReturnaccessoryImg() {
            return ReturnaccessoryImg;
        }

        public void setReturnaccessoryImg(List<ReturnaccessoryImg> returnaccessoryImg) {
            ReturnaccessoryImg = returnaccessoryImg;
        }

        public List<GAccessory> getOrderAccessroyDetail() {
            return OrderAccessroyDetail;
        }

        public void setOrderAccessroyDetail(List<GAccessory> orderAccessroyDetail) {
            OrderAccessroyDetail = orderAccessroyDetail;
        }

        public List<GService> getOrderServiceDetail() {
            return OrderServiceDetail;
        }

        public void setOrderServiceDetail(List<GService> orderServiceDetail) {
            OrderServiceDetail = orderServiceDetail;
        }

        public String getId() {
            return Id;
        }

        public void setId(String Id) {
            this.Id = Id;
        }

        public String getOrderID() {
            return OrderID;
        }

        public void setOrderID(String OrderID) {
            this.OrderID = OrderID;
        }

        public String getTypeID() {
            return TypeID;
        }

        public void setTypeID(String TypeID) {
            this.TypeID = TypeID;
        }

        public String getTypeName() {
            return TypeName;
        }

        public void setTypeName(String TypeName) {
            this.TypeName = TypeName;
        }

        public String getSubTypeID() {
            return SubTypeID;
        }

        public void setSubTypeID(String SubTypeID) {
            this.SubTypeID = SubTypeID;
        }

        public String getSubTypeName() {
            return SubTypeName;
        }

        public void setSubTypeName(String SubTypeName) {
            this.SubTypeName = SubTypeName;
        }

        public String getCategoryID() {
            return CategoryID;
        }

        public void setCategoryID(String CategoryID) {
            this.CategoryID = CategoryID;
        }

        public String getCategoryName() {
            return CategoryName;
        }

        public void setCategoryName(String CategoryName) {
            this.CategoryName = CategoryName;
        }

        public String getSubCategoryID() {
            return SubCategoryID;
        }

        public void setSubCategoryID(String SubCategoryID) {
            this.SubCategoryID = SubCategoryID;
        }

        public String getSubCategoryName() {
            return SubCategoryName;
        }

        public void setSubCategoryName(String SubCategoryName) {
            this.SubCategoryName = SubCategoryName;
        }

        public String getMemo() {
            return Memo;
        }

        public void setMemo(String Memo) {
            this.Memo = Memo;
        }

        public String getBrandID() {
            return BrandID;
        }

        public void setBrandID(String BrandID) {
            this.BrandID = BrandID;
        }

        public String getBrandName() {
            return BrandName;
        }

        public void setBrandName(String BrandName) {
            this.BrandName = BrandName;
        }

        public String getProductType() {
            return ProductType;
        }

        public void setProductType(String ProductType) {
            this.ProductType = ProductType;
        }

        public String getNum() {
            return Num;
        }

        public void setNum(String Num) {
            this.Num = Num;
        }

        public String getProvinceCode() {
            return ProvinceCode;
        }

        public void setProvinceCode(String ProvinceCode) {
            this.ProvinceCode = ProvinceCode;
        }

        public String getCityCode() {
            return CityCode;
        }

        public void setCityCode(String CityCode) {
            this.CityCode = CityCode;
        }

        public String getAreaCode() {
            return AreaCode;
        }

        public void setAreaCode(String AreaCode) {
            this.AreaCode = AreaCode;
        }

        public String getDistrictCode() {
            return DistrictCode;
        }

        public void setDistrictCode(String DistrictCode) {
            this.DistrictCode = DistrictCode;
        }

        public String getAddress() {
            return Address;
        }

        public void setAddress(String Address) {
            this.Address = Address;
        }

        public String getLongitude() {
            return Longitude;
        }

        public void setLongitude(String Longitude) {
            this.Longitude = Longitude;
        }

        public String getDimension() {
            return Dimension;
        }

        public void setDimension(String Dimension) {
            this.Dimension = Dimension;
        }

        public String getUserID() {
            return UserID;
        }

        public void setUserID(String UserID) {
            this.UserID = UserID;
        }

        public String getGuarantee() {
            if ("Y".equals(Guarantee)){
                return "保内";
            }else{
                return "保外";
            }
        }

        public void setGuarantee(String Guarantee) {
            this.Guarantee = Guarantee;
        }

        public String getUserName() {
            return UserName;
        }

        public void setUserName(String UserName) {
            this.UserName = UserName;
        }

        public String getPhone() {
            return Phone;
        }

        public void setPhone(String Phone) {
            this.Phone = Phone;
        }

        public String getCreateDate() {
            return CreateDate.replace("T"," ");
        }

        public void setCreateDate(String CreateDate) {
            this.CreateDate = CreateDate;
        }

        public String getAudDate() {
            return AudDate.replace("T"," ");
        }

        public void setAudDate(String AudDate) {
            this.AudDate = AudDate;
        }

        public String getRepairCompleteDate() {
            return RepairCompleteDate;
        }

        public void setRepairCompleteDate(String RepairCompleteDate) {
            this.RepairCompleteDate = RepairCompleteDate;
        }

        public String getAppraiseDate() {
            return AppraiseDate;
        }

        public void setAppraiseDate(String AppraiseDate) {
            this.AppraiseDate = AppraiseDate;
        }

        public String getState() {
            String status="";
            switch (State){
                case "-2":
                    status="申请废除工单";
                    break;
                case "-1":
                    status="退单处理";
                    break;
                case "0":
                    status="待审核";
                    break;
                case "1":
                    status="待接单";
                    break;
                case "2":
                    status="已接单待联系客户";
                    break;
                case "3":
                    status="已联系客户待服务";
                    break;
                case "4":
                    status="服务中";
                    break;
                case "5":
                    status="服务完成";
                    break;
                case "6":
                    status="待评价";
                    break;
                case "7":
                    status="已完成";
                    break;
            }
            return status;
        }

        public void setState(String State) {
            this.State = State;
        }

        public String getStateHtml() {
            return StateHtml;
        }

        public void setStateHtml(String StateHtml) {
            this.StateHtml = StateHtml;
        }

        public String getExtra() {
            return Extra;
        }

        public void setExtra(String Extra) {
            this.Extra = Extra;
        }

        public String getExtraTime() {
            if ("0".equals(ExtraTime)){
                return "无";
            }else{
                return ExtraTime;
            }
        }

        public void setExtraTime(String ExtraTime) {
            this.ExtraTime = ExtraTime;
        }

        public String getExtraFee() {
            return ExtraFee;
        }

        public void setExtraFee(String ExtraFee) {
            this.ExtraFee = ExtraFee;
        }

        public String getIsUse() {
            return IsUse;
        }

        public void setIsUse(String IsUse) {
            this.IsUse = IsUse;
        }

        public String getSendUser() {
            return SendUser;
        }

        public void setSendUser(String SendUser) {
            this.SendUser = SendUser;
        }

        public String getOrgSendUser() {
            return OrgSendUser;
        }

        public void setOrgSendUser(String OrgSendUser) {
            this.OrgSendUser = OrgSendUser;
        }

        public String getLoginUser() {
            return LoginUser;
        }

        public void setLoginUser(String LoginUser) {
            this.LoginUser = LoginUser;
        }

        public String getIsPay() {
            if ("Y".equals(IsPay)){
                return "已支付";
            }else{
                return "未支付";
            }
        }

        public void setIsPay(String IsPay) {
            this.IsPay = IsPay;
        }

        public String getOrderMoney() {
            return OrderMoney;
        }

        public void setOrderMoney(String OrderMoney) {
            this.OrderMoney = OrderMoney;
        }

        public String getInitMoney() {
            return InitMoney;
        }

        public void setInitMoney(String InitMoney) {
            this.InitMoney = InitMoney;
        }

        public String getBeyondMoney() {
            return BeyondMoney;
        }

        public void setBeyondMoney(String BeyondMoney) {
            this.BeyondMoney = BeyondMoney;
        }

        public String getBeyondID() {
            return BeyondID;
        }

        public void setBeyondID(String BeyondID) {
            this.BeyondID = BeyondID;
        }

        public String getBeyondState() {
            return BeyondState;
        }

        public void setBeyondState(String BeyondState) {
            this.BeyondState = BeyondState;
        }

        public String getBeyondDistance() {
            return BeyondDistance;
        }

        public void setBeyondDistance(String BeyondDistance) {
            this.BeyondDistance = BeyondDistance;
        }

        public String getAccessory() {
            return Accessory;
        }

        public void setAccessory(String Accessory) {
            this.Accessory = Accessory;
        }

        public String getAccessorySequency() {
            return AccessorySequency;
        }
        public String getAccessorySequencyStr() {
            String AccessorySequencyStr="";
            if (AccessoryState==null){
                return "";
            }
            switch(AccessoryState){
                case "0":
                    AccessorySequencyStr="厂家自购件";
                    break;
                case "1":
                    AccessorySequencyStr="师傅自购件";
                    break;
                case "2":
                    AccessorySequencyStr="用户自购件";
                    break;
                default:
                    break;
            }
            return AccessorySequencyStr;
        }

        public void setAccessorySequency(String AccessorySequency) {
            this.AccessorySequency = AccessorySequency;
        }

        public String getAccessoryApplyState() {
            return AccessoryApplyState;
        }

        public void setAccessoryApplyState(String AccessoryApplyState) {
            this.AccessoryApplyState = AccessoryApplyState;
        }

        public String getAccessoryState() {
            return AccessoryState;
        }

        public void setAccessoryState(String AccessoryState) {
            this.AccessoryState = AccessoryState;
        }

        public String getAccessorySendState() {
            if ("Y".equals(AccessorySendState)){
                return "是";
            }else{
                return "否";
            }
        }

        public void setAccessorySendState(String AccessorySendState) {
            this.AccessorySendState = AccessorySendState;
        }

        public String getAccessoryMoney() {
            return AccessoryMoney;
        }

        public void setAccessoryMoney(String AccessoryMoney) {
            this.AccessoryMoney = AccessoryMoney;
        }

        public String getService() {
            return Service;
        }

        public void setService(String Service) {
            this.Service = Service;
        }

        public String getServiceMoney() {
            return ServiceMoney;
        }

        public void setServiceMoney(String ServiceMoney) {
            this.ServiceMoney = ServiceMoney;
        }

        public String getReturnAccessory() {
            return ReturnAccessory;
        }

        public void setReturnAccessory(String ReturnAccessory) {
            this.ReturnAccessory = ReturnAccessory;
        }

        public String getReturnAccessoryMsg() {
            return ReturnAccessoryMsg;
        }

        public void setReturnAccessoryMsg(String ReturnAccessoryMsg) {
            this.ReturnAccessoryMsg = ReturnAccessoryMsg;
        }

        public String getApplyCancel() {
            return ApplyCancel;
        }

        public void setApplyCancel(String ApplyCancel) {
            this.ApplyCancel = ApplyCancel;
        }

        public String getUpdateTime() {
            return UpdateTime;
        }

        public void setUpdateTime(String UpdateTime) {
            this.UpdateTime = UpdateTime;
        }

        public String getOrderPayStr() {
            return OrderPayStr;
        }

        public void setOrderPayStr(String OrderPayStr) {
            this.OrderPayStr = OrderPayStr;
        }

        public String getThirdPartyNo() {
            if ("".equals(ThirdPartyNo)||ThirdPartyNo==null){
                return "无";
            }else{
                return ThirdPartyNo;
            }
        }

        public void setThirdPartyNo(String ThirdPartyNo) {
            this.ThirdPartyNo = ThirdPartyNo;
        }

        public String getExpressNo() {
            if ("".equals(ExpressNo)||ExpressNo==null){
                return "无";
            }else{
                return ExpressNo;
            }
        }

        public void setExpressNo(String ExpressNo) {
            this.ExpressNo = ExpressNo;
        }

        public String getRecycleOrderHour() {
            return RecycleOrderHour;
        }

        public void setRecycleOrderHour(String RecycleOrderHour) {
            this.RecycleOrderHour = RecycleOrderHour;
        }

        public String getIsRecevieGoods() {
            return IsRecevieGoods;
        }

        public void setIsRecevieGoods(String IsRecevieGoods) {
            this.IsRecevieGoods = IsRecevieGoods;
        }

        public String getAppointmentMessage() {
            return AppointmentMessage;
        }

        public void setAppointmentMessage(String AppointmentMessage) {
            this.AppointmentMessage = AppointmentMessage;
        }

        public String getAppointmentState() {
            return AppointmentState;
        }

        public void setAppointmentState(String AppointmentState) {
            this.AppointmentState = AppointmentState;
        }

        public String getDistance() {
            return Distance;
        }

        public void setDistance(String distance) {
            Distance = distance;
        }

        public String getPage() {
            return page;
        }

        public void setPage(String page) {
            this.page = page;
        }

        public String getLimit() {
            return limit;
        }

        public void setLimit(String limit) {
            this.limit = limit;
        }

        public String getVersion() {
            return Version;
        }

        public void setVersion(String Version) {
            this.Version = Version;
        }
    }
}
