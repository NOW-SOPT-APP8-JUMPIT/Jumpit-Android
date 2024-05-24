package com.sopt.now.jumpit.data.remote.service

import MyResumeResponse
import com.sopt.now.jumpit.data.remote.request.ResumeEnrollRequest
import com.sopt.now.jumpit.data.remote.request.ResumePrivateRequest
import com.sopt.now.jumpit.data.remote.response.BaseResponse
import com.sopt.now.jumpit.data.remote.response.ResumeResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface ResumeService {
    @POST("resumes")
    suspend fun enrollResume(
        @Body request: ResumeEnrollRequest,
    ): BaseResponse<ResumeResponse>

    @GET("resumes/{userId}")
    suspend fun getMyResume(
        @Path("userId") userId: Long,
    ): BaseResponse<MyResumeResponse>

    @PATCH("resumes/{resumeId}")
    suspend fun privateResume(
        @Path("resumeId") resumeId: Long,
        @Body request: ResumePrivateRequest,
    ): BaseResponse<ResumeResponse>
}