package com.zhenghaikj.shop.mvp.presenter;

import com.zhenghaikj.shop.base.BaseObserver;
import com.zhenghaikj.shop.base.BaseObserver2;
import com.zhenghaikj.shop.base.BaseResult;
import com.zhenghaikj.shop.entity.Data;
import com.zhenghaikj.shop.entity.Express;
import com.zhenghaikj.shop.entity.HistoryVisite;
import com.zhenghaikj.shop.entity.Logistics;
import com.zhenghaikj.shop.entity.Order;
import com.zhenghaikj.shop.entity.PersonalInformation;
import com.zhenghaikj.shop.entity.Track;
import com.zhenghaikj.shop.entity.UserInfo;
import com.zhenghaikj.shop.entity.WorkOrder;
import com.zhenghaikj.shop.mvp.contract.MineContract;

import java.util.List;

public class MinePresenter extends MineContract.Presenter {
    @Override
    public void PersonalInformation(String UserKey) {
        mModel.PersonalInformation(UserKey)
                .subscribe(new BaseObserver<PersonalInformation>() {
                    @Override
                    protected void onHandleSuccess(PersonalInformation value) {
                        mView.PersonalInformation(value);
                    }
                });
    }
    @Override
    public void GetHistoryVisite(String userkey) {
        mModel.GetHistoryVisite(userkey)
                .subscribe(new BaseObserver<HistoryVisite>() {
                    @Override
                    protected void onHandleSuccess(HistoryVisite value) {
                        mView.GetHistoryVisite(value);
                    }
                });
    }


    @Override
    public void GetUserInfoList(String userName, String limit) {
        mModel.GetUserInfoList(userName, limit)
                .subscribe(new BaseObserver2<UserInfo>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<UserInfo> value) {
                        mView.GetUserInfoList(value);
                    }
                });
    }

    @Override
    public void GetOrderByhmall(String UserID) {
        mModel.GetOrderByhmall(UserID)
                .subscribe(new BaseObserver2<Data<List<WorkOrder.DataBean>>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Data<List<WorkOrder.DataBean>>> value) {
                        mView.GetOrderByhmall(value);
                    }
                });
    }

    @Override
    public void EnSureOrder(String OrderID, String PayPassword, String Grade, String Grade1, String Grade2, String Grade3, String OrgAppraise) {
        mModel.EnSureOrder(OrderID, PayPassword, Grade, Grade1, Grade2, Grade3, OrgAppraise)
             .subscribe(new BaseObserver2<Data<String>>() {
                 @Override
                 protected void onHandleSuccess(BaseResult<Data<String>> value) {
                     mView.EnSureOrder(value);
                 }
             });
    }

    @Override
    public void GetOrderInfoList(String UserID,String state, String page, String limit) {
        mModel.GetOrderInfoList(UserID,state, page, limit)
                .subscribe(new BaseObserver2<WorkOrder>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<WorkOrder> value) {
                        mView.GetOrderInfoList(value);
                    }
                });
    }

    @Override
    public void GetOrderByhmalluserid(String UserID) {
        mModel.GetOrderByhmalluserid(UserID)
                .subscribe(new BaseObserver2<Data<List<WorkOrder.DataBean>>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Data<List<WorkOrder.DataBean>>> value) {
                        mView.GetOrderByhmalluserid(value);
                    }
                });
    }

    @Override
    public void GetOrderRecordByOrderID(String OrderId) {
        mModel.GetOrderRecordByOrderID(OrderId)
                .subscribe(new BaseObserver2<List<Track>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<List<Track>> value) {
                        mView.GetOrderRecordByOrderID(value);
                    }
                });
    }

    @Override
    public void PressWokerAccount(String OrderID, String Content) {
        mModel.PressWokerAccount(OrderID, Content)
                .subscribe(new BaseObserver2<Data<String>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Data<String>> value) {
                        mView.PressWokerAccount(value);
                    }
                });

    }

    @Override
    public void GetExpressInfo(String ExpressNo) {
        mModel.GetExpressInfo(ExpressNo)
                .subscribe(new BaseObserver2<Data<List<Logistics>>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Data<List<Logistics>>> value) {
                        mView.GetExpressInfo(value);
                    }
                });
    }

    @Override
    public void GetOrders(String orderStatus,String pageNo,String pageSize,String userkey ) {
        mModel.GetOrders(orderStatus, pageNo, pageSize, userkey )
                .subscribe(new BaseObserver<Order>() {
                    @Override
                    protected void onHandleSuccess(Order value) {
                        mView.GetOrders(value);
                    }
                });
    }

    @Override
    public void GetExpress(String orderId, String userkey) {
        mModel.GetExpress(orderId, userkey)
                .subscribe(new BaseObserver<Express>() {
                    @Override
                    protected void onHandleSuccess(Express value) {
                        mView.GetExpress(value);
                    }
                });
    }

}
