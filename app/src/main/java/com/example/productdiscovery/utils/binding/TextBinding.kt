package com.example.productdiscovery.utils.binding

import android.widget.TextView
import androidx.databinding.BindingAdapter

object TextBinding {
    @JvmStatic
    @BindingAdapter("android:text")
    fun<T> setText(textView: TextView, value: T?) {
        if (value == null) return
        textView.text = value.toString()
    }
}