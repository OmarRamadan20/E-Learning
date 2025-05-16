package com.example.data.repository

import com.example.data.contract.LecturesDataSource
import com.example.domain.common.MyResult
import com.example.domain.contract.LecturesRepository
import com.example.domain.models.lectures.LectureAccessRequest
import com.example.domain.models.lectures.LectureAccessResponse
import com.example.domain.models.lectures.LecturesResponse
import com.example.domain.models.lectures.myLecture.MyLecturesResponse
import javax.inject.Inject

class LecturesRepositoryImpl @Inject constructor(private val dataSource: LecturesDataSource):LecturesRepository {

    override suspend fun getAllLectures(token: String): MyResult<LecturesResponse> {
        return dataSource.getAllLectures(token)
    }

    override suspend fun getMyLectures(token: String): MyResult<MyLecturesResponse> {
        return dataSource.getMyLectures(token)
    }


    override suspend fun requestLectureAccess(
        body: LectureAccessRequest
    ): MyResult<LectureAccessResponse> {
        return dataSource.requestLectureAccess(body)
    }

}