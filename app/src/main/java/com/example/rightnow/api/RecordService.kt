package com.example.rightnow.api

import com.example.rightnow.model.*
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface RecordService {
    @GET("/posts/1")
    fun getTest(): Call<TestGetModel>

    @POST("/process_audio/")
    fun postTest(@Body postData: RecordModel): Call<PostTestModel>

    @POST("/posts")
    fun postRecord(@Body recordData: RecordModel): Call<ResultModel>
}