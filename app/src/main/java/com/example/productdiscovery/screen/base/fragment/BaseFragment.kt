package com.example.productdiscovery.screen.base.fragment

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import dagger.android.support.DaggerFragment
import javax.inject.Inject

abstract class BaseFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        retrieveViewOrRestoreState()
    }

    abstract fun retrieveViewOrRestoreState()

}