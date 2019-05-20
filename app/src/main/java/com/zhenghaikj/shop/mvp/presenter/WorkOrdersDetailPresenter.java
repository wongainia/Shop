package com.zhenghaikj.shop.mvp.presenter;


import com.zhenghaikj.shop.base.BaseObserver2;
import com.zhenghaikj.shop.base.BaseResult;
import com.zhenghaikj.shop.entity.Address;
import com.zhenghaikj.shop.entity.Data;
import com.zhenghaikj.shop.entity.WorkOrder;
import com.zhenghaikj.shop.mvp.contract.WorkOrdersDetailContract;

import java.util.List;

public class WorkOrdersDetailPresenter extends WorkOrdersDetailContract.Presenter {


    @Override
    public void GetOrderInfo(String OrderID) {
        mModel.GetOrderInfo(OrderID)
                .subscribe(new BaseObserver2<WorkOrder.DataBean>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<WorkOrder.DataBean> value) {
                        mView.GetOrderInfo(value);
                    }
                });
    }

    @Override
    public void ApplyCustomService(String OrderID) {
        mModel.ApplyCustomService(OrderID)
                .subscribe(new BaseObserver2<Data<String>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Data<String>> value) {
                        mView.ApplyCustomService(value);
                    }
                });
    }

    @Override
    public void ApproveOrderAccessory(String OrderID, String AccessoryApplyState,String NewMoney) {
        mModel.ApproveOrderAccessory(OrderID, AccessoryApplyState,NewMoney)
                .subscribe(new BaseObserver2<Data<String>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Data<String>> value) {
                        mView.ApproveOrderAccessory(value);
                    }
                });
    }

    @Override
    public void ApproveBeyondMoney(String OrderID, String BeyondState) {
        mModel.ApproveBeyondMoney(OrderID, BeyondState)
                .subscribe(new BaseObserver2<Data<String>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Data<String>> value) {
                        mView.ApproveBeyondMoney(value);
                    }
                });
    }

    @Override
    public void ApproveOrderService(String OrderID, String State) {
        mModel.ApproveOrderService(OrderID, State)
                .subscribe(new BaseObserver2<Data<String>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Data<String>> value) {
                        mView.ApproveOrderService(value);
                    }
                });
    }

    @Override
    public void AddOrUpdateExpressNo(String OrderID, String ExpressNo) {
        mModel.AddOrUpdateExpressNo(OrderID, ExpressNo).subscribe(new BaseObserver2<Data<String>>() {
            @Override
            protected void onHandleSuccess(BaseResult<Data<String>> value) {
                mView.AddOrUpdateExpressNo(value);
            }
        });
    }


    @Override
    public void EnSureOrder(String OrderID, String PayPassword, String Grade, String OrgAppraise) {
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
    public void UpdateIsReturnByOrderID(String OrderID, String IsReturn, String AddressBack, String PostPayType) {
        mModel.UpdateIsReturnByOrderID(OrderID, IsReturn, AddressBack, PostPayType)
                .subscribe(new BaseObserver2<Data<String>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Data<String>> value) {
                        mView.UpdateIsReturnByOrderID(value);
                    }
                });
    }
    @Override
    public void GetAccountAddress(String UserId) {
        mModel.GetAccountAddress(UserId)
                .subscribe(new BaseObserver2<List<Address>>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<List<Address>> value) {
                        mView.GetAccountAddress(value);
                    }
                });
    }
}
