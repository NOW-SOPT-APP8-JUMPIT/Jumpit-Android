package com.sopt.now.jumpit.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.sopt.now.jumpit.data.local.dao.SearchKeywordDao
import com.sopt.now.jumpit.data.model.SearchKeyword

@Database(entities = [SearchKeyword::class], version = 1, exportSchema = false)
abstract class LocalDatabase : RoomDatabase() {
    abstract fun searchKeywordDao(): SearchKeywordDao

    companion object {
        private const val DATABASE_NAME = "localDatabase"
        @Volatile
        private var instance: LocalDatabase? = null

        fun getInstance(context: Context): LocalDatabase {
            if (instance == null) {
                synchronized(LocalDatabase::class) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        LocalDatabase::class.java,
                        DATABASE_NAME
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return instance!!
        }
    }
}