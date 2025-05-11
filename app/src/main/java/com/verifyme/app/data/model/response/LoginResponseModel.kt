package com.verifyme.app.data.model.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class LoginResponseModel(
    @SerializedName("token") @Expose var token : String,
    @SerializedName("name") @Expose var name : String,
    @SerializedName("email") @Expose var email : String
) : Serializable
