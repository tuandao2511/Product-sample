package com.example.productdiscovery.utils.rx

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SchedulerProvider private constructor() : BaseSchedulerProvider {

    companion object {
        val instance: BaseSchedulerProvider by lazy { SchedulerProvider() }
    }

    override fun io(): Scheduler {
        return Schedulers.io()
    }

    override fun ui(): Scheduler {
        return AndroidSchedulers.mainThread()
    }

    override fun single(): Scheduler {
        return Schedulers.single()
    }
}