package com.sopt.now.jumpit.data.remote.response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MyResumeResponse(
    @SerialName("userId")
    val userId: Long,
    @SerialName("resume")
    val resume: List<Resume>
) {
    @Serializable
    data class Resume(
        @SerialName("id")
        val id: Long,
        @SerialName("title")
        val title: String,
        @SerialName("isPrivate")
        val isPrivate: Boolean,
        @SerialName("createdAt")
        val createdAt: String,
        @SerialName("modifiedAt")
        val modifiedAt: String
    )
}