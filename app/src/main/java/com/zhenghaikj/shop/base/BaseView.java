package com.zhenghaikj.shop.base;

/**
 * MVP
 * 基础View
 */
public interface BaseView {

    // 加载内容
    void contentLoading();

    // 内容加载完成
    void contentLoadingComplete();

    // 内容加载失败
    void contentLoadingError();

    // 内容为空
    void contentLoadingEmpty();

    //当前页面加载条
    void showProgress();

    //当前页面隐藏加载条
    void hideProgress();

}
