package com.example.productdiscovery.utils.define

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.text.Editable
import android.widget.Toast

inline fun <T : Any> T?.notNull(f: (it: T) -> Unit) {
    if (this != null) f(this)
}

fun String.toEditable(): Editable =  Editable.Factory.getInstance().newEditable(this)

fun Context.hasNetwork(): Boolean {
    var isConnected = false // Initial Value
    val connectivityManager = this.getSystemService(Context.CONNECTIVITY_SERVICE)
            as ConnectivityManager
    val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
    if (activeNetwork != null && activeNetwork.isConnected)
        isConnected = true
    return isConnected
}

fun Context.showErrorMessage(messageError: String) {
    Toast.makeText(this, messageError, Toast.LENGTH_SHORT).show()
}