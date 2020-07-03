package com.example.productdiscovery.utils.listener

interface OnItemClickListener<T> {

    fun onItemClick(item: T, position: Int)
}