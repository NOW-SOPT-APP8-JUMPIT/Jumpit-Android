package com.sopt.now.jumpit.ui.searchResult

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.now.jumpit.data.ServicePool
import com.sopt.now.jumpit.data.remote.response.SearchResultsResponse
import kotlinx.coroutines.launch

class SearchResultViewModel() : ViewModel() {
    private var _searchResults = MutableLiveData<List<SearchResultsResponse.Position>>()
    val searchResults: LiveData<List<SearchResultsResponse.Position>> get() = _searchResults

    private var _pastSearchKeyword = MutableLiveData<String>()
    val pastSearchKeyword: LiveData<String> get() = _pastSearchKeyword

    private var _checkedCategories = MutableLiveData<List<Int>>()
    val checkedCategories: LiveData<List<Int>> get() = _checkedCategories

    fun getSearchResults(searchKeyword: String) {
        viewModelScope.launch {
            runCatching {
                ServicePool.noticeService.getPositions(
                    searchKeyword,
                    _checkedCategories.value.orEmpty()
                )
            }.onSuccess {
                _searchResults.value = it.data?.positions ?: emptyList()
                _pastSearchKeyword.value = searchKeyword
            }.onFailure {
                Log.e("SearchResultViewModel", "getSearchResults: $it")
            }
        }
    }

    fun setCheckedCategories(categories: List<Int>) {
        _checkedCategories.value = categories
    }
}