package com.sopt.now.jumpit.data.remote.service

import com.sopt.now.jumpit.data.remote.response.BaseResponse
import com.sopt.now.jumpit.data.remote.response.DetailResponse
import com.sopt.now.jumpit.data.remote.response.SearchResultsResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NoticeService {
    @GET("positions")
    suspend fun getPositions(
        @Query("keyword") keyword: String,
        @Query("categories") categories: List<Int>,
    ): BaseResponse<SearchResultsResponse>

    @GET("positions/{positionId}")
    suspend fun getPositionDetail(
        @Path("positionId") positionId: Long)
            : BaseResponse<DetailResponse>
}