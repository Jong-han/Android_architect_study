package com.jh.navermovie.ui.main.search

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.jh.navermovie.R
import com.jh.navermovie.data.Resource
import com.jh.navermovie.data.local.db.ReviewEntity
import com.jh.navermovie.data.remote.response.Movie
import com.jh.navermovie.databinding.FragmentSearchBinding
import com.jh.navermovie.ui.dialog.ReviewDialog
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private lateinit var dataBinding: FragmentSearchBinding
    private val viewModel: SearchViewModel by viewModels()
    private val adapter by lazy { SearchAdapter(onClickMovie, onClickDetail) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dataBinding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_search, container, false)
        dataBinding.lifecycleOwner = this
        dataBinding.viewModel = viewModel
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dataBinding.rvSearch.adapter = this.adapter

        dataBinding.btnSearch.setOnClickListener {
            lifecycleScope.launch {
                viewModel.getFilteredMovies().collect {
                    when (it) {
                        is Resource.Loading -> { Toast.makeText(requireContext(), "영화 정보를 가져옵니다", Toast.LENGTH_SHORT).show() }
                        is Resource.Success -> {
                            Toast.makeText(requireContext(), "영화 정보 가져오는데 성공했어요", Toast.LENGTH_SHORT).show()
                            val movieList = it.data?.items?.toList()
                            adapter.submitList(movieList)
                            controlVisibility(movieList.isNullOrEmpty())
                        }
                        is Resource.Error -> {
                            Toast.makeText(requireContext(), "영화 정보 가져오는데 실패했어요", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }

    }

    private val onClickMovie: (Int)->Unit = { pos: Int ->
        val movie = adapter.currentList[pos]
        lifecycleScope.launch {
            val review = withContext(Dispatchers.IO) {
                viewModel.getReview(movie.title ?: "")
            }
            showReviewDialog(movie, review) { reviewEntity ->  viewModel.insertReview(reviewEntity) }
        }
    }

    private val onClickDetail: Function1<String,Unit> = { link: String ->
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(link)))
    }

    private fun controlVisibility(isEmpty: Boolean) {
        if (!isEmpty) {
            dataBinding.rvSearch.visibility = View.VISIBLE
            dataBinding.tvEmpty.visibility = View.INVISIBLE
        } else {
            dataBinding.rvSearch.visibility = View.INVISIBLE
            dataBinding.tvEmpty.visibility = View.VISIBLE
        }
    }

    private fun showReviewDialog(movie: Movie, review: ReviewEntity?, insertReview: suspend (ReviewEntity)->Unit) {
        ReviewDialog.displayReviewDialog(parentFragmentManager, this@SearchFragment, movie.title ?: "Review", movie.image, review ) { reviewEntity: ReviewEntity ->
            lifecycleScope.launch(Dispatchers.IO) {
                insertReview.invoke(reviewEntity)
                withContext(Dispatchers.Main) {
                    Toast.makeText(requireContext(), "리뷰가 등록되었습니다.", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

}