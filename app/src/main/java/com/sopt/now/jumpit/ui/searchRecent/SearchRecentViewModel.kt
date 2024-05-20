package com.sopt.now.jumpit.ui.searchRecent

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.now.jumpit.data.model.SearchKeyword
import com.sopt.now.jumpit.data.repository.SearchKeywordRepository
import kotlinx.coroutines.launch

class SearchRecentViewModel(private val searchKeywordRepository: SearchKeywordRepository) :
    ViewModel() {

    private var _searchKeywords = MutableLiveData<List<SearchKeyword>>()
    val searchKeywords: LiveData<List<SearchKeyword>> get() = _searchKeywords

    fun getSearchKeywords() {
        viewModelScope.launch {
            _searchKeywords.value = searchKeywordRepository.getSearchKeywords()
        }
    }

    fun deleteSearchKeyword(searchKeyword: SearchKeyword) {
        viewModelScope.launch {
            searchKeywordRepository.deleteSearchKeyword(searchKeyword)
            getSearchKeywords()
        }
    }

    fun updateCreatedTime(searchKeyword: SearchKeyword) {
        viewModelScope.launch {
            searchKeywordRepository.updateCreatedTime(searchKeyword)
            getSearchKeywords()
        }
    }

    fun deleteAllSearchKeywords() {
        viewModelScope.launch {
            searchKeywordRepository.deleteAllSearchKeywords()
            getSearchKeywords()
        }
    }
}