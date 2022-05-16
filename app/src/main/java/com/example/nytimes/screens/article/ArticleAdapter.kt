package com.example.nytimes.screens.article

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.nytimes.ArticleDetailFragment
import com.example.nytimes.R
import com.example.nytimes.databinding.ListItemArticleBinding
import com.example.nytimes.room.Article

class ArticleAdapter : ListAdapter<Article, RecyclerView.ViewHolder>(ArticleDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ArticleViewHolder(
            ListItemArticleBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ArticleViewHolder).bind(getItem(position))
    }

    class ArticleViewHolder(private val binding: ListItemArticleBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Article) {
            binding.apply {
                article = item
            }
            binding.setClickListener { view ->
                val args = Bundle().apply { putString(ArticleDetailFragment.ARTICLE_URL, item.url) }
                view.findNavController().navigate(R.id.action_articleListFragment_to_articleDetailFragment, args)
            }
        }
    }
}

private class ArticleDiffCallback : DiffUtil.ItemCallback<Article>() {

    override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
        return oldItem == newItem
    }
}
