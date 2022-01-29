package com.jh.navermovie.ui.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.jh.navermovie.ui.main.review.ReviewFragment
import com.jh.navermovie.ui.main.search.SearchFragment

class MainFragmentStateAdapter(fragmentActivity: FragmentActivity): FragmentStateAdapter(fragmentActivity){

    private val fragmentList = listOf(SearchFragment(), ReviewFragment())

    override fun getItemCount(): Int = fragmentList.size

    override fun createFragment(position: Int): Fragment = fragmentList[position]

}