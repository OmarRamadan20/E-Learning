package com.example.domain.models

import okhttp3.MultipartBody

data class RegisterRequest(
    val firstName:String,
    val lastName:String,
    val password:String,
    val email:String,
    val mobileNumber: String,
    val city:String,
    val gov:String,
    val dob:String,
    val sex:String,
    val picUrl: MultipartBody.Part?
)

