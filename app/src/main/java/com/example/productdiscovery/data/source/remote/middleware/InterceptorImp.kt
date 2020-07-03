package com.example.productdiscovery.data.source.remote.middleware

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class InterceptorImp @Inject constructor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val builder: Request.Builder = initHeader(chain)
        val request = builder.build()
        return chain.proceed(request)
    }

    private fun initHeader(chain: Interceptor.Chain): Request.Builder {
        val originRequest = chain.request()
        return originRequest.newBuilder()
            .header("Content-Type", "application/json")
            .addHeader("Cache-Control", "public, max-age=$MAXIMUM_REQUEST_TIMEOUT")
            .addHeader("Cache-Control", "public, only-if-cached, max-stale=$MAXIMUM_CACHE_TIME")
            .method(originRequest.method(), originRequest.body())
    }

    companion object {
        const val MAXIMUM_REQUEST_TIMEOUT = 5
        const val MAXIMUM_CACHE_TIME = 60 * 60 * 24 * 1
    }
}
