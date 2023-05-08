package com.example.rightnow.repository

import com.example.rightnow.api.RecordService
import com.example.rightnow.model.RecordModel
import com.example.rightnow.model.ResultModel
import retrofit2.Call

class RecordRepository(private val recordService: RecordService) {
    fun getData(d: RecordModel): Call<ResultModel> = recordService.postRecord(d)
}