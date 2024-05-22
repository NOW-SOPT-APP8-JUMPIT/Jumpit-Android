package com.sopt.now.jumpit.ui.searchResult

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.now.jumpit.data.ServicePool
import com.sopt.now.jumpit.data.remote.response.SearchResponse
import kotlinx.coroutines.launch

class SearchResultViewModel() : ViewModel() {
    private var _searchResults = MutableLiveData<List<SearchResponse.Position>>()
    val searchResults: LiveData<List<SearchResponse.Position>> get() = _searchResults

    fun getSearchResults(searchKeyword: String) {
        viewModelScope.launch {
            runCatching {
                ServicePool.noticeService.getPositions(searchKeyword)
            }.onSuccess {
                _searchResults.value = it.data?.positions ?: emptyList()
            }.onFailure {
                Log.e("SearchResultViewModel", "getSearchResults: $it")
            }
        }
    }
}