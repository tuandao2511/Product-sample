package com.example.productdiscovery.utils.common

import java.text.DecimalFormat

object StringUtils {

    fun priceWithDecimal(price: Double?): String {
        val formatter = DecimalFormat("###,###,###.00")
        return formatter.format(price)
    }

    fun priceWithoutDecimal(price: Double?): String {
        val formatter = DecimalFormat("###,###,###.##")
        return formatter.format(price)
    }

    fun priceToString(price: Double?): String {
        if (price == null) return "Không xác định"
        val toShow = priceWithoutDecimal(price)
        return if (toShow.indexOf(".") > 0) {
            priceWithDecimal(price)
        } else {
            priceWithoutDecimal(price)
        }
    }
}