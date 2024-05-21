package com.sopt.now.jumpit.data.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResumeResponse(
    @SerialName("status")
    val status: Int,
    @SerialName("message")
    val message: String,
)
