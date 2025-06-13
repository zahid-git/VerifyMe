package com.verifyme.app.presentation.screens.homepage

import android.content.Context
import androidx.lifecycle.viewModelScope
import com.verifyme.app.data.datasource.DataResult
import com.verifyme.app.data.model.response.StoreInfoResponseModel
import com.verifyme.app.domain.repository.AuthRepository
import com.verifyme.app.domain.repository.StoreRepository
import com.verifyme.app.presentation.base.VerifyMeBaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomepageViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val repository: StoreRepository,
)  : VerifyMeBaseViewModel() {

    private val _homepageViewState = MutableStateFlow(LoginViewState())
    val homepageViewState = _homepageViewState.asStateFlow()

    private val _homepageUIEvent = MutableSharedFlow<HomepageViewEvent>()
    val homepageUIEvent = _homepageUIEvent.asSharedFlow()


    fun getStoreInformation(){
        if(homepageViewState.value.storeInformation == null) {
            viewModelScope.launch {
                repository.getStoreDetails().collect { result ->
                    when (result) {
                        is DataResult.onError -> {

                        }

                        is DataResult.onSuccess -> {
                            when (result.response!!.code) {
                                200 -> {
                                    result.response.data.let {finalResult->
                                        _homepageViewState.value = _homepageViewState.value.copy(storeInformation = StoreInformation(
                                            storeId = finalResult?.storeDetails?.id.toString(),
                                            totalAttempt = finalResult?.storeDetails?.totalUsed!! ,
                                            totalVerified = finalResult.storeDetails.totalVerified!! ,
                                            totalPaid = finalResult.storeDetails.totalPaid!! ,
                                            totalPending = finalResult.storeDetails.totalPending!! ,
                                        ))
                                    }
                                }
                                else -> {

                                }
                            }
                        }
                    }
                }
            }
        }
    }
}