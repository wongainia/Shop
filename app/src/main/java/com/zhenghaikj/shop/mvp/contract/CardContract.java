package com.zhenghaikj.shop.mvp.contract;

import com.zhenghaikj.shop.base.BaseModel;
import com.zhenghaikj.shop.base.BasePresenter;
import com.zhenghaikj.shop.base.BaseResult;
import com.zhenghaikj.shop.base.BaseView;
import com.zhenghaikj.shop.entity.BankCard;
import com.zhenghaikj.shop.entity.Data;
import com.zhenghaikj.shop.entity.UserInfo;

import java.util.List;

import io.reactivex.Observable;


/*添加银行卡页面*/
public interface CardContract {
    interface Model extends BaseModel {
        Observable<BaseResult<UserInfo>> GetUserInfoList(String UserId, String limit);
        Observable<BaseResult<Data<String>>> AddorUpdateAccountPayInfo(String UserId, String PayInfoCode, String PayInfoName, String PayNo,String PayName);
        Observable<BaseResult<List<BankCard>>> GetAccountPayInfoList(String UserId);
        Observable<BaseResult<Data<String>>> GetBankNameByCardNo(String CardNo);


    }

    interface View extends BaseView {
        void GetUserInfoList(BaseResult<UserInfo> baseResult);
        void AddorUpdateAccountPayInfo(BaseResult<Data<String>> baseResult);
        void GetAccountPayInfoList(BaseResult<List<BankCard>> baseResult);
        void GetBankNameByCardNo(BaseResult<Data<String>> baseResult);
    }

    abstract class Presenter extends BasePresenter<View,Model> {
        public abstract void GetUserInfoList(String UserId,String limit);
        public abstract void AddorUpdateAccountPayInfo(String UserId,String PayInfoCode ,String PayInfoName,String PayNo,String PayName);
        public abstract void GetAccountPayInfoList(String UserId);
        public abstract void GetBankNameByCardNo(String CardNo);
    }
}
