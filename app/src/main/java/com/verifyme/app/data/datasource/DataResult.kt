package com.verifyme.app.data.datasource

import android.os.Message

sealed class DataResult<T> (response: T? = null, message: Message? = null, code: Int? = -1) {
    object onLoading : DataResult<Nothing>()
    class onSuccess<T>(response: T?, message: Message, httpCode: Int) : DataResult<T>(response, message, httpCode)
    class onError<T>(response: T?, message: Message, httpCode: Int) : DataResult<T>(response, message, httpCode)
}