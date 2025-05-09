package com.example.data.api.datasource

import com.example.data.api.WebServices
import com.example.data.api.contract.AuthDataSource
import com.example.domain.models.LoginRequest
import com.example.domain.models.LoginResponse
import com.example.domain.models.RegisterRequest
import com.example.domain.models.RegisterResponse
import com.example.domain.common.MyResult
import com.example.domain.common.ServerError
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import javax.inject.Inject

class AuthDataSourceImpl @Inject constructor(private val webServices: WebServices) : AuthDataSource {

    override suspend fun signUp(request: RegisterRequest): MyResult<RegisterResponse> {
        return try {
            val response = webServices.signUp(
                email = request.email.toRequestBody(),
                password = request.password.toRequestBody(),
                firstName = request.firstName.toRequestBody(),
                lastName = request.lastName.toRequestBody(),
                dob = request.dob.toRequestBody(),
                sex = request.sex.toRequestBody(),
                mobileNumber = request.mobileNumber.toRequestBody(),
                city = request.city.toRequestBody(),
                gov = request.gov.toRequestBody(),
                profilePicture = request.picUrl
            )
            if (response.isSuccessful) {
                MyResult.Success(response.body()!!)
            } else {
                val errorMessage = try {
                    val errorJson = JSONObject(response.errorBody()?.string() ?: "")
                    errorJson.getString("message")
                } catch (e: Exception) {
                    response.message() ?: "Unknown error"
                }
                MyResult.ServerFail(ServerError(message = errorMessage))
            }
        } catch (e: Exception) {
            MyResult.Failure(e)
        }
    }

    override suspend fun signIn(request: LoginRequest): MyResult<LoginResponse> {
        return try {
            val response = webServices.signIn(request)
            if (response.isSuccessful) {
                MyResult.Success(response.body()!!)
            } else {
                val errorMessage = try {
                    val errorJson = JSONObject(response.errorBody()?.string() ?: "")
                    errorJson.getString("message")
                } catch (e: Exception) {
                    response.message() ?: "Unknown error"
                }
                MyResult.ServerFail(ServerError(message = errorMessage))
            }
        } catch (e: Exception) {
            MyResult.Failure(e)
        }
    }
}
