package com.zhenghaikj.shop.mvp.contract;

import com.zhenghaikj.shop.base.BaseModel;
import com.zhenghaikj.shop.base.BasePresenter;
import com.zhenghaikj.shop.base.BaseResult;
import com.zhenghaikj.shop.base.BaseView;
import com.zhenghaikj.shop.entity.Data;
import com.zhenghaikj.shop.entity.HistoryVisite;
import com.zhenghaikj.shop.entity.PersonalInformation;
import com.zhenghaikj.shop.entity.UserInfo;
import com.zhenghaikj.shop.entity.WorkOrder;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import retrofit2.http.Field;

public interface MineContract {
    interface Model extends BaseModel {
        Observable<PersonalInformation> PersonalInformation(String UserKey);
        Observable<HistoryVisite> GetHistoryVisite(String userkey);

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
    }

    interface View extends BaseView {
        void PersonalInformation(PersonalInformation result);
        void GetHistoryVisite(HistoryVisite result);
        void GetUserInfoList(BaseResult<UserInfo> Result);
        void GetOrderByhmall(BaseResult<Data<List<WorkOrder.DataBean>>> Result);
        void EnSureOrder(BaseResult<Data<String>> Result);
        void GetOrderInfoList(BaseResult<WorkOrder> baseResult);
    }

    abstract class Presenter extends BasePresenter<View,Model> {
        public abstract void PersonalInformation(String UserKey);
        public abstract void GetHistoryVisite(String userkey);

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
    }

}
