package com.example.productdiscovery.screen.adapter

import android.content.Context
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.productdiscovery.R
import com.example.productdiscovery.data.model.Product
import com.example.productdiscovery.databinding.ItemProductSearchBinding
import com.example.productdiscovery.screen.base.recyclerview.BaseRecyclerViewAdapter
import com.example.productdiscovery.screen.search.SearchViewModel

class SearchAdapter(context: Context, val searchViewModel: SearchViewModel) : BaseRecyclerViewAdapter<Product, RecyclerView.ViewHolder>(context) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = DataBindingUtil.inflate<ItemProductSearchBinding>(
            layoutInflater, R.layout.item_product_search, parent, false
        )
        return SearchViewHolder(
            binding,
            searchViewModel
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as SearchViewHolder).bindViewHolder(listData[position])
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    class SearchViewHolder(
        private val binding: ItemProductSearchBinding,
        val viewModel: SearchViewModel
    ) : RecyclerView.ViewHolder(binding.root) {

        init {

        }

        fun bindViewHolder(product: Product) {

            binding.viewModel = viewModel
            binding.executePendingBindings()
        }
    }
}
