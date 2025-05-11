package com.verifyme.app.presentation.screens.login

import android.content.Context
import android.util.Log
import androidx.lifecycle.viewModelScope
import com.verifyme.app.domain.repository.AuthRepository
import com.verifyme.app.presentation.base.VerifyMeBaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val repository: AuthRepository,
)  : VerifyMeBaseViewModel() {

    private val _loginViewState = MutableStateFlow(LoginViewState())
    val loginViewState = _loginViewState.asStateFlow()

    fun onEmailAddressChange(email: String){
        _loginViewState.value = _loginViewState.value.copy(emailAddress = email)
    }

    fun onPasswordChange(password: String){
        _loginViewState.value = _loginViewState.value.copy(password = password)
    }

    fun doLogin(){
        viewModelScope.launch {
            var data = repository.checkLogin(loginViewState.value.emailAddress,loginViewState.value.password)
            data.collect {
                //Log.e("TAG", "doLogin: "+it.code())
            }
        }
    }







}