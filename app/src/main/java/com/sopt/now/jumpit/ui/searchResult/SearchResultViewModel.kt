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
        Log.e("SearchResultViewModelTest1", "getSearchResults: $searchKeyword")
        viewModelScope.launch {
            runCatching {
                Log.d("SearchResultViewModelTest2", "getSearchResults: $searchKeyword")
                ServicePool.noticeService.getPositions(searchKeyword)
            }.onSuccess {
                _searchResults.value = it.data?.positions ?: emptyList()
                Log.e("SearchResultViewModelTest3", "getSearchResults: $it")
            }.onFailure {
                Log.e("SearchResultViewModelTest4", "getSearchResults: $it")
            }
        }
    }
}