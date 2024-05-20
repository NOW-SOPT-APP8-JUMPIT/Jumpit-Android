package com.sopt.now.jumpit.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(tableName = "local_search_keyword")
data class RecentKeyword(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    var keyword: String,
    var createdTime: String = LocalDateTime.now().toString(),
)