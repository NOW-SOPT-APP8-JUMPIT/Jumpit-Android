package com.sopt.now.jumpit.ui.searchResult

import android.os.Bundle
import android.view.View
import com.sopt.now.jumpit.R
import com.sopt.now.jumpit.databinding.FragmentSearchResultBinding
import com.sopt.now.jumpit.ui.base.BindingFragment

class SearchResultFragment :
    BindingFragment<FragmentSearchResultBinding>(R.layout.fragment_search_result) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        performSearch()
    }

    private fun performSearch() {
        var searchKeyword = arguments?.getString("keyword").orEmpty()
        // Perform search
    }
}