package com.example.productdiscovery.di.module

import com.example.productdiscovery.di.scope.ActivityScope
import com.example.productdiscovery.screen.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = [FragmentBuilderModule::class])
    abstract fun mainActivity(): MainActivity
}
