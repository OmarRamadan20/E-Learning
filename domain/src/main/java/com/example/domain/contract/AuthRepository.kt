package com.example.domain.contract

import com.example.domain.models.LoginRequest
import com.example.domain.models.LoginResponse
import com.example.domain.models.RegisterRequest
import com.example.domain.models.RegisterResponse
import com.example.domain.common.MyResult


interface AuthRepository {
    suspend fun signUp(request: RegisterRequest): MyResult<RegisterResponse>

    suspend fun signIn(loginRequest: LoginRequest): MyResult<LoginResponse>

}