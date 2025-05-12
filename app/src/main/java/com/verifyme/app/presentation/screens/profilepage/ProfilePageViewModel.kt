package com.verifyme.app.presentation.screens.profilepage

import android.content.Context
import com.verifyme.app.domain.repository.AuthRepository
import com.verifyme.app.presentation.base.VerifyMeBaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject


@HiltViewModel
class ProfilePageViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val repository: AuthRepository,
)  : VerifyMeBaseViewModel() {

    /*private val _loginViewState = MutableStateFlow(LoginViewState())
    val loginViewState = _loginViewState.asStateFlow()

    private val _loginUiEvent = MutableSharedFlow<HomepageViewEvent>()
    val loginUiEvent = _loginUiEvent.asSharedFlow()
    */
}