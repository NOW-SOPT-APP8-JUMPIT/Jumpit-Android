package com.sopt.now.jumpit.ui.search

import android.os.Bundle
import android.view.View
import androidx.activity.addCallback
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import com.sopt.now.jumpit.JumpitApp
import com.sopt.now.jumpit.R
import com.sopt.now.jumpit.databinding.FragmentSearchBinding
import com.sopt.now.jumpit.ui.common.base.BaseFactory
import com.sopt.now.jumpit.ui.common.base.BindingFragment
import com.sopt.now.jumpit.ui.searchResult.SearchResultFragment
import com.sopt.now.jumpit.ui.searchResult.SearchResultViewModel

class SearchFragment : BindingFragment<FragmentSearchBinding>(R.layout.fragment_search) {

    private lateinit var searchViewModel: SearchViewModel
    private val searchResultViewModel: SearchResultViewModel by activityViewModels<SearchResultViewModel>()
    private lateinit var searchAdapter: SearchAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupDefaultFragment()
        setupSearchViewModel()
        setupRecentKeywords()
        setupListeners()
        setupSearchRecentRecyclerView()
        setupOnBackPressedDispatcher()
        observeRecentKeywords()
        observeSelectedCategories()
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
                performSearch(query.orEmpty())
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }

    private fun performSearch(keyword: String) {
        searchViewModel.insertSearchKeyword(keyword)
        searchResultViewModel.getSearchResults(keyword)
        binding.fcvSearch.visibility = View.VISIBLE
        binding.svSearch.clearFocus()
    }

    private fun setupNavigateToBackClickListener() {
        binding.ivSearchNavigateBack.setOnClickListener {
            handleBackPress()
        }
    }

    private fun handleBackPress() {
        with(binding) {
            when (fcvSearch.visibility) {
                View.GONE -> {
                    if (checkIsInitialState()) {
                        parentFragmentManager.popBackStack()
                        return
                    }
                    fcvSearch.visibility = View.VISIBLE
                    svSearch.setQuery(searchResultViewModel.pastSearchKeyword.value, false)
                    svSearch.clearFocus()
                }

                View.VISIBLE -> parentFragmentManager.popBackStack()
                else -> {}
            }
        }
    }

    private fun checkIsInitialState(): Boolean {
        return searchResultViewModel.pastSearchKeyword.value.isNullOrEmpty()
    }

    private fun setupOnBackPressedDispatcher() {
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            handleBackPress()
        }
    }

    private fun setupSearchRecentRecyclerView() {
        searchAdapter = SearchAdapter(
            onKeywordClick = {
                binding.svSearch.setQuery(it.keyword, false)
                performSearch(it.keyword)
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

    private fun observeSelectedCategories() {
        searchResultViewModel.checkedCategories.observe(viewLifecycleOwner) {
            performSearch(binding.svSearch.query.toString())
        }
    }
}