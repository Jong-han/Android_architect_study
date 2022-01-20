package com.jh.navermovie.ext

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("poster")
fun ImageView.setPoster(url: String) {

    Glide.with(this)
        .load( url )
        .into(this)

}