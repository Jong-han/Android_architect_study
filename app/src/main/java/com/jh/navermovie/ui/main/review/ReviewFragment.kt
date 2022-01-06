package com.jh.navermovie.ui.main.review

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.jh.navermovie.R
import com.jh.navermovie.databinding.FragmentReviewBinding
import com.jh.navermovie.db.MovieDB
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ReviewFragment : Fragment() {

    private lateinit var dataBinding: FragmentReviewBinding
    private val adapter by lazy { ReviewAdapter() }
    private val db by lazy { Room.databaseBuilder(requireContext(), MovieDB::class.java, "MovieDB.db").build() }

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

        dataBinding.rvReview.adapter = this.adapter

    }

    override fun onResume() {
        super.onResume()
        lifecycleScope.launch {
            val reviewList = withContext(Dispatchers.IO) {
                db.reviewDAO().getAll()
            }
            adapter.submitList(reviewList)
        }
    }

}