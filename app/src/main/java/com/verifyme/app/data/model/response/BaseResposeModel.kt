package com.verifyme.app.data.model.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.descriptors.StructureKind
import java.io.Serializable

data class BaseResponseModel<T>(
    @SerializedName("code") var code : Int = -1,
    @SerializedName("status") var status : Boolean = false,
    @SerializedName("message") var message : String = "",
    @SerializedName("data") var data : T? = null
) : Serializable
