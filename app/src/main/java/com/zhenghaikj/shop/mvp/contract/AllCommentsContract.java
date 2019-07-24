package com.zhenghaikj.shop.mvp.contract;

import com.zhenghaikj.shop.base.BaseModel;
import com.zhenghaikj.shop.base.BasePresenter;
import com.zhenghaikj.shop.base.BaseView;
import com.zhenghaikj.shop.entity.Comment;

import io.reactivex.Observable;

public interface AllCommentsContract {
    interface Model extends BaseModel {
        Observable<Comment> GetProductComment(String pId, String pageNo, String pageSize, String commentType);
    }

    interface View extends BaseView {
        void GetProductComment(Comment Result);
    }

    abstract class Presenter extends BasePresenter<View,Model> {

        public abstract void  GetProductComment(String pId,String pageNo,String pageSize,String commentType);
    }
}
