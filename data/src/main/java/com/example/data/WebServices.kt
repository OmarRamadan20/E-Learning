package com.example.data.api

import com.example.domain.models.LoginRequest
import com.example.domain.models.LoginResponse
import com.example.domain.models.RegisterResponse
import com.example.domain.models.lectures.LecturesResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part


interface WebServices {



    @Multipart
    @POST("auth/sign_up")
    suspend fun signUp(
        @Part("email") email: RequestBody,
        @Part("password") password: RequestBody,
        @Part("firstName") firstName: RequestBody,
        @Part("lastName") lastName: RequestBody,
        @Part("DOB") dob: RequestBody,
        @Part("sex") sex: RequestBody,
        @Part("mobileNumber") mobileNumber: RequestBody,
        @Part("city") city: RequestBody,
        @Part("GOV") gov: RequestBody,
        @Part profilePicture: MultipartBody.Part?
    ): Response<RegisterResponse>


    @POST("auth/login")
    suspend fun signIn(@Body request: LoginRequest): Response<LoginResponse>


    @GET("lectures")
    suspend fun getAllLectures(@Header("token") token: String): Response<LecturesResponse>
}