package com.sopt.now.jumpit.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.sopt.now.jumpit.data.model.RecentKeyword

@Dao
interface RecentKeywordDao {

    @Insert
    suspend fun insertSearchKeyword(recentKeyword: RecentKeyword)

    @Query("SELECT * FROM local_search_keyword ORDER BY createdTime DESC LIMIT 5")
    suspend fun getSearchKeywords(): List<RecentKeyword>

    @Delete
    suspend fun deleteSearchKeyword(recentKeyword: RecentKeyword)

    @Query("DELETE FROM local_search_keyword")
    suspend fun deleteAllSearchKeywords()

    @Query("SELECT COUNT(id) FROM local_search_keyword")
    suspend fun countSearchKeywords(): Int

    @Query("DELETE FROM local_search_keyword WHERE id IN (SELECT id FROM local_search_keyword ORDER BY createdTime ASC LIMIT 1)")
    suspend fun deleteOldestSearchKeyword()

    @Query("UPDATE local_search_keyword SET createdTime = :newCreatedTime WHERE id = :keywordId")
    suspend fun updateCreatedTime(keywordId: Int, newCreatedTime: String)
}
