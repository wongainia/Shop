package com.zhenghaikj.shop.mvp.presenter;

import com.zhenghaikj.shop.base.BaseObserver2;
import com.zhenghaikj.shop.base.BaseResult;
import com.zhenghaikj.shop.entity.Data;
import com.zhenghaikj.shop.entity.UserInfo;
import com.zhenghaikj.shop.mvp.contract.ChangePayPasswordContract;

public class ChangePayPasswordPresenter extends ChangePayPasswordContract.Presenter {

    @Override
    public void GetUserInfoList(String UserId, String limit) {
        mModel.GetUserInfoList(UserId, limit)
                .subscribe(new BaseObserver2<UserInfo>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<UserInfo> value) {
                        mView.GetUserInfoList(value);
                    }
                });
    }
    @Override
    public void ChangePayPassword(String UserID, String OldPayPassword, String PayPassword) {
        mModel.ChangePayPassword(UserID, OldPayPassword, PayPassword)
                .subscribe(new BaseObserver2<Data>() {
                    @Override
                    protected void onHandleSuccess(BaseResult<Data> value) {
                        mView.ChangePayPassword(value);
                    }
                });
    }


}
