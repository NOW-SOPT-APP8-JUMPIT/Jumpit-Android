package com.sopt.now.jumpit.ui.search

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import com.sopt.now.jumpit.R
import com.sopt.now.jumpit.databinding.FragmentSearchBinding
import com.sopt.now.jumpit.ui.base.BindingFragment
import com.sopt.now.jumpit.ui.searchRecent.SearchRecentFragment
import com.sopt.now.jumpit.ui.searchResult.SearchResultFragment

class SearchFragment : BindingFragment<FragmentSearchBinding>(R.layout.fragment_search) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        changeFragment(SearchRecentFragment())
        setupSearchViewFocusChangeListener()
        setupSearchViewQueryTextListener()
        setupNavigateToBackClickListener()
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
        parentFragmentManager.beginTransaction()
            .replace(R.id.fcvSearch, fragment)
            .commit()
    }
}