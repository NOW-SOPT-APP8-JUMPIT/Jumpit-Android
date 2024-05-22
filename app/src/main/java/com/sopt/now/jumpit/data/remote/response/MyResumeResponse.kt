package com.sopt.now.jumpit.data.remote.response


import com.sopt.now.jumpit.ui.resume.Resume
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MyResumeResponse(
    @SerialName("status")
    val status: Int,
    @SerialName("message")
    val message: String,
    @SerialName("data")
    val data: Data
) {
    @Serializable
    data class Data(
        @SerialName("userId")
        val userId: Long,
        @SerialName("resumes")
        val resumes: List<Resumes>
    ) {
        @Serializable
        data class Resumes(
            @SerialName("id")
            val id: Long,
            @SerialName("title")
            val title: String,
            @SerialName("isPrivate")
            val isPrivate: Boolean,
        )

    }
}
