package com.sopt.now.jumpit.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.now.jumpit.data.model.SearchKeyword
import com.sopt.now.jumpit.data.repository.SearchKeywordRepository
import kotlinx.coroutines.launch

class SearchViewModel(private val searchKeywordRepository: SearchKeywordRepository) : ViewModel() {

    fun insertSearchKeyword(input: String) {
        viewModelScope.launch {
            searchKeywordRepository.insertSearchKeyword(SearchKeyword(keyword = input))
        }
    }
}