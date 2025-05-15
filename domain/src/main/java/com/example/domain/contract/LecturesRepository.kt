package com.example.domain.contract

import com.example.domain.common.MyResult
import com.example.domain.models.lectures.LecturesResponse

interface LecturesRepository {

    suspend fun getAllLectures(token:String): MyResult<LecturesResponse>
}