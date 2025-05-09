package com.example.domain.models

data class LoginResponse(

	val user: User? = null,

	val status: String? = null,

	val token: String,

	)