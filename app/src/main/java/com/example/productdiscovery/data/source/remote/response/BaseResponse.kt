package com.example.productdiscovery.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class BaseResponse<T> (
    @SerializedName("result")
    val result: T? = null,
    @SerializedName("code")
    val code: String? = null,
    @SerializedName("error")
    val error: String? = null
)