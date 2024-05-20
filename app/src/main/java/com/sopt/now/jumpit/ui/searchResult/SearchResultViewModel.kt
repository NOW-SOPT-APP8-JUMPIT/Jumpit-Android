package com.sopt.now.jumpit.ui.searchResult

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sopt.now.jumpit.data.remote.response.SearchResult

class SearchResultViewModel() : ViewModel() {
    private var _searchResults = MutableLiveData<List<SearchResult>>()
    val searchResults: LiveData<List<SearchResult>> get() = _searchResults

    fun getSearchResults(searchKeyword: String) {
        _searchResults.value = SearchResult.dummyResults
    }
}