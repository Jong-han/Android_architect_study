package com.jh.navermovie.ui.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.LifecycleOwner
import com.jh.navermovie.R
import com.jh.navermovie.databinding.DialogReviewBinding
import com.jh.navermovie.db.ReviewEntity

class ReviewDialog: DialogFragment() {

    companion object {

        private const val TAG = "SHOW_REVIEW_DIALOG"
        private const val MOVIE_TITLE = "MOVIE_TITLE"
        private const val MOVIE_RATING = "MOVIE_RATING"
        private const val MOVIE_REVIEW = "MOVIE_REVIEW"
        private const val REVIEW_RATING = "REVIEW_RATING"
        private const val REVIEW_STRING = "REVIEW_STRING"
        private const val REVIEW_REQUEST_KEY = "REVIEW_REQUEST_KEY"

        fun displayReviewDialog( fragmentManager: FragmentManager, lifecycleOwner: LifecycleOwner, title: String, url: String?, review: ReviewEntity?, saveReview: (ReviewEntity)->Unit) {

            ReviewDialog().run {
                fragmentManager.setFragmentResultListener(REVIEW_REQUEST_KEY, lifecycleOwner) { requestKey, result ->
                    if ( requestKey == REVIEW_REQUEST_KEY ) {
                        val reviewEntity = ReviewEntity(title, result.getFloat(REVIEW_RATING).toString(), result.getString(REVIEW_STRING, ""), url ?: "")
                        saveReview.invoke(reviewEntity)
                    }
                }
                val bundle = Bundle()
                bundle.putString(MOVIE_TITLE, title)
                bundle.putString(MOVIE_RATING, review?.rate)
                bundle.putString(MOVIE_REVIEW, review?.review)
                arguments = bundle
                show(fragmentManager, TAG)
            }

        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isCancelable = true
    }
    private lateinit var dataBinding: DialogReviewBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dataBinding = DataBindingUtil.inflate(layoutInflater, R.layout.dialog_review, container, false)
        dataBinding.lifecycleOwner = this
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val title = arguments?.getString(MOVIE_TITLE)
        val rating = arguments?.getString(MOVIE_RATING)
        val review = arguments?.getString(MOVIE_REVIEW)

        dataBinding.tvTitle.text = title ?: "영화제목안넘어옴"
        dataBinding.rb.rating = rating?.toFloat() ?: 0f
        dataBinding.etReview.setText(review ?: "")

        dataBinding.btnConfirm.setOnClickListener {
            if (dataBinding.rb.rating != 0f && !dataBinding.etReview.text.isNullOrEmpty()) {
                val reviewBundle = Bundle().apply {
                    putFloat(REVIEW_RATING, dataBinding.rb.rating)
                    putString(REVIEW_STRING, dataBinding.etReview.text.toString())
                }
                parentFragmentManager.setFragmentResult(REVIEW_REQUEST_KEY, reviewBundle)
            }
            else
                Toast.makeText(requireContext(), "별점/리뷰를 입력해주세요.", Toast.LENGTH_SHORT).show()
            dismiss()
        }
        dataBinding.btnCancel.setOnClickListener {
            dismiss()
        }

    }
}