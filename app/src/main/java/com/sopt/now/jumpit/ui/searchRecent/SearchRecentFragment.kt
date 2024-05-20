package com.sopt.now.jumpit.ui.searchRecent

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.sopt.now.jumpit.JumpitApp
import com.sopt.now.jumpit.R
import com.sopt.now.jumpit.data.model.SearchKeyword
import com.sopt.now.jumpit.databinding.FragemntSearchRecentBinding
import com.sopt.now.jumpit.ui.base.BaseFactory
import com.sopt.now.jumpit.ui.base.BindingFragment
import com.sopt.now.jumpit.ui.searchResult.SearchResultFragment

class SearchRecentFragment :
    BindingFragment<FragemntSearchRecentBinding>(R.layout.fragemnt_search_recent) {
    private lateinit var viewModel: SearchRecentViewModel
    private lateinit var searchRecentAdapter: SearchRecentAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViewModel()
        setupRecyclerView()
        setupDeleteAllListener()
        observeSearchKeywords()
    }

    private fun setupViewModel() {
        val factory = BaseFactory { SearchRecentViewModel(JumpitApp.getSearchKeywordRepository()) }
        viewModel = ViewModelProvider(this, factory)[SearchRecentViewModel::class.java]
    }

    private fun setupRecyclerView() {
        searchRecentAdapter = SearchRecentAdapter(
            onKeywordClick = { performSearch(it) },
            onDeleteClick = { viewModel.deleteSearchKeyword(it) }
        )
        binding.rvSearchRecent.adapter = searchRecentAdapter
    }

    private fun performSearch(searchKeyword: SearchKeyword) {
        val fragment = setupSearchResultFragment(searchKeyword)
        viewModel.updateCreatedTime(searchKeyword)
        replaceFragment(fragment)
    }

    private fun setupSearchResultFragment(searchKeyword: SearchKeyword): SearchResultFragment {
        val bundle = Bundle()
        bundle.putString("keyword", searchKeyword.keyword)
        SearchResultFragment().arguments = bundle
        return SearchResultFragment()
    }

    private fun replaceFragment(fragment: Fragment) {
        if (fragment.isAdded) return
        parentFragmentManager.beginTransaction()
            .replace(R.id.fcvSearch, fragment)
            .commit()
    }

    private fun setupDeleteAllListener() {
        binding.tvSearchRecentDeleteAll.setOnClickListener {
            viewModel.deleteAllSearchKeywords()
        }
    }

    private fun observeSearchKeywords() {
        viewModel.searchKeywords.observe(viewLifecycleOwner) { keywords ->
            searchRecentAdapter.submitList(keywords)
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getSearchKeywords()
    }
}