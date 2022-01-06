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
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.jh.navermovie.R
import com.jh.navermovie.api.MovieDataSource
import com.jh.navermovie.api.MovieResult
import com.jh.navermovie.databinding.FragmentSearchBinding
import com.jh.navermovie.db.MovieDB
import com.jh.navermovie.db.ReviewEntity
import com.jh.navermovie.ui.dialog.ReviewDialog
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SearchFragment : Fragment() {

    private lateinit var dataBinding: FragmentSearchBinding
    private val movieDataSource by lazy { MovieDataSource() }
    private val adapter by lazy { SearchAdapter(onClickMovie, onClickDetail) }
    private val db by lazy { Room.databaseBuilder(requireContext(), MovieDB::class.java, "MovieDB.db").build() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dataBinding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_search, container, false)
        dataBinding.lifecycleOwner = this
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dataBinding.rvSearch.adapter = this.adapter

        dataBinding.btnSearch.setOnClickListener {
            lifecycleScope.launch {
                val result = searchMovie(dataBinding.etSearch.text.toString())
                adapter.submitList( result?.items?.filter { (!it.image.isNullOrEmpty() && it.userRating != "0.00") } )
                if (adapter.currentList.isNotEmpty()) {
                    dataBinding.rvSearch.visibility = View.VISIBLE
                    dataBinding.tvEmpty.visibility = View.INVISIBLE
                } else {
                    dataBinding.rvSearch.visibility = View.INVISIBLE
                    dataBinding.tvEmpty.visibility = View.VISIBLE
                }
            }
        }

    }

    private suspend fun searchMovie(searchString: String ): MovieResult? {
        return withContext(Dispatchers.IO) {
            movieDataSource.getMovies(searchString)
        }
    }

    private val onClickMovie: Function1<Int,Unit> = { pos: Int ->
        val movie = adapter.currentList[pos]
        lifecycleScope.launch {
            val review = withContext(Dispatchers.IO) {
                movie.title?.let { db.reviewDAO().getRate(it) }
            }
            ReviewDialog.displayReviewDialog(parentFragmentManager, this@SearchFragment, movie.title ?: "Review", movie.image, review ) { reviewEntity: ReviewEntity ->
                lifecycleScope.launch(Dispatchers.IO) {
                    db.reviewDAO().insert(reviewEntity)
                    withContext(Dispatchers.Main) {
                        Toast.makeText(requireContext(), "리뷰가 등록되었습니다.", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private val onClickDetail: Function1<String,Unit> = { link: String ->
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(link)))
    }

}