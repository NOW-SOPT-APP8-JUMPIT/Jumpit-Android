package com.sopt.now.jumpit.ui.search

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.now.jumpit.data.model.RecentKeyword
import com.sopt.now.jumpit.data.repository.SearchKeywordRepository
import kotlinx.coroutines.launch

class SearchViewModel(private val searchKeywordRepository: SearchKeywordRepository) : ViewModel() {
    private var _recentKeywords = MutableLiveData<List<RecentKeyword>>()
    val recentKeywords: LiveData<List<RecentKeyword>> get() = _recentKeywords

    fun insertSearchKeyword(input: String) {
        viewModelScope.launch {
            searchKeywordRepository.insertRecentKeyword(RecentKeyword(keyword = input))
        }
    }

    fun getSearchKeywords() {
        viewModelScope.launch {
            _recentKeywords.value = searchKeywordRepository.getRecentKeywords()
            Log.e("SearchViewModelTest", "getSearchKeywords: ${_recentKeywords.value}")
        }
    }

    fun deleteSearchKeyword(recentKeyword: RecentKeyword) {
        viewModelScope.launch {
            searchKeywordRepository.deleteRecentKeyword(recentKeyword)
            getSearchKeywords()
        }
    }

    fun updateCreatedTime(recentKeyword: RecentKeyword) {
        viewModelScope.launch {
            searchKeywordRepository.updateCreatedTimeOfRecentKeyword(recentKeyword)
            getSearchKeywords()
        }
    }

    fun deleteAllSearchKeywords() {
        viewModelScope.launch {
            searchKeywordRepository.deleteAllRecentKeywords()
            getSearchKeywords()
        }
    }

}