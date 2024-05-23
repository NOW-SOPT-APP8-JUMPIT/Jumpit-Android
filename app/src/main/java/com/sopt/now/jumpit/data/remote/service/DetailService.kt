package com.sopt.now.jumpit.data.remote.service

import com.sopt.now.jumpit.data.remote.response.BaseResponse
import com.sopt.now.jumpit.data.remote.response.DetailResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface DetailService {

    @GET("positions/{positionId}")
    suspend fun getPositionDetail(
        @Path("positionId") positionId: Long)
    : BaseResponse<DetailResponse>
}