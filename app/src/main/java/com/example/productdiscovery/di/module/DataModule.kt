package com.example.productdiscovery.di.module

import android.app.Application
import com.example.productdiscovery.BuildConfig
import com.example.productdiscovery.data.source.remote.DetailDataSource
import com.example.productdiscovery.data.source.remote.DetailRemoteDataSource
import com.example.productdiscovery.data.source.remote.SearchDataSource
import com.example.productdiscovery.data.source.remote.SearchRemoteDataSource
import com.example.productdiscovery.data.source.remote.middleware.InterceptorImp.Companion.MAXIMUM_CACHE_TIME
import com.example.productdiscovery.data.source.remote.middleware.InterceptorImp.Companion.MAXIMUM_REQUEST_TIMEOUT
import com.example.productdiscovery.data.source.remote.service.Api
import com.example.productdiscovery.utils.common.Constant
import com.example.productdiscovery.utils.define.hasNetwork
import dagger.Binds
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
abstract class DataModule {
    @Module
    companion object {
        private const val CACHE_SIZE = (5 * 1024 * 1024).toLong()

        @JvmStatic
        @Singleton
        @Provides
        fun provideInteceptor(application: Application) = Interceptor { chain ->
            var request = chain.request()
            val originalHttpUrl = request.url()
            val newUrl = originalHttpUrl.newBuilder().build()
            request = if (application.hasNetwork()) {
                request.newBuilder()
                    .url(newUrl)
                    .header(
                        "Cache-Control",
                        "public, max-age=${MAXIMUM_REQUEST_TIMEOUT}"
                    )
                    .build()
            } else {
                request.newBuilder()
                    .url(newUrl)
                    .header(
                        "Cache-Control",
                        "public, only-if-cached, max-stale=${MAXIMUM_CACHE_TIME}"
                    )
                    .build()
            }
            chain.proceed(request)
        }

        @JvmStatic
        @Singleton
        @Provides
        fun provideCache(application: Application) = Cache(application.cacheDir, CACHE_SIZE)

        @JvmStatic
        @Singleton
        @Provides
        fun provideHttpLog(): HttpLoggingInterceptor {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            return interceptor
        }

        @JvmStatic
        @Singleton
        @Provides
        fun provideOkHttpClient(
            interceptor: Interceptor,
            httpLoggingInterceptor: HttpLoggingInterceptor, cache: Cache
        ): OkHttpClient {
            val okHttpClient = OkHttpClient.Builder()
            if (BuildConfig.DEBUG) {
                okHttpClient.addInterceptor(httpLoggingInterceptor)
            }
            return okHttpClient.addInterceptor(interceptor)
                .cache(cache)
                .build()
        }

        @JvmStatic
        @Singleton
        @Provides
        fun provideRetrofit(okHttpClient: OkHttpClient): Api {
            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(Constant.END_POINT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(Api::class.java)
        }
    }

//    @Singleton
//    @Binds
//    abstract fun provideInteceptor(interceptor: InterceptorImp): Interceptor

    @Singleton
    @Binds
    abstract fun provideSearchDataSource(searchRemoteDataSource: SearchRemoteDataSource)
            : SearchDataSource.RemoteDataSource

    @Singleton
    @Binds
    abstract fun provideDetailDataSource(detailRemoteDataSource: DetailRemoteDataSource)
            : DetailDataSource.RemoteDataSource
}