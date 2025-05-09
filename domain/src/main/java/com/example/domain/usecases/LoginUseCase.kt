package com.example.domain.usecases


import com.example.domain.contract.AuthRepository
import com.example.domain.models.LoginRequest
import com.example.domain.models.LoginResponse
import com.example.domain.common.MyResult
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val authRepository: AuthRepository) {
    suspend fun invoke (request: LoginRequest): MyResult<LoginResponse> {
        return authRepository.signIn(request)
    }
}