package com.verifyme.app.presentation.screens.login

sealed class LoginUIEvent{
    object NavigationToHomePage : LoginUIEvent()
    data class ShowSuccessMessage(val message: String) : LoginUIEvent()
    data class ShowErrorMessage(val errorMessage: String) : LoginUIEvent()
}
