package com.example.productdiscovery.utils.rx

import io.reactivex.Scheduler
import io.reactivex.annotations.NonNull

/**
 * Created by tuan-dao on 7/3/2020.
 * Contact: tuandaouet@gmail.com
 */
interface BaseSchedulerProvider {
    @NonNull
    fun io(): Scheduler

    @NonNull
    fun ui(): Scheduler

    @NonNull
    fun single(): Scheduler
}