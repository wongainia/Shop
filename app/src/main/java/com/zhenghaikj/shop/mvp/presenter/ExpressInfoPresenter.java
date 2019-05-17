package com.zhenghaikj.shop.mvp.presenter;

import com.zhenghaikj.shop.base.BaseObserver2;
import com.zhenghaikj.shop.base.BaseResult;
import com.zhenghaikj.shop.entity.Data;
import com.zhenghaikj.shop.entity.Logistics;
import com.zhenghaikj.shop.entity.WorkOrder;
import com.zhenghaikj.shop.mvp.contract.ExpressInfoContract;

import java.util.List;

public class ExpressInfoPresenter extends ExpressInfoContract.Presenter {
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
    public void GetOrderInfo(String OrderID) {
        mModel.GetOrderInfo(OrderID).subscribe(new BaseObserver2<WorkOrder.DataBean>() {
            @Override
            protected void onHandleSuccess(BaseResult<WorkOrder.DataBean> value) {
                mView.GetOrderInfo(value);
            }
        });
    }

}
