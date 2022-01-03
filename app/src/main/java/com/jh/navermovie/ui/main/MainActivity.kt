package com.jh.navermovie.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.google.android.material.tabs.TabLayout
import com.jh.navermovie.R
import com.jh.navermovie.databinding.ActivityMainBinding
import kotlin.math.tan

class MainActivity : AppCompatActivity() {

    private lateinit var dataBinding: ActivityMainBinding
    private val fragmentAdapter by lazy { MainFragmentStateAdapter(this) }

    companion object {
        const val SEARCH = 0
        const val REVIEW = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView<ActivityMainBinding>(
            this,
            R.layout.activity_main
        ).apply {
            lifecycleOwner = this@MainActivity
        }

        dataBinding.vp.adapter = fragmentAdapter
        dataBinding.tabs.addOnTabSelectedListener(onTabSelectedListener)

    }

    private val onTabSelectedListener = object : TabLayout.OnTabSelectedListener {
        override fun onTabSelected(tab: TabLayout.Tab?) {
            tab?.let { t ->
                dataBinding.vp.currentItem = t.position
            }
        }

        override fun onTabUnselected(tab: TabLayout.Tab?) {}

        override fun onTabReselected(tab: TabLayout.Tab?) {}

    }
}