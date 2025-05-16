package com.example.domain.models.lectures


data class LecturesResponse(

    val data: List<LectureItem?>? = null,

    val message: String? = null,

    val status:String?=null
)