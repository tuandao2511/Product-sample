package com.example.productdiscovery.utils.binding

import android.net.Uri
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.productdiscovery.R

object ImageBinding {
    @JvmStatic
    @BindingAdapter("app:imageUrl")
    fun setImage(imageView: ImageView, path: String?) {
        if (path!=null) {
            Glide.with(imageView.context)
                .load(Uri.parse(path))
                .apply(RequestOptions()
                    .error(R.drawable.image_error)
                    .placeholder(R.drawable.image_error))
                .into(imageView)
        } else {
            Glide.with(imageView.context)
                .load(R.drawable.image_error)
                .into(imageView)
        }
    }
}