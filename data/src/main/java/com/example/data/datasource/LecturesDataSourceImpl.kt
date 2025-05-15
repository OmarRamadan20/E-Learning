package com.example.data.datasource

import com.example.data.api.WebServices
import com.example.data.contract.LecturesDataSource
import com.example.domain.common.MyResult
import com.example.domain.common.ServerError
import com.example.domain.models.lectures.LecturesResponse
import org.json.JSONObject
import javax.inject.Inject

class LecturesDataSourceImpl @Inject constructor(val webServices: WebServices):LecturesDataSource {
    override suspend fun getAllLectures(token: String): MyResult<LecturesResponse> {
        return try {
            val response = webServices.getAllLectures(token)
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