package com.verifyme.app.presentation.screens.login

import android.content.Context
import androidx.lifecycle.viewModelScope
import com.verifyme.app.data.datasource.DataResult
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

    // Types
    fun onEmailAddressChange(email: String){
        _loginViewState.value = _loginViewState.value.copy(emailAddress = email)
    }

    fun onPasswordChange(password: String){
        _loginViewState.value = _loginViewState.value.copy(password = password)
    }

    fun updateDialogData(title: String = "", message: String = "", isSuccess: Boolean = false, showDialog: Boolean) {
        _loginViewState.value = _loginViewState.value.copy(dialogData = DialogData(title=title,message=message,isSuccess=isSuccess, showDialog=showDialog))
    }

    fun doLogin(){
        _loginViewState.value = _loginViewState.value.copy(isLoginApiLoading = true)
        viewModelScope.launch {
            repository.checkLogin(loginViewState.value.emailAddress, loginViewState.value.password).collect { result ->

                when(result) {
                    is DataResult.onSuccess -> {
                        // Success
                        if(result.response?.code == 200) {
                            updateDialogData(title = "Login Successful", message = "Welcome back! You've successfully logged in.", isSuccess = true, showDialog = true)
                        } else {
                            updateDialogData(title = "Login Failed", message = "Your login attempt was unsuccessful. Please check your username and password, then try again.", isSuccess = false, showDialog = true)
                        }
                        _loginViewState.value = _loginViewState.value.copy(isLoginApiLoading = false)
                    }
                    is DataResult.onError -> {
                        // Errors
                        _loginViewState.value = _loginViewState.value.copy(isLoginApiLoading = false)
                    }
                }
            }
        }
    }



}