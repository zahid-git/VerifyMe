package com.verifyme.app.presentation.screens.login

sealed class LoginViewEvent{
    object NavigationToHomePage : LoginViewEvent()
    data class ShowSuccessMessage(val message: String) : LoginViewEvent()
    data class ShowErrorMessage(val errorMessage: String) : LoginViewEvent()
}
