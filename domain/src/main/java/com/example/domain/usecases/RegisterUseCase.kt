package com.example.domain.usecases

import android.util.Log
import com.example.domain.contract.AuthRepository
import com.example.domain.models.RegisterRequest
import com.example.domain.models.RegisterResponse
import com.example.domain.common.MyResult

import javax.inject.Inject

class RegisterUseCase @Inject constructor(private val authRepository: AuthRepository) {
    suspend fun invoke (request: RegisterRequest): MyResult<RegisterResponse> {
        Log.e("RegisterUseCase","invoke")
        return authRepository.signUp(request)
    }
}