package com.example.productdiscovery.screen.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.example.productdiscovery.R
import com.example.productdiscovery.databinding.ActivityMainBinding
import com.example.productdiscovery.screen.base.activity.BaseActivityDataBinding
import com.example.productdiscovery.screen.search.SearchFragment

class MainActivity : BaseActivityDataBinding<ActivityMainBinding, MainViewModel>() {
    override fun getLayoutRes() = R.layout.activity_main

    override fun retrieveViewOrRestoreState(savedInstanceState: Bundle?) {
        initComponent()
        initView()
        val searchFragment = SearchFragment.newInstance()
        supportFragmentManager.beginTransaction().add(R.id.flMain, searchFragment, SearchFragment::class.java.name)
            .commit()
    }

    override fun initComponent() {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)
        viewDataBinding.viewModel = viewModel
    }

    override fun initView() {
        setupActionBar()
    }

    override fun registerData() {
    }

    private fun setupActionBar() {
        val actionBar = supportActionBar
        actionBar?.hide()
    }
}
