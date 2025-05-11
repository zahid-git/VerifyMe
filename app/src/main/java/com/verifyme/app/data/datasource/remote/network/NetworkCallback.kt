package com.verifyme.app.data.datasource.remote.network

import com.google.gson.Gson
import com.verifyme.app.data.datasource.DataResult
import com.verifyme.app.data.model.response.BaseResponseModel
import retrofit2.Response
import java.net.HttpURLConnection

abstract class NetworkCallback() {
    fun <T> success (response: T?, httpCode: Int) : DataResult<T> = DataResult.onSuccess(response =response, code = httpCode)
    fun <T> fail(response: T? = null, httpCode: Int) : DataResult<T> = DataResult.onError(response =response, code = httpCode)

    inline fun <reified T: Any> safeAPICall(apiCall: () -> Response<BaseResponseModel<T>>): DataResult<BaseResponseModel<T>> {
        return try {
            val apiResponse = apiCall()
            if(apiResponse.isSuccessful) {
                val gson = Gson()
                apiResponse.body()?.let {response ->
                    val responseSuccessMainData = gson.toJson(apiResponse.body())
                    val successResponse = gson.fromJson(responseSuccessMainData, BaseResponseModel::class.java)
                    val successData = gson.fromJson(gson.toJson(successResponse.data), T::class.java)
                    success(BaseResponseModel(code = successResponse.code, message = successResponse.message, data = successData), httpCode = apiResponse.code())
                } ?: run {
                    val errorResponse = gson.fromJson(gson.toJson(apiResponse.errorBody()), BaseResponseModel::class.java)
                    val errorData = gson.fromJson(gson.toJson(errorResponse.data), T::class.java)
                    fail(BaseResponseModel(code = errorResponse.code, message = errorResponse.message, data = errorData), httpCode = apiResponse.code())
                }
            }
            else if(apiResponse.code() == HttpURLConnection.HTTP_BAD_REQUEST){
                fail(BaseResponseModel(code = apiResponse.code(), message = "Bad Request", data = null), httpCode = apiResponse.code())
            }else if(apiResponse.code() == HttpURLConnection.HTTP_UNAUTHORIZED){
                fail(BaseResponseModel(code = apiResponse.code(), message = "Unauthorized", data = null), httpCode = apiResponse.code())
            }else if (apiResponse.code() == HttpURLConnection.HTTP_INTERNAL_ERROR){
                fail(BaseResponseModel(code = apiResponse.code(), message = "Internal Error", data = null), httpCode = apiResponse.code())
            } else {
                fail(BaseResponseModel(code = apiResponse.code(), message = "Error Occurred", data = null), httpCode = apiResponse.code())
            }
        } catch (e: Exception) {
            fail(BaseResponseModel(code = 400, message = e.message!!.toString(), data = null), httpCode = 400)
        }
    }








}