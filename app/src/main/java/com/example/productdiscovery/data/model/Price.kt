package com.example.productdiscovery.data.model

import com.google.gson.annotations.SerializedName

data class Price(
    @SerializedName("supplierSalePrice")
    val supplierSalePrice: Double? = null,
    @SerializedName("sellPrice")
    val sellPrice: Double? = null
)
