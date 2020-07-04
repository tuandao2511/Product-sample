package com.example.productdiscovery.data.model

/**
 * chưa có SerializedName cho từng field, cẩn thận lỗi không thể reflaxtion khi config proguard
 */
data class Product(
    val sku: String? = null,
    val attributeSet: AttributeSet? = null,
    val brand: Brand ?= null,
    val categories: List<Category>? = null,
    val displayName: String? = null,
    val images: List<Image>? = null,
    val isBundle: Boolean? = null,
    val magentoId: Int? = null,
    val name: String? = null,
    val objective: Objective? = null,
    val price: Price? = null,
    val productLine: ProductLine? = null,
    val productType: ProductType? = null,
    val promotionPrices: List<PromotionPrice>? = null,
    val rating: Rating? = null,
    val seller: Seller? = null,
    val serialGenerated: Boolean? = null,
    val serialManaged: Boolean? = null,
    val status: Status? = null,
    val totalAvailable: Double? = null,
    val totalAvailableByStocks: List<TotalAvailableByStock>? = null,
    val url: String? = null
)
