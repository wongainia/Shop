package com.zhenghaikj.shop.mvp.presenter;

import com.zhenghaikj.shop.base.BaseObserver2;
import com.zhenghaikj.shop.base.BaseResult;
import com.zhenghaikj.shop.entity.Data;
import com.zhenghaikj.shop.mvp.contract.ChageUserNameContract;

public class ChageUserNamePresenter extends ChageUserNameContract.Presenter {
    @Override
    public void UpdateAccountNickName(String UserID, String NickName) {
        mModel.UpdateAccountNickName(UserID,NickName)
               .subscribe(new BaseObserver2<Data>() {
                   @Override
                   protected void onHandleSuccess(BaseResult<Data> value) {
                       mView.UpdateAccountNickName(value);
                   }
               });
    }
}
