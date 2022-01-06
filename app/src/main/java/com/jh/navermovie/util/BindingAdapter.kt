package com.jh.navermovie.util

import android.view.View
import android.widget.ImageView
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("poster")
fun ImageView.setPoster(url: String) {

    Glide.with(this)
        .load( url )
        .into(this)

}