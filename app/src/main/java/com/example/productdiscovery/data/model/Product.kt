package com.example.productdiscovery.data.model

import com.google.gson.annotations.SerializedName

data class Product(
    @SerializedName("displayName")
    val displayName: String? = null,
    @SerializedName("price")
    val price: Price? = null,
    @SerializedName("images")
    val images: List<Image>? = null,
    @SerializedName("name")
    val name: String? = null
)
