package com.zhenghaikj.shop.mvp.contract;

import com.zhenghaikj.shop.base.BaseModel;
import com.zhenghaikj.shop.base.BasePresenter;
import com.zhenghaikj.shop.base.BaseView;
import com.zhenghaikj.shop.entity.AddtoCartResult;
import com.zhenghaikj.shop.entity.CollectResult;
import com.zhenghaikj.shop.entity.DetailResult;
import com.zhenghaikj.shop.entity.GetCommentResult;
import com.zhenghaikj.shop.entity.GetGoodSKu;

import io.reactivex.Observable;

public interface AllCommentsContract {
    interface Model extends BaseModel {
        Observable<String> GetProductComment(String pId,String pageNo,String pageSize,String commentType);
    }

    interface View extends BaseView {
        void GetProductComment(String Result);
    }

    abstract class Presenter extends BasePresenter<View,Model> {

        public abstract void  GetProductComment(String pId,String pageNo,String pageSize,String commentType);
    }
}
