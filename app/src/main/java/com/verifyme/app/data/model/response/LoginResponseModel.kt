package com.verifyme.app.data.model.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class LoginResponseModel(
    @SerializedName("code") @Expose var code : String,
    @SerializedName("status") @Expose var status : Boolean,
    @SerializedName("message") @Expose var message : String,
    @SerializedName("data") @Expose var data : LoginResponseDataModel
) : Serializable

data class LoginResponseDataModel(
    @SerializedName("token") @Expose var token : String,
    @SerializedName("name") @Expose var name : String,
    @SerializedName("email") @Expose var email : String
)
