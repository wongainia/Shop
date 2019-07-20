package com.zhenghaikj.shop.mvp.contract;


import com.zhenghaikj.shop.base.BaseModel;
import com.zhenghaikj.shop.base.BasePresenter;
import com.zhenghaikj.shop.base.BaseResult;
import com.zhenghaikj.shop.base.BaseView;
import com.zhenghaikj.shop.entity.Data;
import com.zhenghaikj.shop.entity.WorkOrder;

import java.util.List;

import io.reactivex.Observable;


public interface AllWorkOrdersContract {
    interface Model extends BaseModel {
        Observable<BaseResult<WorkOrder>> GetOrderInfoList(String UserID, String state, String page, String limit);
        Observable<BaseResult<Data<String>>> UpdateOrderState(String OrderID, String Content);
        Observable<BaseResult<Data<String>>> FactoryComplaint(String OrderID, String Content);
        Observable<BaseResult<Data<String>>> EnSureOrder(String OrderID, String PayPassword,String Grade,String OrgAppraise);
        Observable<BaseResult<Data<String>>> FactoryEnsureOrder(String OrderID, String PayPassword);
        Observable<BaseResult<Data<String>>> UpdateOrderFIsLook(String OrderID, String IsLook, String FIsLook);
        Observable<BaseResult<Data<List<WorkOrder.DataBean>>>> GetOrderByhmalluserid(String UserID,String State);
    }

    interface View extends BaseView {
        void GetOrderInfoList(BaseResult<WorkOrder> baseResult);
        void UpdateOrderState(BaseResult<Data<String>> baseResult);
        void FactoryComplaint(BaseResult<Data<String>> baseResult);
        void EnSureOrder(BaseResult<Data<String>> baseResult);
        void FactoryEnsureOrder(BaseResult<Data<String>> baseResult);
        void UpdateOrderFIsLook(BaseResult<Data<String>> baseResult);
        void GetOrderByhmalluserid(BaseResult<Data<List<WorkOrder.DataBean>>> baseResult);
    }

    abstract class Presenter extends BasePresenter<View,Model> {
        public abstract void GetOrderInfoList(String UserID,String state, String page,String limit);
        public abstract void UpdateOrderState(String OrderID,String Content);
        public abstract void FactoryComplaint(String OrderID,String Content);
        public abstract void EnSureOrder(String OrderID, String PayPassword,String Grade,String OrgAppraise);
        public abstract void FactoryEnsureOrder(String OrderID, String PayPassword);
        public abstract void UpdateOrderFIsLook(String OrderID, String IsLook,String FIsLook);
        public abstract void GetOrderByhmalluserid(String UserID,String State);
    }
}
