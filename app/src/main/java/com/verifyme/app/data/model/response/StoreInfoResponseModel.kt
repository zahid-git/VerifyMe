package com.verifyme.app.data.model.response

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class StoreInfoResponseModel(
    var storeInfo : List<StoreInfoDataModel> = listOf()
) : Serializable

data class StoreInfoDataModel(
    @SerializedName("id") var id : String,
    @SerializedName("appStoreId") var appStoreId : String,
    @SerializedName("appStorePassword") var appStorePassword : String,
    @SerializedName("storeName") var storeName : String,
    @SerializedName("storeDesc") var storeDesc : String,
    @SerializedName("storeImage") var storeImage : String,
    @SerializedName("storeCreated") var storeCreated : String,
    @SerializedName("lastUpdate") var lastUpdate : String
) : Serializable
