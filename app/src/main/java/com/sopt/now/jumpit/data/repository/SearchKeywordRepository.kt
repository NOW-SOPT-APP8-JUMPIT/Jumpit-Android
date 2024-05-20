package com.sopt.now.jumpit.data.repository

import com.sopt.now.jumpit.data.local.dao.SearchKeywordDao
import com.sopt.now.jumpit.data.model.SearchKeyword

class SearchKeywordRepository(private val searchKeywordDao: SearchKeywordDao) {
    suspend fun getSearchKeywords() = searchKeywordDao.getSearchKeywords()

    suspend fun deleteSearchKeyword(searchKeyword: SearchKeyword) =
        searchKeywordDao.deleteSearchKeyword(searchKeyword)

    suspend fun insertSearchKeyword(searchKeyword: SearchKeyword) {
        searchKeywordDao.insertSearchKeyword(searchKeyword)
        if (searchKeywordDao.countSearchKeywords() > 5) searchKeywordDao.deleteOldestSearchKeyword()
    }

    suspend fun deleteAllSearchKeywords() = searchKeywordDao.deleteAllSearchKeywords()

    suspend fun countSearchKeywords() = searchKeywordDao.countSearchKeywords()

    suspend fun deleteOldestSearchKeyword() = searchKeywordDao.deleteOldestSearchKeyword()

    suspend fun updateCreatedTime(searchKeyword: SearchKeyword) =
        searchKeywordDao.updateCreatedTime(searchKeyword)
}