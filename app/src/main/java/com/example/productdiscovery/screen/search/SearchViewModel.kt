package com.example.productdiscovery.screen.search

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.productdiscovery.data.model.Product
import com.example.productdiscovery.data.repository.SearchRepository
import com.example.productdiscovery.data.source.remote.response.NetworkStatus
import com.example.productdiscovery.screen.base.viewmodel.BaseViewModel
import java.util.ArrayList

class SearchViewModel(application: Application, val repository: SearchRepository) : BaseViewModel(application){

    private var _products: MutableLiveData<NetworkStatus<List<Product>>> =
        MutableLiveData<NetworkStatus<List<Product>>>().apply { emptyList<Product>() }
    val products: LiveData<NetworkStatus<List<Product>>>
        get() = _products

    fun getSchedulerProvider() = baseSchedulerProvides

    fun searchProducts(keyword: String) {
        val disposable = repository.searchProducts(keyword)
            .subscribeOn(baseSchedulerProvides.io())
            .observeOn(baseSchedulerProvides.ui())
            .subscribe({
                it.result?.data?.let { products ->
                    _products.value = NetworkStatus.success(products)
                }
            }, {
                _products.value = NetworkStatus.failure(it)
            })
        compositeDisposable.addAll(disposable)
    }

    fun clearSearchedResult() {
        val emptyProducts = ArrayList<Product>()
        _products.value = NetworkStatus.success(emptyProducts)
    }
}
