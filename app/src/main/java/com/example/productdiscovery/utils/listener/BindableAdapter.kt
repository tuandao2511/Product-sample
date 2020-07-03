package com.example.productdiscovery.utils.listener

interface BindableAdapter<T> {
    fun refreshData(data: T)
}