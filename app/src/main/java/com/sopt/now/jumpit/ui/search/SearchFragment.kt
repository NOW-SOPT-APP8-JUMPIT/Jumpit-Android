package com.sopt.now.jumpit.ui.search

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.sopt.now.jumpit.JumpitApp
import com.sopt.now.jumpit.R
import com.sopt.now.jumpit.databinding.FragmentSearchBinding
import com.sopt.now.jumpit.ui.base.BaseFactory
import com.sopt.now.jumpit.ui.base.BindingFragment
import com.sopt.now.jumpit.ui.searchRecent.SearchRecentFragment
import com.sopt.now.jumpit.ui.searchResult.SearchResultFragment

class SearchFragment : BindingFragment<FragmentSearchBinding>(R.layout.fragment_search) {

    private lateinit var viewModel: SearchViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        changeFragment(SearchRecentFragment())
        setupViewModel()
        setupSearchViewFocusChangeListener()
        setupSearchViewQueryTextListener()
        setupNavigateToBackClickListener()
    }

    private fun setupViewModel() {
        val factory = BaseFactory { SearchViewModel(JumpitApp.getSearchKeywordRepository()) }
        viewModel = ViewModelProvider(this, factory)[SearchViewModel::class.java]
    }

    private fun setupSearchViewFocusChangeListener() {
        binding.svSearch.setOnQueryTextFocusChangeListener { _, hasFocus ->
            if (hasFocus) changeFragment(SearchRecentFragment())
        }
    }

    private fun setupSearchViewQueryTextListener() {
        binding.svSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                changeFragment(SearchResultFragment())
                binding.svSearch.clearFocus()
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }

    private fun setupNavigateToBackClickListener() {
        binding.ivSearchNavigateBack.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }

    private fun changeFragment(fragment: Fragment) {
        if (fragment.isAdded) return
        if (fragment is SearchResultFragment) handleInputKeyword(fragment)
        parentFragmentManager.beginTransaction()
            .replace(R.id.fcvSearch, fragment)
            .commit()
    }

    private fun handleInputKeyword(fragment: SearchResultFragment) {
        val bundle = Bundle()
        bundle.putString("keyword", binding.svSearch.query.toString())
        fragment.arguments = bundle
        viewModel.insertSearchKeyword(binding.svSearch.query.toString())
    }
}