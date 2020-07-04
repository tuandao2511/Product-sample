package com.example.productdiscovery.data.source.remote

import com.example.productdiscovery.data.source.remote.response.BaseResponse
import com.example.productdiscovery.data.source.remote.response.SearchResponse
import com.example.productdiscovery.data.source.remote.service.Api
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SearchRemoteDataSource @Inject constructor(val api: Api) : SearchDataSource.RemoteDataSource {

    override fun searchProducts(query: String, page: Int): Single<BaseResponse<SearchResponse>> {
        return api.searchProducts(query = query, page = page)
    }
}