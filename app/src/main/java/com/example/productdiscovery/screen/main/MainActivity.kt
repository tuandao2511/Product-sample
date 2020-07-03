package com.example.productdiscovery.screen.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.productdiscovery.R
import com.example.productdiscovery.databinding.ActivityMainBinding
import com.example.productdiscovery.screen.base.activity.BaseActivityDataBinding

class MainActivity : BaseActivityDataBinding<ActivityMainBinding, MainViewModel>() {
    override fun getLayoutRes() = R.layout.activity_main

    override fun retrieveViewOrRestoreState(savedInstanceState: Bundle?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun initComponent() {
    }

    override fun initView() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun registerData() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
