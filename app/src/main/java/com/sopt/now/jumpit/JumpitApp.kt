package com.sopt.now.jumpit

import android.app.Application
import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import com.sopt.now.jumpit.data.local.dao.SearchKeywordDao
import com.sopt.now.jumpit.data.local.database.LocalDatabase
import com.sopt.now.jumpit.data.repository.SearchKeywordRepository

class JumpitApp : Application() {
    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }

    companion object {
        lateinit var appContext: Context
            private set

        fun getSearchKeywordRepository(): SearchKeywordRepository {
            val searchKeywordDao: SearchKeywordDao =
                LocalDatabase.getInstance(appContext).searchKeywordDao()

            return SearchKeywordRepository(searchKeywordDao)
        }
    }
}