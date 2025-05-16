package com.example.domain.usecases

import com.example.domain.common.MyResult
import com.example.domain.contract.LecturesRepository
import com.example.domain.models.lectures.LectureAccessRequest
import com.example.domain.models.lectures.LectureAccessResponse
import javax.inject.Inject

class RequestLectureAccessUseCase @Inject constructor(
    private val repository: LecturesRepository
) {
    suspend operator fun invoke(
        token: String,
        request: LectureAccessRequest
    ): MyResult<LectureAccessResponse> {
        return repository.requestLectureAccess(request)
    }
}
