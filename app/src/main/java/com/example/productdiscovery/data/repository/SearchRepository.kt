package com.example.productdiscovery.data.repository

import com.example.productdiscovery.data.source.remote.SearchDataSource
import com.example.productdiscovery.data.source.remote.response.BaseResponse
import com.example.productdiscovery.data.source.remote.response.SearchResponse
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SearchRepository @Inject constructor(val remote: SearchDataSource.RemoteDataSource)
    : SearchDataSource.RemoteDataSource {
    override fun searchProducts(query: String): Single<BaseResponse<SearchResponse>> {
        return remote.searchProducts(query)
    }
}
