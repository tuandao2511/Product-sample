package com.example.productdiscovery.utils.binding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.productdiscovery.utils.listener.BindableAdapter

object RecyclerViewBinding {
    @JvmStatic
    @BindingAdapter("data")
    fun <T> setRecyclerViewProperties(recyclerView: RecyclerView, items: T) {
        if (recyclerView.adapter is BindableAdapter<*> && items!=null) {
            (recyclerView.adapter as BindableAdapter<T>).refreshData(items)
        }
    }
}