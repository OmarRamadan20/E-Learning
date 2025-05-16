package com.example.domain.models.lectures.myLecture

import com.example.domain.models.lectures.LectureItem


data class MyLecturesResponse(

	val lectureCount: Int? = null,

	val lectures: List<LectureItem?>? = null
)