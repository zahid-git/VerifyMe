package com.verifyme.app.presentation.screens.profilepage

import android.content.Context
import android.graphics.pdf.models.ListItem
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.verifyme.app.data.datasource.DataResult
import com.verifyme.app.data.model.response.BaseResponseModel
import com.verifyme.app.data.model.response.LoginResponseModel
import com.verifyme.app.data.model.response.UsersListDataModel
import com.verifyme.app.domain.repository.AuthRepository
import com.verifyme.app.domain.repository.StoreRepository
import com.verifyme.app.presentation.base.VerifyMeBaseViewModel
import com.verifyme.app.utils.DefaultPaginator
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ProfilePageViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val repository: StoreRepository,
) : VerifyMeBaseViewModel() {

    private val _profileViewState = MutableStateFlow(ProfilePageViewState())
    val profileViewState = _profileViewState.asStateFlow()

    private val _profileUiEvent = MutableSharedFlow<ProfilePageViewEvent>()
    val profileUiEvent = _profileUiEvent.asSharedFlow()

    fun updateStoreId(storeId: String) {
        if (profileViewState.value.storeId.isNullOrBlank())
            _profileViewState.value = _profileViewState.value.copy(storeId)
    }

    private val paginator = DefaultPaginator(
        initKey = profileViewState.value.page,
        onLoadUpdate = {
            _profileViewState.value = _profileViewState.value.copy(isLoading = true)
        },
        onRequest = { nextPage ->
            getItems()
        },
        getNextKey = {
            profileViewState.value.page + 1
        },
        onError = {
            _profileViewState.value = _profileViewState.value.copy(onError = it?.localizedMessage)
        },
        onSuccess = { items, newKey ->
            _profileViewState.value = _profileViewState.value.copy(
                profileList = profileViewState.value.profileList + items,
                page = newKey,
                endReached = items.isEmpty()
            )
        }
    )

    fun getItems(): Result<List<UsersListDataModel>> {
        return Result.success((1..100).map {
            UsersListDataModel(
                it.toString()
            )
        })
    }

    fun getProfileData() {
        _profileViewState.value = _profileViewState.value.copy(isLoading = true)
        val page: Int = profileViewState.value.page + 1
        viewModelScope.launch {
            repository.getUserList(profileViewState.value.storeId.toString(), page)
                .collect { result ->
                    when (result) {
                        is DataResult.onError -> {
                            _profileViewState.value = _profileViewState.value.copy(
                                onError = result.response?.message,
                                isLoading = false
                            )
                        }

                        is DataResult.onSuccess -> {
                            result.response.let { response ->
                                if (response?.code == 200) {
                                    if (response.data?.userList?.size != 0) {
                                        _profileViewState.value = _profileViewState.value.copy(
                                            profileList = profileViewState.value.profileList + response.data!!.userList,
                                            page = profileViewState.value.page + 1,
                                            isLoading = false
                                        )
                                    } else {
                                        _profileViewState.value = _profileViewState.value.copy(
                                            endReached = true,
                                            isLoading = false
                                        )
                                    }
                                } else {
                                    _profileViewState.value = _profileViewState.value.copy(
                                        onError = response?.message,
                                        isLoading = false
                                    )
                                }
                            }
                        }
                    }
                }
        }
    }

}