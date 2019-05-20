package com.zhenghaikj.shop.mvp.presenter;


import com.zhenghaikj.shop.base.BaseObserver2;
import com.zhenghaikj.shop.base.BaseResult;
import com.zhenghaikj.shop.entity.Data;
import com.zhenghaikj.shop.entity.WorkOrder;
import com.zhenghaikj.shop.mvp.contract.AllWorkOrdersContract;

public class AllWorkOrdersPresenter extends AllWorkOrdersContract.Presenter {

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
    public void UpdateOrderState(String OrderID, String State) {
        mModel.UpdateOrderState(OrderID,State)
                .subscribe(new BaseObserver2<Data<String>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Data<String>> value) {
                        mView.UpdateOrderState(value);
                    }
                });
    }

    @Override
    public void FactoryComplaint(String OrderID, String Content) {
        mModel.FactoryComplaint(OrderID, Content)
                .subscribe(new BaseObserver2<Data<String>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Data<String>> value) {
                        mView.FactoryComplaint(value);
                    }
                });
    }

    @Override
    public void EnSureOrder(String OrderID, String PayPassword,String Grade,String OrgAppraise) {
        mModel.EnSureOrder(OrderID, PayPassword,Grade,OrgAppraise)
                .subscribe(new BaseObserver2<Data<String>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Data<String>> value) {
                        mView.EnSureOrder(value);
                    }
                });
    }
    @Override
    public void FactoryEnsureOrder(String OrderID, String PayPassword) {
        mModel.FactoryEnsureOrder(OrderID, PayPassword)
                .subscribe(new BaseObserver2<Data<String>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Data<String>> value) {
                        mView.FactoryEnsureOrder(value);
                    }
                });
    }

    @Override
    public void UpdateOrderFIsLook(String OrderID, String IsLook, String FIsLook) {
        mModel.UpdateOrderFIsLook(OrderID, IsLook,FIsLook)
                .subscribe(new BaseObserver2<Data<String>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Data<String>> value) {
                        mView.UpdateOrderFIsLook(value);
                    }
                });
    }
}
