package com.example.productdiscovery.screen.base.activity

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LifecycleOwner
import com.example.productdiscovery.screen.base.viewmodel.BaseViewModel

abstract class BaseActivityDataBinding<ViewBinding : ViewDataBinding, ViewModel : BaseViewModel> :
    BaseActivity(), LifecycleOwner {

    lateinit var viewDataBinding: ViewBinding

    lateinit var viewModel: ViewModel

    override fun initComponents(savedInstanceState: Bundle?) {
        viewDataBinding = DataBindingUtil.setContentView(this, getLayoutRes())
        viewDataBinding.setLifecycleOwner(this)
        retrieveViewOrRestoreState(savedInstanceState)
    }

    abstract fun retrieveViewOrRestoreState(savedInstanceState: Bundle?)

    abstract fun getLayoutRes(): Int

    abstract fun initComponent()

    abstract fun initView()

    abstract fun registerData()
}