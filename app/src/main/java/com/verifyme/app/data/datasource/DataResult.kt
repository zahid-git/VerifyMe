package com.verifyme.app.data.datasource

import android.os.Message
import com.verifyme.app.data.model.response.BaseResponseModel

sealed class DataResult<T> (val response: T? = null, val code: Int? = -1) {
    class onSuccess<T>(response: T?, code: Int) : DataResult<T>(response = response, code)
    class onError<T>(response: T?, code: Int) : DataResult<T>(response = response, code)
}