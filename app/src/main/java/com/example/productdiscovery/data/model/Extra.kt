package com.example.productdiscovery.data.model

data class Extra(
    val page: Int? = null,
    val pageSize: Int? = null,
    val priceRanges: List<PriceRange>? = null,
    val totalItems: Int? = null
)