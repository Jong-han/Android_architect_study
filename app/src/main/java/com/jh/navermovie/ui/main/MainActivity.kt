package com.jh.navermovie.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.google.android.material.tabs.TabLayoutMediator
import com.jh.navermovie.R
import com.jh.navermovie.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var dataBinding: ActivityMainBinding
    private val fragmentAdapter by lazy { MainFragmentStateAdapter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView<ActivityMainBinding>(
            this,
            R.layout.activity_main
        ).apply {
            lifecycleOwner = this@MainActivity
        }

        dataBinding.vp.adapter = fragmentAdapter
        initTabLayout()

    }

    private fun initTabLayout() {
        TabLayoutMediator(dataBinding.tabs, dataBinding.vp) { tab, position ->
            when(position) {
                0 -> tab.text = "검색"
                1 -> tab.text = "한줄평"
            }
        }.attach()
    }
}