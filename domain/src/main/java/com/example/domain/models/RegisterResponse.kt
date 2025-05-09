package com.example.domain.models


data class RegisterResponse(

	val status: String,
	val message: String,
	val data: UserData?
)

data class UserData(
	val id: String,
	val email: String,
	val firstName: String,
	val lastName: String,
	val profilePhotoUrl: String
)
