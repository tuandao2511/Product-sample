package com.example.productdiscovery.di.component

import com.example.productdiscovery.di.module.ActivityBuilderModule
import com.example.productdiscovery.di.module.AppModule
import com.example.productdiscovery.di.module.DataModule
import com.example.productdiscovery.screen.base.PDApp
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AndroidSupportInjectionModule::class,
        ActivityBuilderModule::class,
        AppModule::class,
        AppModule::class,
        DataModule::class]
)
interface AppComponent : AndroidInjector<PDApp> {
    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<PDApp>()
}