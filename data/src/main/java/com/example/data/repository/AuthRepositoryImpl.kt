package com.example.data.api.repository

import com.example.domain.contract.AuthRepository
import com.example.domain.models.RegisterRequest
import com.example.data.api.contract.AuthDataSource
import com.example.domain.models.LoginRequest
import com.example.domain.models.LoginResponse
import com.example.domain.models.RegisterResponse
import com.example.domain.common.MyResult
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(private val dataSource: AuthDataSource) :
    AuthRepository {

    override
    suspend fun signUp(request: RegisterRequest): MyResult<RegisterResponse> {
        return dataSource.signUp(request)
    }

    override suspend fun signIn(loginRequest:LoginRequest): MyResult<LoginResponse> {
        return dataSource.signIn(loginRequest)
    }
}

