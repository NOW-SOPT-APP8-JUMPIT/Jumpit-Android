package com.sopt.now.jumpit.ui.search

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import com.sopt.now.jumpit.JumpitApp
import com.sopt.now.jumpit.R
import com.sopt.now.jumpit.data.model.RecentKeyword
import com.sopt.now.jumpit.databinding.FragmentSearchBinding
import com.sopt.now.jumpit.ui.base.BaseFactory
import com.sopt.now.jumpit.ui.base.BindingFragment
import com.sopt.now.jumpit.ui.searchResult.SearchResultFragment
import com.sopt.now.jumpit.ui.searchResult.SearchResultViewModel

class SearchFragment : BindingFragment<FragmentSearchBinding>(R.layout.fragment_search) {

    private lateinit var searchViewModel: SearchViewModel
    private val searchResultViewModel: SearchResultViewModel by activityViewModels()
    private lateinit var searchAdapter: SearchAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupDefaultFragment()
        setupSearchViewModel()
        setupRecentKeywords()
        setupListeners()
        setupSearchRecentRecyclerView()
        observeRecentKeywords()
    }

    private fun setupDefaultFragment() {
        parentFragmentManager.beginTransaction()
            .replace(R.id.fcvSearch, SearchResultFragment())
            .commit()
    }

    private fun setupSearchViewModel() {
        val factory = BaseFactory { SearchViewModel(JumpitApp.getSearchKeywordRepository()) }
        searchViewModel = ViewModelProvider(this, factory)[SearchViewModel::class.java]
    }

    private fun setupRecentKeywords() {
        searchViewModel.getSearchKeywords()
    }

    private fun setupListeners() {
        setupSearchViewFocusChangeListener()
        setupSearchViewQueryTextListener()
        setupNavigateToBackClickListener()
        setupDeleteAllClickListener()
    }

    private fun setupSearchViewFocusChangeListener() {
        binding.svSearch.setOnQueryTextFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                binding.fcvSearch.visibility = View.GONE
                searchViewModel.getSearchKeywords()
            }
        }
    }

    private fun setupSearchViewQueryTextListener() {
        binding.svSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchViewModel.insertSearchKeyword(query.orEmpty())
                performSearch(RecentKeyword(keyword = query.orEmpty()))
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }

    private fun performSearch(recentKeyword: RecentKeyword) {
        searchResultViewModel.getSearchResults(recentKeyword.keyword)
        binding.fcvSearch.visibility = View.VISIBLE
        binding.svSearch.clearFocus()
    }

    private fun setupNavigateToBackClickListener() {
        binding.ivSearchNavigateBack.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }

    private fun setupSearchRecentRecyclerView() {
        searchAdapter = SearchAdapter(
            onKeywordClick = {
                searchViewModel.updateCreatedTime(it)
                binding.svSearch.setQuery(it.keyword, false)
                performSearch(it)
            },
            onDeleteClick = { searchViewModel.deleteSearchKeyword(it) }
        )
        binding.rvSearchRecent.adapter = searchAdapter
    }

    private fun setupDeleteAllClickListener() {
        binding.tvSearchRecentDeleteAll.setOnClickListener {
            searchViewModel.deleteAllSearchKeywords()
        }
    }

    private fun observeRecentKeywords() {
        searchViewModel.recentKeywords.observe(viewLifecycleOwner) { keywords ->
            searchAdapter.submitList(keywords)
        }
    }
}