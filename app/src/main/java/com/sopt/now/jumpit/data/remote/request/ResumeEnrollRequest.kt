package com.sopt.now.jumpit.data.remote.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResumeEnrollRequest(
    @SerialName("title")
    val title: String,
    @SerialName("userId")
    val userId: Int,
)
