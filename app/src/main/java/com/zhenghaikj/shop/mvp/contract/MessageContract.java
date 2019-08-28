package com.zhenghaikj.shop.mvp.contract;

import com.zhenghaikj.shop.base.BaseModel;
import com.zhenghaikj.shop.base.BasePresenter;
import com.zhenghaikj.shop.base.BaseResult;
import com.zhenghaikj.shop.base.BaseView;
import com.zhenghaikj.shop.entity.Announcement;
import com.zhenghaikj.shop.entity.AnnouncementDetail;
import com.zhenghaikj.shop.entity.MessageReadResult;
import com.zhenghaikj.shop.entity.UserInfo;

import io.reactivex.Observable;

public interface MessageContract {
    interface Model extends BaseModel {
        Observable<Announcement> GetList(String categoryId, String rows, String page, String userkey);
        Observable<AnnouncementDetail> GetDetail(String id);
        Observable<MessageReadResult> AddArticlRead(String UserId, String CategoryId, String HiMallArticleId);
        Observable<BaseResult<UserInfo>> GetUserInfoList(String userName, String limit);
    }

    interface View extends BaseView {
        void GetList(Announcement result);
        void GetDetail(AnnouncementDetail result);
        void AddArticlRead(MessageReadResult result);
        void GetUserInfoList(BaseResult<UserInfo> Result);
    }

    abstract class Presenter extends BasePresenter<View,Model> {
        public abstract void GetList(String categoryId,String rows, String page, String userkey);
        public abstract void GetDetail(String id);
        public abstract void AddArticlRead(String UserId,String CategoryId,String HiMallArticleId);
        public abstract void GetUserInfoList(String userName, String limit);
    }
}
