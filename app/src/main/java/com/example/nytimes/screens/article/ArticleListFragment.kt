package com.example.nytimes.screens.article

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nytimes.MainActivity
import com.example.nytimes.R
import com.example.nytimes.databinding.FragmentArticleListBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ArticleListFragment : Fragment() {

    private val adapter = ArticleAdapter()

    private lateinit var binding: FragmentArticleListBinding
    private lateinit var viewModel: ArticleViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        (activity as MainActivity).supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(false)
            setHomeButtonEnabled(false)
        }
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_article_list, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ArticleViewModel::class.java)
        binding.RecyclerViewArticleList.adapter = adapter
        binding.RecyclerViewArticleList.layoutManager = LinearLayoutManager(activity)
        viewModel.fetchArticle()
        viewModel.articles.observe(viewLifecycleOwner) { articles ->
            adapter.submitList(articles)
        }

        viewModel.showProgressBar.observe(viewLifecycleOwner) { status ->
            binding.ProgressBar.visibility = if (status) View.VISIBLE else View.GONE
        }

        viewModel.showSnackBarMessage.observe(viewLifecycleOwner) { message ->
            Snackbar.make(binding.root, message, Snackbar.LENGTH_SHORT).show()
        }
    }
}