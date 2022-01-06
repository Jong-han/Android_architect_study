package com.jh.navermovie.ui.main.review

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jh.navermovie.R
import com.jh.navermovie.databinding.FragmentReviewItemBinding
import com.jh.navermovie.db.ReviewEntity

class ReviewAdapter: ListAdapter<ReviewEntity, ReviewAdapter.ReviewViewHolder>( ReviewDiffUtil() ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<FragmentReviewItemBinding>( inflater, R.layout.fragment_review_item, parent, false )
        return ReviewViewHolder( binding )
    }

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        holder.bind( currentList[position] )
    }

    inner class ReviewViewHolder( private val binding: FragmentReviewItemBinding): RecyclerView.ViewHolder( binding.root ) {
        fun bind( item: ReviewEntity) {
            binding.run {
                data = item
            }
        }
    }
    class ReviewDiffUtil: DiffUtil.ItemCallback<ReviewEntity>() {
        override fun areItemsTheSame(oldItem: ReviewEntity, newItem: ReviewEntity): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: ReviewEntity, newItem: ReviewEntity): Boolean {
            return oldItem == newItem
        }
    }

}