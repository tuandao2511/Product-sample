package com.example.productdiscovery.screen.base.viewmodel

import androidx.databinding.BaseObservable
import androidx.databinding.Observable
import androidx.lifecycle.MutableLiveData
import com.example.productdiscovery.utils.rx.BaseSchedulerProvider
import com.example.productdiscovery.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

open class BaseItemViewModel() : BaseObservable() {
    protected val compositeDisposable = CompositeDisposable()
    val error: MutableLiveData<Throwable> = MutableLiveData()
    protected val baseSchedulerProvides: BaseSchedulerProvider by lazy { SchedulerProvider.instance }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback) {
        super.removeOnPropertyChangedCallback(callback)
        compositeDisposable.clear()
    }
}