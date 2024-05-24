package com.sopt.now.jumpit.data.repository

import com.sopt.now.jumpit.data.local.dao.RecentKeywordDao
import com.sopt.now.jumpit.data.model.RecentKeyword
import java.time.LocalDateTime

class SearchKeywordRepository(private val recentKeywordDao: RecentKeywordDao) {
    suspend fun getRecentKeywords() = recentKeywordDao.getSearchKeywords()

    suspend fun deleteRecentKeyword(recentKeyword: RecentKeyword) =
        recentKeywordDao.deleteSearchKeyword(recentKeyword)

    suspend fun insertRecentKeyword(recentKeyword: RecentKeyword) {
        if (recentKeywordDao.isKeywordExists(recentKeyword.keyword)) {
            recentKeywordDao.updateKeywordCreatedTime(
                recentKeyword.keyword,
                LocalDateTime.now().toString()
            )
            return
        }
        recentKeywordDao.insertSearchKeyword(recentKeyword)
        if (recentKeywordDao.countSearchKeywords() > MAX_SEARCH_KEYWORDS) recentKeywordDao.deleteOldestSearchKeyword()
    }

    suspend fun deleteAllRecentKeywords() = recentKeywordDao.deleteAllSearchKeywords()

    suspend fun countRecentKeywords() = recentKeywordDao.countSearchKeywords()

    suspend fun deleteOldestRecentKeyword() = recentKeywordDao.deleteOldestSearchKeyword()

    companion object {
        private const val MAX_SEARCH_KEYWORDS = 5
    }
}