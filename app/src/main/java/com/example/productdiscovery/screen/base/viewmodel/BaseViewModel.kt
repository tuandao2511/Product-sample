package com.example.productdiscovery.screen.base.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import com.example.productdiscovery.utils.rx.BaseSchedulerProvider
import com.example.productdiscovery.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

open class BaseViewModel(application: Application) :
    AndroidViewModel(application),
    LifecycleObserver {

    protected val compositeDisposable = CompositeDisposable()
    val error: MutableLiveData<Throwable> = MutableLiveData()
    protected val baseSchedulerProvides: BaseSchedulerProvider by lazy { SchedulerProvider.instance }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}