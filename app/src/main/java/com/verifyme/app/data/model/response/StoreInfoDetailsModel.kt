package com.verifyme.app.data.model.response

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class StoreInfoDetailsModel(
    @SerializedName("storeDetails") var storeDetails: StoreInfoDetailsDataModel
) : Serializable

data class StoreInfoDetailsDataModel(
    @SerializedName("id") var id: String? = null,
    @SerializedName("storeId") var storeId: String? = null,
    @SerializedName("storePassword") var storePassword: String? = null,
    @SerializedName("storeName") var storeName: String? = null,
    @SerializedName("storeDesc") var storeDesc: String? = null,
    @SerializedName("storeImage") var storeImage: String? = null,
    @SerializedName("callbackUrl") var callbackUrl: String? = null,
    @SerializedName("totalUsed") var totalUsed: Int? = null,
    @SerializedName("totalVerified") var totalVerified: Int? = null,
    @SerializedName("totalPaid") var totalPaid: Double? = null,
    @SerializedName("totalPending") var totalPending: Double? = null,
    @SerializedName("storeCreated") var storeCreated: String? = null,
    @SerializedName("lastUpdate") var lastUpdate: String? = null
) : Serializable
