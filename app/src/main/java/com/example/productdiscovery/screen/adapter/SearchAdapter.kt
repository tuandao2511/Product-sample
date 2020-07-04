package com.example.productdiscovery.screen.adapter

import android.content.Context
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.productdiscovery.R
import com.example.productdiscovery.data.model.Product
import com.example.productdiscovery.databinding.ItemProductSearchBinding
import com.example.productdiscovery.screen.base.recyclerview.BaseRecyclerViewAdapter
import com.example.productdiscovery.screen.search.SearchViewModel
import com.example.productdiscovery.utils.common.ProductDiffCallback
import com.example.productdiscovery.utils.common.StringUtils

class SearchAdapter(context: Context, val searchViewModel: SearchViewModel) : BaseRecyclerViewAdapter<Product, RecyclerView.ViewHolder>(context) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = DataBindingUtil.inflate<ItemProductSearchBinding>(
            layoutInflater, R.layout.item_product_search, parent, false
        )
        return SearchViewHolder(binding, searchViewModel)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as SearchViewHolder).bindViewHolder(listData[position])
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    fun setDate(newProducts: List<Product>?) {
        newProducts?.let {
            val diffCallback = ProductDiffCallback(listData, newProducts)
            val diffResult = DiffUtil.calculateDiff(diffCallback)
            listData.clear()
            listData.addAll(newProducts)
            diffResult.dispatchUpdatesTo(this)
        }
    }

    class SearchViewHolder(
        private val binding: ItemProductSearchBinding,
        private val viewModel: SearchViewModel
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bindViewHolder(product: Product) {

            product.price?.let {
                binding.supplierSalePrice = StringUtils.priceToString(product.price.supplierSalePrice)
            }
            product.images?.let {
                if (it.isEmpty()) {
                    binding.imageUrl = ""
                } else {
                    binding.imageUrl = it[0].url
                }
            }
            binding.product  = product
            binding.viewModel = viewModel
            binding.executePendingBindings()
        }
    }
}
