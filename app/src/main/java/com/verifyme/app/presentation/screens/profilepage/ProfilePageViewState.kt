package com.verifyme.app.presentation.screens.profilepage

import com.verifyme.app.data.model.response.UsersListDataModel

data class ProfilePageViewState(
    var storeId: String? = null,
    var isLoading: Boolean = false,
    var profileList: List<UsersListDataModel> = arrayListOf(),
    var onError: String? = null,
    var endReached: Boolean = false,
    var page: Int = 0,
)