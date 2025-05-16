package com.example.domain.models.lectures

data class LectureAccessRequest(
    val lectureId: String,
    val code: String
)
data class LectureAccessResponse(
    val message: String,
    val success: Boolean
    )
