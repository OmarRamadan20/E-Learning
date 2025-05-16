package com.example.domain.usecases

import com.example.domain.common.MyResult
import com.example.domain.contract.LecturesRepository
import com.example.domain.models.lectures.LecturesResponse
import com.example.domain.models.lectures.myLecture.MyLecturesResponse
import javax.inject.Inject


class GetMyLecturesUseCase @Inject constructor(
    private val repository: LecturesRepository
) {
    suspend  fun getMyLectures(token: String): MyResult<MyLecturesResponse> {
        return repository.getMyLectures(token)
    }
}
