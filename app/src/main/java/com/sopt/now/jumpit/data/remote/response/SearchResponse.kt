package com.sopt.now.jumpit.data.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchResponse(
    @SerialName("position")
    val positions: List<Position>
) {
    @Serializable
    data class Position(
        @SerialName("id")
        val id: Long,
        @SerialName("title")
        val title: String,
        @SerialName("skills")
        val skills: List<String>,
        @SerialName("company")
        val company: Company
    ) {
        @Serializable
        data class Company(
            @SerialName("id")
            val id: Long,
            @SerialName("name")
            val name: String,
            @SerialName("image")
            val image: String
        )
    }
}
