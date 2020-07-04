package com.example.productdiscovery.data.source.remote.service

import com.example.productdiscovery.data.source.remote.response.BaseResponse
import com.example.productdiscovery.data.source.remote.response.SearchResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("api/search/")
    fun searchProducts(
        @Query("channel") channel: String = "pv_showroom"
        , @Query("q") query: String, @Query("terminal") terminal: String = "CP01",
        @Query("_page") page: Int)
            : Single<BaseResponse<SearchResponse>>

//    @GET("api/search/")
//    fun searchProducts(
//        @Query("channel") channel: String = "pv_showroom"
//        , @Query("q") query: String, @Query("terminal") terminal: String = "CP01")
//            : Single<String>
}