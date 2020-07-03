package com.example.productdiscovery.di.module

import com.example.productdiscovery.di.scope.FragmentScope
import com.example.productdiscovery.screen.search.SearchFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuilderModule {

    @FragmentScope
    @ContributesAndroidInjector
    abstract fun searchFragment(): SearchFragment
}