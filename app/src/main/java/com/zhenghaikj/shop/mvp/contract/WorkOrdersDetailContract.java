package com.zhenghaikj.shop.mvp.contract;


import com.zhenghaikj.shop.base.BaseModel;
import com.zhenghaikj.shop.base.BasePresenter;
import com.zhenghaikj.shop.base.BaseResult;
import com.zhenghaikj.shop.base.BaseView;
import com.zhenghaikj.shop.entity.Address;
import com.zhenghaikj.shop.entity.Data;
import com.zhenghaikj.shop.entity.WorkOrder;

import java.util.List;

import io.reactivex.Observable;


public interface WorkOrdersDetailContract {
    interface Model extends BaseModel {
        Observable<BaseResult<WorkOrder.DataBean>> GetOrderInfo(String OrderID);
        Observable<BaseResult<Data<String>>> ApplyCustomService(String OrderID);
        Observable<BaseResult<Data<String>>> ApproveOrderAccessory(String OrderID, String AccessoryApplyState, String NewMoney);
        Observable<BaseResult<Data<String>>> ApproveBeyondMoney(String OrderID, String BeyondState);
        Observable<BaseResult<Data<String>>> ApproveOrderService(String OrderID, String State);
        Observable<BaseResult<Data<String>>> AddOrUpdateExpressNo(String OrderID, String ExpressNo);

        Observable<BaseResult<Data<String>>> EnSureOrder(String OrderID, String PayPassword);
        Observable<BaseResult<Data<String>>> FactoryEnsureOrder(String OrderID, String PayPassword);
        Observable<BaseResult<Data<String>>> UpdateIsReturnByOrderID(String OrderID, String IsReturn, String AddressBack, String PostPayType);
        Observable<BaseResult<List<Address>>> GetAccountAddress(String UserId);
    }

    interface View extends BaseView {
        void GetOrderInfo(BaseResult<WorkOrder.DataBean> baseResult);

        /**
         * 发起质保
         * @param baseResult
         */
        void ApplyCustomService(BaseResult<Data<String>> baseResult);

        /**
         * 审核派件单
         * @param baseResult
         */
        void ApproveOrderAccessory(BaseResult<Data<String>> baseResult);

        /**
         * 审核远程费
         * @param baseResult
         */
        void ApproveBeyondMoney(BaseResult<Data<String>> baseResult);
         /**
         * 审核服务
         * @param baseResult
         */
        void ApproveOrderService(BaseResult<Data<String>> baseResult);
        void AddOrUpdateExpressNo(BaseResult<Data<String>> baseResult);

        void EnSureOrder(BaseResult<Data<String>> baseResult);
        void FactoryEnsureOrder(BaseResult<Data<String>> baseResult);
        void UpdateIsReturnByOrderID(BaseResult<Data<String>> baseResult);
        void GetAccountAddress(BaseResult<List<Address>> baseResult);
    }

    abstract class Presenter extends BasePresenter<View,Model> {
        public abstract void GetOrderInfo(String OrderID);
        public abstract void ApplyCustomService(String OrderID);
        public abstract void ApproveOrderAccessory(String OrderID,String AccessoryApplyState,String NewMoney);
        public abstract void ApproveBeyondMoney(String OrderID,String BeyondState);
        public abstract void ApproveOrderService(String OrderID,String State);
        public abstract void AddOrUpdateExpressNo(String OrderID,String ExpressNo);

        public abstract void EnSureOrder(String OrderID, String PayPassword);
        public abstract void FactoryEnsureOrder(String OrderID, String PayPassword);
        public abstract void UpdateIsReturnByOrderID(String OrderID, String IsReturn,String AddressBack,String PostPayType);
        public abstract void GetAccountAddress(String UserId);
    }
}
