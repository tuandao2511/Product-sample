package com.example.productdiscovery.data.source.remote

import com.example.productdiscovery.data.source.remote.response.BaseResponse
import com.example.productdiscovery.data.source.remote.response.SearchResponse
import io.reactivex.Single

interface SearchDataSource {

    interface RemoteDataSource {
        fun searchProducts(query: String, page: Int) : Single<BaseResponse<SearchResponse>>
    }

}
