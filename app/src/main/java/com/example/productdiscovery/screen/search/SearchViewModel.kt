package com.example.productdiscovery.screen.search

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.productdiscovery.data.model.Product
import com.example.productdiscovery.data.repository.SearchRepository
import com.example.productdiscovery.data.source.remote.response.NetworkStatus
import com.example.productdiscovery.screen.base.viewmodel.BaseViewModel
import com.example.productdiscovery.utils.livedata.Event
import java.util.*
import javax.inject.Inject

class SearchViewModel @Inject constructor(application: Application, val repository: SearchRepository) : BaseViewModel(application){

    private var _products: MutableLiveData<NetworkStatus<List<Product>>> =
        MutableLiveData<NetworkStatus<List<Product>>>().apply { emptyList<Product>() }
    val products: LiveData<NetworkStatus<List<Product>>>
        get() = _products

    private var _clearSearch : MutableLiveData<Event<Unit>> = MutableLiveData()
    val clearSearch : LiveData<Event<Unit>>
        get() = _clearSearch

    private var _productsPage: MutableLiveData<NetworkStatus<List<Product>>> =
        MutableLiveData<NetworkStatus<List<Product>>>().apply { emptyList<Product>() }
    val productsPage: LiveData<NetworkStatus<List<Product>>>
        get() = _productsPage

    private var pageSize = 1

    fun getSchedulerProvider() = baseSchedulerProvides

    fun searchProducts(keyword: String, page: Int) {
        if (page > pageSize) return

        val disposable = repository.searchProducts(keyword, page)
            .subscribeOn(baseSchedulerProvides.io())
            .observeOn(baseSchedulerProvides.ui())
            .subscribe({
                it?.extra?.pageSize?.let {
                    pageSize = it
                }
                if (page > 1) {
                    it.result?.data?.let {products ->
                        _productsPage.value = NetworkStatus.success(products)
                    }
                } else {
                    it.result?.data?.let { products ->
                        _products.value = NetworkStatus.success(products)
                    }
                }
            }, {
                _products.value = NetworkStatus.failure(it)
            })
        compositeDisposable.addAll(disposable)
    }

    fun clearSearchedResult() {
        _clearSearch.value = Event(Unit)
        pageSize = 1
    }
}
