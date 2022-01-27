package com.jh.navermovie.ext

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.jh.navermovie.R

@BindingAdapter("poster")
fun ImageView.setPoster(url: String) {

    Glide.with(this.context)
        .load( url )
        .error(R.mipmap.ic_launcher_round)
        .into(this)

}