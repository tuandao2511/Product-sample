package com.example.productdiscovery.data.source.remote.response

import com.example.productdiscovery.data.model.Product
import com.google.gson.annotations.SerializedName

data class SearchResponse(
    @SerializedName("products")
    val data: List<Product>? = null
)
