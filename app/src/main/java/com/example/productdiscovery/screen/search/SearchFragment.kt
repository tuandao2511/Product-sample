package com.example.productdiscovery.screen.search

import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.productdiscovery.R
import com.example.productdiscovery.data.model.Product
import com.example.productdiscovery.data.source.remote.response.NetworkStatus
import com.example.productdiscovery.databinding.FragmentSearchBinding
import com.example.productdiscovery.screen.adapter.SearchAdapter
import com.example.productdiscovery.screen.base.fragment.BaseDataBindingFragment
import com.example.productdiscovery.utils.common.Constant.SEARCH_DEBOUNCE_TIME
import com.example.productdiscovery.utils.define.showErrorMessage
import com.example.productdiscovery.utils.listener.EndLessScrollListener
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.layout_search_box.view.*
import java.util.concurrent.TimeUnit

class SearchFragment : BaseDataBindingFragment<FragmentSearchBinding, SearchViewModel>(){

    override fun getLayoutId() = R.layout.fragment_search

    companion object {
        fun newInstance(): SearchFragment {
            val searchFragment = SearchFragment()
            return searchFragment
        }
    }

    private lateinit var adapter: SearchAdapter
    private var lastQuery: String = ""
    private lateinit var endLessScrollListener: EndLessScrollListener

    override fun initOnCreateView() {
        initComponent()
        initView()
        registerData()
    }

    override fun initComponent() {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(SearchViewModel::class.java)
        viewDataBinding.viewModel = viewModel
    }

    override fun initView() {
        adapter = SearchAdapter(this.context!!.applicationContext, viewModel)
        val linearLayoutManager = LinearLayoutManager(context)
        viewDataBinding.recyclerSearchProduct.layoutManager = linearLayoutManager
        viewDataBinding.recyclerSearchProduct.adapter = adapter

        endLessScrollListener = object : EndLessScrollListener(linearLayoutManager) {
            override fun onLoadMore(view: RecyclerView, totalItemCount: Int, page: Int) {
                viewModel.searchProducts(lastQuery, page)
            }
        }
        viewDataBinding.recyclerSearchProduct.addOnScrollListener(endLessScrollListener)
        setupActionBar()
    }

    override fun retrieveViewOrRestoreState() {
    }

    override fun registerData() {
        viewModel.products.observe(viewLifecycleOwner, Observer {
            when(it) {
                is NetworkStatus.Success -> {
                    it?.data?.let {products ->
                        adapter.refreshData(products as MutableList<Product>)
                    }
                }
                is NetworkStatus.Failure -> {
                    activity!!.showErrorMessage(it.e.message.toString())
                }
            }
        })

        viewModel.productsPage.observe(viewLifecycleOwner, Observer {
            when(it) {
                is NetworkStatus.Success -> {
                    it?.data?.let {products ->
                        adapter.addData(products as MutableList<Product>)
                    }
                }
                is NetworkStatus.Failure -> {
                    activity!!.showErrorMessage(it.e.message.toString())
                }
            }
        })

        viewModel.clearSearch.observe(viewLifecycleOwner, Observer {
            it.getContentIfNotHandled()?.let {
                adapter.refreshData(ArrayList())
            }
        })
    }

    private fun setupActionBar() {
        val actionBar = activity!!.actionBar
        actionBar?.hide()
        setupSearchView()
    }

    private fun setupSearchView() {
        RxSearchObservable.fromView(viewDataBinding.layoutSearchBox.searchProduct)
            .debounce(SEARCH_DEBOUNCE_TIME, TimeUnit.MILLISECONDS)
            .distinctUntilChanged()
            .subscribeOn(viewModel.getSchedulerProvider().io())
            .observeOn(viewModel.getSchedulerProvider().ui())
            .subscribe {
                endLessScrollListener.resetState()
                if (!it.isNullOrEmpty()) {
                    lastQuery = it.trim()
                    viewModel.searchProducts(lastQuery, 1)
                    return@subscribe
                }
                lastQuery = ""
                viewModel.clearSearchedResult()
            }
    }

    object RxSearchObservable {
        fun fromView(searchView: SearchView): Observable<String> {
            val subject: PublishSubject<String> = PublishSubject.create()

            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    subject.onComplete()
                    return true
                }

                override fun onQueryTextChange(text: String?): Boolean {
                    subject.onNext(text!!)
                    return true
                }

            })
            return subject
        }
    }
}
