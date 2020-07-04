package com.example.productdiscovery.utils.common

import androidx.recyclerview.widget.DiffUtil
import com.example.productdiscovery.data.model.Product
import android.os.Bundle



class ProductDiffCallback (private val oldList: List<Product>, private val newList: List<Product>) : DiffUtil.Callback(){

    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].sku == newList[newItemPosition].sku
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldDisplayName = oldList[oldItemPosition].displayName
        val oldSupplierSalePrice = oldList[oldItemPosition].price?.supplierSalePrice
        val newDisplayName = oldList[oldItemPosition].displayName
        val newSupplierSalePrice = oldList[oldItemPosition].price?.supplierSalePrice
        return oldDisplayName == newDisplayName && oldSupplierSalePrice == newSupplierSalePrice
    }

    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
        val diff = Bundle()
        val oldDisplayName = oldList[oldItemPosition].displayName
        val oldSupplierSalePrice = oldList[oldItemPosition].price?.supplierSalePrice
        val newDisplayName = oldList[oldItemPosition].displayName
        val newSupplierSalePrice = oldList[oldItemPosition].price?.supplierSalePrice

        if (newDisplayName !=null && oldDisplayName != newDisplayName) {
            diff.putString("displayName", newDisplayName)
        }

        if (newSupplierSalePrice!=null && oldSupplierSalePrice != newSupplierSalePrice) {
            diff.putDouble("supplierSalePrice", newSupplierSalePrice)
        }

        if (diff.size() == 0 ) {
            return null
        }
        return diff
    }
}