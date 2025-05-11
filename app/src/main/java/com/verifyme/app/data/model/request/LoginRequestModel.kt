package com.verifyme.app.data.model.request

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class LoginRequestModel(
    @SerializedName("email") @Expose var emailAddress: String,
    @SerializedName("password") @Expose var password: String
) : Serializable
