package com.example.productdiscovery.screen.base.fragment

import android.os.Bundle
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

abstract class BaseBottomSheetFragment : BottomSheetDialogFragment() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        retrieveViewOrRestoreState()
    }

    abstract fun retrieveViewOrRestoreState()

    abstract fun initComponent()

    abstract fun initView()

    abstract fun registerData()
}