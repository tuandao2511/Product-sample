package com.example.productdiscovery.screen.base

import com.example.productdiscovery.di.component.DaggerAppComponent
import com.example.productdiscovery.utils.common.TDLogging
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication


class PDApp : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().create(this)
    }

    override fun onCreate() {
        super.onCreate()
        Thread.setDefaultUncaughtExceptionHandler { thread, e ->
            TDLogging.d("AppCrash", "Error ${e.message}")
        }
    }

}