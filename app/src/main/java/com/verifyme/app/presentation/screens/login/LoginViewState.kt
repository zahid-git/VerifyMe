package com.verifyme.app.presentation.screens.login

data class LoginViewState(
    var isLoginApiLoading: Boolean = false,
    var dialogData: DialogData = DialogData(),
    var emailAddress: String = "",
    var password: String = "",
)


// Dialog dialog
data class DialogData(
    var title: String = "",
    var message: String = "",
    var isSuccess: Boolean = false,
    var showDialog: Boolean = false,
    var topIcon: Int? = null,
    var successBtnName: String = "Okay",
    var cancelBtnName: String = "Cancel",
)
