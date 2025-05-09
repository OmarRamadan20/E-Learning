package com.example.data.api.model

import com.google.gson.annotations.SerializedName

data class RegisterResponseDto(
    @field:SerializedName("status")
    val status: String,

    @field:SerializedName("message")
    val message: String,

    val data: User?
)
