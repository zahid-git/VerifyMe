package com.verifyme.app.data.model.response

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class UsersListResponseModel(
    @SerializedName("totalDocument") var totalDocument: Int? = null,
    @SerializedName("userList") var userList: ArrayList<UsersListDataModel> = arrayListOf()
)

@Serializable
data class UsersListDataModel(
    @SerializedName("_id") var _id: String? = null,
    @SerializedName("country") var country: String? = null,
    @SerializedName("countryName") var countryName: String? = null,
    @SerializedName("idType") var idType: String? = null,
    @SerializedName("created_at") var createdAt: String? = null,
    @SerializedName("updated_at") var updatedAt: String? = null,
    @SerializedName("id") var id: String? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("user_image") var userImage: UserImageDataModel? = UserImageDataModel(),
    @SerializedName("selfie_image") var selfieImage: SelfieImageDataModel? = SelfieImageDataModel()
)

@Serializable
data class SelfieImageDataModel(
    @SerializedName("selfie_image_link") var selfieImageLink: String? = null
)

@Serializable
data class UserImageDataModel(
    @SerializedName("user_image_link") var userImageLink: String? = null
)

