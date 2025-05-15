package com.example.data.repository

import com.example.data.contract.LecturesDataSource
import com.example.domain.common.MyResult
import com.example.domain.contract.LecturesRepository
import com.example.domain.models.lectures.LecturesResponse
import javax.inject.Inject

class LecturesRepositoryImpl @Inject constructor(private val dataSource: LecturesDataSource):LecturesRepository {

    override suspend fun getAllLectures(token: String): MyResult<LecturesResponse> {
        return dataSource.getAllLectures(token)
    }

}