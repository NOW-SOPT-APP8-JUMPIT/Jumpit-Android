package com.sopt.now.jumpit.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.sopt.now.jumpit.data.model.SearchKeyword

@Dao
interface SearchKeywordDao {

    @Insert
    suspend fun insertSearchKeyword(searchKeyword: SearchKeyword)

    @Query("SELECT * FROM local_search_keyword ORDER BY createdTime DESC LIMIT 5")
    suspend fun getSearchKeywords(): List<SearchKeyword>

    @Delete
    suspend fun deleteSearchKeyword(searchKeyword: SearchKeyword)

    @Query("DELETE FROM local_search_keyword")
    suspend fun deleteAllSearchKeywords()

    @Query("SELECT COUNT(id) FROM local_search_keyword")
    suspend fun countSearchKeywords(): Int

    @Query("DELETE FROM local_search_keyword WHERE id IN (SELECT id FROM local_search_keyword ORDER BY createdTime ASC LIMIT 1)")
    suspend fun deleteOldestSearchKeyword()

    @Query("SELECT * FROM local_search_keyword")
    suspend fun updateCreatedTime(searchKeyword: SearchKeyword) {
        deleteSearchKeyword(searchKeyword)
        insertSearchKeyword(searchKeyword)
    }
}
