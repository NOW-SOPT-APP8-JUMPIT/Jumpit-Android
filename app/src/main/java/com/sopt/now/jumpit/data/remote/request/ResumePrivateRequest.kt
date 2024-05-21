package com.sopt.now.jumpit.data.remote.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResumePrivateRequest(
    @SerialName("isPrivate")
    val isPrivate: Boolean
)
