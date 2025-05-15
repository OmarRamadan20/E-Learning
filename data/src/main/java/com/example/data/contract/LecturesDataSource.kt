package com.example.data.contract

import com.example.domain.common.MyResult
import com.example.domain.models.lectures.LecturesResponse

interface LecturesDataSource {

    suspend fun getAllLectures(token:String): MyResult<LecturesResponse>
}