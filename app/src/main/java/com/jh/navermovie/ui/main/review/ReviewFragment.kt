package com.jh.navermovie.ui.main.review

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.jh.navermovie.R
import com.jh.navermovie.databinding.FragmentReviewBinding
import com.jh.navermovie.ext.repeatOnStarted
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class ReviewFragment : Fragment() {

    private lateinit var dataBinding: FragmentReviewBinding
    private val viewModel: ReviewViewModel by viewModels()
    private val adapter by lazy { ReviewAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_review, container, false)
        dataBinding.lifecycleOwner = this
        dataBinding.viewModel = viewModel
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dataBinding.rvReview.adapter = this.adapter

        repeatOnStarted {
            viewModel.getReviewList().collect {
                adapter.submitList(it.reversed())
                dataBinding.rvReview.scrollToPosition(0)
            }
        }

    }
}