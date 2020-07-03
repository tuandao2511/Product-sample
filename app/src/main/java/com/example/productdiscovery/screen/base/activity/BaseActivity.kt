package com.example.productdiscovery.screen.base.activity

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

abstract class BaseActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initComponents(savedInstanceState)
    }

    abstract fun initComponents(savedInstanceState: Bundle?)

    fun getSupportActionbar(title: Int) {
        supportActionBar?.title = getString(title)
        supportActionBar?.setDisplayShowTitleEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    fun toast(msg: String?) {
        Toast.makeText(this, msg!!, Toast.LENGTH_SHORT).show()
    }
}