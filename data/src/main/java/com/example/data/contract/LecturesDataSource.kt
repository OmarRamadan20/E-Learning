package com.example.data.contract

import com.example.domain.common.MyResult
import com.example.domain.models.lectures.LectureAccessRequest
import com.example.domain.models.lectures.LectureAccessResponse
import com.example.domain.models.lectures.LecturesResponse
import com.example.domain.models.lectures.myLecture.MyLecturesResponse

interface LecturesDataSource {

    suspend fun getAllLectures(token:String): MyResult<LecturesResponse>

    suspend fun getMyLectures(token:String): MyResult<MyLecturesResponse>


    suspend fun requestLectureAccess(body:LectureAccessRequest): MyResult<LectureAccessResponse>

}