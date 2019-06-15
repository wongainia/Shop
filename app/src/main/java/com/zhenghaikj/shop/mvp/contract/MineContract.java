package com.zhenghaikj.shop.mvp.contract;

import com.zhenghaikj.shop.base.BaseModel;
import com.zhenghaikj.shop.base.BasePresenter;
import com.zhenghaikj.shop.base.BaseResult;
import com.zhenghaikj.shop.base.BaseView;
import com.zhenghaikj.shop.entity.Data;
import com.zhenghaikj.shop.entity.Express;
import com.zhenghaikj.shop.entity.HistoryVisite;
import com.zhenghaikj.shop.entity.Logistics;
import com.zhenghaikj.shop.entity.Order;
import com.zhenghaikj.shop.entity.PersonalInformation;
import com.zhenghaikj.shop.entity.Track;
import com.zhenghaikj.shop.entity.UserInfo;
import com.zhenghaikj.shop.entity.WorkOrder;

import java.util.List;

import io.reactivex.Observable;

public interface MineContract {
    interface Model extends BaseModel {
        Observable<PersonalInformation> PersonalInformation(String UserKey);
        Observable<HistoryVisite> GetHistoryVisite(String rows,String page,String userkey);

        Observable<BaseResult<UserInfo>> GetUserInfoList(String userName, String limit);

        Observable<BaseResult<Data<List<WorkOrder.DataBean>>>> GetOrderByhmall(String UserID);

        Observable<BaseResult<Data<String>>> EnSureOrder(String OrderID,
                                                         String PayPassword,
                                                         String Grade,
                                                         String Grade1,
                                                         String Grade2,
                                                         String Grade3,
                                                         String OrgAppraise);
        Observable<BaseResult<WorkOrder>> GetOrderInfoList(String UserID, String state, String page, String limit);
        Observable<BaseResult<Data<List<WorkOrder.DataBean>>>> GetOrderByhmalluserid(String UserID);
        Observable<BaseResult<List<Track>>> GetOrderRecordByOrderID(String OrderId);
        Observable<BaseResult<Data<String>>> PressWokerAccount(String OrderID,String Content);
        Observable<BaseResult<Data<List<Logistics>>>> GetExpressInfo(String ExpressNo);
        Observable<Order> GetOrders(String orderStatus, String pageNo, String pageSize, String userkey );
        //查询物流Or
        Observable<Express> GetExpress(String orderId, String userkey);
    }

    interface View extends BaseView {
        void PersonalInformation(PersonalInformation result);
        void GetHistoryVisite(HistoryVisite result);
        void GetUserInfoList(BaseResult<UserInfo> Result);
        void GetOrderByhmall(BaseResult<Data<List<WorkOrder.DataBean>>> Result);
        void EnSureOrder(BaseResult<Data<String>> Result);
        void GetOrderInfoList(BaseResult<WorkOrder> baseResult);
        void GetOrderByhmalluserid(BaseResult<Data<List<WorkOrder.DataBean>>> baseResult);
        void GetOrderRecordByOrderID(BaseResult<List<Track>> baseResult);
        void PressWokerAccount(BaseResult<Data<String>> baseResult);
        void GetExpressInfo(BaseResult<Data<List<Logistics>>> baseResult);
        void GetOrders(Order result);
        void GetExpress(Express Result);
    }

    abstract class Presenter extends BasePresenter<View,Model> {
        public abstract void PersonalInformation(String UserKey);
        public abstract void GetHistoryVisite(String rows,String page,String userkey);

        public abstract void GetUserInfoList(String userName, String limit);

        public abstract void GetOrderByhmall(String UserID);
        public abstract void EnSureOrder(String OrderID,
                                         String PayPassword,
                                         String Grade,
                                         String Grade1,
                                         String Grade2,
                                         String Grade3,
                                         String OrgAppraise);
        public abstract void GetOrderInfoList(String UserID,String state, String page,String limit);
        public abstract void GetOrderByhmalluserid(String UserID);
        public abstract void GetOrderRecordByOrderID(String OrderId);
        public abstract void PressWokerAccount(String OrderID,String Content);
        public abstract void GetExpressInfo(String ExpressNo);
        public abstract void GetOrders(String orderStatus,String pageNo,String pageSize,String userkey );
        public abstract void GetExpress(String orderId,String userkey);
    }

}
