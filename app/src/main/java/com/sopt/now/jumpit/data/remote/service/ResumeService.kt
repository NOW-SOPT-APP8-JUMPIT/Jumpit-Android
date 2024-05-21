package com.sopt.now.jumpit.data.remote.service

import com.sopt.now.jumpit.data.remote.response.BaseResponse
import com.sopt.now.jumpit.data.remote.response.MyResumeResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ResumeService {

    @GET("resume/{userId}")
    suspend fun getMyResume(
        @Path("userId") userId: Long,
    ): BaseResponse<MyResumeResponse>
}