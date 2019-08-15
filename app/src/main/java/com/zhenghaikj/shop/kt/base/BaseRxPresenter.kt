package com.zhenghaikj.shop.kt.base

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
Data:2019/8/14
Time:14:23
author:ying
 **/
open class BaseRxPresenter<V:IBaseView> :IBasePresenter<V> {

    var mView:V?=null
    private set

    private var compositeDisposable:CompositeDisposable?=null

    fun addSubScription(disposable: Disposable){
        if (compositeDisposable==null){
            compositeDisposable= CompositeDisposable()
        }
        compositeDisposable?.add(disposable)

    }

    override fun attachView(view: V) {
        this.mView=view
    }

    override fun detachView() {
        mView=null
        compositeDisposable?.run {
            if (!isDisposed){
                clear()
            }

        }

    }
}