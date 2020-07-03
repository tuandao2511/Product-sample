package com.example.productdiscovery.di.module

import android.app.Application
import android.content.Context
import com.example.productdiscovery.screen.base.PDApp
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class])
class AppModule {

    @Provides
    @Singleton
    fun provideContext(pdApp: PDApp): Context = pdApp.applicationContext

    @Provides
    @Singleton
    fun provideApplication(pdApp: PDApp): Application = pdApp
}
