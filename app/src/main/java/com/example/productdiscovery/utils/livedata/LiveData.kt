package com.example.productdiscovery.utils.livedata

import androidx.lifecycle.*
import androidx.lifecycle.Observer
import io.reactivex.*
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException



fun <T : Any?> MutableLiveData<T>.default(initialValue: T) = apply { setValue(initialValue) }


fun <T : Any?> MutableLiveData<T>.notifyDataChange() {
    this.value = this.value
}

fun <T> LiveData<T>.observeOnce(lifecycleOwner: LifecycleOwner, observer: Observer<T>) {
    observe(lifecycleOwner, object : Observer<T> {
        override fun onChanged(t: T?) {
            observer.onChanged(t)
            removeObserver(this)
        }
    })
}

//fun <T> Flowable<T>.toLiveData() : LiveData<T> {
//    return LiveDataReactiveStreams.fromPublisher(this)
//}
//
//fun <T> Observable<T>.toLiveData(backPressureStrategy: BackpressureStrategy) : LiveData<T> {
//    return LiveDataReactiveStreams.fromPublisher(this.toFlowable(backPressureStrategy))
//}
//
//fun <T> Single<T>.toLiveData() : LiveData<T> {
//    return LiveDataReactiveStreams.fromPublisher(this.toFlowable())
//}
//
//fun <T> Maybe<T>.toLiveData() : LiveData<T> {
//    return LiveDataReactiveStreams.fromPublisher(this.toFlowable())
//}
//
//fun <T> Completable.toLiveData() : LiveData<T> {
//    return LiveDataReactiveStreams.fromPublisher(this.toFlowable())
//}

//fun <In, Out> combineLatest(vararg sources: LiveData<In>, combineFunction: (List<In>) -> Out): LiveData<Out> {
//    val finalLiveData: MediatorLiveData<Out> = MediatorLiveData()
//
//    val sourcesIndexToHasEmitted = ConcurrentHashMap<Int, In?>(sources.size)
//    sources.forEachIndexed { index, _ ->
//        sourcesIndexToHasEmitted.put(index, null)
//    }
//
//    sources.forEachIndexed { index, source ->
//        finalLiveData.addSource(source) { value ->
//            sourcesIndexToHasEmitted.put(index, value)
//
//            if (sourcesIndexToHasEmitted.all { (_, value) -> value != null }) {
//                synchronized(finalLiveData) {
//                    @Suppress("UNCHECKED_CAST")
//                    finalLiveData.value = combineFunction(sourcesIndexToHasEmitted.values.toList() as List<In>)
//                }
//            }
//        }
//    }
//
//    return finalLiveData
//}

fun <T> LiveData<T>.getOrAwaitValue(
    time: Long = 2,
    timeUnit: TimeUnit = TimeUnit.SECONDS,
    afterObserve: () -> Unit = {}
): T {
    var data: T? = null
    val latch = CountDownLatch(1)
    val observer = object : Observer<T> {
        override fun onChanged(o: T?) {
            data = o
            latch.countDown()
            this@getOrAwaitValue.removeObserver(this)
        }
    }
    this.observeForever(observer)

    afterObserve.invoke()

    // Don't wait indefinitely if the LiveData is not set.
    if (!latch.await(time, timeUnit)) {
        this.removeObserver(observer)
        throw TimeoutException("LiveData value was never set.")
    }

    @Suppress("UNCHECKED_CAST")
    return data as T
}

/**
 * Observes a [LiveData] until the `block` is done executing.
 */
fun <T> LiveData<T>.observeForTesting(block: () -> Unit) {
    val observer = Observer<T> { }
    try {
        observeForever(observer)
        block()
    } finally {
        removeObserver(observer)
    }
}