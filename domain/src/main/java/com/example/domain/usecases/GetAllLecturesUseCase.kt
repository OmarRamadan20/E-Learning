package com.example.domain.usecases

import com.example.domain.common.MyResult
import com.example.domain.contract.LecturesRepository
import com.example.domain.models.lectures.LecturesResponse
import javax.inject.Inject

class GetAllLecturesUseCase @Inject constructor(private val repository: LecturesRepository) {

    suspend fun getAllLectures(token:String): MyResult<LecturesResponse> {
        return repository.getAllLectures(token)
    }
}