package com.jh.navermovie.ui.main.review

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.jh.navermovie.App
import com.jh.navermovie.R
import com.jh.navermovie.databinding.FragmentReviewBinding
import com.jh.navermovie.db.MovieDB
import com.jh.navermovie.db.ReviewEntity
import kotlinx.coroutines.launch

class ReviewFragment : Fragment(), ReviewContract.View {

    private lateinit var dataBinding: FragmentReviewBinding
    private val adapter by lazy { ReviewAdapter() }
    private val reviewPresenter by lazy { ReviewPresenterImpl() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_review, container, false)
        dataBinding.lifecycleOwner = this
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        reviewPresenter.initPresenter(this, App.db)
        dataBinding.rvReview.adapter = this.adapter

    }

    override fun onResume() {
        super.onResume()
        lifecycleScope.launch {
            reviewPresenter.onResumeReview()
        }
    }

    override fun submitReviewList(list: List<ReviewEntity>) {
        adapter.submitList(list)
    }

}