package com.jh.navermovie.ui.main.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jh.navermovie.R
import com.jh.navermovie.data.remote.response.Movie
import com.jh.navermovie.databinding.FragmentSearchItemBinding

class SearchAdapter(private val onClickMovie: (Int)->Unit, private val onClickDetail: (String)->Unit): ListAdapter<Movie, SearchAdapter.SearchViewHolder>( SearchDiffUtil() ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<FragmentSearchItemBinding>( inflater, R.layout.fragment_search_item, parent, false )
        return SearchViewHolder( binding )
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bind( currentList[position] )
    }

    inner class SearchViewHolder( private val binding: FragmentSearchItemBinding ): RecyclerView.ViewHolder( binding.root ) {
        fun bind( item: Movie) {
            binding.run {
                data = item.also {
                    it.title = it.title?.replace("<b>","")
                    it.title = it.title?.replace("</b>","")
                }
                parent.setOnClickListener {
                    onClickMovie.invoke(adapterPosition)
                }
                tvLink.setOnClickListener {
                    item.link?.let { onClickDetail.invoke(it) }
                }
            }
        }
    }
    class SearchDiffUtil: DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }
    }

}