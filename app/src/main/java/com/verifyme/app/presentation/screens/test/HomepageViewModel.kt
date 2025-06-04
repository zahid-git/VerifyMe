package com.verifyme.app.presentation.screens.test

import android.content.Context
import androidx.lifecycle.viewModelScope
import com.verifyme.app.data.datasource.DataResult
import com.verifyme.app.domain.repository.AuthRepository
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
    private val repository: AuthRepository,
)  : VerifyMeBaseViewModel() {

    /*private val _loginViewState = MutableStateFlow(LoginViewState())
    val loginViewState = _loginViewState.asStateFlow()

    private val _loginUiEvent = MutableSharedFlow<HomepageViewEvent>()
    val loginUiEvent = _loginUiEvent.asSharedFlow()
    */
}